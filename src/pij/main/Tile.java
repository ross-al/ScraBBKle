package pij.main;

/**
 * A tile is an object used to form words on a board
 */

public interface Tile {


    /**
     * Calculate the value of a tile based on its letter
     *
     * @param letter the letter of the tile
     * @return int value of letter value
     */
    int calculateTileValue(char letter);

    /**
     * Assigns a letter to a blank tile
     *
     * @param c letter to be assigned to blank tile
     */
    void assignWildCard(char c);


}
