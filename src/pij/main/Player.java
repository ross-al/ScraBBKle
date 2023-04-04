package pij.main;

import java.util.ArrayList;

/**
 * A Player can make moves in a game and score points to win
 */

public interface Player {

    /**
     * Plays a word given by the player
     *
     * @param moveWord      word to be played
     * @param movePosition  position on which to place the first tile of the word
     * @param moveDirection the direction in which to read the word, i.e. down or right
     */
    void playWord(String moveWord, String movePosition, String moveDirection, ArrayList<ScrabbkleTile> tileRack);

    /**
     * Places a tile on the board
     *
     * @param tile tile to place on the board
     * @param row  int position of row on board
     * @param col  int position of column on board
     */
    void placeTile(ScrabbkleTile tile, int row, int col);

    /**
     * Calculates word score for played word
     *
     * @param moveDirection the direction in which to read the word, i.e. down or right
     * @param row           col int position of column on board
     * @param col           int position of column on board
     * @param tiles         the number of tiles used in a move
     * @return int score of move
     */
    int calculateWordScore(String moveDirection, int row, int col, int tiles);


}