package com.kodilla.rps;

public class DataValidatorImplement implements MyDataValidator {
    @Override
    public String validateName(String name) throws InvalidNameException {
        if (name == null) {
            throw new InvalidNameException("Name is null");
        }

        name = name.trim();

        if (name.isEmpty() || name.length() < 3) {
            throw new InvalidNameException("Name is empty or must be at least 3 characters");
        }
        return name;
    }

    @Override
    public MoveGame validateMove(String raw) throws InvalidMoveException {
        if (raw == null || raw.isEmpty()) {
            throw new InvalidMoveException("Move is empty");
        }
        String upperCase = raw.toUpperCase().trim();
        try {
            return MoveGame.valueOf(upperCase);
        } catch (IllegalArgumentException e) {
            throw new InvalidMoveException("Move is not valid: " + raw);
        }
    }

    @Override
    public int numberBetweenFromOneToThree(int number) throws InvalidNumberException {
        if  (number < 1 || number > 3) {
            throw new InvalidNumberException("Number is out of range");
        }
        return number;
    }
}
