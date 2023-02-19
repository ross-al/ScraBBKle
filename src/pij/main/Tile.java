package pij.main;

public class Tile {
    //or can we use an array or hashmap instead?
    //do we need to instantiate each tile?
    private char letter;
    private int value;

    public Tile(char letter){ //value should come from map
        this.letter = letter;
       this.value = calculateTileValue(letter); //need wildcard logic, needs small letter 'w' not 'W' when played
    }

    public char getLetter(){
        return this.letter;
    }

    public int getValue(){
        return this.value;
    }

    public String toString(){
        char upperCaseLetter = Character.toUpperCase(letter); //we don't want this for WildCard tiles
        return "[" + upperCaseLetter + value + "]";
    }

    public int calculateTileValue(char letter){ //need wildcard logic too
        //need to cast to UpperCase?
        int value;
        switch (letter){
            case 'A':
            case 'E':
            case 'I':
            case 'O':
            case 'U':
            case 'L':
            case 'N':
            case 'S':
            case 'T':
            case 'R':
                value = 1;
                break;
            case 'D':
            case 'G':
                value = 2;
                break;
            case 'B':
            case 'C':
            case 'M':
            case 'P':
                value = 3;
                break;
            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y':
                value = 4;
                break;
            case 'K':
                value = 5;
                break;
            case 'J':
            case 'X':
                value = 8;
                break;
            case 'Q':
            case 'Z':
                value = 10;
                break;
            default:
                value = 0;
                break;
        }
        return value;
    }



}

//tile values:
//(1 point)-A, E, I, O, U, L, N, S, T, R.
//(2 points)-D, G.
//(3 points)-B, C, M, P.
//(4 points)-F, H, V, W, Y.
//(5 points)-K.
//(8 points)- J, X.
//(10 points)-Q, Z.