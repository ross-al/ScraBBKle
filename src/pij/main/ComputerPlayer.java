package pij.main;


//REDUNDANT CLASS???

public class ComputerPlayer extends ScrabbklePlayer {

    public ComputerPlayer(ScrabbkleBoard board, WordList wordList, ScrabbkleTileBag tileBag) {
        super(board, wordList, tileBag);
    }

    public void playMove() {
        fillRack();
        System.out.println();
        System.out.println("testing: SKIP COMPUTER PLAYER MOVE");
        System.out.println();
    }
}
