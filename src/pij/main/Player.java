package pij.main;

public interface Player {

    int calculateScore();

    int getCurrentScore();

    Tile fetchTile();

    Tile placeTile();

    Tile playWord();
}