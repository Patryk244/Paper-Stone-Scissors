package com.kodilla.rps;

import java.util.Scanner;

public class InputUserValidator {
    private final Scanner sc;
    private final DataValidatorImplement validator;

    public InputUserValidator(Scanner sc, DataValidatorImplement validator) {
        this.sc = sc;
        this.validator = validator;
    }

    public String inputGetString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    public int getInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                int number = Integer.parseInt(input);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Enter number");
            }
        }
    }
}
