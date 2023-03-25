package pij.main;

import java.util.ArrayList;
import java.util.Scanner;

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
                //split player instructions into string array
                String[] instructions = interpretPlayerMove(move);
                moveWord = instructions[0];
                movePosition = instructions[1];
                moveDirection = instructions[2];
                //split each letter in word into character
                splitWordToChar(moveWord);
                calculateMoveSquares(moveWord, movePosition, moveDirection);
                //check if valid word, position and direction
                if (isValidMove()) {
                   validMove = true;
                    //rest of code here?????

                    //print move summary, scores and updated board
                   System.out.print("The move is: " + moveWord);
                   System.out.print(" at position " + movePosition + ",");
                   System.out.print(" direction: "+ getMoveDirection());
                   //remove all chars in list after each move
                   charsInWord.clear();

                   //NEED TO CLEAR MOVESQUARES 2D ARRAY TOO???
                    //OR GETS OVERRIDDEN EACH TIME METHOD IS CALLED?

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





    public String getPlayerMove(){
        System.out.println("Please enter your move with letter sequence, position, and");
        System.out.println("direction (d for down, r for right) separated by commas.");
        System.out.println("Entering just two commas passes.");
        System.out.println();
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public boolean isSkip(String word){
        return word.equals(",,");
    }

    
    public String[] interpretPlayerMove(String word){
        return word.split(",");
    }


    public boolean isValidMove(){
        return (isValidWord(moveWord)
                && isValidPosition(movePosition)
                && isValidDirection(moveDirection));
                //&& hasAllTilesAvailable(charsInWord);
    }

    public boolean isValidWord(String str){
        return super.getWordList().isWord(str);
    }


    public boolean isValidPosition(String movePosition) {
        //assume position is not valid at first
        boolean validPosition = false;
        //get col and row as int 
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        //check if this is humanPlayer's first move
        if (!isFirstMove()) {
            //check if legalPosition in super
            //PLACEHOLDER SUPER METHOD DOESN'T WORK YET!!!!!!!!!
            if (super.isLegalPosition(col, row)) {
                validPosition = true;
            }
        } else {
            //if humanPlayer's first move:
            //check if first position matches startSquare in Board
            //return true if so
            int startSquare = getBoard().getStartSquare();
            if ((col == startSquare) && (row == startSquare)) {
                validPosition = true;
                firstMove = false;
                }
            }
        return validPosition;
        }


    public boolean isFirstMove(){
        return (firstMove);
    }

    //split given word into character arrayList
    public void splitWordToChar(String moveWord){
        for (int i = 0; i < moveWord.length(); i++){
            char c = moveWord.charAt(i);
            charsInWord.add(c);
        }
    }
    //check if tiles are available in rack to make desired word

/*    public boolean hasAllTilesAvailable(ArrayList<Character> charsInWord){
        //boolean allTilesAvailable= false; //MIGHT NOT NEED
        ArrayList<ScrabbkleTile> tileRack = getTileRack(); //gets tileRack from super
        //first check if characters exist in tile rack
        for (Character character : charsInWord) {
            for (ScrabbkleTile scrabbkleTile : tileRack) {
                if (character == scrabbkleTile.getLetter()) {
                    return true;
                    //if not, check if characters already exist on board
                } else {
                    for(int i = 0; i < moveSquares.length; i++){
                        for(int j = 0; j < moveSquares.length; j++){
                            int col = moveSquares[i][j];

                            //will give error if null???? try catch?
                            //getTile, if no tile, then false
                            //if tile present, get letter
                            char letter = super.getBoard().getBoard()[col][row].getTile().getLetter();
                            int row =
                            if(character == letter)
                                    //do stuff
                                return true;
                            }
                        }

                    for (int i = 1; i < wordLength; i++) {
                        moveSquares[i][0] = startCol + i;
                    }
                    for (int j = 0; j < wordLength; j++){
                        moveSquares[j][1] = startCol;
                    }
                    //check if on board

                       //check if squares in moveSquares have tiles
                    }
                    //need to get word start position
                    //need word length
                    //need word direction
                    //then calculate index

                }
            }
            //check if char in charsInWord list matches tile letter in player tileRack

            //check if char in tile in rack
            //else check if on board, in direction of move (need indexes)
            //return true
        }
        //wildcard, if char lowercase, then if char is empty in rack, or lowercase on board
        //check if tiles with char are on board already
        //if not check if tiles are in rack
        return allTilesAvailable;
    }*/

    //methods used to print move summary in console
    public String getMoveDirection(){
        if (moveDirection.equals("d")){
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

    public int getPositionColumn(String movePosition){
        char position = movePosition.charAt(0);
        int col = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if (position == c) {
                col = c - 'a' + 1;
            }
        }
        return col;
    }


    public int getPositionRow(String movePosition){
        String str = movePosition.substring(1);
        return Integer.parseInt(str);
    }

    public void calculateMoveSquares(String moveWord, String movePosition, String moveDirection){
        //create ArrayList of int[] to store row and column positions for each tile on board
        int wordLength = moveWord.length();
        moveSquares = new ArrayList<int[]>();
        //add movePosition in first 2 elements
        int startCol = getPositionColumn(movePosition);
        int startRow = getPositionRow(movePosition);
        if(moveDirection.equals("d")) {
            for(int i = 0; i < wordLength; i++){
                int[] positions = {startCol, startRow + i};
                moveSquares.add(positions);
            }
        } else {
            for(int i = 0; i < wordLength; i++){
                int[] positions = {startCol + i, startRow};
                moveSquares.add(positions);
                }
            }
    }


    public boolean isValidDirection(String moveDirection){
        //PLACEHOLDER !!!!!!!!!!!!!!
        //need to create logic here to get direction is valid!!!!
        return true;
    }

    public ArrayList<int[]> getMoveSquares(){
        return moveSquares;
    }
}


    //use wildCard()
    //get string input


