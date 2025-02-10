package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Floor floor = null;
        Robot robot = null;
        boolean initialized = false;
        String history = "";

        System.out.println("Robot simulation started. Enter commands (I, C, D, U, M, R, L, P, Q):");

        while (true) {
            System.out.print(">Enter command: ");
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");

            if (!initialized) {
                if (parts.length == 2 && parts[0].equalsIgnoreCase("I")) {
                    try {
                        int size = Integer.parseInt(parts[1]);
                        floor = new Floor(size);
                        robot = new Robot(floor);
                        initialized = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Wrong cuh");
                    }
                } else {
                    System.out.println("Please Initialize first");
                }
                continue;
            }
            switch (parts[0].toUpperCase()) {
                case "U":
                    robot.penUp();
                    break;
                case "D":
                    robot.penDown();
                    break;
                case "R":
                    robot.rotate(Turn.RIGHT);
                    break;
                case "L":
                    robot.rotate(Turn.LEFT);
                    break;
                case "M":
                    if (parts.length != 2) {
                        System.out.println("Invalid command");
                        continue;
                    }
                    int movementCount = Integer.parseInt(parts[1]);
                    robot.moveForward(movementCount);
                    break;
                case "P":
                    floor.printFloor();
                case "C":
                    robot.printStatus();
                    break;
                case "Q":
                    System.out.println("exit");
                    return;
                default:
                    System.out.println("HI");
            }
        }
    }

}
