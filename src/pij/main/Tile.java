package pij.main;

public interface Tile {

    /**
     *
     * @return
     */

    char getLetter();
    int getValue();
    String getPrintInTileRackFormat();
    String getPrintOnBoardFormat();
    int calculateTileValue(char letter);
    void assignWildCard(char c);
    void removeWildCard();

}
