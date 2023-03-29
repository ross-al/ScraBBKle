package pij.main;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Main launcher = new Main();
        launcher.launch();
    }

    public void launch() {

        // Create new empty board
        ScrabbkleBoard board = new ScrabbkleBoard();

        // Ask player for board type choice
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
        boolean validFile= false;
        while(!validFile){
            // Get input file to load selected board type
            inputFile = board.getInputFile(boardChoice);
            // Check if file exists
            boolean fileExists = board.inputFileExists(inputFile);
            if(fileExists){
                // Calculate board size
                board.calculateBoardSize(inputFile);
                if(board.isValidSize()){
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

        // Create wordlist based on provided wordlist
        ScrabbkleWordList wordList = new ScrabbkleWordList();

        // Create tileBag and fill bag with tiles
        ScrabbkleTileBag tileBag = new ScrabbkleTileBag();
        tileBag.fillTileBag();

        // Create one human player and one computer player
        HumanPlayer humanPlayer = new HumanPlayer(board, wordList, tileBag);
        ComputerPlayer computerPlayer = new ComputerPlayer(board, wordList, tileBag);

        // Start game
        boolean gameOver = false;

        // Fill player tile racks to enter gameplay loop
        humanPlayer.fillRack();
        computerPlayer.fillRack();

        // Print empty line for console readability
        System.out.println();

        // Continue loop until game over
        // Game continues until tileBag is empty, and one of the player racks is empty
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
                humanPlayer.fillRack();
                humanPlayer.printRack();

                ScrabbkleTile tile = humanPlayer.getTileRack().get(1);
                board.getBoard()[3][7].setTile(tile);


                // Get human player move input and make move
                humanPlayer.playMove();

                // Print scores and update board
                System.out.println();
                System.out.println("Human player score: " + humanPlayer.getPlayerScore());
                System.out.println("Computer player score: " + computerPlayer.getPlayerScore());
                System.out.println();
                board.printBoard();

                // End humanPlayer turn
                humanPlayer.isPlayerTurn = false;
                computerPlayer.isPlayerTurn = true;
            }
            while (computerPlayer.isPlayerTurn) {
                computerPlayer.fillRack();
                computerPlayer.playMove();

                // System.out.print("The move is: " + computerPlayer.getMoveWord());
                // System.out.print(" at position " + computerPlayer.getMovePosition() + ",");
                // System.out.print(" direction: "+computerPlayer.getMoveDirection());

                //print updated board after computer player move (????)
                //board.printBoard();

                System.out.println();
                System.out.println("Human player score: " + humanPlayer.getPlayerScore());
                System.out.println("Computer player score: " + computerPlayer.getPlayerScore());
                System.out.println();

                // End computerPlayer turn
                computerPlayer.isPlayerTurn = false;
                humanPlayer.isPlayerTurn = true;
            }

            if ((tileBag.isEmpty()) && (isHumanRackEmpty || isComputerRackEmpty)) {
                gameOver = true;
            }
        }

        // Calculate scores and declare winner

        int humanFinalScore = humanPlayer.getPlayerScore();
        int computerFinalScore = computerPlayer.getPlayerScore();
        System.out.println("Game Over!");
        System.out.println("The human player scored " + humanFinalScore + " points.");
        System.out.println("The computer player scored " + computerFinalScore + " points.");
        if (humanFinalScore > computerFinalScore) {
            System.out.println("The human player wins!");
        } else if (computerFinalScore > humanFinalScore) {
            System.out.println("The computer player wins!");
        } else {
            System.out.println("It's a draw!");
        }

    }
}

//   Thanks for playing! :)
//    ________  ________  ________  ________  ________  ________  ___  __    ___       _______
//   |\   ____\|\   ____\|\   __  \|\   __  \|\   __  \|\   __  \|\  \|\  \ |\  \     |\  ___ \
//   \ \  \___|\ \  \___|\ \  \|\  \ \  \|\  \ \  \|\ /\ \  \|\ /\ \  \/  /|\ \  \    \ \   __/|
//    \ \_____  \ \  \    \ \   _  _\ \   __  \ \   __  \ \   __  \ \   ___  \ \  \    \ \  \_|/__
//     \|____|\  \ \  \____\ \  \\  \\ \  \ \  \ \  \|\  \ \  \|\  \ \  \\ \  \ \  \____\ \  \_|\ \
//       ____\_\  \ \_______\ \__\\ _\\ \__\ \__\ \_______\ \_______\ \__\\ \__\ \_______\ \_______\
//      |\_________\|_______|\|__|\|__|\|__|\|__|\|_______|\|_______|\|__| \|__|\|_______|\|_______|
//      \|_________|


