package pij.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbklePlayerTest {
    ScrabbkleBoard board;
    ScrabbklePlayer player;
    ArrayList<ScrabbkleTile> tileRack;

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
    }

    @Test
    void shouldReturnWordScoreOf18(){
        ScrabbkleTile tileS = new ScrabbkleTile('S');
        ScrabbkleTile tileT = new ScrabbkleTile('T');
        ScrabbkleTile tileA = new ScrabbkleTile('A');
        ScrabbkleTile tileR = new ScrabbkleTile('R');
        ScrabbkleTile tileK = new ScrabbkleTile('K');

        player.placeTile(tileS, 7, 8);
        player.placeTile(tileT, 8, 8);
        player.placeTile(tileA, 9, 8);
        player.placeTile(tileR, 10, 8);
        player.placeTile(tileK, 11, 8);

        String moveDirection = "d";
        // move position is h7 {7,8}
        int row = 7;
        int col = 8;
        int tileCounter = 0;

        int actual = player.calculateWordScore(moveDirection, row, col, tileCounter);
        int expected = 18;
        assertEquals(expected, actual);
    }


    @Test
    void shouldReturnWordScoreOf30(){
        ScrabbkleTile tileS = new ScrabbkleTile('S');
        ScrabbkleTile tileT = new ScrabbkleTile('T');
        ScrabbkleTile tileA = new ScrabbkleTile('A');
        ScrabbkleTile tileR = new ScrabbkleTile('R');
        ScrabbkleTile tileK = new ScrabbkleTile('K');

        player.placeTile(tileS, 1, 1);
        player.placeTile(tileT, 2, 1);
        player.placeTile(tileA, 3, 1);
        player.placeTile(tileR, 4, 1);
        player.placeTile(tileK, 5, 1);

        String moveDirection = "d";

        int row = 1;
        int col = 1;
        int tileCounter = 0;

        int actual = player.calculateWordScore(moveDirection, row, col, tileCounter);
        int expected = 30;
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnWordScoreOf100With7TileBonus(){
        ScrabbkleTile tileS = new ScrabbkleTile('S');
        ScrabbkleTile tileT = new ScrabbkleTile('T');
        ScrabbkleTile tileA = new ScrabbkleTile('A');
        ScrabbkleTile tileR = new ScrabbkleTile('R');
        ScrabbkleTile tileK = new ScrabbkleTile('K');

        player.placeTile(tileS, 1, 1);
        player.placeTile(tileT, 2, 1);
        player.placeTile(tileA, 3, 1);
        player.placeTile(tileR, 4, 1);
        player.placeTile(tileK, 5, 1);

        String moveDirection = "d";

        int row = 1;
        int col = 1;
        int tileCounter = 7;

        int actual = player.calculateWordScore(moveDirection, row, col, tileCounter);
        int expected = 100;
        assertEquals(expected, actual);
    }


    @Test
    void shouldReturnPremiumWordValue() {

        // player placed tiles
        ScrabbkleTile tileS = new ScrabbkleTile('S');
        player.placeTile(tileS, 1, 1);
        int actual = tileS.getPremiumWordValue();
        int expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnPremiumLetterValue() {

        // player placed tiles
        ScrabbkleTile tileS = new ScrabbkleTile('S');
        player.placeTile(tileS, 7, 7);
        int actual = tileS.getPremiumLetterValue();
        int expected = 2;

        assertEquals(expected, actual);
    }


    @Test
    void shouldReturnTrueIfAllTilesAvailableInRack(){
        tileRack = new ArrayList<>();
        String moveWord = "BOOKKEEPING";
        ScrabbkleTile tileB = new ScrabbkleTile('B');
        ScrabbkleTile tileO = new ScrabbkleTile('O');
        ScrabbkleTile tileO2 = new ScrabbkleTile('O');
        ScrabbkleTile tileK= new ScrabbkleTile('K');
        ScrabbkleTile tileK2 = new ScrabbkleTile('K');
        ScrabbkleTile tileE = new ScrabbkleTile('E');
        ScrabbkleTile tileE2 = new ScrabbkleTile('E');
        ScrabbkleTile tileP = new ScrabbkleTile('P');
        ScrabbkleTile tileI = new ScrabbkleTile('I');
        ScrabbkleTile tileN = new ScrabbkleTile('N');
        ScrabbkleTile tileG = new ScrabbkleTile('G');

        tileRack.add(tileB);
        tileRack.add(tileO);
        tileRack.add(tileO2);
        tileRack.add(tileK);
        tileRack.add(tileK2);
        tileRack.add(tileE);
        tileRack.add(tileE2);
        tileRack.add(tileP);
        tileRack.add(tileI);
        tileRack.add(tileN);
        tileRack.add(tileG);

        assertTrue(player.hasAllTilesAvailable(moveWord,  tileRack));
    }

    @Test
    void shouldReturnFalseIfNotAllTilesAvailableInRack(){
        tileRack = new ArrayList<>();
        String moveWord = "BOOKKEEPING";
        ScrabbkleTile tileB = new ScrabbkleTile('B');
        ScrabbkleTile tileO = new ScrabbkleTile('O');
        ScrabbkleTile tileO2 = new ScrabbkleTile('O');
        ScrabbkleTile tileK= new ScrabbkleTile('K');
        //ScrabbkleTile tileK2 = new ScrabbkleTile('K');
        ScrabbkleTile tileE = new ScrabbkleTile('E');
        ScrabbkleTile tileE2 = new ScrabbkleTile('E');
        ScrabbkleTile tileP = new ScrabbkleTile('P');
        ScrabbkleTile tileI = new ScrabbkleTile('I');
        ScrabbkleTile tileN = new ScrabbkleTile('N');
        ScrabbkleTile tileG = new ScrabbkleTile('G');

        tileRack.add(tileB);
        tileRack.add(tileO);
        tileRack.add(tileO2);
        tileRack.add(tileK);
        //tileRack.add(tileK2);
        tileRack.add(tileE);
        tileRack.add(tileE2);
        tileRack.add(tileP);
        tileRack.add(tileI);
        tileRack.add(tileN);
        tileRack.add(tileG);

        assertFalse(player.hasAllTilesAvailable(moveWord,  tileRack));
    }

    @Test
    void shouldReturnTrueIfWildCardInRack(){
        tileRack = new ArrayList<>();
        String moveWord = " ";
        ScrabbkleTile wildCard = new ScrabbkleTile(' ');
        tileRack.add(wildCard);
        assertTrue(player.hasAllTilesAvailable(moveWord, tileRack));

    }


}