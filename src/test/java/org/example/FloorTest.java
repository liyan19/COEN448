package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FloorTest {
    private Floor floor;
    @BeforeEach
    void setUp() {
        floor = new Floor(10);
    }
    @Test
    void testFloorInitialization() {
        int[][] grid = floor.getGrid();

        // Check if all values are set to 0
        for (int i = 0; i < floor.getSize(); i++) {
            for (int j = 0; j < floor.getSize(); j++) {
                assertEquals(0, grid[i][j]);
            }
        }
    }
    @Test
    void testMarkPosition() {
        floor.markPosition(2, 2);
        assertEquals(1, floor.getGrid()[2][2]);

    }
    @Test
    void testMarkOutOfBounds() {
        floor.markPosition(-1, 2); // Negative X
        floor.markPosition(2, -1); // Negative Y
        floor.markPosition(10, 3); // X out of bounds
        floor.markPosition(3, 10); // Y out of bounds

      //check that nothing happened outside the grid
        for (int i = 0; i < floor.getSize(); i++) {
            for (int j = 0; j < floor.getSize(); j++) {
                assertEquals(0, floor.getGrid()[i][j]);
            }
        }
    }
}
