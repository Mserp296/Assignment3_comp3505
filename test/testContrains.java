import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.Range;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testConstrains {

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
    void testConstrain() {
        assertEquals(5.0, range.constrain(5));
        assertEquals(-10, range.constrain(-15));
        assertEquals(10, range.constrain(15));
    }
    
    @Test
    void testConstrainJustInsideLowerBound() {
        assertEquals(-9.99, range.constrain(-9.99));
    }
    
    @Test
    void testConstrainJustInsideUpperBound() {
        assertEquals(9.99, range.constrain(9.99));
    }
    
    @Test
    void testConstrainJustOutsideLowerBound() {
        assertEquals(-10.0, range.constrain(-10.01));
    }
    
    @Test
    void testConstrainJustOutsideUpperBound() {
        assertEquals(10.0, range.constrain(10.01));
    }
    
    @Test
    void testConstrainBelowRange() {
        assertEquals(-10.0, range.constrain(-1000));
    }
    
    @Test
    void testConstrainAboveRange() {
        assertEquals(10.0, range.constrain(1000));
    }
    
    @Test
    void testConstrainDirectlyExecutesElseIf() {
        double testValue = -11.0;
        assertFalse(range.contains(testValue));
        double constrainedValue = range.constrain(testValue);
        assertEquals(-10.0, constrainedValue);
    }

    @Test
    void testConstrainBelowLowerBound() {
        assertEquals(-10.0, range.constrain(-100.0));
    }
}
