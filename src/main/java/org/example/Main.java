package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Floor floor = null;
        Robot robot = null;
        boolean initialized = false;
        String history = "";
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
                        System.out.println("Please enter a valid number");
                    }
                } else {
                    System.out.println("Please Initialize first");
                }
                continue;
            }
            boolean validCommand = false;
            switch (parts[0].toUpperCase()) {
                case "U":
                    if (parts.length != 1) {
                        System.out.println("Invalid command.");
                        continue;
                    }
                    robot.penUp();
                    validCommand=true;
                    break;
                case "D":
                    if (parts.length != 1) {
                        System.out.println("Invalid command.");
                        continue;
                    }
                    robot.penDown();
                    validCommand=true;
                    break;
                case "R":
                    if (parts.length != 1) {
                        System.out.println("Invalid command.");
                        continue;
                    }
                    robot.rotate(Turn.RIGHT);
                    validCommand=true;
                    break;
                case "L":
                    if (parts.length != 1) {
                        System.out.println("Invalid command.");
                        continue;
                    }
                    robot.rotate(Turn.LEFT);
                    validCommand=true;
                    break;
                case "M":
                    if (parts.length != 2) {
                        System.out.println("Invalid command. Write M <number of steps>");
                        continue;
                    }
                    try{
                        int movementCount = Integer.parseInt(parts[1]);
                        robot.moveForward(movementCount);
                        validCommand=true;
                    } catch (NumberFormatException e) {
                    }

                    break;
                case "P":
                    if (parts.length != 1) {
                        System.out.println("Invalid command.");
                        continue;
                    }
                    floor.printFloor();
                    validCommand=true;
                    break;
                case "C":
                    if (parts.length != 1) {
                        System.out.println("Invalid command.");
                        continue;
                    }
                    robot.printStatus();
                    validCommand=true;
                    break;
                case "Q":

                    System.out.println("exit");
                    return;
                case "H":
                    if (parts.length != 1) {
                        System.out.println("Invalid command.");
                        continue;
                    }
                    System.out.println(history);
                    validCommand=true;
                    break;
                default:
                    System.out.println("Invalid Command");

                    continue;
            }
            if (validCommand) {
                if (!history.isEmpty()) {
                    history += ", ";
                }
                history += parts[0].toUpperCase();
            }
        }
    }

}
