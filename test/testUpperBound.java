import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testUpperBound {

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
    void testGetUpperBound() {
        assertEquals(10.0, range.getUpperBound());
    }
    
    @Test
    void testGetUpperBoundNegativeRange() {
        Range negativeRange = new Range(-50, -10);
        assertEquals(-10.0, negativeRange.getUpperBound());
    }
    
}
