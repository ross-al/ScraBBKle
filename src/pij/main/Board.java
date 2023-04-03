package pij.main;

import java.io.File;

/**
 * A Board is the structure for holding player pieces, such as tiles
 */

public interface Board {

    /**
     * Converts a given input file into the desired board type
     *
     * @param inputFile the input file from which to load board layout
     */
    void convertToBoard(File inputFile);

    /**
     * Print the board on the player's screen
     */
    void printBoard();


}
