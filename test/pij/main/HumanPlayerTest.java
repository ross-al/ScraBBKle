package pij.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {
    ScrabbkleBoard board;
    ScrabbkleTile tile1, tile2, tile3, tile4, tile5;
    HumanPlayer humanPlayer;
    ScrabbkleWordList wordList;
    ScrabbkleTileBag tileBag;
    ArrayList<ScrabbkleTile> tileRack;

    @BeforeEach
    void buildUp(){
        board = new ScrabbkleBoard();
        board.setBoardSize(15);
        String fileName = "./resources/defaultBoard.txt";
        File inputFile = new File(fileName);
        board.convertToBoard(inputFile);
        tile1 = new ScrabbkleTile('I');
        tile2 = new ScrabbkleTile('T');
        tile3 = new ScrabbkleTile('F');
        tile4 = new ScrabbkleTile('E');
        tile5 = new ScrabbkleTile('E');
        wordList = new ScrabbkleWordList();
        tileBag = new ScrabbkleTileBag();
        humanPlayer = new HumanPlayer(board,wordList,tileBag);
        tileRack = new ArrayList<>();
        tileRack.add(tile1);
        tileRack.add(tile2);
        tileRack.add(tile3);
        tileRack.add(tile4);
        tileRack.add(tile5);
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

    @Test
    void shouldReturnTrueIfPlayerHasEnoughTiles(){
        char f = 'F';
        char e = 'E';
        char e2 = 'E';
        char t = 'T';
        ArrayList<Character> charsInWord = new ArrayList<>() {
            {
                add(0, f);
                add(1, e);
                add(2, e2);
                add(3, t);
            }
        };
        assertTrue(humanPlayer.hasAllTilesAvailable(charsInWord, tileRack));

    }

    @Test
    void shouldReturnFalseIfPlayerDoesNotHaveEnoughTiles(){
        char f = 'F';
        char e = 'E';
        char e2 = 'E';
        char t = 'T';
        char f2 = 'F';
        ArrayList<Character> charsInWord = new ArrayList<>() {
            {
                add(0, f);
                add(1, e);
                add(2, e2);
                add(3, t);
                add(4,f2);
            }
        };
        assertFalse(humanPlayer.hasAllTilesAvailable(charsInWord, tileRack));
    }

    @Test
    void shouldReturnTileFromRackWithMatchingLetter(){
        ScrabbkleTile testTile = new ScrabbkleTile('I');
        humanPlayer.getTileRack().add(testTile);
        ScrabbkleTile actual = humanPlayer.getTileFromRack('I');
        ScrabbkleTile expected = testTile;
        assertEquals(expected,actual);
    }

}