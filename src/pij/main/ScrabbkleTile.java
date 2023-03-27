package pij.main;

public class ScrabbkleTile implements Tile{
    private char letter;
    private int value;

    //constructor for letters
    public ScrabbkleTile(char letter){
        this.letter = letter;
        this.value = calculateTileValue(this.letter);
    }


    public char getLetter(){
        return this.letter;
    }

    public int getValue(){
        return this.value;
    }

    public String getPrintInTileRackFormat(){
        //char upperCaseLetter = Character.toUpperCase(letter); //we don't want this for WildCard tiles
        return "[" + letter + value + "]";
    }

    public String getPrintOnBoardFormat(){
        //char upperCaseLetter = Character.toUpperCase(letter); //we don't want this for WildCard tiles
        return letter + String.valueOf(value);
    }

    public void assignWildCard(String str){
        char wildCard = str.charAt(0);
        this.letter = Character.toLowerCase(wildCard);

    }

    public int calculateTileValue(char letter){ //need wildcard logic too
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