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
    }

    @Test
    void shouldReturnWordScoreOfXX(){
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
    void shouldReturnWordScoreOfXXUsingManualNeighbouringTiles() {

        // player placed tiles
        ScrabbkleTile tileS = new ScrabbkleTile('S');
        ScrabbkleTile tileT = new ScrabbkleTile('T');
        ScrabbkleTile tileA = new ScrabbkleTile('A');
        ScrabbkleTile tileR = new ScrabbkleTile('R');
        // move position is h7 {7,8}
        board.getBoard()[7][8].setTile(tileS);
        board.getBoard()[8][8].setTile(tileT);
        board.getBoard()[9][8].setTile(tileA);
        board.getBoard()[10][8].setTile(tileR);
        //manually set neighbour tiles

        // Tile S, above tile = null, below tile = T
        //board.getBoard()[7][8].getTile().setAboveTile();
        board.getBoard()[7][8].getTile().setBelowTile(tileT);

        // Tile T, above tile = S, below tile = A
        board.getBoard()[8][8].getTile().setAboveTile(tileS);
        board.getBoard()[8][8].getTile().setBelowTile(tileA);

        // Tile A, above tile = T, below tile = R
        board.getBoard()[9][8].getTile().setAboveTile(tileT);
        board.getBoard()[9][8].getTile().setBelowTile(tileR);

        // Tile R, above tile = A, below tile = null
        board.getBoard()[10][8].getTile().setAboveTile(tileA);
        //board.getBoard()[9][8].getTile().setBelowTile();

        String moveDirection = "d";
        // move position is h7 {7,8}
        int row = 7;
        int col = 8;
        int tileCounter = 0;

        int actual = player.calculateWordScore(moveDirection, row, col, tileCounter);
        int expected = 8;

        assertEquals(expected, actual);
    }
}