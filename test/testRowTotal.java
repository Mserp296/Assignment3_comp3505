import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testRowTotal {

    private Values2D mockValues2D;

    @BeforeEach
    void setUp() {
        mockValues2D = mock(Values2D.class);
    }

    @Test
    void testCalculateRowTotalWithValidData() {
        when(mockValues2D.getColumnCount()).thenReturn(3);
        when(mockValues2D.getValue(1, 0)).thenReturn(1.0);
        when(mockValues2D.getValue(1, 1)).thenReturn(2.0);
        when(mockValues2D.getValue(1, 2)).thenReturn(3.0);

        assertEquals(6.0, DataUtilities.calculateRowTotal(mockValues2D, 1), 0.0001);
    }

    @Test
    void testCalculateRowTotalWithNullValues() {
        when(mockValues2D.getColumnCount()).thenReturn(3);
        when(mockValues2D.getValue(1, 0)).thenReturn(null);
        when(mockValues2D.getValue(1, 1)).thenReturn(2.0);
        when(mockValues2D.getValue(1, 2)).thenReturn(null);

        assertEquals(2.0, DataUtilities.calculateRowTotal(mockValues2D, 1), 0.0001);
    }

    @Test
    void testCalculateRowTotalEmptyRow() {
        when(mockValues2D.getColumnCount()).thenReturn(0);
        assertEquals(0.0, DataUtilities.calculateRowTotal(mockValues2D, 1));
    }
}
