package pij.main;

//NEED JAVADOC!
public interface Player {

    void addTileToRack(ScrabbkleTile myTile);

    int getRackSize();

    int calculateWordScore();

    int getPlayerScore();

    ScrabbkleTile placeTile();

    ScrabbkleTile playWord();
}