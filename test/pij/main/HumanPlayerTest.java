package pij.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {
    ScrabbkleBoard board;
    ScrabbkleTile tile1, tile2;
    HumanPlayer humanPlayer;
    ScrabbkleWordList wordList;
    ScrabbkleTileBag tileBag;

    @BeforeEach
    void buildUp(){
        board = new ScrabbkleBoard();
        board.setBoardSize(15);
        String fileName = "./resources/defaultBoard.txt";
        File inputFile = new File(fileName);
        board.convertToBoard(inputFile);
        tile1 = new ScrabbkleTile('I');
        tile2 = new ScrabbkleTile('T');
        wordList = new ScrabbkleWordList();
        tileBag = new ScrabbkleTileBag();
        humanPlayer = new HumanPlayer(board,wordList,tileBag);
    }

    @Test
    void shouldReturnPositionColumnAsInt(){
        String movePosition = "h7";
        int actual = humanPlayer.getPositionColumn(movePosition);
        int expected = 8;
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnPositionRowAsInt(){
        String movePosition = "h7";
        int actual = humanPlayer.getPositionRow(movePosition);
        int expected = 7;
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnFinalWordGoingDown() {
        board.getBoard()[8][7].setTile(tile1);
        board.getBoard()[8][8].setTile(tile2);
        humanPlayer.calculateFinalWord("SAR","h7","d");
        String actual = humanPlayer.getFinalWord();
        String expected = "STAR";
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnFinalWordGoingRight() {
        board.getBoard()[8][7].setTile(tile1);
        board.getBoard()[8][8].setTile(tile2);
        humanPlayer.calculateFinalWord("LTLE","f8","r");
        String actual = humanPlayer.getFinalWord();
        String expected = "LITTLE";
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMoveSquaresGoingDown() {
        humanPlayer.calculateMoveSquares("STAR", "h7", "d");
        ArrayList<int[]> moveSquares = humanPlayer.getMoveSquares();
        ArrayList<int[]> testSquares = new ArrayList<>() {
            {
                add(0, new int[]{7,8});
                add(1, new int[]{8,8});
                add(2, new int[]{9,8});
                add(3, new int[]{10,8});
            }
        };
        int[] square = moveSquares.get(3);
        int actual = square[1];

        int[] test = testSquares.get(3);
        int expected = test[1];
     assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMoveSquaresGoingRight(){
        humanPlayer.calculateMoveSquares("STAR", "h7", "r");
        ArrayList<int[]> moveSquares = humanPlayer.getMoveSquares();
        ArrayList<int[]> testSquares = new ArrayList<>() {
            {
                add(0, new int[]{7,8});
                add(1, new int[]{7,9});
                add(2, new int[]{7,10});
                add(3, new int[]{7,11});
            }
        };
        int[] square = moveSquares.get(3);
        int actual = square[1];

        int[] test = testSquares.get(3);
        int expected = test[1];
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnTrueIfCentreSquareIsInMoveSquares(){
        humanPlayer.calculateMoveSquares("STAR", "h8", "d");
        ArrayList<int[]> moveSquares = humanPlayer.getMoveSquares();
        int[] centreSquare = board.getCentreSquare();
        assertTrue(humanPlayer.containsCentreSquare(moveSquares,centreSquare));
    }

    @Test
    void shouldReturnFalseIfCentreSquareIsNotInMoveSquares(){
        humanPlayer.calculateMoveSquares("STAR", "a1", "r");
        ArrayList<int[]> moveSquares = humanPlayer.getMoveSquares();
        int[] centreSquare = board.getCentreSquare();
        assertFalse(humanPlayer.containsCentreSquare(moveSquares,centreSquare));
    }

}