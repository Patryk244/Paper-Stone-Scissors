package com.kodilla.rps;

import java.util.Scanner;

public class UserInterface {
    private Scanner sc = new Scanner(System.in);
    private DataValidatorImplement imp = new DataValidatorImplement();
    private InputUserValidator input = new InputUserValidator(sc, imp);

    public void welcome(String username) {
        System.out.println("Welcome: " +  username);
    }

    public String askPlayerName(String prompt) {
        while (true) {
            try {
                String name = input.inputGetString(prompt);
                imp.validateName(name);
                return name;
            } catch (InvalidNameException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int askTargetWins() {
        while (true) {
            int number = input.getInteger("Target wins: ");
            if (number <= 10 || number <= 0) {
                return number;
            } else  {
                System.out.println("Value between one to ten!");
            }
        }
    }


    public String askPlayerMove() {
        while (true) {
            String result = input.inputGetString("Move (STONE/PAPER/SCISSORS): ").trim().toUpperCase();
            try {
                imp.validateMove(result);
                return  result;
            } catch (InvalidMoveException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean confirmExit() {
        while (true) {
            String result = input.inputGetString("Do you want to exit? (y/n): ");
            if (result.equalsIgnoreCase("y")) {
                System.out.println("Exiting...");
                return true;
            } else if (result.equalsIgnoreCase("n")) {
                System.out.println("Game is stay open!");
                return false;
            } else {
                System.out.println("Not recognize input please try again.");
            }
        }

    }

    public void printControls() {
        System.out.println("\n=== Game Controls ===");
        System.out.println("1 - Stone");
        System.out.println("2 - Paper");
        System.out.println("3 - Scissors");
        System.out.println("4 - End the game (with confirmation)");
        System.out.println("5 - Start a new game (with confirmation)");
        System.out.println("=====================");
    }

    public void printRoundResult(String p1, MoveGame m1, String p2, MoveGame m2, String winner) {
        System.out.println("Round Result: ");
        System.out.println(p1 + ": " + m1 + " vs " + p2 + ": " + m2);
        if ("Draw".equals(winner)) {
            System.out.println("It is a draw!");
        } else {
            System.out.println("Winner: " + winner);
        }
    }

    public void printScore(String p1, int s1, String p2, int s2) {
        System.out.println("Score -> " + p1 + ": " + s1 + " | " + p2 + ": " + s2);
    }

    public boolean confirmNewGame() {
        String ans = input.inputGetString("Start a new game? (y/n): ").trim().toLowerCase();
        return ans.startsWith("y");
    }

    public void announceWinner(String winner, int s1, int s2) {
        System.out.println("\n=== GAME OVER ===");
        System.out.println("Winner: " + winner);
        System.out.println("Final score: " + s1 + " : " + s2);
        System.out.println("=================\n");
    }
}
