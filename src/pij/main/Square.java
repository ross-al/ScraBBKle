package pij.main;

/**
 * A square holds a player's tile on the board.
 * Squares can have premium values
 */

public interface Square {


    /**
     * Get a squares premium letter value
     *
     * @return int premium letter value
     */
    int getPremiumLetterValue();

    /**
     * Get a squares premium word value
     *
     * @return int premium word value
     */
    int getPremiumWordValue();

    /**
     * Get print label for printing square
     *
     * @return String print label
     */
    String getPrintLabel();

    /**
     * Place a tile on a square
     *
     * @param tile tile to be place on square
     */
    void setTile(ScrabbkleTile tile);


}
