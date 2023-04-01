package pij.main;

public class ScrabbkleTile implements Tile{
    private char letter;
    private final int value;
    private int premiumWordValue;
    private int premiumLetterValue;
    private boolean premiumWordValueUsed;
    private boolean premiumLetterValueUsed;
    private ScrabbkleTile leftTile;
    private ScrabbkleTile rightTile;
    private ScrabbkleTile aboveTile;
    private ScrabbkleTile belowTile;


    public ScrabbkleTile(char letter){
        this.letter = letter;
        this.value = calculateTileValue(this.letter);
        premiumWordValue = 1;
        premiumLetterValue = 1;
        premiumWordValueUsed = false;
        premiumLetterValueUsed = false;
    }


    public char getLetter(){
        return this.letter;
    }

    public int getValue(){
        return this.value;
    }

    public String getPrintInTileRackFormat(){
        return "[" + letter + value + "]";
    }

    public String getPrintOnBoardFormat(){
        return letter + String.valueOf(value);
    }

    public void assignWildCard(String str){
        char wildCard = str.charAt(0);
        this.letter = Character.toLowerCase(wildCard);

    }

    /*public void setRowAndCol(int row, int col){
        this.row = row;
        this.col = col;
    }*/

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

    public void setLeftTile(ScrabbkleTile leftTile) {
        this.leftTile = leftTile;
    }

    public ScrabbkleTile getLeftTile() {
        return leftTile;
    }

    public ScrabbkleTile getRightTile() {
        return rightTile;
    }

    public void setRightTile(ScrabbkleTile rightTile) {
        this.rightTile = rightTile;
    }

    public ScrabbkleTile getAboveTile() {
        return aboveTile;
    }

    public void setAboveTile(ScrabbkleTile aboveTile) {
        this.aboveTile = aboveTile;
    }

    public ScrabbkleTile getBelowTile() {
        return belowTile;
    }

    public void setBelowTile(ScrabbkleTile belowTile) {
        this.belowTile = belowTile;
    }


    public int getPremiumWordValue() {
        return premiumWordValue;
    }

    public void setPremiumWordValue(int premiumWordValue) {
        this.premiumWordValue = premiumWordValue;
    }

    public int getPremiumLetterValue() {
        return premiumLetterValue;
    }

    public void setPremiumLetterValue(int premiumLetterValue) {
        this.premiumLetterValue = premiumLetterValue;
    }

    public void setPremiumWordValueUsed(boolean bool){
        premiumWordValueUsed = bool;
    }

    public void setPremiumLetterValueUsed(boolean bool){
        premiumLetterValueUsed = bool;
    }

    public boolean isPremiumWordValueUsed() {
        return premiumWordValueUsed;
    }

    public boolean isPremiumLetterValueUsed() {
        return premiumLetterValueUsed;
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

//testing
//STARK = 9
//STAR = 4