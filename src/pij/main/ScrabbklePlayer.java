package pij.main;

import java.util.ArrayList;

public class ScrabbklePlayer implements Player{
    protected boolean isPlayerTurn;
    private int score;
    private ArrayList<ScrabbkleTile> tileRack;//need to restrict to 7 tiles
    private ScrabbkleBoard board;
    private WordList wordList;
    private ScrabbkleTileBag tileBag;

    public ScrabbklePlayer(ScrabbkleBoard board, WordList wordList, ScrabbkleTileBag tileBag){
        this.board = board;
        this.wordList = wordList;
        this.tileBag = tileBag;
        tileRack = new ArrayList<>();
    }

    public void fillRack(){
        if (!tileBag.isEmpty()) {
            while (tileRack.size()<7){
                addTileToRack();
            }
        }
    }

    @Override
    public void addTileToRack() {

        ScrabbkleTile myTile = tileBag.getRandomTile();
        tileRack.add(myTile);
    }


    public void printRack(){
        System.out.println();
        if (!(tileRack.isEmpty())) {
            System.out.println("It's your turn! Your tiles: ");
            System.out.print(tileRack.get(0).getPrintInTileRackFormat());
            for (int i = 1; i < tileRack.size(); i++) {
                System.out.print(", " + tileRack.get(i).getPrintInTileRackFormat());
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

    public ArrayList<ScrabbkleTile> getTileRack(){
        return tileRack;
    }
}
