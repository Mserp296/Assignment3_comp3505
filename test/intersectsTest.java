import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class intersectsTest {
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
    void testIntersects() {
        assertTrue(range.intersects(-5, 5));
        assertTrue(range.intersects(10, 20));
        assertTrue(range.intersects(-20, -10));
        assertFalse(range.intersects(11, 20));
    }
    @Test
    void testIntersectsCoversUpperBound() {
        assertFalse(range.intersects(5, 11));
        assertTrue(range.intersects(5, 9)); 
    }

    @Test
    void testIntersectsCoversLowerBound() {
        assertFalse(range.intersects(-20, -10));
        assertTrue(range.intersects(-20, -9)); 
    }

    @Test
    void testIntersectsCoversUpperEdge() {
        assertFalse(range.intersects(5, 10));  
        assertTrue(range.intersects(5, 9));    
    }

    @Test
    void testIntersectsLowerEqualsUpper() {
        assertTrue(range.intersects(5, 5));  
    }

    @Test
    void testIntersectsUpperExactlyAtUpperBound() {
        assertFalse(range.intersects(5, 10)); 
    }

    @Test
    void testIntersectsUpperJustBelowUpperBound() {
        assertTrue(range.intersects(5, 9.9)); 
    }

    @Test
    void testIntersectsLowerGreaterThanUpper() {
        assertFalse(range.intersects(8, 7)); 
    }
    @Test
    void testIntersectsExactBounds() {
        assertTrue(range.intersects(-10, -10));
        assertTrue(range.intersects(10, 10));
        assertFalse(range.intersects(11, 20)); 
        assertFalse(range.intersects(-20, -11)); 
    }

    @Test
    void testIntersectsEdgeCases() {
        assertFalse(range.intersects(-20, -11)); 
        assertTrue(range.intersects(-10, -10)); 
        assertFalse(range.intersects(11, 20)); 
    }

    @Test
    void testIntersectsUpperBoundary() {
        assertFalse(range.intersects(10, 10));
    }
}
