import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testColumnTotal{

    private Values2D mockValues2D;

    @BeforeEach
    void setUp() {
        mockValues2D = mock(Values2D.class);
    }

    @Test
    void testCalculateColumnTotalWithValidData() {
        when(mockValues2D.getRowCount()).thenReturn(3);
        when(mockValues2D.getValue(0, 1)).thenReturn(1.0);
        when(mockValues2D.getValue(1, 1)).thenReturn(2.0);
        when(mockValues2D.getValue(2, 1)).thenReturn(3.0);

        assertEquals(6.0, DataUtilities.calculateColumnTotal(mockValues2D, 1), 0.0001);
    }

    @Test
    void testCalculateColumnTotalWithNullValues() {
        when(mockValues2D.getRowCount()).thenReturn(3);
        when(mockValues2D.getValue(0, 1)).thenReturn(null);
        when(mockValues2D.getValue(1, 1)).thenReturn(2.0);
        when(mockValues2D.getValue(2, 1)).thenReturn(null);

        assertEquals(2.0, DataUtilities.calculateColumnTotal(mockValues2D, 1), 0.0001);
    }

    @Test
    void testCalculateColumnTotalEmptyTable() {
        when(mockValues2D.getRowCount()).thenReturn(0);
        assertEquals(0.0, DataUtilities.calculateColumnTotal(mockValues2D, 1));
    }
}
