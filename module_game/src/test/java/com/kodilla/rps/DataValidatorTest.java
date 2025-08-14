package com.kodilla.rps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DataValidatorTest {

    private DataValidatorImplement dataValidatorImplement;

    @BeforeEach
    public void setup() {
        dataValidatorImplement = new DataValidatorImplement();
    }

    @Test
    void NameIsValid() throws InvalidNameException {
        assertDoesNotThrow(() -> dataValidatorImplement.validateName("Ola"));
    }

    @Test
    void NameIsNotValid() throws InvalidNameException {
        assertThrows((InvalidNameException.class), () -> dataValidatorImplement.validateName(null));
    }

    @Test
    void MoveIsValid() {
        MoveGame validMove1 = dataValidatorImplement.validateMove(MoveGame.PAPER.toString());
        MoveGame validMove2 = dataValidatorImplement.validateMove(MoveGame.SCISSORS.toString());
        MoveGame validMove3 = dataValidatorImplement.validateMove(MoveGame.STONE.toString());

        assertEquals(MoveGame.PAPER, validMove1);
        assertEquals(MoveGame.SCISSORS, validMove2);
        assertEquals(MoveGame.STONE, validMove3);
    }

    @Test
    void MoveIsNotValid() {
        assertThrows(InvalidMoveException.class, () -> dataValidatorImplement.validateMove(null));
        assertThrows(InvalidMoveException.class, () -> dataValidatorImplement.validateMove(""));
        assertThrows(InvalidMoveException.class, () -> dataValidatorImplement.validateMove("x"));
        assertThrows(InvalidMoveException.class, () -> dataValidatorImplement.validateMove("paperx"));

    }

    @Test
    void TestNumberBetweenFromOneToThree() throws InvalidNumberException {
        assertThrows(InvalidNumberException.class, () -> dataValidatorImplement.numberBetweenFromOneToThree(0));
        assertThrows(InvalidNumberException.class, () -> dataValidatorImplement.numberBetweenFromOneToThree(4));
    }

    @Test
    void TestNumberBetweenFromOneToThreeNotTrue() throws InvalidNumberException {
        assertDoesNotThrow(() -> dataValidatorImplement.numberBetweenFromOneToThree(1));
        assertDoesNotThrow(() -> dataValidatorImplement.numberBetweenFromOneToThree(2));
        assertDoesNotThrow(() -> dataValidatorImplement.numberBetweenFromOneToThree(3));
    }




}
