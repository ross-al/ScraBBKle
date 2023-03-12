package pij.main;

//NEED JAVADOC!
public interface Player {

    void addTileToRack();

    int calculateWordScore();

    int getPlayerScore();

    ScrabbkleTile placeTile();

    ScrabbkleTile playWord();
}