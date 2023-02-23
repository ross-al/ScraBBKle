package pij.main;

import java.util.ArrayList;


public class ScrabbkleTileBag implements TileBag{
    private ArrayList<ScrabbkleTile> scrabbkleTileBag;
    private int tileCounter;

    public ScrabbkleTileBag(){
        scrabbkleTileBag = new ArrayList<>();
        tileCounter = 0;
    }

    //use a map for tile counter, map can sum all?
    //how many starting letters?
    //need counter for how many letters left
    //do we need to track used letters? e.g. usedLetters()

    public int getTileCounter(){
        return this.tileCounter; //can we get ArrayList size() instead?
    }

    public boolean isEmpty(){
        return (!(tileCounter > 0));
    }

    public void fillTileBag() {
        for (int i = 0; i < 12; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('E');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 9; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('A');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 9; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('I');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 8; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('O');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 6; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('N');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 6; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('R');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 6; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('T');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 4; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('L');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 4; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('S');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 4; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('U');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 4; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('D');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 3; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('G');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('B');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('C');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('M');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('P');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('F');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('H');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('V');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('W');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 2; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('Y');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 1; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('K');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 1; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('J');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 1; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('X');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 1; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('Q');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
        for (int i = 0; i < 1; i++) {
            ScrabbkleTile scrabbkleTile = new ScrabbkleTile('Z');
            scrabbkleTileBag.add(scrabbkleTile);
            tileCounter++;
        }
    }
}


//

//randomise bag?

//Quantity (tileBag must contain 100 tiles at start):
//2 blank tiles (Wildcards)
//E ×12, A ×9, I ×9, O ×8, N ×6, R ×6, T ×6, L ×4, S ×4, U ×4
//D ×4, G ×3
//B ×2, C ×2, M ×2, P ×2
//F ×2, H ×2, V ×2, W ×2, Y ×2
//K ×1
//J ×1, X ×1
//Q ×1, Z ×1
