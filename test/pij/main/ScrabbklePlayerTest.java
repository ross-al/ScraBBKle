package pij.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbklePlayerTest {
    ScrabbkleBoard board;
    ScrabbkleTile tileA, tileB, tileC, tileD, tileE;
    ScrabbklePlayer player;

    @BeforeEach
    void buildUp() {
        ScrabbkleWordList wordList = new ScrabbkleWordList();
        ScrabbkleTileBag tileBag = new ScrabbkleTileBag();
        board = new ScrabbkleBoard();
        player = new ScrabbklePlayer(board, wordList, tileBag);
        board.setBoardSize(15);
        String fileName = "./resources/defaultBoard.txt";
        File inputFile = new File(fileName);
        board.convertToBoard(inputFile);
        tileA = new ScrabbkleTile('A'); //value 1
        tileB = new ScrabbkleTile('B'); //value 3
        tileC = new ScrabbkleTile('C'); //value 3
        tileD = new ScrabbkleTile('D'); //value 2
        tileE = new ScrabbkleTile('E'); //value 1
        player.placeTile(tileA,1,1);
        player.placeTile(tileB,1,2);
        player.placeTile(tileC,1,3);
        player.placeTile(tileD,1,4);
        player.placeTile(tileE,1,5);
    }

    @Test
    void shouldReturnWordScoreOf36(){
        String moveDirection = "r";
        int tileCounter = 0;
        int expected = 36;
        int actual = player.calculateWordScore(moveDirection,1,1,tileCounter);
        assertEquals(expected,actual);
    }

}