package pij.main;

import java.util.ArrayList;

abstract class ScrabbklePlayer implements Player{
    int score;
    private ArrayList<ScrabbkleTile> myTileRack;//need to restrinct to 7 tiles

    public ScrabbklePlayer(){
        myTileRack = new ArrayList<>();
    }

    @Override
    public void addTileToRack(ScrabbkleTile myTile) {
        myTileRack.add(myTile);
    }

    @Override
    public int getRackSize() {
        return myTileRack.size();
    }

    public void printRack(){
        for (int i =0; i < myTileRack.size(); i++ ){
            System.out.print(myTileRack.get(i).getPrintInTileRackFormat());
        }
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
}
