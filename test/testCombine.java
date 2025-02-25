import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testCombine {
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
    void testCombines() {
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
    
    @Test
    void testCombineOverlappingRanges() {
        Range range1 = new Range(-10, 5);
        Range range2 = new Range(0, 15);
        Range combined = Range.combine(range1, range2);
        
        assertNotNull(combined);
        assertEquals(-10.0, combined.getLowerBound());
        assertEquals(15.0, combined.getUpperBound());
    }
    
    @Test
    void testCombineSameRanges() {
        Range range1 = new Range(-5, 5);
        Range range2 = new Range(-5, 5);
        Range combined = Range.combine(range1, range2);
        
        assertNotNull(combined);
        assertEquals(-5.0, combined.getLowerBound());
        assertEquals(5.0, combined.getUpperBound());
    }
    
    @Test
    void testCombineSubsetRange() {
        Range range1 = new Range(-10, 10);
        Range range2 = new Range(-5, 5);
        Range combined = Range.combine(range1, range2);
        
        assertNotNull(combined);
        assertEquals(-10.0, combined.getLowerBound());
        assertEquals(10.0, combined.getUpperBound());
    }
    
}
