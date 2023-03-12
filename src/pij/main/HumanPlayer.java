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
        inputPlayerWord();
        //check if word is possible (should be in super)
        //check if had wildcard (should be in super?, or limit wildcard to human)
        //play word (should be in super)
        //getwordscore (should be in super)
    }

    public String inputPlayerWord(){
        System.out.println("Please enter your move with letter sequence, position, and");
        System.out.println("direction (d for down, r for right) separated by commas.");
        System.out.println("Entering just two commas passes.");
        System.out.println();
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }


    //use wildCard()
    //get string input

}
