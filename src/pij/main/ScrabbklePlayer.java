package pij.main;

import java.util.ArrayList;

abstract class ScrabbklePlayer implements Player{ //abstract class? need to be public?
    int score;
    private ArrayList<ScrabbkleTile> myTileRack;

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
        for (int i =0; i<myTileRack.size(); i++ ){
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
        return null;
    }
}
