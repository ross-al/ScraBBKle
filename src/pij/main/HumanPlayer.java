package pij.main;

import java.util.*;

/**
 * A HumanPlayer is a Player than needs to provide move inputs
 * HumanPlayer always moves first
 */

public class HumanPlayer extends ScrabbklePlayer {
    /**
     * a tile rack for storing a player's tiles
     */
    private ArrayList<ScrabbkleTile> tileRack;

    /**
     * boolean to determine if player has had first move or not
     */
    private boolean firstMove = true;

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
    public HumanPlayer(ScrabbkleBoard board, ScrabbkleWordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
        tileRack = new ArrayList<ScrabbkleTile>();
        score = 0;
        skipCount = 0;
    }


    /**
     * Game engine for making a human player move
     */
    public void playMove() {
        String moveWord;
        String movePosition;
        String moveDirection;
        String finalWord;
        ArrayList<int[]> moveSquares;
        int tileCounter;
        boolean validMove = false;
        while (!validMove) {

            boolean validInputFormat = false;

            // Initial string for getting player's input
            String move = null;

            // Check if valid string format "WORD, cr, d" or ",," for skip
            while (!validInputFormat) {
                // Get player move input
                move = getPlayerMove();
                if (move.equals(",,")) {
                    validInputFormat = true;
                } else {
                    boolean isMatch = move.matches("[a-zA-Z]+,[a-z]\\d{1,2},[dr]+");
                    if (isMatch) {
                        validInputFormat = true;
                    }
                }
            }

            if (!isSkip(move)) {

                // Split player instructions into string array
                String[] instructions = interpretPlayerMove(move);
                moveWord = instructions[0];
                movePosition = instructions[1];
                moveDirection = instructions[2];

                // Check if tiles available for given word
                boolean tilesAvailable = hasAllTilesAvailable(moveWord, tileRack);

                // Calculate which squares the word will occupy (i.e. skip over occupied squares)
                moveSquares = calculateMoveSquares(moveWord, movePosition, moveDirection);

                // Check if tile positions are in bounds
                boolean inBounds = squaresAreInBounds(moveSquares);

                // If it is the human player's first move:
                if (firstMove) {

                    // Get the centre square for the board
                    int[] centreSquare = getBoard().getCentreSquare();

                    // Check if any of the tiles in the first word will be on the centre square
                    boolean tileOnCentreSquare = containsCentreSquare(moveSquares, centreSquare);

                    if (tilesAvailable && inBounds && tileOnCentreSquare) {
                        // Place tiles on board
                        // Set int row and col for each placed tile
                        // Set neighbouring tiles (above, below, right, left) for each placed tile
                        playWord(moveWord, movePosition, moveDirection, tileRack);
                        // Find the final word by iterating over linked tiles on board
                        finalWord = calculateFinalWord(movePosition, moveDirection);

                        // Then check if placed tiles are a valid word and meet placement rules:
                        // Check if word is valid
                        boolean isValidWord = isValidWord(finalWord);
                        if (isValidWord) {
                            // If a valid move then:
                            // Remove tiles from rack
                            removeTilesFromRack(moveWord, tileRack);

                            // Count how many tiles in move
                            tileCounter = moveWord.length();
                            // Calculate word score
                            int wordScore = calculateWordScore(movePosition, moveDirection, tileCounter);
                            score = score + wordScore;

                            // Print summary of player's move
                            printMoveSummary(moveWord, movePosition, moveDirection, "human player");

                            // Update tile connectsToExistingWord boolean flag
                            connectToWord(movePosition, moveDirection);

                            // Set validMove to true to break loop
                            // Set firstMove to false ready for next move
                            validMove = true;
                            firstMove = false;

                        }
                    } else { // Explain why move was rejected
                        if (!inBounds) {
                            System.out.println("This is not a valid move");
                            System.out.println("Error: Your word is out of bounds.");
                        }
                        if (!tilesAvailable) {
                            System.out.println("Error: You do not have the right tile(s).");
                        }
                        if (!tileOnCentreSquare) {
                            System.out.println("This is not a valid move");
                            String startSquare = getBoard().printCentreSquare(centreSquare);
                            System.out.println("Error: Your first move must contain the centre square (" + startSquare + ")");

                        }
                        System.out.println();
                    }

                } else { // If not the first move:

                    // Check the squares are free
                    boolean squaresAreOccupied = moveSquaresOccupied(moveSquares);

                    if (tilesAvailable && inBounds && !squaresAreOccupied) {
                        // Place tiles on board
                        // Set int row and col for each placed tile
                        // Set neighbouring tiles (above, below, right, left) for each placed tile
                        playWord(moveWord, movePosition, moveDirection, tileRack);

                        // Find the final word by iterating over linked tiles on board
                        finalWord = calculateFinalWord(movePosition, moveDirection);

                        // Then check if placed tiles are a valid word and meet placement rules:
                        // Check if word is valid
                        boolean isValidWord = isValidWord(finalWord);
                        // Check if placed word connects to a word on the board
                        boolean intersectsExistingWord = intersectsWord(moveSquares);
                        // Check if placed tiles form more than one word (not allowed in Scrabbkle)
                        boolean formsMultipleWords = formsMultipleWords(moveSquares);

                        // If player has available tiles, move is within bounds and squares are not already occupied
                        // then play word by placing tiles

                        if (isValidWord && intersectsExistingWord && !formsMultipleWords) {
                            // If a valid move then:
                            // Remove tiles from rack
                            removeTilesFromRack(moveWord, tileRack);

                            // Count how many tiles in move
                            tileCounter = moveWord.length();
                            // Calculate word score
                            int wordScore = calculateWordScore(movePosition, moveDirection, tileCounter);
                            score = score + wordScore;

                            // Print summary of player's move
                            printMoveSummary(moveWord, movePosition, moveDirection, "human player");

                            // Update tile connectsToExistingWord boolean flag
                            connectToWord(movePosition, moveDirection);

                            // Reduce skipCount

                            if (skipCount > 0) {
                                skipCount--;
                            }

                            // Set validMove to true to break the while loop;
                            validMove = true;


                            // If not valid word:
                        } else {

                            // Remove tiles from board
                            removeTilesFromBoard(moveSquares);

                            System.out.println("This is not a valid move");
                            if (!isValidWord) {
                                System.out.println("Error: Your word is not valid.");
                            }
                            if (formsMultipleWords) {
                                System.out.println("Error: You cannot create multiple words.");
                            }
                            if (!intersectsExistingWord) {
                                System.out.println("Error: Your word must connect to a word on the board.");
                            }
                            System.out.println();
                        }
                    } else { // Explain why move was rejected
                        if (!inBounds) {
                            System.out.println("This is not a valid move");
                            System.out.println("Error: Your word is out of bounds.");
                        }
                        if (!tilesAvailable) {
                            System.out.println("Error: You do not have the right tile(s).");
                        }
                        if (squaresAreOccupied) {
                            System.out.println("Error: Square(s) already occupied");
                        }
                        System.out.println();
                    }
                }
            } else { // If the move is skipped
                System.out.print("You skipped your move.");
                System.out.println();
                skipCount++;
                return;
            }
        }
    }

