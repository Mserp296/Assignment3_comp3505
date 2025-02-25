import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testToString {

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
    void testToStringMethod() {
        assertEquals("Range[-10.0,10.0]", range.toString());
    }
    
    @Test
    void testToStringNegativeRange() {
        Range negativeRange = new Range(-50, -10);
        assertEquals("Range[-50.0,-10.0]", negativeRange.toString());
    }
    
    @Test
    void testToStringZeroRange() {
        Range zeroRange = new Range(0, 0);
        assertEquals("Range[0.0,0.0]", zeroRange.toString());
    }
}
