package pij.main;

import java.util.ArrayList;

/**
 * A ComputerPlayer is a Player that has automated moves
 */


public class ComputerPlayer extends ScrabbklePlayer {
    /**
     * a tile rack for storing a player's tiles
     */
    private ArrayList<ScrabbkleTile> tileRack;

    /**
     * a player's score
     */
    private int score;

    /**
     * a player's skip count, must not go below 0
     */
    private int skipCount;

    /**
     * constructor method
     *
     * @param board    the board for the game
     * @param wordList the dictionary used to check if word is valid
     * @param tileBag  the bag from which players draw tiles
     */
    public ComputerPlayer(ScrabbkleBoard board, ScrabbkleWordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
        tileRack = super.getTileRack();
        score = 0;
        skipCount = 0;
    }



    /**
     * The game engine for computer moves
     */
    public void playMove() {

        // Both booleans set to true for testing
        // Computer player will always skip during testing

        boolean isSkip = true;
        boolean validMove = true;

        // Check all tiles going right
        String moveDirection = "r";
        String movePosition; //i.e. 'h7'
        ArrayList<int[]> moveSquares;

        // TOO SLOW!
        // Need faster logic:

        // Dumb computer
        // Keep a record of every occupied square in an arrayList<int[]> of positions
        // Then iterate through every occupied square on board
        // Iterate through every combination of tiles in rack (ignore wildcards for now)
        // and check if the added tiles make a valid word
        // Place tile if so and end move
        // Else skip move

        for (ScrabbkleTile tile : tileRack) {
            while (!validMove) {
                for (int i = 1; i < getBoard().getBoardSize(); i++) {
                    for (int j = 1; j < getBoard().getBoardSize(); j++) {
                        int row = i;
                        int col = j;

                        // Use the tile's letter as the moveWord string
                        String moveWord = String.valueOf(tile.getLetter());

                        // Convert positions into string to use with existing methods
                        movePosition = convertRowAndColToString(row, col);

                        // Calculate which squares the word will occupy (i.e. skip over occupied squares)
                        moveSquares = calculateMoveSquares(moveWord, movePosition, moveDirection);

                        // Check if tile positions are in bounds
                        boolean inBounds = squaresAreInBounds(moveSquares);
                        if (getBoard().getBoard()[row][col].getTile() == null) {
                            if (inBounds) {

                                playWord(moveWord, movePosition, moveDirection);
                                String finalWord = calculateFinalWord(movePosition, moveDirection);

                                boolean isValidWord = isValidWord(finalWord);

                                if (isValidWord) {

                                    // If a valid move then:
                                    // Remove tiles from rack
                                    removeTilesFromRack(moveWord);

                                    // Count how many tiles in move
                                    int tileCounter = moveWord.length();
                                    // Calculate word score
                                    int wordScore = calculateWordScore(movePosition, row, col, tileCounter);
                                    score = score + wordScore;

                                    // Print summary of player's move
                                    printMoveSummary(moveWord, movePosition, moveDirection);

                                    // Update tile connectsToExistingWord boolean flag
                                    connectToWord(movePosition, moveDirection);

                                    // Set validMove to true to break loop
                                    validMove = true;
                                } else {
                                    // If not a valid move...
                                    // Remove tiles from board
                                    removeTilesFromBoard(moveSquares);
                                }
                                if (validMove) {
                                    // If successful move, keep isSkip as false, else true to tell humanPlayer move was skipped
                                    isSkip = false;
                                }
                            }
                        }
                    }
                }
            }
        }

        // Check all tiles going down
        // moveDirection = "d";


        if (!isSkip) {
            if (skipCount > 0) {
                skipCount--;
            }
        }

        if (isSkip) {
            System.out.println();
            System.out.println("Computer player skipped their turn");
            System.out.println();
            skipCount++;
        }
    }

    // Helper methods for computer engine

    /**
     * Calculate movePosition (e.g. 'h7') based on given int row and int col
     *
     * @param row index for row in board
     * @param col index for column in board
     * @return String concat value of col and row, e.g. h7
     */
    public String convertRowAndColToString(int row, int col) {
        char c = getColumnLetter(col);
        String r = String.valueOf(Integer.valueOf(row));
        return c + r;
    }

    /**
     * Convert the int provided into a letter for board index position
     *
     * @param col int value to be converted to char
     * @return char value of int
     */
    public char getColumnLetter(int col) {
        return (char) ('a' + col - 1);
    }


    // Getters

    public int getSkipCount() {
        return skipCount;
    }

    public int getPlayerScore() {
        return score;
    }
}
