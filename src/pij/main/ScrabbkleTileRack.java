package pij.main;

import java.util.ArrayList;

//MUST LIMIT NUMBER OF TILES
//loop to draw x tiles to start
//then method to draw one tile at a time as long as bag is not empty
//and rack is not full
//manage in main?

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

    public void printRack(){
        for (int i =0; i<myTileRack.size(); i++ ){
            System.out.print(myTileRack.get(i).getPrintInTileRackFormat());
        }
    }

    // playTile(takes char as param)
    //removes tile from list once played

    //print arraylist tile.


}
