import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.security.InvalidParameterException;
import java.util.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.junit.jupiter.api.*;

class testCumulative {
    private static final int timeout = 5;
    private KeyedValues testKeyedValues;
    private KeyedValues nullKeyedValues;

    @BeforeAll
    static void setUpBeforeClass() {
    }

    @BeforeEach
    void setUp() {
        testKeyedValues = mock(KeyedValues.class);
        nullKeyedValues = mock(KeyedValues.class);

        when(testKeyedValues.getItemCount()).thenReturn(3);
        when(testKeyedValues.getValue(0)).thenReturn(5);
        when(testKeyedValues.getValue(1)).thenReturn(9);
        when(testKeyedValues.getValue(2)).thenReturn(2);
        
        //returns A, B, C for non null test cases
        when(testKeyedValues.getKey(0)).thenReturn("A");
        when(testKeyedValues.getKey(1)).thenReturn("B");
        when(testKeyedValues.getKey(2)).thenReturn("C");
        
        List<String> keyList = Arrays.asList("A", "B", "C");
        
        when(testKeyedValues.getKeys()).thenReturn(keyList);
        when(nullKeyedValues.getItemCount()).thenReturn(3);
        when(nullKeyedValues.getValue(0)).thenReturn(5);
        when(nullKeyedValues.getValue(1)).thenReturn(null);
        when(nullKeyedValues.getValue(2)).thenReturn(2);
        
        //returns X, Y, Z for null test cases
        when(nullKeyedValues.getKey(0)).thenReturn("X");
        when(nullKeyedValues.getKey(1)).thenReturn("Y");
        when(nullKeyedValues.getKey(2)).thenReturn("Z");
    }

    @Test
    void testGetCumulativePercentagesNullInput() {
        assertThrows(InvalidParameterException.class, () -> DataUtilities.getCumulativePercentages(null));
    }

    @Test
    @Timeout(timeout)
    void testGetCumulativePercentagesValidValues() {
        KeyedValues result = DataUtilities.getCumulativePercentages(testKeyedValues);

        assertAll(
            () -> assertEquals(0.3125, result.getValue("A").doubleValue(), 0.0001),
            () -> assertEquals(0.875, result.getValue("B").doubleValue(), 0.0001),
            () -> assertEquals(1.0, result.getValue("C").doubleValue(), 0.0001)
        );
    }

    @Test
    @Timeout(timeout)
    void testGetCumulativePercentagesKey() {
        KeyedValues result = DataUtilities.getCumulativePercentages(testKeyedValues);

        assertAll(
            () -> assertEquals("A", result.getKey(0)),
            () -> assertEquals("B", result.getKey(1)),
            () -> assertEquals("C", result.getKey(2))
        );
    }


    @Test
    @Timeout(timeout)
    void testGetCumulativePercentagesIndex() {
        KeyedValues result = DataUtilities.getCumulativePercentages(testKeyedValues);

        assertAll(
            () -> assertEquals(0, result.getIndex("A")),
            () -> assertEquals(1, result.getIndex("B")),
            () -> assertEquals(2, result.getIndex("C"))
        );
    }

    @AfterAll
    static void tearDownAfterClass() {
    }

    @AfterEach
    void tearDown() {
    }
    
    
}
