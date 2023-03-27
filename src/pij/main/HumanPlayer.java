package pij.main;

import java.util.*;

public class HumanPlayer extends ScrabbklePlayer {
    private String moveWord;
    private String movePosition;
    private String moveDirection;
    private String finalWord;
    private ArrayList<Character> charsInWord;
    private ArrayList<int[]> moveSquares;
    private String errorReason;
    private boolean firstMove = true;

    public HumanPlayer(ScrabbkleBoard board, ScrabbkleWordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
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
            String move = getPlayerMove();
            if (!isSkip(move)) {
                //Split player instructions into string array
                String[] instructions = interpretPlayerMove(move);
                moveWord = instructions[0];
                movePosition = instructions[1];
                moveDirection = instructions[2];
                //Split each letter in word into character
                splitWordToChar(moveWord);
                calculateFinalWord(moveWord, movePosition, moveDirection);
                calculateMoveSquares(finalWord, movePosition, moveDirection);
                //Check if valid word, position and direction
                if (isValidMove()) {
                    validMove = true;

                    //rest of code here?????
                    //place tiles on board

                    //Print move summary, scores and updated board
                    System.out.print("The move is: " + moveWord);
                    System.out.print(" at position " + movePosition + ",");
                    System.out.print(" direction: " + getMoveDirection());

                    // testing tiles in rack:
                    System.out.println();
                    System.out.println("tiles available: " + hasAllTilesAvailable(charsInWord));
                    System.out.println();


                } else {
                    System.out.println("This is not a valid move");
                    System.out.println("Error Reason: " + errorReason);
                }
            } else {
                //skip move
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


    public String getPlayerMove() {
        System.out.println("Please enter your move with letter sequence, position, and");
        System.out.println("direction (d for down, r for right) separated by commas.");
        System.out.println("Entering just two commas passes.");
        System.out.println();
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public boolean isSkip(String word) {
        return word.equals(",,");
    }


    public String[] interpretPlayerMove(String word) {
        return word.split(",");
    }


    public boolean isValidWord(String word) {
        errorReason = "not valid word";
        return super.getWordList().isWord(word);
    }

    public void calculateFinalWord(String moveWord, String movePosition, String moveDirection){
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        finalWord = "";
        char c;
        if(moveDirection.equals("d")){
            for(int i = 0; i < moveWord.length(); i++){
                if( getBoard().getBoard()[col][row].getTile() != null ){
                    String tileLetter = String.valueOf(getBoard().getBoard()[col][row].getTile().getLetter());
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
                if( getBoard().getBoard()[col][row].getTile() != null ){
                    String tileLetter = String.valueOf(getBoard().getBoard()[col][row].getTile().getLetter());
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


    public boolean isValidMove() {
        boolean validMove = false;
        // Get col and row of start position as int
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        // Check if this is humanPlayer's first move
        if (!isFirstMove()) {
            // Check if all inputs are valid
            if(isValidWord(finalWord)
                    && hasAllTilesAvailable(charsInWord)
                    && isValidDirection(moveSquares, moveDirection)
                    && super.intersectsWord(moveSquares))
                {
                validMove = true;
             }
        } else {
            // If humanPlayer's first move, startSquare must be present in moveSquares
            int[] startSquare = getBoard().getStartSquare();
            if(moveSquares.contains(startSquare)){
                if(isValidWord(finalWord) && hasAllTilesAvailable(charsInWord)){
                    validMove = true;
                    firstMove = false;
                }
            }
        }
        return validMove;
    }


    public boolean isFirstMove() {
        return (firstMove);
    }

    // Split given word into character arrayList
    public void splitWordToChar(String moveWord) {
        charsInWord = new ArrayList<>();
        for (int i = 0; i < moveWord.length(); i++) {
            char c = moveWord.charAt(i);
            charsInWord.add(c);
        }
    }

    // Check if the player's rack has all the necessary tiles, including duplicates
    public boolean hasAllTilesAvailable(ArrayList<Character> charsInWord) {
        errorReason = "not all tiles available";
        ArrayList<ScrabbkleTile> tileRack = getTileRack();
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
                return false;
            }
        }
        return true;
    }

    // Get print format for move direction for move summary in console
    public String getMoveDirection() {
        if (moveDirection.equals("d")) {
            return "down";
        } else {
            return "right";
        }
    }

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

    public int getPositionRow(String movePosition) {
        String str = movePosition.substring(1);
        return Integer.parseInt(str);
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
                int[] positions = {startCol, startRow + i};
                moveSquares.add(positions);
            }
        } else {
            for (int i = 0; i < wordLength; i++) {
                int[] positions = {startCol + i, startRow};
                moveSquares.add(positions);
            }
        }
    }

    // Check if given move direction is possible on the existing board
    // Move is possible if word is in bounds of board, and has no parallel words
    public boolean isValidDirection(ArrayList<int[]> moveSquares, String moveDirection) {
        errorReason = "not valid direction";
        return (super.squaresAreInBounds(moveSquares)) && !(super.hasAdjacentWords(moveSquares, moveDirection));
    }

    public ArrayList<int[]> getMoveSquares(){
        return moveSquares;
    }

    // Used for testing
    public ArrayList<Character> getCharsInWord() {
        return charsInWord;
    }
}

    //use wildCard()
    //get string input


