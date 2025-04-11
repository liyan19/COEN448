package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class RobotTest {
    private Robot robot;
    private Floor floor;
    @BeforeEach
    void setUp() {
        floor = new Floor(10); // Create a 10x10 grid
        robot = new Robot(floor); // Initialize robot
    }
    @Test
    public void testInitialState() {

        assertEquals(0, robot.getX());
        assertEquals(0, robot.getY());
        assertEquals(Direction.NORTH, robot.getDirection());
        assertFalse(robot.isPenDown());
    }
    @Test
    void testPenUp() {
        robot.penUp();
        assertFalse(robot.isPenDown());
    }

    @Test
    void testPenDown() {
        robot.penDown();
        assertTrue(robot.isPenDown());
    }

    @Test
    void testRotateRight() {

        robot.rotate(Turn.RIGHT);
        assertEquals(Direction.EAST, robot.getDirection());

        robot.rotate(Turn.RIGHT);
        assertEquals(Direction.SOUTH, robot.getDirection());

        robot.rotate(Turn.RIGHT);
        assertEquals(Direction.WEST, robot.getDirection());

        robot.rotate(Turn.RIGHT);
        assertEquals(Direction.NORTH, robot.getDirection());
    }

    @Test
    void testRotateLeft() {
        robot.rotate(Turn.LEFT);
        assertEquals(Direction.WEST, robot.getDirection());

        robot.rotate(Turn.LEFT);
        assertEquals(Direction.SOUTH, robot.getDirection());

        robot.rotate(Turn.LEFT);
        assertEquals(Direction.EAST, robot.getDirection());

        robot.rotate(Turn.LEFT);
        assertEquals(Direction.NORTH, robot.getDirection());
    }


    @Test
    void testMoveForwardNorth() {
        robot.penDown();
        int startX = robot.getX();
        int startY = robot.getY();
        int steps = 4;

        robot.moveForward(steps);

        // Check that all positions from (0,0) to (0,4) are marked
        for (int i = startY; i <= startY + steps; i++) {
            assertEquals(1, floor.getGrid()[startX][i]);
        }
    }

    @Test
    void testMoveForwardEast() {
        robot.rotate(Turn.RIGHT);
        robot.penDown();
        int startX = robot.getX();
        int startY = robot.getY();
        int steps = 3;

        robot.moveForward(steps);

        // Check that all positions from (0,0) to (3,0) are marked
        for (int i = startX; i <= startX + steps; i++) {
            assertEquals(1, floor.getGrid()[i][startY]);
        }
    }

    @Test
    void testMoveForwardSouth() {
        robot.moveForward(4); // Move north first
        robot.rotate(Turn.RIGHT); // Turn east
        robot.rotate(Turn.RIGHT); // Turn south
        robot.penDown();
        int startX = robot.getX();
        int startY = robot.getY();
        int steps = 2;

        robot.moveForward(steps);

        // Check that all positions from (0,4) to (0,2) are marked
        for (int i = startY; i >= startY - steps; i--) {
            assertEquals(1, floor.getGrid()[startX][i]);
        }
    }

    @Test
    void testMoveForwardWest() {
        robot.rotate(Turn.RIGHT); // East
        robot.moveForward(4); // Move east first
        robot.rotate(Turn.RIGHT); // South
        robot.rotate(Turn.RIGHT); // West
        robot.penDown();
        int startX = robot.getX();
        int startY = robot.getY();
        int steps = 2;

        robot.moveForward(steps);

        // Check that all positions from (4,0) to (2,0) are marked
        for (int i = startX; i >= startX - steps; i--) {
            assertEquals(1, floor.getGrid()[i][startY]);
        }
    }

    @Test
    void testMoveOutOfBounds() {
        robot.penDown();
        int steps = 12;
        int startX = robot.getX();
        int startY = robot.getY();

        robot.moveForward(steps);

        for (int i = startY; i <= floor.getSize() - 1; i++) {
            assertEquals(1, floor.getGrid()[startX][i]);
        }
        assertEquals(startX, robot.getX());
        assertEquals(floor.getSize() - 1, robot.getY());
    }

    //Regression Testing
    @Test
    void testSingleStep(){
        robot.penDown();
        robot.moveForward(1);
        assertEquals(1, floor.getGrid()[0][0]);
        assertEquals(1, floor.getGrid()[0][1]);
    }
    @Test
    void testMoveForwardDataFlow(){
        //Move out of Bound
        robot.moveForward(10);
        assertEquals(0, robot.getX());
        assertEquals(9, robot.getY());
        //Reset robot
        robot=new Robot(floor);
        //Move to (0,2)
        robot.moveForward(2);
        assertEquals(0, robot.getX());
        assertEquals(2, robot.getY());
        assertEquals(0, floor.getGrid()[0][2]);
        //Put pen down and move to (0,5)
        robot.penDown();
        robot.moveForward(3);
        assertEquals(0, floor.getGrid()[0][1]);
        assertEquals(1, floor.getGrid()[0][2]);
        assertEquals(1, floor.getGrid()[0][3]);
        assertEquals(1, floor.getGrid()[0][4]);
        assertEquals(1, floor.getGrid()[0][5]);
        //Turn east and move to (2,5)
        robot.rotate(Turn.RIGHT);
        robot.moveForward(2);
        assertEquals(2,robot.getX());
        assertEquals(5,robot.getY());
        assertEquals(1, floor.getGrid()[0][5]);
        assertEquals(1, floor.getGrid()[1][5]);
        assertEquals(1, floor.getGrid()[2][5]);
    }
}
