package pij.main;

import java.util.ArrayList;
import java.util.Collections;


/**
 * ScrabbkleTileBag is a TileBag that contains 100 tiles for the game
 */


public class ScrabbkleTileBag implements TileBag {

    /**
     * a list of all the tiles in a bag
     */
    private ArrayList<ScrabbkleTile> scrabbkleTileBag;

    /**
     * Constructor
     */
    public ScrabbkleTileBag() {
        scrabbkleTileBag = new ArrayList<>();
    }


    public int getSize() {
        return scrabbkleTileBag.size();
    }

    public boolean isEmpty() {
        return (!(getSize() > 0));
    }

    /**
     * Shuffles the tile bag
     */
    public void shuffleBag() {
        Collections.shuffle(scrabbkleTileBag);
    }


    /**
     * Returns a random tile from a shuffled bag
     *
     * @return a tile from the bag
     */
    @Override
    public ScrabbkleTile getRandomTile() {
        shuffleBag();
        return scrabbkleTileBag.remove(0); //need to consider IndexOutOfBounds
    }

    /**
     * Fill the tile bag with 100 tiles
     */
    public void fillTileBag() {
        for (int i = 0; i < 12; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('E');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 9; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('A');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 9; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('I');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 8; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('O');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 6; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('N');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 6; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('R');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 6; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('T');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 4; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('L');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 4; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('S');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 4; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('U');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 4; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('D');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 3; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('G');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('B');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('C');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('M');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('P');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('F');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('H');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('V');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('W');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('Y');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 1; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('K');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 1; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('J');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 1; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('X');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 1; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('Q');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 1; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('Z');
            scrabbkleTileBag.add(scrabbkleTile);
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile(' '); //wildcards
            scrabbkleTileBag.add(scrabbkleTile);
        }
    }
}


//Quantity (tileBag must contain 100 tiles at start):
//2 blank tiles (Wildcards)
//E ×12, A ×9, I ×9, O ×8, N ×6, R ×6, T ×6, L ×4, S ×4, U ×4
//D ×4, G ×3
//B ×2, C ×2, M ×2, P ×2
//F ×2, H ×2, V ×2, W ×2, Y ×2
//K ×1
//J ×1, X ×1
//Q ×1, Z ×1
