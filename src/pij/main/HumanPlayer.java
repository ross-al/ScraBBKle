package pij.main;

import java.util.Scanner;

public class HumanPlayer extends ScrabbklePlayer {
    String moveWord;
    String movePosition;
    String moveDirection;
    boolean firstMove = true;

    public HumanPlayer(ScrabbkleBoard board, ScrabbkleWordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
    }

    public void playMove() {
        getBoard().printBoard();
        fillRack();
        printRack();
        String move = getPlayerMove();
        if(!isSkip(move)){
            //need while loop to check if valid move and print
            //"This is not a valid move." if not

            //split player instructions into string array
            String[] instructions =  interpretPlayerMove(move);
            moveWord = instructions[0];
            movePosition = instructions[1];
            moveDirection = instructions[2];

            //check if word is valid (should be in super)??
            isValidWord(moveWord);

            //check if correct tiles in rack
            //check if correct tiles on board already

            //check if position is valid
            isValidPosition(movePosition);

            //check if direction is valid

            //check if had wildcard (should be in super?, or limit wildcard to human)
            //place word on board



            //print word on  board //(should be in super)
            //display board


            //return to end move


        } else {
            //skip move
            return;
        }
    }

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

    public boolean isValidWord(String str){
        return super.getWordList().isWord(str);
    }

    public boolean isValidPosition(String movePosition){
        //assume position is not valid at first
        boolean validPosition = false;
        //get col and row as int 
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        //check if this is humanPlayer's first move
        if(!isFirstMove()){
            //this should be in super as applies to both classes //CREATE METHOD IN SUPER
            //check position is legal
            //need to check if squares are empty?
            //need to check if any adjacent squares?
        } else {
            //if humanPlayer's first move:
            //check if first position matches startSquare in Board
            //return true if so
            int startSquare = getBoard().getStartSquare();
            if((col == startSquare) && (row == startSquare)){
                validPosition = true;
            }
        }
        return validPosition;
    }

    public boolean isFirstMove(){
        return (firstMove);
    }

    //check if tiles are available in rack to make desired word
    public boolean hasTilesAvailable(){
        return true;
    }

    //methods used to print move summary in console
    public String getMoveDirection(){
        if (moveDirection.equals("d")){
            return "down";
        } else {
            return "right";
        }
    }

    public String getMoveWord(){
        return moveWord;
    }

    public String getMovePosition(){
        return movePosition;
    }

    public int getPositionColumn(String movePosition){
        char position = movePosition.charAt(0);
        char c = 'a';
        int col = 0;
        for (int i = 1; i < getBoard().getBoardSize(); i++) {
            if (position == c) {
                col = i;
            } else {
                c++;
            }
        }
        return col;
    }

    public int getPositionRow(String movePosition){
        String str = movePosition.substring(1);
        return Integer.parseInt(str);
    }


}


    //use wildCard()
    //get string input


