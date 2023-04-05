package pij.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    ScrabbkleBoard board;
    ComputerPlayer player;

    @BeforeEach
    void buildUp() {
        ScrabbkleWordList wordList = new ScrabbkleWordList();
        ScrabbkleTileBag tileBag = new ScrabbkleTileBag();
        board = new ScrabbkleBoard();
        player = new ComputerPlayer(board, wordList, tileBag);
        board.setBoardSize(15);
        String fileName = "./resources/defaultBoard.txt";
        File inputFile = new File(fileName);
        board.convertToBoard(inputFile);
    }

    @Test
    void shouldReturnRowAndColAsString() {
        int row = 7;
        int col = 8;
        String actual = board.convertRowAndColToString(row, col);
        String expected = "h7";
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnAllLetterCombinations() {
        String word = "abc";
        List<String> allCombinations = player.generateLetterCombinations(word);
        String actual = allCombinations.toString();
        String expected = "[a, ab, abc, ac, b, bc, c]";
        assertEquals(expected, actual);
    }

}