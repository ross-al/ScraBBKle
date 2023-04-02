package pij.main;

public interface TileBag {

    /**
     *
     * @return
     */

    int getSize();
    boolean isEmpty();
    void shuffleBag();
    Tile getRandomTile();
    void fillTileBag();

}
