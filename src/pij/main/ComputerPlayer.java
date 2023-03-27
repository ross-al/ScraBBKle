package pij.main;


public class ComputerPlayer extends ScrabbklePlayer {

    //Change ScrabbkleWordList to just WordList if we remove WordList interface?
    public ComputerPlayer(ScrabbkleBoard board, ScrabbkleWordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
    }

    public void playMove() {
        System.out.println();
        System.out.println("testing: SKIP COMPUTER PLAYER MOVE");
        System.out.println();
    }
}
