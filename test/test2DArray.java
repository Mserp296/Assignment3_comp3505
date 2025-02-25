import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

class test2DArray {

    @Test
    void testCreateNumberArray2DValidInput() {
        double[][] data = {{1.1, 2.2}, {3.3, 4.4}};
        Number[][] expected = {{1.1, 2.2}, {3.3, 4.4}};
        assertArrayEquals(expected, DataUtilities.createNumberArray2D(data));
    }

    @Test
    void testCreateNumberArray2DEmpty() {
        double[][] data = {};
        Number[][] expected = {};
        assertArrayEquals(expected, DataUtilities.createNumberArray2D(data));
    }

    @Test
    void testCreateNumberArray2DNull() {
        assertThrows(IllegalArgumentException.class, () -> DataUtilities.createNumberArray2D(null));
    }
}

