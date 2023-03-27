package pij.main;

import java.io.File;
import java.util.Arrays;


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
        while(!validBoardChoice){
            boardChoice = board.getBoardChoice();
            if ((boardChoice.equals("d") || boardChoice.equals("l"))) {
                validBoardChoice = true;
            }
        }

        // Get input file to load selected board type
        File inputFile = board.getInputFile(boardChoice);

        // Check if file exists
        boolean fileExists = board.inputFileExists(inputFile);
        if(fileExists){
            // Calculate board size
            board.calculateBoardSize(inputFile);
        }
        // Check if input file exists and is valid size
        while(!board.inputFileExists(inputFile) || (!board.isValidSize())){
            System.out.println("This is not a valid file.");
            if(!board.inputFileExists(inputFile)){
                System.out.println("Error: Cannot find file.");
            }
            if(!board.isValidSize()){
                System.out.println("Error: File size not valid.");
            }
            inputFile = board.getInputFile(boardChoice);
        }

        // Load input file into board
        board.convertToBoard(inputFile);

        // Create wordlist based on provided wordlist
        ScrabbkleWordList myWordList = new ScrabbkleWordList();

        // Create tileBag and fill bag with tiles
        ScrabbkleTileBag tileBag = new ScrabbkleTileBag();
        tileBag.fillTileBag();

        // Create one human player and one computer player
        HumanPlayer humanPlayer = new HumanPlayer(board, myWordList,tileBag);
        ComputerPlayer computerPlayer = new ComputerPlayer(board, myWordList,tileBag);

        // Start game play
        boolean gameEnded = false;
        // Fill player tile racks to enter gameplay loop
        humanPlayer.fillRack();
        computerPlayer.fillRack();

        System.out.println();
        // Continue loop until game over
        // Game continues until tileBag is empty, and one of the player racks is empty
        while (!gameEnded){
            // Check if player racks are empty
            boolean isHumanRackEmpty = humanPlayer.getTileRack().isEmpty();
            boolean isComputerRackEmpty = computerPlayer.getTileRack().isEmpty();
            // Human player plays first
            humanPlayer.isPlayerTurn = true;
            computerPlayer.isPlayerTurn = false;
            while (humanPlayer.isPlayerTurn){
                // Print current board
                board.printBoard();
                // Refill player tile rack and print
                humanPlayer.fillRack();
                humanPlayer.printRack();
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
            while(computerPlayer.isPlayerTurn) {
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
            if ((tileBag.isEmpty()) && isHumanRackEmpty || isComputerRackEmpty) {
                gameEnded = true;
            }
        }

        // Calculate scores and declare winner
        int humanFinalScore = humanPlayer.getPlayerScore();
        int computerFinalScore = computerPlayer.getPlayerScore();
        System.out.println("Game Over!");
        System.out.println("The human player scored " + humanFinalScore +" points.");
        System.out.println("The computer player scored " + computerFinalScore +" points.");
        if (humanFinalScore > computerFinalScore){
            System.out.println("The human player wins!");
        } else if (computerFinalScore > humanFinalScore){
            System.out.println("The computer player wins!");
        } else {
            System.out.println("It's a draw!");
        }













 /*       ScrabbkleWord myWord = new ScrabbkleWord("apple"); //only prints string. need Tile input?
        System.out.println(myWord); //won't print type Word
        System.out.println(myWordList.isWord(myWord)); //won't print type Word
        System.out.println(myWordList.isWord("aa"));*/


        //System.out.println(myTile.toString());
        //Tile myTile2 = new Tile('d', 2);
        //System.out.println(myTile2.toString());



    /*    board.printBoard();
        System.out.println("Square 00: ");
        System.out.println(board.getMyBoard()[0][0].getPremiumLetterValue());
        System.out.println("Should be 3: ");
        System.out.println(board.getMyBoard()[1][1].getPremiumWordValue());

        System.out.println();
        System.out.println("input : Z");
        ScrabbkleTile myScrabbkleTile = new ScrabbkleTile('Z');
        System.out.println(myScrabbkleTile.getValue());
        board.getMyBoard()[1][1].setTile(myScrabbkleTile);
        System.out.println("expected : [Z10]");
        System.out.println(board.getMyBoard()[1][1].getPrintLabel());
        System.out.println("expected : Z");
        System.out.println(board.getMyBoard()[1][1].getTile(myScrabbkleTile).getLetter());
        System.out.println("expected : 10");
        System.out.println(board.getMyBoard()[1][1].getTile(myScrabbkleTile).getValue());
        ScrabbkleTile myScrabbkleTile2 = new ScrabbkleTile('O');
        board.getMyBoard()[2][1].setTile(myScrabbkleTile2);
        ScrabbkleTile myScrabbkleTile3 = new ScrabbkleTile('O');
        board.getMyBoard()[3][1].setTile(myScrabbkleTile3);

        board.printBoard();

        System.out.println("bag is empty: " + tileBag.isEmpty());
        System.out.println("bag contains: " + tileBag.getSize() + " tiles");*/



        }

}