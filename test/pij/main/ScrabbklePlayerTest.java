package pij.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbklePlayerTest {
    ScrabbkleBoard board;
    ScrabbkleTile tile1, tile2, tile3, tile4, tile5;
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
        tile1 = new ScrabbkleTile('A');
        tile2 = new ScrabbkleTile('B');
        tile3 = new ScrabbkleTile('C');
        tile4 = new ScrabbkleTile('D');
        tile5 = new ScrabbkleTile('E');
    }

    @Test
    void shouldReturnAboveTile(){
        // Tile that was already on the board
        board.getBoard()[7][8].setTile(tile1);
        // Tile that player places below the above tile during a move
        player.placeTile(tile2, 8, 8);
        ScrabbkleTile expected = tile1;
        ScrabbkleTile actual = board.getBoard()[8][8].getTile().getAboveTile();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnBelowTile(){
        // Tile that was already on the board
        board.getBoard()[7][8].setTile(tile1);
        // Tile that player places above tile1 during a move
        player.placeTile(tile2, 6, 8);
        ScrabbkleTile expected = tile1;
        ScrabbkleTile actual = board.getBoard()[6][8].getTile().getBelowTile();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnLeftTile(){
        // Tile that was already on the board
        board.getBoard()[7][8].setTile(tile1);
        // Tile that player places to right of tile1 during a move
        player.placeTile(tile2, 7, 9);
        ScrabbkleTile expected = tile1;
        ScrabbkleTile actual = board.getBoard()[7][9].getTile().getLeftTile();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnRightTile(){
        // Tile that was already on the board
        board.getBoard()[7][8].setTile(tile1);
        // Tile that player places to left of tile1 during a move
        player.placeTile(tile2, 7, 7);
        ScrabbkleTile expected = tile1;
        ScrabbkleTile actual = board.getBoard()[7][7].getTile().getRightTile();
        assertEquals(expected, actual);
    }
}