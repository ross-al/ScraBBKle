package pij.main;

import org.jetbrains.annotations.VisibleForTesting;

import java.util.*;

public class HumanPlayer extends ScrabbklePlayer {
    /*    private String moveWord;
        private String movePosition;
        private String moveDirection;
        private String finalWord;
        private ArrayList<Character> charsInWord;
        private ArrayList<int[]> moveSquares;*/
    private ArrayList<ScrabbkleTile> tileRack;
    private boolean firstMove = true;
    private int score;

    public HumanPlayer(ScrabbkleBoard board, ScrabbkleWordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
        tileRack = super.getTileRack();
        score = super.getPlayerScore();
    }

    // Game engine for making a human player move
    public void playMove() {
        String moveWord;
        String movePosition;
        String moveDirection;
        String finalWord = "";
        ArrayList<Character> charsInWord;
        ArrayList<int[]> moveSquares;
        int tileCounter;
        boolean validMove = false;
        while (!validMove) {

            // Get player move input
            String move = getPlayerMove();

            // Check if valid string format "WORD, cr, d" or ",,"
            //(isValidMoveFormat(String move)

            if (!isSkip(move)) {

                // Split player instructions into string array
                String[] instructions = interpretPlayerMove(move);
                moveWord = instructions[0];
                movePosition = instructions[1];
                moveDirection = instructions[2];

                // Split each letter in word into character
                charsInWord = splitWordToChar(moveWord);

                // Check if tiles available for given word
                boolean tilesAvailable = hasAllTilesAvailable(charsInWord, tileRack);

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
                        playWord(moveWord, movePosition, moveDirection);
                        // Find the final word by iterating over linked tiles on board
                        finalWord = calculateFinalWord(movePosition, moveDirection);

                        // Then check if placed tiles are a valid word and meet placement rules:
                        // Check if word is valid
                        boolean isValidWord = isValidWord(finalWord);
                        if (isValidWord) {
                            // If a valid move then:
                            // Remove tiles from rack
                            removeTilesFromRack(moveWord);

                            // Calculate word score
                            // Calculate premium scores
                            // Calculate 7 tile bonus

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
                        System.out.println();
                    }

                } else { // If not the first move:

                    // Check the squares are free
                    boolean squaresAreOccupied = moveSquaresOccupied(moveSquares);

                    if (tilesAvailable && inBounds && !squaresAreOccupied) {
                        // Place tiles on board
                        // Set int row and col for each placed tile
                        // Set neighbouring tiles (above, below, right, left) for each placed tile
                        playWord(moveWord, movePosition, moveDirection);

                        // Find the final word by iterating over linked tiles on board
                        finalWord = calculateFinalWord(movePosition, moveDirection);

                        // Then check if placed tiles are a valid word and meet placement rules:
                        // Check if word is valid
                        boolean isValidWord = isValidWord(finalWord);
                        // Check if placed word connects to a word on the board
                        boolean intersectsExistingWord = intersectsWord(moveSquares);
                        // Check if placed tiles form more than one word (not allowed in Scrabbkle)
                        boolean formsMultipleWords = formsMultipleWords(moveSquares, moveDirection);

                        // If player has available tiles, move is within bounds and squares are not already occupied
                        // then play word by placing tiles

                        if (isValidWord && intersectsExistingWord && !formsMultipleWords) {
                            // If a valid move then:
                            // Remove tiles from rack
                            removeTilesFromRack(moveWord);

                            //ADD THIS TO FIRSTMOVE AS WELL!

                            // Count how many tiles in move
                            tileCounter = moveWord.length();
                            // Calculate word score
                            score += this.calculateWordScore(movePosition, moveDirection, tileCounter);

                            // Calculate premium scores

                            // Calculate 7 tile bonus

                            // Print summary of player's move
                            printMoveSummary(moveWord, movePosition, moveDirection);

                            // Set validMove to true to break the while loop;
                            validMove = true;


                            // If not valid word:
                        } else {

                            // Remove tiles from board
                            removeTilesFromBoard(moveSquares);

                            // Test if:
                            // Reset tile int row col values (CAN'T REMEMBER WHAT THIS IS FOR!!!!???)
                            // Reset neighbouring tiles?
                            // Reset Square print labels (as we removed Tile from board)

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
                return;
            }
        }
    }


/*                // Check if human player's first move

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
    }*/


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
    public ArrayList<Character> splitWordToChar(String moveWord) {
        ArrayList<Character> charsInWord = new ArrayList<>();
        for (int i = 0; i < moveWord.length(); i++) {
            char c = moveWord.charAt(i);
            charsInWord.add(c);
        }
        return charsInWord;
    }


    // Calculate the player's intended word on board to check if in dictionary
    public String calculateFinalWord(String movePosition, String moveDirection) {
        // Convert the 'cr' format provided into int for index positions
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        // Create empty string for final word
        String finalWord = "";
        char c;
        // Create square to track position in linked list
        ScrabbkleTile currentTile;
        // If word direction is down
        if (moveDirection.equals("d")) {
            // Find the left-most tile in the word on the board
            // First find if there is a tile to the left of player's position and find left-most tile if so
            // This will be the beginning of the word
            currentTile = getBoard().getBoard()[row][col].getTile();
            while (currentTile.getAboveTile() != null) {
                currentTile = currentTile.getAboveTile();
            }
            // add the tiles to the right of the currentTile to finalWord
            while (currentTile != null && currentTile.getBelowTile() != null && currentTile != currentTile.getBelowTile()) {
                finalWord += currentTile.getLetter(); // append the letter to the finalWord string
                currentTile = currentTile.getBelowTile(); // move to the next tile to the right
            }
            // add the currentTile to finalWord
            finalWord += currentTile.getLetter();
            // add the tiles to the right of the currentTile to finalWord
            currentTile = currentTile.getBelowTile();
            while (currentTile != null) {
                finalWord += currentTile.getLetter(); // append the letter to the finalWord string
                currentTile = currentTile.getBelowTile(); // move to the next tile to the right
            }

            // if word direction is right
        } else{
                // Find the left-most tile in the word on the board
                // First find if there is a tile to the left of player's position and find left-most tile if so
                // This will be the beginning of the word
                currentTile = getBoard().getBoard()[row][col].getTile();
                while (currentTile.getLeftTile() != null) {
                    currentTile = currentTile.getLeftTile();
                }
                // add the tiles to the right of the currentTile to finalWord
                while (currentTile != null && currentTile.getRightTile() != null && currentTile != currentTile.getRightTile()) {
                    finalWord += currentTile.getLetter(); // append the letter to the finalWord string
                    currentTile = currentTile.getRightTile(); // move to the next tile to the right
                }
                // add the currentTile to finalWord
                finalWord += currentTile.getLetter();
                // add the tiles to the right of the currentTile to finalWord
                currentTile = currentTile.getRightTile();
                while (currentTile != null) {
                    finalWord += currentTile.getLetter(); // append the letter to the finalWord string
                    currentTile = currentTile.getRightTile(); // move to the next tile to the right
                }
            }
        return finalWord;
    }




        // Get the positions for the squares in the move
    public ArrayList<int[]> calculateMoveSquares(String moveWord, String movePosition, String moveDirection){
        // Create ArrayList of int[] to store row and column positions for each tile in move
        ArrayList<int[]> moveSquares = new ArrayList<int[]>();
        // Get position for first tile
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        // If move is down
        if (moveDirection.equals("d")) {
            for (int i = 0; i < moveWord.length(); i++) {
                if (getBoard().getBoard()[row][col].getTile() != null) {
                    row++;
                    i--;
                } else {
                    int[] positions = {row, col};
                    moveSquares.add(positions);
                    row++;
                }
            }
        }
        // If move is right
        for(int i = 0; i < moveWord.length(); i++){
            if( getBoard().getBoard()[row][col].getTile() != null ) {
                col++;
                i--;
            }
            else {
                int[] positions = {row, col};
                moveSquares.add(positions);
                col++;
            }

        }
        return moveSquares;
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


    // Place tiles for given word on board
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
                    if(!tileRack.isEmpty()) {
                        ScrabbkleTile tile = getTileFromRack(c);
                        placeTile(tile, row, col);
                        row++;
                    }
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
                    if(!tileRack.isEmpty()) {
                        c = moveWord.charAt(i);
                        ScrabbkleTile tile = getTileFromRack(c);
                        placeTile(tile, row, col);
                        col++;
                    }
                }
            }
        }
    }


    // Check if it is the human player's first move
    public boolean isFirstMove() {
        return (firstMove);
        //return true; //for testing
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
        return containsCentreSquare;
        //return true; //for testing
    }


    // Get print format for move direction for move summary in console
    public String getMoveDirectionPrintFormat(String moveDirection) {
        if (moveDirection.equals("d")) {
            return "down";
        } else {
            return "right";
        }
    }


    // Print move summary, scores and updated board
    public void printMoveSummary(String moveWord, String movePosition, String moveDirection){
        System.out.print("The move is: " + moveWord);
        System.out.print(" at position " + movePosition + ",");
        System.out.print(" direction: " + getMoveDirectionPrintFormat(moveDirection));
    }


    public int calculateWordScore(String movePosition, String moveDirection, int tileCounter){
        // Convert the 'cr' format provided into int for index positions
        int wordScore = 0;
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        wordScore = super.calculateWordScore(moveDirection, row, col, tileCounter);
        return wordScore;
    }

    // duplicate placeTile method of same signature is redundant but needed for testing humanPlayer
    // as cannot call methods in super in jUnit tests in HumanPlayerTest
    @VisibleForTesting
    public void placeTile(ScrabbkleTile tile, int row, int col) {
        super.placeTile(tile,row,col);
    }




    //DELETE OR KEEP???

