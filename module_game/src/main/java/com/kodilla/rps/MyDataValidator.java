package com.kodilla.rps;

public interface MyDataValidator {
    String validateName(String name) throws InvalidNameException;
    MoveGame validateMove(String raw) throws InvalidMoveException;
    int numberBetweenFromOneToThree(int number) throws InvalidNumberException;
}
