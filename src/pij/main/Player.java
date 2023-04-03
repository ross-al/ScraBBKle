package pij.main;

public interface Player {

    /**
     *
     */

    void playWord(String moveWord, String movePosition, String moveDirection);

    void placeTile(ScrabbkleTile tile, int col, int row);

    int calculateWordScore(String moveDirection, int row, int col, int tiles);


}