package pt.isel.ls.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IntsTests {

    @Test
    public void max_returns_greatest() {
        assertEquals(1, Ints.max(1, -2));
        assertEquals(1, Ints.max(-2, 1));
        assertEquals(-1, Ints.max(-1, -2));
        assertEquals(-1, Ints.max(-2, -1));
    }

    @Test
    public void indexOfBinary_returns_negative_if_not_found() {
        // Arrange
        int[] v = {1, 2, 3};

        // Act
        int ix = Ints.indexOfBinary(v, 0, 3, 4);

        // Assert
        assertTrue(ix < 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void indexOfBinary_throws_IllegalArgumentException_if_indexes_are_not_valid() {
        // Arrange
        int[] v = {1, 2, 3};

        // Act
        int ix = Ints.indexOfBinary(v, 2, 1, 4);

        // Assert
        assertTrue(ix < 0);
    }

    @Test
    public void indexOfBinary_right_bound_parameter_is_exclusive() {
        int[] v = {2, 2, 2};
        int ix = Ints.indexOfBinary(v, 1, 1, 2);
        assertTrue(ix < 0);
    }

    @Test
    public void indexOfBinary_right_bound_is_negative() {
        int[] v = {1, 2, 3, 4, 5, 6};
        int ix = Ints.indexOfBinary(v, -6, 4, 3);
        assertEquals(-1, ix);
    }

    @Test
    public void indexOfBinary_bounds_are_negative() {
        int[] v = {1, 2, 3, 4, 5, 6};
        int ix = Ints.indexOfBinary(v, -10, -6, 3);
        assertEquals(-1, ix);
    }

    @Test
    public void indexOfBinary01() {
        int[] v = {6, 2};
        int ix = Ints.indexOfBinary(v, 0, 2, 2);
        assertEquals(ix, 1);
    }

    @Test
    public void indexOfBinary02() {
        int[] v = {6, 2, 1, 0, 8, 9, 15, 22};
        int ix = Ints.indexOfBinary(v, 0, 6, 0);
        assertEquals(ix, 3);
    }

}