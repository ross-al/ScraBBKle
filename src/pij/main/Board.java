package pij.main;
import java.io.File;

//NEED JAVADOC!

public interface Board {

    ScrabbkleSquare[][] convertToBoard(ScrabbkleSquare[][] board);
    File getInputFile();
    int getBoardSize();
    boolean isValidSize();
    void printBoard();
    ScrabbkleSquare[][] getMyBoard(); //remove if redundant
    int getStartSquare();
}
