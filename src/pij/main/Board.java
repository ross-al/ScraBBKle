package pij.main;

import java.io.File;

public interface Board {

    String[][] convertToBoard(String[][] board);
    //take type Square instead of String[][]
    // should be able to take Tile
    // should have premiumSquare or premiumWord member fields
    File getInputFile();
    int getBoardSize();
    boolean isValidSize();
    void printBoard(String[][] board);
    String[][] getMyBoard();


}
