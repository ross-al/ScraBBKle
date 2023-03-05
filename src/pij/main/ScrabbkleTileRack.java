package pij.main;

import java.util.ArrayList;

public class ScrabbkleTileRack implements TileRack{
    private ArrayList<ScrabbkleTile> myTileRack;

    public ScrabbkleTileRack() {
        myTileRack= new ArrayList<>();
    }

    @Override
    public void addTile(ScrabbkleTile myTile) {
        myTileRack.add(myTile);
    }

    @Override
    public int getSize() {
        return myTileRack.size();
    }

    public void printRank(){
        for (int i =0; i<myTileRack.size(); i++ ){
            System.out.print(myTileRack.get(i).getPrintInTileRackFormat());
        }
    }

    // playTile(takes char as param)
    //removes tile from list once played

    //print arraylist tile.


}
