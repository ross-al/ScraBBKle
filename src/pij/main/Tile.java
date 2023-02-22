package pij.main;

public interface Tile {

    char getLetter();
    int getValue();
    String getPrintInTileRackFormat();
    String getPrintOnBoardFormat();
    int calculateTileValue(char letter);

}