/*    // Check if given move direction is possible on the existing board
    // Move is possible if word is in bounds of board, and has no parallel words
    public boolean isValidDirection(ArrayList<int[]> moveSquares, String moveDirection) {
        //return (super.squaresAreInBounds(moveSquares)) && !(super.hasAdjacentWords(moveSquares, moveDirection));
        return true; //for testing
    }*/



  /*  // Check if all the conditions for a valid move are met
    public boolean isValidMove() {
        *//*return (isValidWord(finalWord)
                && hasAllTilesAvailable(charsInWord, tileRack)
                && isValidDirection(moveSquares, moveDirection)
                && super.intersectsWord(moveSquares));*//*
        return true; //always true for testing
    }
*/










/*    public void calculateFinalWord(String moveWord, String movePosition, String moveDirection){
        // Convert the 'cr' format provided into int for index positions
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        // Create empty string for final word
        finalWord = "";
        char c;
        // Create square to track position in linked list
        ScrabbkleTile currentTile;
        if(moveDirection.equals("d")){
            // If tile already occupied in start position, take character from tile
            // and add to finalWord string.
            // If not already occupied, using the character provided by player
            //WHAT IF TILE PLACED AT BOTTOM/END OF WORD, E.G. WORD + S
            //LOOK IF SQUARE TO LEFT HAS TILE, THEN KEEP GOING LEFT UNTIL NULL
            //REPEAT FOR BOTTOM
            //USE SAME LOGIC FOR SCORING

            // Get all the letters going down
            for(int i = 0; i < moveWord.length(); i++){
                //Check if there is a tile above already
                if( getBoard().getBoard()[row - 1][col].getTile() != null ){
                    // First find if any letter above player's position and find top most tile if so
                    // This will be the beginning of the word
                    currentTile = getBoard().getBoard()[row - 1 ][col].getTile();
                    while(currentTile.getAboveTile() != null){
                        currentTile = currentTile.getAboveTile();
                    }
                    while (currentTile != null && currentTile != currentTile.getBelowTile()) {
                        finalWord += currentTile.getLetter(); // append the letter to the finalWord string
                        currentTile = currentTile.getBelowTile(); // move to the next tile below

                    }

                    //NEED TO ADD FINAL LETTER
                    //NEED TO CHECK GAPS

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
    }*/

    // Get all the positions for the squares in the move, including squares which
    // already have placed tiles >>>>>> CHANGED SO NO LONGER HAS THIS!!!!
/*    public void calculateMoveSquares(String finalWord, String movePosition, String moveDirection) {
        //create ArrayList of int[] to store row and column positions for each tile on board
        int wordLength = finalWord.length();
        moveSquares = new ArrayList<int[]>();
        //add movePosition in first 2 elements
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        if (moveDirection.equals("d")) {
            for (int i = 0; i < wordLength; i++) {
                int[] positions = {row + i, col};
                moveSquares.add(positions);
            }
        } else {
            for (int i = 0; i < wordLength; i++) {
                int[] positions = {row, col + i};
                moveSquares.add(positions);
            }
        }
    }*/





 /*   // Get a list of all the squares in the player's move
    public ArrayList<int[]> getMoveSquares(){
        return moveSquares;
    }*/




    // Used for testing only
/*    @VisibleForTesting
    protected ArrayList<Character> getCharsInWord() {
        return charsInWord;
    }

    // Used for testing only
    @VisibleForTesting
    protected String getFinalWord() {
        return finalWord;
    }*/
}

    //use wildCard()
    //get string input


