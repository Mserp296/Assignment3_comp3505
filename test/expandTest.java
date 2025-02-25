import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class expandTest {

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
    void testExpandToInclude() {
        Range expanded = Range.expandToInclude(range, 15.0);
        assertEquals(-10.0, expanded.getLowerBound());
        assertEquals(15.0, expanded.getUpperBound());
    }

    @Test
    void testExpandToIncludeNullRange() {
        Range newRange = Range.expandToInclude(null, 5);
        assertEquals(5, newRange.getLowerBound());
        assertEquals(5, newRange.getUpperBound());
    }
    
    @Test
    void testExpandToIncludeNewLower() {
        Range expanded = Range.expandToInclude(range, -15);
        assertEquals(-15, expanded.getLowerBound());
        assertEquals(10, expanded.getUpperBound());
    }

    
    @Test
    void testExpand() {
        Range expanded = Range.expand(range, 0.5, 0.5);
        assertEquals(-20.0, expanded.getLowerBound());
        assertEquals(20.0, expanded.getUpperBound());
    }
    
    @Test
    void testExpandNullRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            Range.expand(null, 0.5, 0.5);
        });
    }
    

    @Test
    void testExpandToIncludeValueWithinRange() {
        Range sameRange = Range.expandToInclude(range, 0);
        assertEquals(range, sameRange);
    }
    
    @Test
    void testExpandExtremeCase() {
        Range expanded = Range.expand(range, 1.0, 1.0);
        assertEquals(-30, expanded.getLowerBound());
        assertEquals(30, expanded.getUpperBound());
    }
}
