import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testCentralValue {

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
    void testGetCentralValue() {
        assertEquals(0, range.getCentralValue());
    }
    
    @Test
    void testGetCentralValueNegativeToPositiveRange() {
        Range negativeToPositiveRange = new Range(-50, 50);
        assertEquals(0.0, negativeToPositiveRange.getCentralValue());
    }

    @Test
    void testGetCentralValueZeroBoundRange() {
        Range zeroBoundRange = new Range(0, 20);
        assertEquals(10.0, zeroBoundRange.getCentralValue());
    }
}
