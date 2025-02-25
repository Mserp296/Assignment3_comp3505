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
        assertFalse(range.intersects(5, 11)); // upper == this.upper
        assertTrue(range.intersects(5, 9)); // upper < this.upper
    }

    @Test
    void testIntersectsCoversLowerBound() {
        assertFalse(range.intersects(-20, -10)); // upper == this.lower
        assertTrue(range.intersects(-20, -9)); // upper > this.lower
    }

    @Test
    void testIntersectsCoversUpperEdge() {
        assertFalse(range.intersects(5, 10));  // upper == this.upper (fails `upper < this.upper`)
        assertTrue(range.intersects(5, 9));    // upper < this.upper (should return true)
    }

    @Test
    void testIntersectsLowerEqualsUpper() {
        assertTrue(range.intersects(5, 5));  // upper == lower, both inside range
    }

    @Test
    void testIntersectsUpperExactlyAtUpperBound() {
        assertFalse(range.intersects(5, 10)); // upper == this.upper (this may be failing coverage)
    }

    @Test
    void testIntersectsUpperJustBelowUpperBound() {
        assertTrue(range.intersects(5, 9.9)); // upper < this.upper but still valid
    }

    @Test
    void testIntersectsLowerGreaterThanUpper() {
        assertFalse(range.intersects(8, 7)); // lower > upper (should return false)
    }
    @Test
    void testIntersectsExactBounds() {
        assertTrue(range.intersects(-10, -10)); // Lower bound match
        assertTrue(range.intersects(10, 10)); // Upper bound match
        assertFalse(range.intersects(11, 20)); // Outside upper bound
        assertFalse(range.intersects(-20, -11)); // Outside lower bound
    }

    @Test
    void testIntersectsEdgeCases() {
        assertFalse(range.intersects(-20, -11)); // Before lower bound
        assertTrue(range.intersects(-10, -10)); // Exactly lower bound
        assertFalse(range.intersects(11, 20)); // Outside upper bound
    }

    
}
