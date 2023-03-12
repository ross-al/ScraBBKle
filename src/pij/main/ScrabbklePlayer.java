package pij.main;

import java.util.ArrayList;

public class ScrabbklePlayer implements Player{
    private int score;
    private ArrayList<ScrabbkleTile> myTileRack;//need to restrict to 7 tiles
    private ScrabbkleBoard board;
    private WordList wordList;
    private ScrabbkleTileBag tileBag;

    public ScrabbklePlayer(ScrabbkleBoard board, WordList wordList, ScrabbkleTileBag tileBag){
        this.board = board;
        this.wordList = wordList;
        this.tileBag = tileBag;
        myTileRack = new ArrayList<>();
    }

    public void fillRack(){
        if (!tileBag.isEmpty()) {
            while (myTileRack.size()<7){
                addTileToRack();
            }
        }
    }

    @Override
    public void addTileToRack() {

        ScrabbkleTile myTile = tileBag.getRandomTile();
        myTileRack.add(myTile);
    }


    public void printRack(){
        System.out.println();
        if (!(myTileRack.isEmpty())) {
            System.out.println("It's your turn! Your tiles: ");
            System.out.print(myTileRack.get(0).getPrintInTileRackFormat());
            for (int i = 1; i < myTileRack.size(); i++) {
                System.out.print(", " + myTileRack.get(i).getPrintInTileRackFormat());
            }
        }
        else {
            System.out.println("Your rack is empty");
        }
        System.out.println();
        System.out.println();
    }

    @Override
    public int calculateWordScore() {
        return 0;
    }

    @Override
    public int getPlayerScore() {
        return score;
    }


    @Override
    public ScrabbkleTile placeTile() {
        return null;
    }

    @Override
    public ScrabbkleTile playWord() {
        //HumanPlayer first move MUST be in centre square
        //if board is even, then top left square in centre is centre square
        return null;
    }

    public ScrabbkleBoard getBoard(){
        return board;
    }
}
