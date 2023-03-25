package pij.main;

import java.util.*;

public class HumanPlayer extends ScrabbklePlayer {
    private String moveWord;
    private String movePosition;
    private String moveDirection;
    private ArrayList<Character> charsInWord;
    private ArrayList<int[]> moveSquares;
    private boolean firstMove = true;

    public HumanPlayer(ScrabbkleBoard board, ScrabbkleWordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
        charsInWord = new ArrayList<>();
    }

    public void playMove() {
        boolean validMove = false;
        while (!validMove) {
            String move = getPlayerMove();
            if (!isSkip(move)) {
                //Split player instructions into string array
                String[] instructions = interpretPlayerMove(move);
                moveWord = instructions[0];
                movePosition = instructions[1];
                moveDirection = instructions[2];
                //Split each letter in word into character
                splitWordToChar(moveWord);
                calculateMoveSquares(moveWord, movePosition, moveDirection);
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

                    //Remove all chars in list after each move
                    charsInWord.clear();

                } else {
                    System.out.println("This is not a valid move");
                }
            } else {
                //skip move
                return;
            }
        }
    }

    //check if correct tiles in rack
    //check if correct tiles on board already

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


    public boolean isValidMove() {
        return (isValidWord(moveWord)
                && isValidPosition(movePosition)
                && isValidDirection(moveSquares)
                && hasAllTilesAvailable(charsInWord));
    }

    public boolean isValidWord(String str) {
        return super.getWordList().isWord(str);
    }


    public boolean isValidPosition(String movePosition) {
        boolean validPosition = false;
        // Get col and row of start position as int
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        // Check if this is humanPlayer's first move
        if (!isFirstMove()) {
            //check if legalPosition in super
            //PLACEHOLDER SUPER METHOD DOESN'T WORK YET!!!!!!!!!
            if (super.isLegalPosition(col, row)) {
                validPosition = true;
            }
        } else {
            // If humanPlayer's first move:
            // check if first position matches startSquare in Board
            // return true if so
            int startSquare = getBoard().getStartSquare();
            if ((col == startSquare) && (row == startSquare)) {
                validPosition = true;
                firstMove = false;
            }
        }
        return validPosition;
    }


    public boolean isFirstMove() {
        return (firstMove);
    }

    // Split given word into character arrayList
    public void splitWordToChar(String moveWord) {
        for (int i = 0; i < moveWord.length(); i++) {
            char c = moveWord.charAt(i);
            charsInWord.add(c);
        }
    }

    public boolean hasAllTilesAvailable(ArrayList<Character> charsInWord) {
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
            int boardCount = 0;
            // Count the number of tiles in the tile rack and on the board
            for (ScrabbkleTile tile : tileRack) {
                if (tile.getLetter() == c) {
                    tileRackCount++;
                }
            }
            for (int[] positions : moveSquares) {
                int col = positions[0];
                int row = positions[1];
                if (super.getBoard().getBoard()[col][row].getTile() != null) {
                    char letter = super.getBoard().getBoard()[col][row].getTile().getLetter();
                    if (letter == c) {
                        boardCount++;
                    }
                }
            }
            // Check if there are enough tiles to satisfy the count
            //NEED TO UPDATE FOR WILDCARDS
            if (tileRackCount + boardCount != requiredCount) {
                return false;
            }
        }
        return true;
    }

    //Method used to print move summary in console after move
    public String getMoveDirection() {
        if (moveDirection.equals("d")) {
            return "down";
        } else {
            return "right";
        }
    }

/*   //REMOVE IF REDUNDANT ??????????
    public String getMoveWord(){
        return moveWord;
    }*/

/*    //used for testing
    public String getMovePosition(){
        return movePosition;
    }*/

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

    public void calculateMoveSquares(String moveWord, String movePosition, String moveDirection) {
        //create ArrayList of int[] to store row and column positions for each tile on board
        int wordLength = moveWord.length();
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

    //Check if given move is possible on the existing board
    //Move is possible if word is in bounds of board, and has no adjacent words
    public boolean isValidDirection(ArrayList<int[]> moveSquares) {
        return (moveIsInBounds(moveSquares)) && !(super.hasAdjacentWords(moveSquares));
    }

    //Check if the given move is within the bounds of the board
    public boolean moveIsInBounds(ArrayList<int[]> moveSquares) {
        int boardSize = super.getBoard().getBoardSize();
        for (int[] moveSquare : moveSquares) {
            int col = moveSquare[0];
            int row = moveSquare[1];
            if (row < 0 || row > boardSize || col < 0 || col > boardSize) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<int[]> getMoveSquares(){
        return moveSquares;
    }

    public ArrayList<Character> getCharsInWord() {
        return charsInWord;
    }
}

    //use wildCard()
    //get string input


