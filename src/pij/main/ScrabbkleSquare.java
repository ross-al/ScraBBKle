package pij.main;

/**
 *  A ScrabbkleSquare is a Square which we use to populate a board
 *  Players place tiles on a square
 */
public class ScrabbkleSquare implements Square {

    /** a tile that can be placed in a square*/
    private ScrabbkleTile scrabbkleTile;

    /** the print label for a square, will change when tile placed */
    private String printLabel;

    /** print label from input file, used to restore printLabel if tile removed */
    private final String filePrintLabel;

    /** a premium letter value for scoring*/
    private int premiumLetterValue;

    /** a premium word value for scoring */
    private int premiumWordValue;


    /**
     * Constructor
     * @param printLabel print label from board input file
     */
    public ScrabbkleSquare(String printLabel) {
        this.printLabel = printLabel; //String from input file
        this.filePrintLabel = this.printLabel; // used to remember original print label
        premiumLetterValue = convertPremiumLetter(this.printLabel);
        premiumWordValue = convertPremiumWord(this.printLabel);

    }


    /**
     * Convert file format to print label
     * @param printLabel label used to print
     * @return int value of print label
     */
    public int convertPremiumLetter(String printLabel) {
        String sub = String.valueOf(1);
        int value;
        if (printLabel.contains("(")) {  //e.g. (3)
            if (printLabel.charAt(2) == ')') {
                sub = String.valueOf(printLabel.charAt(1)); //e.g. 3
            }
            if (printLabel.charAt(2) != ')') { //e.g. (99
                sub = printLabel.substring(1); //e.g. 99
            }
            value = Integer.parseInt(sub);
        } else {
            value = 1;
        }
        return value;
    }



    /**
     * Convert file format to print label
     * @param printLabel label used to print
     * @return int value of print label
     */
    public int convertPremiumWord(String printLabel) {
        String sub = String.valueOf(1);
        int value;
        if (printLabel.contains("{")) {  //e.g. {3}
            if (printLabel.charAt(2) == '}') {
                sub = String.valueOf(printLabel.charAt(1)); //e.g. 3
            }
            if (printLabel.charAt(2) != '}') { //e.g. {99
                sub = printLabel.substring(1); //e.g. 99
            }
            value = Integer.parseInt(sub);
        } else {
            value = 1;
        }
        return value;
    }



    /**
     * Remove a tile from a square when move is invalid
     */
    public void removeTile() {
        // Remove tile from the Square
        scrabbkleTile = null;
        // Reset print label
        printLabel = filePrintLabel;
    }


    // Setters

    public void setTile(ScrabbkleTile tile) {
        if (tile != null) {
            scrabbkleTile = tile;
            setPrintLabel(tile);
        }
    }

    public void setPrintLabel(ScrabbkleTile tile) {
        printLabel = tile.getPrintOnBoardFormat();
    }

    // Getters

    public int getPremiumLetterValue() {
        return premiumLetterValue;
    }

    public int getPremiumWordValue() {
        return premiumWordValue;
    }

    public String getPrintLabel() {
        return printLabel;
    }

    public ScrabbkleTile getTile() {
        return scrabbkleTile;
    }

}
