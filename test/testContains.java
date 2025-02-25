import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testContains {

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
    void testContain() {
        assertTrue(range.contains(0));
        assertTrue(range.contains(-10));
        assertTrue(range.contains(10));
        assertFalse(range.contains(-11));
        assertFalse(range.contains(11));
    }

    @Test
    void testContainsNegativeToPositiveRange() {
        Range negativeToPositive = new Range(-50, 50);
        assertTrue(negativeToPositive.contains(0));
        assertTrue(negativeToPositive.contains(-25));
        assertTrue(negativeToPositive.contains(25));
    }
    
    @Test
    void testContainsFractionRange() {
        Range fractionalRange = new Range(1.5, 4.5);
        assertTrue(fractionalRange.contains(2.5));
        assertFalse(fractionalRange.contains(1.49));
        assertFalse(fractionalRange.contains(4.51));
    }
}
