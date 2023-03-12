package pij.main;

import java.util.Scanner;

public class HumanPlayer extends ScrabbklePlayer {

    public HumanPlayer(ScrabbkleBoard board, WordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
    }

    public void playMove() {
        getBoard().printBoard();
        fillRack();
        printRack();
        String move = getPlayerMove();
        //NEED TO FACTOR IN SKIP WITH ",,"
        if(!isSkip(move)){
            String[] instructions =  interpretPlayerMove(move);
            String word = instructions[0];
            String position = instructions[1];
            String direction = instructions[2];
            //rest of code here
        } else {
            //skip move
            return;
        }
        
        //check if word is possible (should be in super)
        //check if had wildcard (should be in super?, or limit wildcard to human)
        //play word (should be in super)
        //getwordscore (should be in super)
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

    public String getPlayerWord(String[] str){
       return str[0];
    }

    public String getMovePosition(String[] str){
        return str[1];
    }

    public String getMoveDirection(String[] str){
        return str[2];
    }


    //use wildCard()
    //get string input

}
