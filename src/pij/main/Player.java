package pij.main;

//NEED JAVADOC!
public interface Player {

    void addTileToRack();

    int calculateWordScore();

    int getPlayerScore();

    void placeTile(ScrabbkleTile tile, int col, int row);

    ScrabbkleTile playWord(); //remove if redundant
}