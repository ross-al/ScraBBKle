package pij.main;

public interface TileBag {

    int getSize();
    boolean isEmpty();
    void shuffleBag();
    Tile getRandomTile();
    void fillTileBag();

}
