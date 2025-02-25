import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class shiftTest {

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
    
    
}
