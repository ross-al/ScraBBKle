package pij.main;

//NEED JAVADOC!
public interface Player {

    void addTileToRack();

    int calculateWordScore(String moveDirection, int row, int col, int tiles);

    void placeTile(ScrabbkleTile tile, int col, int row);

}