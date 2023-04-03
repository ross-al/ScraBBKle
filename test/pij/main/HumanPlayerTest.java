package pij.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HumanPlayerTest {
    ScrabbkleBoard board;
    ScrabbkleTile tileI, tileT, tileF, tileE, tileE2;
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
        tileI = new ScrabbkleTile('I');
        tileT = new ScrabbkleTile('T');
        tileF = new ScrabbkleTile('F');
        tileE = new ScrabbkleTile('E');
        tileE2 = new ScrabbkleTile('E');
        wordList = new ScrabbkleWordList();
        tileBag = new ScrabbkleTileBag();
        humanPlayer = new HumanPlayer(board,wordList,tileBag);
        tileRack = new ArrayList<>();
        tileRack.add(tileI);
        tileRack.add(tileT);
        tileRack.add(tileF);
        tileRack.add(tileE);
        tileRack.add(tileE2);
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
    void shouldReturnMoveSquaresGoingDown() {
        ArrayList<int[]> moveSquares = humanPlayer.calculateMoveSquares("STAR", "h7", "d");
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
        ArrayList<int[]> moveSquares = humanPlayer.calculateMoveSquares("STAR", "h7", "r");
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
        ArrayList<int[]> moveSquares = humanPlayer.calculateMoveSquares("STAR", "h8", "d");
        int[] centreSquare = board.getCentreSquare();
        assertTrue(humanPlayer.containsCentreSquare(moveSquares,centreSquare));
    }

    @Test
    void shouldReturnFalseIfCentreSquareIsNotInMoveSquares(){
        ArrayList<int[]> moveSquares = humanPlayer.calculateMoveSquares("STAR", "a1", "r");
        int[] centreSquare = board.getCentreSquare();
        assertFalse(humanPlayer.containsCentreSquare(moveSquares,centreSquare));
    }


    @Test
    void shouldReturnTileFromRackWithMatchingLetter(){
        ScrabbkleTile expected = new ScrabbkleTile('I');
        humanPlayer.getTileRack().add(expected);
        ScrabbkleTile actual = humanPlayer.getTileFromRack('I');
        assertEquals(expected,actual);
    }


    @Test
    void shouldReturnAboveTile(){
        ScrabbkleTile tileQ = new ScrabbkleTile('Q');
        ScrabbkleTile tileZ = new ScrabbkleTile('Z');
        // Tile that was already on the board
        board.getBoard()[7][8].setTile(tileQ);
        // Tile that player places below the above tile during a move
        humanPlayer.placeTile(tileZ, 8, 8);
        ScrabbkleTile expected = tileQ;
        ScrabbkleTile actual = board.getBoard()[8][8].getTile().getAboveTile();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnBelowTile(){
        ScrabbkleTile tileJ = new ScrabbkleTile('J');
        ScrabbkleTile tileK = new ScrabbkleTile('K');
        // Tile that was already on the board
        //board.getBoard()[3][3].setTile(tileJ);
        humanPlayer.placeTile(tileJ, 3,3);
        // Tile that player places above tile1 during a move
        humanPlayer.placeTile(tileK, 2, 3);
        ScrabbkleTile expected = tileJ;
        ScrabbkleTile actual = board.getBoard()[2][3].getTile().getBelowTile();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnLeftTile(){
        ScrabbkleTile tileM = new ScrabbkleTile('M');
        ScrabbkleTile tileP = new ScrabbkleTile('P');
        // Tile that was already on the board
        board.getBoard()[10][4].setTile(tileM);
        // Tile that player places to right of tile1 during a move
        humanPlayer.placeTile(tileP, 10, 5);
        ScrabbkleTile expected = tileM;
        ScrabbkleTile actual = board.getBoard()[10][5].getTile().getLeftTile();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnRightTile(){
        ScrabbkleTile tileB = new ScrabbkleTile('B');
        ScrabbkleTile tileH = new ScrabbkleTile('H');
        // Tile that was already on the board
        board.getBoard()[13][11].setTile(tileB);
        // Tile that player places to left of tile1 during a move
        humanPlayer.placeTile(tileH, 13, 10);
        ScrabbkleTile expected = tileB;
        ScrabbkleTile actual = board.getBoard()[13][10].getTile().getRightTile();
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnFinalWordGoingDown() {
        //tiles on board already at {8,7} and {8,8}
        humanPlayer.placeTile(tileT, 8, 8);
        // player placed tiles
        ScrabbkleTile tileS = new ScrabbkleTile('S');
        ScrabbkleTile tileA = new ScrabbkleTile('A');
        ScrabbkleTile tileR = new ScrabbkleTile('R');
        // move position is h7 {7,8}
        humanPlayer.placeTile(tileS, 7, 8);
        humanPlayer.placeTile(tileA, 9, 8);
        humanPlayer.placeTile(tileR, 10, 8);
        String actual = humanPlayer.calculateFinalWord("h7","d");
        String expected = "STAR";
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnFinalWordGoingRight() {
        humanPlayer.placeTile(tileI, 8, 7);
        humanPlayer.placeTile(tileT, 8, 8);
        // player placed tiles
        ScrabbkleTile tileL = new ScrabbkleTile('L');
        ScrabbkleTile tileT2 = new ScrabbkleTile('T');
        ScrabbkleTile tileL2 = new ScrabbkleTile('L');
        ScrabbkleTile tileE = new ScrabbkleTile('E');
        // move position is f8 {8,6}

        humanPlayer.placeTile(tileL, 8, 6);
        humanPlayer.placeTile(tileT2, 8, 9);
        humanPlayer.placeTile(tileL2, 8, 10);
        humanPlayer.placeTile(tileE, 8, 11);

        String actual = humanPlayer.calculateFinalWord("f8","r");
        String expected = "LITTLE";
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnFinalWordGoingDownWithManuallySetNeighbourTiles(){
        //tiles on board already at {8,7} and {8,8}
        board.getBoard()[8][8].setTile(tileT);
        // player placed tiles
        ScrabbkleTile tileS = new ScrabbkleTile('S');
        ScrabbkleTile tileA = new ScrabbkleTile('A');
        ScrabbkleTile tileR = new ScrabbkleTile('R');
        // move position is h7 {7,8}
        board.getBoard()[7][8].setTile(tileS);
        board.getBoard()[9][8].setTile(tileA);
        board.getBoard()[10][8].setTile(tileA);
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

        String actual = humanPlayer.calculateFinalWord("h7","d");
        String expected = "STAR";
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnFinalWordGoingRightWithManuallySetNeighbourTiles() {
        //tiles on board already at {8,7} and {8,8}
        board.getBoard()[8][7].setTile(tileI);
        board.getBoard()[8][8].setTile(tileT);
        // player placed tiles
        // player placed tiles
        ScrabbkleTile tileL = new ScrabbkleTile('L');
        ScrabbkleTile tileT2 = new ScrabbkleTile('T');
        ScrabbkleTile tileL2 = new ScrabbkleTile('L');
        ScrabbkleTile tileE = new ScrabbkleTile('E');

        // move position is f8 {8,6}
        board.getBoard()[8][6].setTile(tileL);
        board.getBoard()[8][9].setTile(tileT2);
        board.getBoard()[8][10].setTile(tileL2);
        board.getBoard()[8][11].setTile(tileE);
        //manually set neighbour tiles

        // Tile L
        //board.getBoard()[8][6].getTile().setLeftTile();
        board.getBoard()[8][6].getTile().setRightTile(tileI);

        // Tile I
        board.getBoard()[8][7].getTile().setLeftTile(tileL);
        board.getBoard()[8][7].getTile().setRightTile(tileT);

        // Tile T
        board.getBoard()[8][8].getTile().setLeftTile(tileI);
        board.getBoard()[8][8].getTile().setRightTile(tileT2);

        // Tile T2
        board.getBoard()[8][9].getTile().setLeftTile(tileT2);
        board.getBoard()[8][9].getTile().setRightTile(tileL2);

        // Tile L2
        board.getBoard()[8][10].getTile().setLeftTile(tileT2);
        board.getBoard()[8][10].getTile().setRightTile(tileE);

        // Tile E
        board.getBoard()[8][11].getTile().setLeftTile(tileT2);
        //board.getBoard()[8][11].getTile().setRightTile();

        String actual = humanPlayer.calculateFinalWord("f8","r");
        String expected = "LITTLE";
        assertEquals(expected, actual);
    }


    @Test
    void shouldReturnFinalWordWhenOnlyPlacingOneTileAtBottomOfExistingWord(){
        //e.g. word on board: STAR
        //new word on board: STAR + S

        ScrabbkleTile tileS = new ScrabbkleTile('S');
        ScrabbkleTile tileA = new ScrabbkleTile('A');
        ScrabbkleTile tileR = new ScrabbkleTile('R');
        ScrabbkleTile tileS2 = new ScrabbkleTile('S');

        //tiles on board already at {8,7} and {8,8}
        humanPlayer.placeTile(tileS, 7, 8);
        humanPlayer.placeTile(tileT, 8, 8);
        humanPlayer.placeTile(tileA, 9, 8);
        humanPlayer.placeTile(tileR, 10, 8);

        // player placed tiles
        humanPlayer.placeTile(tileS2, 11, 8);

        // Place tile at bottom of word in position h11 {11,8}
        String actual = humanPlayer.calculateFinalWord("h11","d");
        String expected = "STARS";
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnScoreOf3(){
        int actual = humanPlayer.getPlayerScore();
    }
}