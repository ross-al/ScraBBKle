package pij.main;

/**
 * ScrabbkleTile is a tile
 * Players place tiles in a free square on a board
 */

public class ScrabbkleTile implements Tile {

    /**
     * letter of a tile
     */
    private char letter;

    /**
     * value of the letter score
     */
    private int value;

    /**
     * value of premium word score, can be between -9 and 99, score defaults to 1
     */
    private int premiumWordValue;

    /**
     * value of premium letter score, can be between -9 and 99, score defaults to 1
     */
    private int premiumLetterValue;

    /**
     * pointer for tile's left tile, can be null
     */
    private ScrabbkleTile leftTile;

    /**
     * pointer for tile's right tile, can be null
     */
    private ScrabbkleTile rightTile;

    /**
     * pointer for tile's above tile, can be null
     */
    private ScrabbkleTile aboveTile;

    /**
     * pointer for tile's below tile, can be null
     */
    private ScrabbkleTile belowTile;

    /**
     * flag to show if card is wildCard, i.e. no letter
     */
    private boolean isWildCard = false;

    /**
     * flag to show tile forms part of a valid  word on a board
     */
    private boolean connectsToExistingWord;


    /**
     * Contructor
     *
     * @param letter letter used to determine tile value
     */
    public ScrabbkleTile(char letter) {
        this.letter = letter;
        this.value = calculateTileValue(letter);
        premiumWordValue = 1;
        premiumLetterValue = 1;
    }


    /**
     * Assign a lower case letter to a tile to make it a wildcard
     *
     * @param c lowercase character used to assign wildcard letter
     */
    public void assignWildCard(char c) {
        this.letter = Character.toLowerCase(c);
        isWildCard = true;
    }


    /**
     * Remove a wildCard assignment when removing invalid moves from board
     */
    public void removeWildCard() {
        letter = ' ';
        isWildCard = false;
    }

    /**
     * Calculate tile value based on letter
     * default is wildcard
     *
     * @param letter letter used to calculate tile value, can be null which is a wildCard
     * @return int value of tile based on letter
     */
    public int calculateTileValue(char letter) {
        int value;
        switch (letter) {
            case 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'S', 'T', 'R' -> value = 1;
            case 'D', 'G' -> value = 2;
            case 'B', 'C', 'M', 'P' -> value = 3;
            case 'F', 'H', 'V', 'W', 'Y' -> value = 4;
            case 'K' -> value = 5;
            case 'J', 'X' -> value = 8;
            case 'Q', 'Z' -> value = 10;
            default -> {
                value = 3; //for WildCard
                isWildCard = true;
            }
        }
        return value;
    }


    public boolean isWildCard() {
        return isWildCard;
    }

    // Getters and Setters

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

    public void resetAboveTile() {
        aboveTile = null;
    }

    public void resetBelowTile() {
        belowTile = null;
    }

    public void resetLeftTile() {
        leftTile = null;
    }

    public void resetRightTile() {
        rightTile = null;
    }

    public char getLetter() {
        return this.letter;
    }

    public int getValue() {
        return this.value;
    }

    public String getPrintInTileRackFormat() {
        return "[" + letter + value + "]";
    }

    public String getPrintOnBoardFormat() {
        return letter + String.valueOf(value);
    }

    public boolean isConnectsToExistingWord() {
        return connectsToExistingWord;
    }

    public void setConnectsToExistingWord(boolean connectsToExistingWord) {
        this.connectsToExistingWord = connectsToExistingWord;
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