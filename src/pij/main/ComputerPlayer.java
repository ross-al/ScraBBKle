package pij.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        tileRack = new ArrayList<ScrabbkleTile>();
        score = 0;
        skipCount = 0;
    }


    /**
     * The game engine for computer moves
     */
    public void playMove() {

        boolean isSkip = false;
        boolean validMove = false;

        // Create a new arraylist to store row col positions for eligible squares.
        // Eligible squares are free squares that must have either a non-null aboveTile, belowTile, leftTile or rightTile,
        // so they can connect to existing valid words on the board.

        ArrayList<int[]> eligibleSquares = findEligibleSquares();

        // Add all the non-null characters in the tile rack into one string

        String allLetters = tileRackToString();

        // Calculate all possible combinations of letters in this string and add to list

        List<String> possibleWords = generateLetterCombinations(allLetters);

        // Create 'winningXYZ' variables to remember which move was used

        String winningWord = null;
        String winningPosition = null;
        String winningDirection = null;
        int winningRow = 0;
        int winningCol = 0;

        // Loop through all possible tile placements

        outerloop:
        for (int[] eligibleSquare : eligibleSquares) {
            int row = eligibleSquare[0];
            int col = eligibleSquare[1];
            for (String moveWord : possibleWords) {

                // Convert positions into string to use with existing methods (to fix: should be refactored with method overloading for int row and int col)
                String movePosition = getBoard().convertRowAndColToString(row, col);

                // Check all words going down

                String moveDirection = "d";

                // Calculate which squares the word will occupy (i.e. skip over occupied squares)
                ArrayList<int[]> moveSquares = calculateMoveSquares(moveWord, movePosition, moveDirection);

                // Check if tile positions are in bounds
                boolean inBounds = squaresAreInBounds(moveSquares);
                if (getBoard().getBoard()[row][col].getTile() == null) {
                    if (inBounds) {

                        // Play the word on the board
                        playWord(moveWord, movePosition, moveDirection, tileRack);

                        // Calculate the final word
                        String finalWord = calculateFinalWord(movePosition, moveDirection);

                        boolean isValidWord = isValidWord(finalWord);

                        // If the word is valid, break the outer loop
                        if (isValidWord) {
                            winningWord = moveWord;
                            winningPosition = movePosition;
                            winningDirection = moveDirection;
                            winningRow = row;
                            winningCol = col;
                            validMove = true;
                            break outerloop;

                        } else {

                            // If not valid, remove the tiles from the board
                            removeTilesFromBoard(moveSquares);

                            // Then check going right  // to fix: should remove duplicate code and create helper methods

                            moveDirection = "r";
                            moveSquares = calculateMoveSquares(moveWord, movePosition, moveDirection);
                            inBounds = squaresAreInBounds(moveSquares);
                            if (getBoard().getBoard()[row][col].getTile() == null) {
                                if (inBounds) {

                                    playWord(moveWord, movePosition, moveDirection, tileRack);
                                    finalWord = calculateFinalWord(movePosition, moveDirection);

                                    isValidWord = isValidWord(finalWord);

                                    if (isValidWord) {
                                        winningWord = moveWord;
                                        winningPosition = movePosition;
                                        winningDirection = moveDirection;
                                        winningRow = row;
                                        winningCol = col;
                                        validMove = true;
                                        break outerloop;
                                    } else {
                                        removeTilesFromBoard(moveSquares);

                                        // If can't play going right, break this loop and try the next tile combination
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (validMove) {

            // If a valid move then:
            // Remove tiles from rack
            removeTilesFromRack(winningWord, tileRack);

            // Count how many tiles in move
            int tileCounter = winningWord.length();
            // Calculate word score and add to player current score
            int wordScore = calculateWordScore(winningPosition, winningRow, winningCol, tileCounter);
            score = score + wordScore;

            // Print summary of player's move
            printMoveSummary(winningWord, winningPosition, winningDirection);

            // Update tile connectsToExistingWord boolean flag
            connectToWord(winningPosition, winningDirection);

            // If successful move, reduce skip count if above 0
            if (skipCount > 0) {
                skipCount--;
            }
        } else {

            // Tell humanPlayer that computer player skipped move
            System.out.println();
            System.out.println();
            System.out.println("The computer player skipped their turn");
            System.out.println();
            // Increase skip count by 1
            skipCount++;
        }

    }

    // END OF COMPUTER PLAYER GAME ENGINE

    /**
     * Get a list of all the occupied squares (i.e. squares that have tiles in a valid word on the board)
     *
     * @return a list of all eligible squares that the computer player can try out
     */
    public ArrayList<int[]> findEligibleSquares() {
        ArrayList<int[]> possibleSquares = new ArrayList<>();
        ArrayList<int[]> occupiedSquares = getBoard().getOccupiedSquares();
        for (int[] square : occupiedSquares) {
            int row = square[0];
            int col = square[1];
            // Then check if they have free neighbours and are in bounds.
            // If so, check if possibleSquares already contains these positions
            // If not, add to possibleSquares
            ScrabbkleTile currentTile = getBoard().getBoard()[row][col].getTile();
            if (positionIsInBounds(row - 1, col)) {
                if (currentTile.getAboveTile() == null) {
                    int[] positions = {row - 1, col};
                    if (!possibleSquares.contains(positions)) {
                        possibleSquares.add(positions);
                    }
                }
            }
            //Repeat for below, left and right neighbours
            if (positionIsInBounds(row + 1, col)) {
                if (currentTile.getBelowTile() == null) {
                    int[] positions = {row + 1, col};
                    if (!possibleSquares.contains(positions)) {
                        possibleSquares.add(positions);
                    }
                }
            }
            if (positionIsInBounds(row, col - 1)) {
                if (currentTile.getLeftTile() == null) {
                    int[] positions = {row, col - 1};
                    if (!possibleSquares.contains(positions)) {
                        possibleSquares.add(positions);
                    }
                }
            }
            if (positionIsInBounds(row, col + 1)) {
                if (currentTile.getRightTile() == null) {
                    int[] positions = {row, col + 1};
                    if (!possibleSquares.contains(positions)) {
                        possibleSquares.add(positions);
                    }
                }
            }
        }
        return possibleSquares;
    }


    // Add all non-wildCard letters to a string

    /**
     * Convert all tiles in the current rack into a string
     * Excluding wildCards
     * @return string of concatenated tile characters
     */
    public String tileRackToString() {
        StringBuilder tileLettersAsString = new StringBuilder();
        for (ScrabbkleTile tile : tileRack) {
            if (!tile.isWildCard()) {
                // excludes wildCards for now
                tileLettersAsString.append(tile.getLetter());
            }
        }
        return tileLettersAsString.toString();
    }





    /**
     * Calculate all the combinations of letters for the tiles in the rack
     * Uses recursion to find all letter combinations for given string
     * @param allLetters a string of characters
     * @return a list of all possible character combinations
     */

    //We exclude wildcards for now (later logic, we should iterate through each letter and assign to wildCard)
    public List<String> generateLetterCombinations(String allLetters) {
        List<String> combinations = new ArrayList<>();
        generateLetterCombinationsHelper(allLetters, "", combinations);
        return combinations.stream().distinct().collect(Collectors.toList());
    }

    // Recursive letter combination generator/helper
    public void generateLetterCombinationsHelper(String remaining, String current, List<String> combinations) {
        // Add the current combination to the list
        if (!current.isEmpty()) {
            combinations.add(current);
        }
        // If there are no characters remaining, return
        if (remaining.isEmpty()) {
            return;
        }
        // Recursively generate combinations with and without the next character
        generateLetterCombinationsHelper(remaining.substring(1), current + remaining.charAt(0), combinations);
        generateLetterCombinationsHelper(remaining.substring(1), current, combinations);
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

}






