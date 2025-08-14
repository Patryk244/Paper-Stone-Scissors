package com.kodilla.rps;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class InputValidatorTest {

    @Test
    void receivedNameForScanner() {
        Scanner sc = new Scanner("Ola\n");
        InputUserValidator input = new InputUserValidator(sc, new DataValidatorImplement());
        String name = input.inputGetString("Enter your name: ");
        assertEquals("Ola", name);
    }

    @Test
    void mustBeTypeInt() {
        Scanner sc = new Scanner("1\n");
        InputUserValidator input = new InputUserValidator(sc, new DataValidatorImplement());
        int number = input.getInteger("Enter your number: ");
        assertEquals(1, number);
    }

}
