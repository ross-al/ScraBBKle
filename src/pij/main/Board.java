package pij.main;
import java.io.File;

//NEED JAVADOC!

public interface Board {

    String getBoardChoice();
    File getInputFile(String boardChoice);
    boolean inputFileExists(File inputFile);
    void calculateBoardSize(File inputFile);
    boolean isValidSize();
    void convertToBoard(File inputFile);
    void printBoard();
    ScrabbkleSquare[][] getBoard();
    int[] calculateCentreSquare(int boardSize);
    int getBoardSize();
    int[] getCentreSquare();

}
