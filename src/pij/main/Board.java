package pij.main;
import java.io.File;

//NEED JAVADOC!

public interface Board {

    ScrabbkleSquare[][] convertToBoard(ScrabbkleSquare[][] board);
    File getInputFile(); //
    int getBoardSize(); //why is this not used?
    boolean isValidSize();
    void printBoard();
    ScrabbkleSquare[][] getMyBoard();
}
