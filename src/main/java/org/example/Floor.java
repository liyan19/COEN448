package org.example;

public class Floor {
    private int[][] grid;
    private int size;

    public Floor(int size) {
        this.size = size;
        grid = new int[size][size];
    }

    public void markPosition(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size) {
            grid[x][y] = 1;
        }
    }

    public void printFloor() {
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[j][i] == 1 ? "* " : "  ");
            }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }
}
