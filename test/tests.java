import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangeTest {

    private Range range;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    	
    }
    
    @BeforeEach
    void setUp() {
        range = new Range(-10, 10);
    }
    
    @AfterEach
    void tearDown() throws Exception {
    	
    }
    
    //toString test
    @Test
    void testToString() {
        assertEquals("Range[-10.0,10.0]", range.toString());
    }
    
    
    //UpperBounds Test
    @Test
    void testGetUpperBound() {
        assertEquals(10.0, range.getUpperBound());
    }
    
    
    //LowerBounds Test
    @Test
    void testGetLowerBound() {
        assertEquals(-10.0, range.getLowerBound());
    }
    
    
    //getLength Tests
    @Test
    void testGetLength() {
        assertEquals(20.0, range.getLength());
    }
    
    @Test
    void testZeroLengthRange() {
        Range zeroRange = new Range(5, 5);
        assertEquals(5.0, zeroRange.getLowerBound());
        assertEquals(5.0, zeroRange.getUpperBound());
        assertEquals(0.0, zeroRange.getLength());
    }
    //end of length
    
    //getCentralValue test
    @Test
    void testGetCentralValue() {
        assertEquals(0, range.getCentralValue());
    }
    
    
    //Equals Tests
    @Test
    void testEquals() {
        Range range1 = new Range(-10, 10);
        Range range2 = new Range(-5, 5);
        assertNotEquals(range1, range2);
    }

    @Test
    void testEqualsDifferentUpper() {
        Range differentUpper = new Range(-10, 5);
        assertNotEquals(range, differentUpper);
    }
    //end of equals
    
    //Contains Test
    @Test
    void testContains() {
        assertTrue(range.contains(0));
        assertTrue(range.contains(-10));
        assertTrue(range.contains(10));
        assertFalse(range.contains(-11));
        assertFalse(range.contains(11));
    }

    
    //Constrain tests
    @Test
    void testConstrain() {
        assertEquals(5.0, range.constrain(5));
        assertEquals(-10, range.constrain(-15));
        assertEquals(10, range.constrain(15));
    }
    
    @Test
    void testConstrainDirectlyExecutesElseIfBranch() {
        double testValue = -11.0;
        assertFalse(range.contains(testValue));
        double constrainedValue = range.constrain(testValue);
        assertEquals(-10.0, constrainedValue);
    }

    @Test
    void testConstrainBelowLowerBound() {
        assertEquals(-10.0, range.constrain(-100.0));
    }
    //end of Constrain

    
    //Combine Tests
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
    void testCombineWithFirstNull() {
        Range range2 = new Range(5, 15);
        assertEquals(range2, Range.combine(null, range2));
    }    
    //end of combine
    
    
    //HashCode test for 100% coverage
    @Test
    void testHashCode() {
        Range range1 = new Range(-10, 10);
        Range range2 = new Range(-10, 10);
        assertEquals(range1.hashCode(), range2.hashCode());
    }
    
    //Constructor Test
    @Test
    void testConstructorThrowsExceptionForInvalidRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Range(10, -10); // Invalid: lower > upper
        });
        assertTrue(exception.getMessage().contains("require lower"));
    }
    
    //shift tests
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
    void testShiftWithNoZeroCrossingNegative() {
        Range negativeRange = new Range(-5, -1);
        Range shifted = Range.shift(negativeRange, -10, false);
        assertEquals(0, shifted.getLowerBound());
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
    //end of shift tests
}

