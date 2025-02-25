import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testLength {

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
    
    @Test
    void testGetLengthNegativeRange() {
        Range negativeRange = new Range(-50, -10);
        assertEquals(40.0, negativeRange.getLength());
    }
    
    @Test
    void testGetLengthZeroBoundedRange() {
        Range zeroBoundedRange = new Range(0, 25);
        assertEquals(25.0, zeroBoundedRange.getLength());
    }
    
    @Test
    void testGetLengthFractionRange() {
        Range fractionalRange = new Range(2.5, 5.5);
        assertEquals(3.0, fractionalRange.getLength());
    }
}
