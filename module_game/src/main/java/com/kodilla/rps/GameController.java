package com.kodilla.rps;

import java.util.Scanner;

public class GameController {
    Scanner sc = new Scanner(System.in);
    UserInterface ui;
    InputUserValidator validator;
    Player humanPlayer;
    Player computerPlayer;
    int targetRound;
    int humanScore;
    int computerScore;

    public GameController() {
        this.humanPlayer = null;
        this.computerPlayer = null;
        ui = new UserInterface();
        validator = new InputUserValidator(sc , new DataValidatorImplement());
        this.targetRound = 0;
        this.humanScore = 0;
        this.computerScore = 0;
    }

    public void startGame() {
        ui = new UserInterface();
        this.humanPlayer = new Player(ui.askPlayerName("Enter your name: "));
        ui.welcome(humanPlayer.getName());
        this.computerPlayer = new Player("Computer");

        this.humanScore = 0;
        this.computerScore = 0;
        this.targetRound = ui.askTargetWins();

        gameLoop();
    }

    public void gameLoop() {
        boolean running = true;

        while (running) {
            ui.printControls();
            String command = ui.askPlayerMove();

            switch (command) {
                case "STONE":
                case "PAPER":
                case "SCISSORS": {
                    MoveGame playerMove = MoveGame.valueOf(command);
                    MoveGame drawMove = computerDrawMove();
                    int res = decideRound(playerMove, drawMove);

                    if (res > 0) {
                        humanScore++;
                        ui.printRoundResult(humanPlayer.getName(), playerMove, computerPlayer.getName(), drawMove, humanPlayer.getName());
                    } else if (res < 0) {
                        computerScore++;
                        ui.printRoundResult(humanPlayer.getName(), playerMove, computerPlayer.getName(), drawMove, computerPlayer.getName());
                    } else {
                        ui.printRoundResult(humanPlayer.getName(), playerMove, computerPlayer.getName(), drawMove, "Draw");
                    }

                    ui.printScore(humanPlayer.getName(), humanScore, computerPlayer.getName(), computerScore);

                    if (humanScore >= targetRound || computerScore >= targetRound) {
                        String winner = (humanScore > computerScore) ? humanPlayer.getName() : computerPlayer.getName();
                        ui.announceWinner(winner, humanScore, computerScore);

                        if (ui.confirmNewGame()) {
                            humanScore = 0;
                            computerScore = 0;
                            targetRound = ui.askTargetWins();
                        } else {
                            running = false;
                        }
                    }
                    break;
                }
                case "4":
                    if (ui.confirmExit()) {
                        running = false;
                    }
                    break;

                case "5":
                    if (ui.confirmNewGame()) {
                        humanScore = 0;
                        computerScore = 0;
                        targetRound = ui.askTargetWins();
                    }
                    break;

                default:
                    System.out.println("Invalid input. Type STONE, PAPER, SCISSORS, 4 or 5.");
            }
        }
    }

    private MoveGame mapChoiceToMove(int choice) {
        switch (choice) {
            case 1: return MoveGame.STONE;
            case 2: return MoveGame.PAPER;
            case 3: return MoveGame.SCISSORS;
            default: throw new IllegalArgumentException("Invalid choice: " + choice + "Choose between 1 and 3.");
        }
    }

    private MoveGame computerDrawMove() {
        int r = 1 + (int)(Math.random() * 3);
        return mapChoiceToMove(r);
    }

    private int decideRound(MoveGame playerMove, MoveGame computerMove) {
        if (playerMove == computerMove) return 0;

        if ((playerMove == MoveGame.STONE && computerMove == MoveGame.SCISSORS) ||
                (playerMove == MoveGame.PAPER && computerMove == MoveGame.STONE) ||
                (playerMove == MoveGame.SCISSORS && computerMove == MoveGame.PAPER)) {
            return 1;
        }
        return -1;
    }
}
