import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testEqual {

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
    
    @Test
    void testEqualsSameRange() {
        Range range1 = new Range(-10, 10);
        Range range2 = new Range(-10, 10);
        assertEquals(range1, range2);
    }
    
    @Test
    void testEqualsDifferentLowerBound() {
        Range range1 = new Range(-10, 10);
        Range range2 = new Range(-5, 10);
        assertNotEquals(range1, range2);
    }
    
    @Test
    void testEqualsDifferentRanges() {
        Range range1 = new Range(-10, 10);
        Range range2 = new Range(50, 100);
        assertNotEquals(range1, range2);
    }
    
    @Test
    void testEqualsWithNull() {
        Range range1 = new Range(-10, 10);
        assertNotEquals(range1, null);
    }
}