    // END OF HUMAN PLAYER GAME ENGINE

    // Helper methods for human player game engine:

    /**
     * Get player input for move
     */
    public String getPlayerMove() {
        System.out.println("Please enter your move with letter sequence, position, and");
        System.out.println("direction (d for down, r for right) separated by commas.");
        System.out.println("Entering just two commas passes.");
        System.out.println();
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Two commas skip the player's move
     *
     * @param word the move input string provided by player
     * @return boolean
     */
    public boolean isSkip(String word) {
        return word.equals(",,");
    }


    /**
     * Split player's move by "," to get individual instructions
     *
     * @param word the move input string provided by player
     * @return each move element in String[]
     */
    public String[] interpretPlayerMove(String word) {
        return word.split(",");
    }


    /**
     * Check if the centre square appears in any of the squares for a given move
     *
     * @param moveSquares  a list of all the square indexes for a given move
     * @param centreSquare the centre square for this board size
     * @return boolean
     */
    public boolean containsCentreSquare(ArrayList<int[]> moveSquares, int[] centreSquare) {
        boolean containsCentreSquare = false;
        for (int[] square : moveSquares) {
            if (Arrays.equals(square, centreSquare)) {
                containsCentreSquare = true;
                break;
            }
        }
        return containsCentreSquare;
    }


    /**
     * Pass params to super method to calculate score
     *
     * @param movePosition  col and row string input, e.g. h7
     * @param moveDirection direction to read word, e.g. down or right
     * @param tileCounter   number of tiles used in a move
     * @return word score for given move
     */
    public int calculateWordScore(String movePosition, String moveDirection, int tileCounter) {
        // Convert the 'cr' format provided into int for index positions
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        return super.calculateWordScore(moveDirection, row, col, tileCounter);
    }

    // Getters

    public int getSkipCount() {
        return skipCount;
    }

    public int getPlayerScore() {
        return score;
    }

    public ArrayList<ScrabbkleTile> getTileRack() {
        return tileRack;
    }

    // duplicate placeTile method of same signature is redundant but needed for testing humanPlayer
    // as cannot call methods in super in jUnit tests in HumanPlayerTest
    public void placeTile(ScrabbkleTile tile, int row, int col) {
        super.placeTile(tile, row, col);
    }


}






