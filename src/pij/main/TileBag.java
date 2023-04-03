package pij.main;

/**
 * A TileBag holds the tiles for a game
 */

public interface TileBag {

    /**
     * Shuffles the tiles in the bag so player can draw randomised tiles
     */
    void shuffleBag();

    /**
     * Gets a tile from the bag
     *
     * @return returns a tile
     */
    Tile getRandomTile();

    /**
     * Fills the bag with tiles
     */
    void fillTileBag();

}
