import static org.junit.jupiter.api.Assertions.*;
import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class test {

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

    
    
    //HashCode test for 100% coverage
    @Test
    void testHashCode() {
        Range range = new Range(-10, 10);
        Range range1 = new Range(-10, 10);
        assertEquals(range.hashCode(), range1.hashCode());
    }
    
    //Constructor Test
    @Test
    void testConstructorThrowsExceptionForInvalidRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Range(10, -10);
        });
        assertTrue(exception.getMessage().contains("require lower"));
    }
    
    @Test
    void testConstructorDoesNotThrowForZeroBounds() {
        assertDoesNotThrow(() -> new Range(0, 0));
    }
    
    @Test
    void testConstructorDoesNotThrowForValidNegativeRange() {
        assertDoesNotThrow(() -> new Range(-10, -5));
    }
    
    @Test
    void testConstructorThrowsForReversedNegativeRange() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Range(-5, -10);
        });
        assertTrue(exception.getMessage().contains("require lower"));
    }
    
    @Test
    void testConstructorDoesNotThrowForEqualBounds() {
        assertDoesNotThrow(() -> new Range(5, 5));
    }
}

