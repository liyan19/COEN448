package org.example;

public class Robot {
    private int x;
    private int y;
    private boolean penDown;
    private Floor floor;
    private Direction direction;

    public Robot(Floor floor) {
        this.floor = floor;
        this.x = 0;
        this.y = 0;
        this.penDown = false;
        this.direction = Direction.NORTH;
    }

    public void penUp() {
        penDown = false;
    }

    public void penDown() {
        penDown = true;
    }

    public void rotate(Turn turn) {
        direction = direction.rotate(turn);
    }

    public void moveForward(int steps) {

        // Check if move is possible
        if (direction == Direction.NORTH && y + steps >= floor.getSize()) {
            steps = floor.getSize() - y - 1;
        } else if (direction == Direction.EAST && x + steps >= floor.getSize()) {
            steps = floor.getSize() - x - 1;
        } else if (direction == Direction.SOUTH && y - steps < 0) {
            steps = y;
        } else if (direction == Direction.WEST && x - steps < 0) {
            steps = x;
        }

        for (int i = 0; i < steps; i++) {
            if (penDown) {
                floor.markPosition(x, y);
            }
            switch (direction) {
                case NORTH:
                    if (y + 1 < floor.getSize()) y++;
                    break;
                case EAST:
                    if (x + 1 < floor.getSize()) x++;
                    break;
                case SOUTH:
                    if (y - 1 >= 0) y--;
                    break;
                case WEST:
                    if (x - 1 >= 0) x--;
                    break;
            }
        }
        if (penDown) {
            floor.markPosition(x, y);
        }
    }

    public void printStatus() {
        System.out.println("Position: (" + x + ", " + y + ") - Pen: " + (penDown ? "Down" : "Up") + " - Facing: " + direction);
    }
}
