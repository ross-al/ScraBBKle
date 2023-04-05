package pij.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbkleBoardTest {
    ScrabbkleBoard board;

    @BeforeEach
    void buildUp() {
        board = new ScrabbkleBoard();
        board.setBoardSize(15);
        String fileName = "./resources/defaultBoard.txt";
        File inputFile = new File(fileName);
        board.convertToBoard(inputFile);
    }

    @Test
    void shouldReturnLetterAFromColumnLabels() {
        String expected = String.valueOf('a');
        String actual = board.getBoard()[0][1].getPrintLabel();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnLetterOFromColumnLabels() {
        String expected = String.valueOf('o');
        String actual = board.getBoard()[0][15].getPrintLabel();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnInt1() {
        int expected = 1;
        int actual = Integer.parseInt(board.getBoard()[1][0].getPrintLabel());
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnInt15() {
        int expected = 15;
        int actual = Integer.parseInt(board.getBoard()[15][0].getPrintLabel());
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnPremiumLetterPrintLabel() {
        String expected = "{3}";
        String actual = board.getBoard()[1][1].getPrintLabel();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnPremiumWordPrintLabel() {
        String expected = "(2)";
        String actual = board.getBoard()[1][4].getPrintLabel();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnPremiumLetterIntValue() {
        int expected = 2;
        int actual = board.getBoard()[4][1].getPremiumLetterValue();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnPremiumWordIntValue() {
        int expected = 3;
        int actual = board.getBoard()[1][1].getPremiumWordValue();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCentreSquareIfOddBoard() {
        int expected = 8;
        int[] centreSquare = board.calculateCentreSquare(15);
        int actual = centreSquare[0];
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCentreSquareIfEvenBoard() {
        int expected = 7;
        int[] centreSquare = board.calculateCentreSquare(14);
        int actual = centreSquare[0];
        assertEquals(expected, actual);
    }
}
