package pij.main;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Main launcher = new Main();
        launcher.launch();
    }

    public void launch() {

        System.out.println();
        System.out.println("GAME ON!");
        System.out.println();

        // Create new empty board
        ScrabbkleBoard board = new ScrabbkleBoard();

        // Ask player for board type choice, either 'default board' or 'load own board'
        String boardChoice = null;
        boolean validBoardChoice = false;
        while (!validBoardChoice) {
            boardChoice = board.getBoardChoice();
            if ((boardChoice.equals("d") || boardChoice.equals("l"))) {
                validBoardChoice = true;
            }
        }

        // Create file variable from which to load board
        File inputFile = null;

        // Create boolean to check if valid file
        boolean validFile = false;
        // Create loop to get valid file
        while (!validFile) {
            // Get input file to load selected board type
            inputFile = board.getInputFile(boardChoice);
            // Check if file exists
            boolean fileExists = board.inputFileExists(inputFile);
            if (fileExists) {
                // Calculate board size
                board.calculateBoardSize(inputFile);
                if (board.isValidSize()) {
                    validFile = true;
                } else {
                    System.out.println("This is not a valid file.");
                    System.out.println("Error: Incorrect file size.");
                }
            } else {
                System.out.println("This is not a valid file.");
                System.out.println("Error: Cannot find file.");
            }
        }

        // Load input file into board
        board.convertToBoard(inputFile);

        // Create wordlist based on provided word list
        ScrabbkleWordList wordList = new ScrabbkleWordList();

        // Create tile bag and fill bag with tiles
        ScrabbkleTileBag tileBag = new ScrabbkleTileBag();
        tileBag.fillTileBag();

        // Create one human player and one computer player
        HumanPlayer humanPlayer = new HumanPlayer(board, wordList, tileBag);
        ComputerPlayer computerPlayer = new ComputerPlayer(board, wordList, tileBag);

        // Create score boards
        int humanPlayerScore;
        int computerPlayerScore;

        // Start game
        boolean gameOver = false;


        // Fill player tile racks to enter gameplay loop
        humanPlayer.fillRack(humanPlayer.getTileRack());
        computerPlayer.fillRack(computerPlayer.getTileRack());

        // Print empty line for console readability
        System.out.println();

        // Continue loop until game over
        // Game continues until tile bag is empty, and one of the player racks is empty
        while (!gameOver) {

            // Check if player racks are empty
            boolean isHumanRackEmpty = humanPlayer.getTileRack().isEmpty();
            boolean isComputerRackEmpty = computerPlayer.getTileRack().isEmpty();

            // Human player plays first
            humanPlayer.isPlayerTurn = true;
            computerPlayer.isPlayerTurn = false;

            while (humanPlayer.isPlayerTurn) {

                // Print current board
                board.printBoard();

                // Refill player tile rack and print
                humanPlayer.fillRack(humanPlayer.getTileRack());
                humanPlayer.printRack(humanPlayer.getTileRack());

                // Get human player move input and make move
                humanPlayer.playMove();

                // Get updated scores
                humanPlayerScore = humanPlayer.getPlayerScore();
                computerPlayerScore = computerPlayer.getPlayerScore();

                // Print scores and update board
                System.out.println();
                System.out.println("Human player score: " + humanPlayerScore);
                System.out.println("Computer player score: " + computerPlayerScore);
                System.out.println();
                board.printBoard();

                // End humanPlayer turn
                humanPlayer.isPlayerTurn = false;
                computerPlayer.isPlayerTurn = true;
            }

            // Start computer player turn
            while (computerPlayer.isPlayerTurn) {
                computerPlayer.fillRack(computerPlayer.getTileRack());
                computerPlayer.playMove();


                // Get updated scores
                humanPlayerScore = humanPlayer.getPlayerScore();
                computerPlayerScore = computerPlayer.getPlayerScore();


                System.out.println();
                System.out.println("Human player score: " + humanPlayerScore);
                System.out.println("Computer player score: " + computerPlayerScore);
                System.out.println();

                // End computerPlayer turn
                computerPlayer.isPlayerTurn = false;
                humanPlayer.isPlayerTurn = true;
            }

            // Get skip counts from players

            int humanSkipCount = humanPlayer.getSkipCount();
            int computerSkipCount = computerPlayer.getSkipCount();

            // If both players skip two times in a row, then game over
            // If tile bag is empty AND either the human rack or computer rack is empty, then game over

            if (((tileBag.isEmpty()) && (isHumanRackEmpty || isComputerRackEmpty)) || ((humanSkipCount >= 2 && computerSkipCount >= 2))) {
                gameOver = true;
            }
        }

        // Calculate scores

        humanPlayerScore = humanPlayer.getPlayerScore();
        computerPlayerScore = computerPlayer.getPlayerScore();

        // Deduct remaining tile values from the current score

        int remainingHumanTiles = humanPlayer.deductLeftOverTiles(humanPlayer.getTileRack());
        int remainingComputerTiles = computerPlayer.deductLeftOverTiles(computerPlayer.getTileRack());

        humanPlayerScore -= remainingHumanTiles;
        computerPlayerScore -= remainingComputerTiles;

        // Declare winner and end game

        System.out.println("Game Over!");
        System.out.println("The human player scored " + humanPlayerScore + " points.");
        System.out.println("The computer player scored " + computerPlayerScore + " points.");
        if (humanPlayerScore > computerPlayerScore) {
            System.out.println("The human player wins!");
        } else if (computerPlayerScore > humanPlayerScore) {
            System.out.println("The computer player wins!");
        } else {
            System.out.println("It's a draw!");
        }

    }
}

//   Thanks for playing! Or trying to... sorry for the bugs! :)
//    ________  ________  ________  ________  ________  ________  ___  __    ___       _______
//   |\   ____\|\   ____\|\   __  \|\   __  \|\   __  \|\   __  \|\  \|\  \ |\  \     |\  ___ \
//   \ \  \___|\ \  \___|\ \  \|\  \ \  \|\  \ \  \|\ /\ \  \|\ /\ \  \/  /|\ \  \    \ \   __/|
//    \ \_____  \ \  \    \ \   _  _\ \   __  \ \   __  \ \   __  \ \   ___  \ \  \    \ \  \_|/__
//     \|____|\  \ \  \____\ \  \\  \\ \  \ \  \ \  \|\  \ \  \|\  \ \  \\ \  \ \  \____\ \  \_|\ \
//       ____\_\  \ \_______\ \__\\ _\\ \__\ \__\ \_______\ \_______\ \__\\ \__\ \_______\ \_______\
//      |\_________\|_______|\|__|\|__|\|__|\|__|\|_______|\|_______|\|__| \|__|\|_______|\|_______|
//      \|_________|


