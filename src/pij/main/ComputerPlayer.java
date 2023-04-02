package pij.main;


import java.util.ArrayList;

public class ComputerPlayer extends ScrabbklePlayer {
    private ArrayList<ScrabbkleTile> tileRack;
    private int score;
    private int skipCount;

    //Change ScrabbkleWordList to just WordList if we remove WordList interface?
    public ComputerPlayer(ScrabbkleBoard board, ScrabbkleWordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
        tileRack = super.getTileRack();
        score = 0;
        skipCount = 0;
    }

    public void playMove() {
        System.out.println();
        System.out.println("Computer player skipped their turn");
        System.out.println();
        skipCount++;
    }

    // Getters

    public int getSkipCount(){
        return skipCount;
    }

    public int getPlayerScore() {
        return score;
    }
}
