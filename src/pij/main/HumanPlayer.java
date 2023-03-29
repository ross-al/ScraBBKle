package pij.main;

import org.jetbrains.annotations.VisibleForTesting;

import java.util.*;

public class HumanPlayer extends ScrabbklePlayer {
    private String moveWord;
    private String movePosition;
    private String moveDirection;
    private String finalWord;
    private ArrayList<Character> charsInWord;
    private ArrayList<int[]> moveSquares;
    private ArrayList<ScrabbkleTile> tileRack;
    //private String errorReason;
    private boolean firstMove = true;

    public HumanPlayer(ScrabbkleBoard board, ScrabbkleWordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
        tileRack = super.getTileRack();
    }

    //NOT WORKING!
    public void playMove() {
        boolean validMove = false;
        while (!validMove) {

            // Remove all inputs from previous move
            if (charsInWord != null) {
                charsInWord.clear();
            }
            if (moveSquares != null){
                moveSquares.clear();
            }

            // Get player move input
            String move = getPlayerMove(); //need to check if valid string format "WORD, cr, d"

            if (!isSkip(move)) {

                // Split player instructions into string array
                String[] instructions = interpretPlayerMove(move);
                moveWord = instructions[0];
                movePosition = instructions[1];
                moveDirection = instructions[2];

                // Split each letter in word into character
                splitWordToChar(moveWord);
                // Calculate the player's intended word on board to check if in dictionary
                calculateFinalWord(moveWord, movePosition, moveDirection);
                // Calculate which squares the word will occupy
                calculateMoveSquares(finalWord, movePosition, moveDirection);

                // Check if human player's first move

                //VALID MOVE IS ALWAYS TRUE FOR TESTING
                if (isFirstMove()) {
                    // If humanPlayer's first move, centreSquare must be present in moveSquares
                    int[] centreSquare = getBoard().getCentreSquare();
                    if (containsCentreSquare(moveSquares, centreSquare)) {
                        // Check if word is valid and player has enough tiles
                        if (isValidWord(finalWord) && hasAllTilesAvailable(charsInWord, tileRack)) {
                            validMove = true;
                            firstMove = false;
                        }
                    }
                    // If it is not the first move:
                } else {
                    // Check all other inputs return true
                    if (isValidMove()) {
                        validMove = true;
                    }
                }
                // Tell player why move is not valid
                if(!validMove) {
                    System.out.println("This is not a valid move");
                    if (!isValidWord(finalWord)) {
                        System.out.println("Error: Word is not valid.");
                    }
                    if (!hasAllTilesAvailable(charsInWord, tileRack)) {
                        System.out.println("Error: Insufficient tiles.");
                    }
                    if (!isValidDirection(moveSquares, moveDirection)) {
                        System.out.println("Error: Position of word is not valid.");
                    }
                    if (!super.intersectsWord(moveSquares)) {
                        System.out.println("Error: Word not connected to other words.");
                    }
                    System.out.println();
                } else{

                    // Place tiles on board
                    playWord(moveWord, movePosition, moveDirection);

                    // Calculate score

                    // Print summary of player's move
                    printMoveSummary();

                    //playWord(charsInWord, moveSquares);
                    //rest of code here?????
                    //place tiles on board

                }
            } else {
                // Skip move
                System.out.print("You skipped your move.");
                System.out.println();
                return;
            }
        }
    }



    //check if had wildcard (should be in super?, or limit wildcard to human)

    //place word on board
    //need to remove tile from rack?
    //remove it during move and pass as parameter to placeTile(tile, col, row)


    //print word on  board //(should be in super)
    //display board
    //return to end move



