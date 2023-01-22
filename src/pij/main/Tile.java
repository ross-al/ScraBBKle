package pij.main;

public class Tile {
    //or can we use an array or hashmap instead?
    //do we need to instantiate each tile?
    private char letter;
    private int value;

    public Tile(char letter, int value){
        this.letter = letter;
        this.value = value;
    }

    public char getLetter(){
        return this.letter;
    }

    public int getValue(){
        return this.value;
    }

    public String toString(){
        char upperCaseLetter = Character.toUpperCase(letter); //we don't want this for WildCard tioles
        return "[" + upperCaseLetter + value + "]";
    }

}

    //tile scores:
    //(1 point)-A, E, I, O, U, L, N, S, T, R.
    //(2 points)-D, G.
    //(3 points)-B, C, M, P.
    //(4 points)-F, H, V, W, Y.
    //(5 points)-K.
    //(8 points)- J, X.
    //(10 points)-Q, Z.