import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testLowerBound {


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
    void testGetLowerBound() {
        assertEquals(-10.0, range.getLowerBound());
    }
    
    void testGetLowerBoundZeroLowerBound() {
        Range zeroLowerRange = new Range(0, 100);
        assertEquals(0.0, zeroLowerRange.getLowerBound());
    }
    
    @Test
    void testGetLowerBoundPositiveRange() {
        Range positiveRange = new Range(10, 50);
        assertEquals(10.0, positiveRange.getLowerBound());
    }
    
    @Test
    void testGetLowerBoundNegativeToZero() {
        Range negativeToZeroRange = new Range(-100, 0);
        assertEquals(-100.0, negativeToZeroRange.getLowerBound());
    }
    
}
