package pij.main;

public class ScrabbkleTile implements Tile{
    private char letter;
    private int value;

    public ScrabbkleTile(char letter){
        this.letter = Character.toUpperCase(letter);
        this.value = calculateTileValue(this.letter); //need wildcard logic, needs small letter 'w' not 'W' when played
    }

    public char getLetter(){
        return this.letter;
    }

    public int getValue(){
        return this.value;
    }

    public String getPrintInTileRackFormat(){
        char upperCaseLetter = Character.toUpperCase(letter); //we don't want this for WildCard tiles
        return "[" + upperCaseLetter + value + "]";
    }

    public String getPrintOnBoardFormat(){
        char upperCaseLetter = Character.toUpperCase(letter); //we don't want this for WildCard tiles
        return upperCaseLetter + String.valueOf(value);
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
                value = 3; //for WildCard
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
//(3 points) Wildcard