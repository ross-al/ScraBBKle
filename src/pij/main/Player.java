package pij.main;

//NEED JAVADOC!
public interface Player {

    int calculateScore();

    int getCurrentScore();

    ScrabbkleTile fetchTile();

    ScrabbkleTile placeTile();

    ScrabbkleTile playWord();
}