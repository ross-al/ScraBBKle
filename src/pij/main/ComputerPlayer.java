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

        // Go through each eligibleSquare with each of the letter combinations from the list
        outerloop:
        for (int[] eligibleSquare : eligibleSquares) {
            int row = eligibleSquare[0];
            int col = eligibleSquare[1];
            for (String moveWord : possibleWords) {

                // Check all words going down

                String moveDirection = "d";

                // Convert positions into string to use with existing methods (should be refactored with method overloading for int row and int col)
                String movePosition = getBoard().convertRowAndColToString(row, col);

                // Calculate which squares the word will occupy (i.e. skip over occupied squares)
                ArrayList<int[]> moveSquares = calculateMoveSquares(moveWord, movePosition, moveDirection);

                // Check if tile positions are in bounds
                boolean inBounds = squaresAreInBounds(moveSquares);
                if (getBoard().getBoard()[row][col].getTile() == null) {
                    if (inBounds) {

                        playWord(moveWord, movePosition, moveDirection, tileRack);
                        String finalWord = calculateFinalWord(movePosition, moveDirection);

                        boolean isValidWord = isValidWord(finalWord);

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
                            System.out.println("TESTING IF COMP MADE MOVE AND FAILED");
                            break;

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
            // Calculate word score
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

            // tell humanPlayer that computer player skipped move
            System.out.println();
            System.out.println("Computer player skipped their turn");
            System.out.println();
            skipCount++;
        }
    }



/*    // Check all words going right
    moveDirection = "r";
    // If valid move, then break*/





    // Get a list of all the occupied squares (i.e. squares that have tiles in a valid word on the board)
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
    public String tileRackToString() {
        StringBuilder tileLettersAsString = new StringBuilder();
        for (ScrabbkleTile tile : tileRack) {
            if (!tile.isWildCard()) {
                tileLettersAsString.append(tile.getLetter());
            }
        }
        return tileLettersAsString.toString();
    }



    // Then calculate all the combinations of letters for the tiles in the rack
    // We exclude wildcards for now (later logic, we should iterate through each letter and assign to wildCard)
    public List<String> generateLetterCombinations(String allLetters) {
        List<String> combinations = new ArrayList<>();
        generateLetterCombinationsHelper(allLetters, "", combinations);
        return combinations.stream().distinct().collect(Collectors.toList());
    }

    // Recursive letter combination generator
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

    public ArrayList<ScrabbkleTile> getTileRack(){
        return tileRack;
    }

}