    // Get player input for move
    public String getPlayerMove() {
        System.out.println("Please enter your move with letter sequence, position, and");
        System.out.println("direction (d for down, r for right) separated by commas.");
        System.out.println("Entering just two commas passes.");
        System.out.println();
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    // Two commas skip the player's move
    public boolean isSkip(String word) {
        return word.equals(",,");
    }

    // Split player's move by "," to get individual instructions
    public String[] interpretPlayerMove(String word) {
        return word.split(",");
    }

    // Split given word into individual characters and put in arrayList
    public void splitWordToChar(String moveWord) {
        charsInWord = new ArrayList<>();
        for (int i = 0; i < moveWord.length(); i++) {
            char c = moveWord.charAt(i);
            charsInWord.add(c);
        }
    }


    // Calculate the player's intended word on board to check if in dictionary
    public void calculateFinalWord(String moveWord, String movePosition, String moveDirection){
        // Convert the 'cr' format provided into int for index positions
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        // Create empty string for final word
        finalWord = "";
        char c;
        if(moveDirection.equals("d")){
            // If tile already occupied in start position, take character from tile
            // and add to finalWord string.
            // If not already occupied, using the character provided by player
            for(int i = 0; i < moveWord.length(); i++){
                if( getBoard().getBoard()[row][col].getTile() != null ){
                    String tileLetter = String.valueOf(getBoard().getBoard()[row][col].getTile().getLetter());
                    finalWord = finalWord + tileLetter;
                    row = row +1;
                    i--;
            }
                else {
                    c = moveWord.charAt(i);
                    finalWord = finalWord + c;
                    row = row + 1;
                }
            }
        } else {
            // If move is right
            for(int i = 0; i < moveWord.length(); i++){
                if( getBoard().getBoard()[row][col].getTile() != null ){
                    String tileLetter = String.valueOf(getBoard().getBoard()[row][col].getTile().getLetter());
                    finalWord = finalWord + tileLetter;
                    col = col +1;
                    i--;
                }
                else {
                    c = moveWord.charAt(i);
                    finalWord = finalWord + c;
                    col = col + 1;
                }
            }
        }
    }

    // Get all the positions for the squares in the move, including squares which
    // already have placed tiles
    public void calculateMoveSquares(String finalWord, String movePosition, String moveDirection) {
        //create ArrayList of int[] to store row and column positions for each tile on board
        int wordLength = finalWord.length();
        moveSquares = new ArrayList<int[]>();
        //add movePosition in first 2 elements
        int startCol = getPositionColumn(movePosition);
        int startRow = getPositionRow(movePosition);
        if (moveDirection.equals("d")) {
            for (int i = 0; i < wordLength; i++) {
                int[] positions = {startRow + i, startCol};
                moveSquares.add(positions);
            }
        } else {
            for (int i = 0; i < wordLength; i++) {
                int[] positions = {startRow, startCol + i};
                moveSquares.add(positions);
            }
        }
    }

    // Check if all the conditions for a valid move are met
    public boolean isValidMove() {
        /*(isValidWord(finalWord)
                && hasAllTilesAvailable(charsInWord, tileRack)
                && isValidDirection(moveSquares, moveDirection)
                && super.intersectsWord(moveSquares));*/
        return true; //always true for testing
    }

    // Check if the centre square appears in any of the squares for a given move
    public boolean containsCentreSquare(ArrayList<int[]> moveSquares, int[] centreSquare){
        boolean containsCentreSquare = false;
        for (int[] square : moveSquares) {
            if (Arrays.equals(square, centreSquare)) {
                containsCentreSquare = true;
                break;
            }
        }
       // return containsCentreSquare;
        return true; //for testing
    }


    // Check if word is in provided dictionary
    public boolean isValidWord(String word) {

        //return super.getWordList().isWord(word);
        return true; //for testing
    }

    // Check if it is the human player's first move
    public boolean isFirstMove() {
        //return (firstMove);
        return true; //for testing
    }



    // Check if the player's rack has all the necessary tiles, including duplicates
    public boolean hasAllTilesAvailable(ArrayList<Character> charsInWord, ArrayList<ScrabbkleTile> tileRack) {
        Map<Character, Integer> charCounts = new HashMap<>();
        // Count the number of times each character appears in the word
        for (char c : charsInWord) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }
        // Check if there are enough tiles for each character
        for (char c : charCounts.keySet()) {
            int requiredCount = charCounts.get(c);
            int tileRackCount = 0;
            // Count the number of tiles in the tile rack
            for (ScrabbkleTile tile : tileRack) {
                if (tile.getLetter() == c) {
                    tileRackCount++;
                }
            }
            if (tileRackCount != requiredCount) {
                return true; //for testing
               // return false;

            }
        }
        return true;
    }

    // Get print format for move direction for move summary in console
    public String getMoveDirectionPrintFormat() {
        if (moveDirection.equals("d")) {
            return "down";
        } else {
            return "right";
        }
    }

    // Convert the 'column' letter provided into int for index position
    public int getPositionColumn(String movePosition) {
        char position = movePosition.charAt(0);
        int col = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if (position == c) {
                col = c - 'a' + 1;
            }
        }
        return col;
    }

    // Convert the row number provided from string to int for index position
    public int getPositionRow(String movePosition) {
        String str = movePosition.substring(1);
        return Integer.parseInt(str);
    }


    // Check if given move direction is possible on the existing board
    // Move is possible if word is in bounds of board, and has no parallel words
    public boolean isValidDirection(ArrayList<int[]> moveSquares, String moveDirection) {
        // return (super.squaresAreInBounds(moveSquares)) && !(super.hasAdjacentWords(moveSquares, moveDirection));
        return true; //for testing
    }

    // Get a list of all the squares in the player's move
    public ArrayList<int[]> getMoveSquares(){
        return moveSquares;
    }

    // Print move summary, scores and updated board
    public void printMoveSummary(){
        System.out.print("The move is: " + moveWord);
        System.out.print(" at position " + movePosition + ",");
        System.out.print(" direction: " + getMoveDirectionPrintFormat());
    }


    // Find tiles in rack and place on board
    public void playWord(String moveWord, String movePosition, String moveDirection){
        // Convert the 'cr' format provided into int for index positions
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        char c;
        // If move direction is down, find the next free row position to place tile
        if(moveDirection.equals("d")){
            for(int i = 0; i < moveWord.length(); i++){
                if( getBoard().getBoard()[row][col].getTile() != null ){
                    row++;
                    i--;
                }
                else {
                    c = moveWord.charAt(i);
                    ScrabbkleTile tile = getTileFromRack(c);
                    placeTile(tile, row, col);
                    tileRack.remove(tile);
                    row++;
                }
            }
        } else {
            // // If move direction is right, find the next free col position to place tile
            for(int i = 0; i < moveWord.length(); i++){
                if( getBoard().getBoard()[row][col].getTile() != null ){
                    col++;
                    i--;
                }
                else {
                    c = moveWord.charAt(i);
                    ScrabbkleTile tile = getTileFromRack(c);
                    placeTile(tile, row, col);
                    tileRack.remove(tile);
                    col++;
                }
            }
        }
    }

    // NEED TO WORK OUT WILDCARDS! can use empty character?
    public ScrabbkleTile getTileFromRack(char c) {
        ScrabbkleTile tile = null;
        for (ScrabbkleTile tileInRack : tileRack) {
            if (tileInRack.getLetter() == c) {
                tile = tileInRack;
            }
        }
        return tile;
    }



    // Used for testing only
    @VisibleForTesting
    protected ArrayList<Character> getCharsInWord() {
        return charsInWord;
    }

    // Used for testing only
    @VisibleForTesting
    protected String getFinalWord() {
        return finalWord;
    }
}

    //use wildCard()
    //get string input


