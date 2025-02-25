import static org.junit.jupiter.api.Assertions.*;

import org.jfree.data.DataUtilities;
import org.junit.jupiter.api.Test;

class testNumberArray {

    @Test
    void testCreateNumberArrayValidInput() {
        double[] data = {1.1, 2.2, 3.3};
        Number[] expected = {1.1, 2.2, 3.3};
        assertArrayEquals(expected, DataUtilities.createNumberArray(data));
    }

    @Test
    void testCreateNumberArrayEmpty() {
        double[] data = {};
        Number[] expected = {};
        assertArrayEquals(expected, DataUtilities.createNumberArray(data));
    }

    @Test
    void testCreateNumberArrayNull() {
        assertThrows(IllegalArgumentException.class, () -> DataUtilities.createNumberArray(null));
    }
}
