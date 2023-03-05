package pij.main;

public interface TileBag {

    int getTileCounter();
    int getSize();
    boolean isEmpty();
    void shuffleBag();
    Tile getRandomTile();
    void fillTileBag();

}
