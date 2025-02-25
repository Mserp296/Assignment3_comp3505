import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangeTest {

    private Range range;

    @BeforeEach
    void setUp() {
        range = new Range(-10, 10);
    }

    @Test
    void testToString() {
        assertEquals("Range[-10.0,10.0]", range.toString());
    }

    @Test
    void testIntersects() {
        assertTrue(range.intersects(-5, 5));
        assertTrue(range.intersects(10, 20));
        assertTrue(range.intersects(-20, -10));
        assertFalse(range.intersects(11, 20));
    }
    @Test
    void testIntersectsCoversUpperBound() {
        assertFalse(range.intersects(5, 11)); // upper == this.upper
        assertTrue(range.intersects(5, 9)); // upper < this.upper
    }

    @Test
    void testIntersectsCoversLowerBound() {
        assertFalse(range.intersects(-20, -10)); // upper == this.lower
        assertTrue(range.intersects(-20, -9)); // upper > this.lower
    }

    @Test
    void testIntersectsCoversUpperEdge() {
        assertFalse(range.intersects(5, 10));  // upper == this.upper (fails `upper < this.upper`)
        assertTrue(range.intersects(5, 9));    // upper < this.upper (should return true)
    }

    @Test
    void testIntersectsLowerEqualsUpper() {
        assertTrue(range.intersects(5, 5));  // upper == lower, both inside range
    }

    @Test
    void testIntersectsUpperExactlyAtUpperBound() {
        assertFalse(range.intersects(5, 10)); // upper == this.upper (this may be failing coverage)
    }

    @Test
    void testIntersectsUpperJustBelowUpperBound() {
        assertTrue(range.intersects(5, 9.9)); // upper < this.upper but still valid
    }

    @Test
    void testIntersectsLowerGreaterThanUpper() {
        assertFalse(range.intersects(8, 7)); // lower > upper (should return false)
    }
    @Test
    void testIntersectsExactBounds() {
        assertTrue(range.intersects(-10, -10)); // Lower bound match
        assertTrue(range.intersects(10, 10)); // Upper bound match
        assertFalse(range.intersects(11, 20)); // Outside upper bound
        assertFalse(range.intersects(-20, -11)); // Outside lower bound
    }

    @Test
    void testIntersectsEdgeCases() {
        assertFalse(range.intersects(-20, -11)); // Before lower bound
        assertTrue(range.intersects(-10, -10)); // Exactly lower bound
        assertFalse(range.intersects(11, 20)); // Outside upper bound
    }

    
    
    @Test
    void testGetUpperBound() {
        assertEquals(10.0, range.getUpperBound());
    }

    @Test
    void testGetLowerBound() {
        assertEquals(-10.0, range.getLowerBound());
    }

    @Test
    void testGetLength() {
        assertEquals(20.0, range.getLength());
    }

    @Test
    void testGetCentralValue() {
        assertEquals(0, range.getCentralValue());
    }

    @Test
    void testEquals() {
        Range range1 = new Range(-10, 10);
        Range range2 = new Range(-5, 5);
        assertNotEquals(range1, range2);
    }

    @Test
    void testContains() {
        assertTrue(range.contains(0));
        assertTrue(range.contains(-10));
        assertTrue(range.contains(10));
        assertFalse(range.contains(-11));
        assertFalse(range.contains(11));
    }

    @Test
    void testConstrain() {
        assertEquals(5.0, range.constrain(5));
        assertEquals(-10, range.constrain(-15));
        assertEquals(10, range.constrain(15));
    }
    
    @Test
    void testConstrainDirectlyExecutesElseIfBranch() {
        double testValue = -11.0; // Slightly outside the range
        assertFalse(range.contains(testValue)); // Ensure it's outside
        double constrainedValue = range.constrain(testValue);
        assertEquals(-10.0, constrainedValue); // Must hit else if (value < this.lower)
    }


    @Test
    void testShift() {
        Range shift1 = Range.shift(range, 2.0);
        Range shift2 = Range.shift(range, -2.0);
        assertEquals(-8.0, shift1.getLowerBound());
        assertEquals(12.0, shift1.getUpperBound());
        assertEquals(-12.0, shift2.getLowerBound());
        assertEquals(8.0, shift2.getUpperBound());
    }

    @Test
    void testShiftWithAllowZeroCrossing() {
        Range shifted = Range.shift(range, 5, true);
        assertEquals(-5, shifted.getLowerBound());
        assertEquals(15, shifted.getUpperBound());
    }

    @Test
    void testExpandToInclude() {
        Range expanded = Range.expandToInclude(range, 15.0);
        assertEquals(-10.0, expanded.getLowerBound());
        assertEquals(15.0, expanded.getUpperBound());
    }

    @Test
    void testExpandToIncludeNullRange() {
        Range newRange = Range.expandToInclude(null, 5);
        assertEquals(5, newRange.getLowerBound());
        assertEquals(5, newRange.getUpperBound());
    }
    
    @Test
    void testExpandToIncludeNewLower() {
        Range expanded = Range.expandToInclude(range, -15);
        assertEquals(-15, expanded.getLowerBound());
        assertEquals(10, expanded.getUpperBound());
    }

    
    @Test
    void testExpand() {
        Range expanded = Range.expand(range, 0.5, 0.5);
        assertEquals(-20.0, expanded.getLowerBound());
        assertEquals(20.0, expanded.getUpperBound());
    }
    
    @Test
    void testCombineWithFirstNull() {
        Range range2 = new Range(5, 15);
        assertEquals(range2, Range.combine(null, range2));
    }


    @Test
    void testCombine() {
        Range range2 = new Range(5, 15);
        Range combined = Range.combine(range, range2);
        assertNotNull(combined);
        assertEquals(-10.0, combined.getLowerBound());
        assertEquals(15.0, combined.getUpperBound());
    }

    @Test
    void testCombineWithNull() {
        assertEquals(range, Range.combine(range, null));
        assertEquals(range, Range.combine(null, range));
        assertNull(Range.combine(null, null));
    }

    @Test
    void testZeroLengthRange() {
        Range zeroRange = new Range(5, 5);
        assertEquals(5.0, zeroRange.getLowerBound());
        assertEquals(5.0, zeroRange.getUpperBound());
        assertEquals(0.0, zeroRange.getLength());
    }

    @Test
    void testExpandNullRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            Range.expand(null, 0.5, 0.5);
        });
    }

    @Test
    void testIntersectsUpperBoundary() {
        assertFalse(range.intersects(10, 10));
    }

    @Test
    void testConstrainBelowLowerBound() {
        assertEquals(-10.0, range.constrain(-100.0));
    }

    @Test
    void testExpandToIncludeValueWithinRange() {
        Range sameRange = Range.expandToInclude(range, 0);
        assertEquals(range, sameRange);
    }

    @Test
    void testShiftWithNoZeroCrossingNegative() {
        Range negativeRange = new Range(-5, -1);
        Range shifted = Range.shift(negativeRange, -10, false);
        assertEquals(0, shifted.getLowerBound());
    }
    

    @Test
    void testEqualsDifferentUpper() {
        Range differentUpper = new Range(-10, 5);
        assertNotEquals(range, differentUpper);
    }

    @Test
    void testExpandExtremeCases() {
        Range expanded = Range.expand(range, 1.0, 1.0);
        assertEquals(-30, expanded.getLowerBound());
        assertEquals(30, expanded.getUpperBound());
    }

    @Test
    void testHashCode() {
        Range range1 = new Range(-10, 10);
        Range range2 = new Range(-10, 10);
        assertEquals(range1.hashCode(), range2.hashCode());
    }
    
    @Test
    void testConstructorThrowsExceptionForInvalidRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Range(10, -10); // Invalid: lower > upper
        });
        assertTrue(exception.getMessage().contains("require lower"));
    }
    
    @Test
    void testShiftWithNoZeroCrossingAllowsPositiveShift() {
        Range shifted = Range.shift(range, 5, false);
        assertEquals(-5, shifted.getLowerBound());
        assertEquals(15, shifted.getUpperBound());
    }
    
    @Test
    void testShiftWithNoZeroCrossingZeroValue() {
        Range zeroRange = new Range(0.0, 0.0);
        Range shifted = Range.shift(zeroRange, 5.0, false);

        assertEquals(5.0, shifted.getLowerBound());  
        assertEquals(5.0, shifted.getUpperBound());
    }


    @Test
    void testShiftWithNoZeroCrossingStopsAtZero() {
        Range negativeRange = new Range(-5, -1);
        Range shifted = Range.shift(negativeRange, 5, false);

        assertEquals(0.0, shifted.getLowerBound());  // Ensures shift stops at zero
        assertEquals(0.0, shifted.getUpperBound());
    }


}

