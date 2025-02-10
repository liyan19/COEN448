package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
//Used AI to generate this function  and the format of a test with the Scanner System.in and System.out. The prompt was: In java, how can I test a method that takes input from System.in and returns output to System.out?
    private String runMainWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));

        try {
            Main.main(new String[]{});
        } catch (Exception ignored) {}

        return out.toString();
    }

    @Test
    void testInitializationSuccess() {
        String output = runMainWithInput("I 5\nQ\n");
        assertFalse(output.contains("Please Initialize first"));
    }

    @Test
    void testInitializationInvalidInput() {
        String output = runMainWithInput("I A\nQ\n");
        assertTrue(output.contains("Please enter a valid number"));
    }

    @Test
    void testInitializationMissingNumber() {
        String output = runMainWithInput("I\nQ\n");
        assertTrue(output.contains("Please Initialize first"));
    }

    @Test
    void testCommandBeforeInitialization() {
        String output = runMainWithInput("D\nQ\n");
        assertTrue(output.contains("Please Initialize first"));
    }

    @Test
    void testPenUpDown() {
        String output = runMainWithInput("I 5\nD\nU\nQ\n");
        assertFalse(output.contains("Invalid command."));
    }

    @Test
    void testMoveForward() {
        String output = runMainWithInput("I 5\nD\nM 3\nQ\n");
        assertFalse(output.contains("Invalid command."));
    }

    @Test
    void testMoveWithoutNumber() {
        String output = runMainWithInput("I 5\nM\nQ\n");
        assertTrue(output.contains("Invalid command. Write M <number of steps>"));
    }

    @Test
    void testRotateLeftRight() {
        String output = runMainWithInput("I 5\nL\nR\nQ\n");
        assertFalse(output.contains("Invalid command."));
    }

    @Test
    void testPrintFloor() {
        String output = runMainWithInput("I 5\nP\nQ\n");
        assertFalse(output.contains("Invalid command."));
    }

    @Test
    void testPrintStatus() {
        String output = runMainWithInput("I 5\nC\nQ\n");
        assertFalse(output.contains("Invalid command."));
    }

    @Test
    void testHistoryTracking() {
        String output = runMainWithInput("I 5\nD\nM 3\nL\nR\nH\nQ\n");
        assertTrue(output.contains("D, M, L, R"));
    }

    @Test
    void testInvalidCommand() {
        String output = runMainWithInput("I 5\nX\nQ\n");
        assertTrue(output.contains("Invalid Command"));
    }
}
