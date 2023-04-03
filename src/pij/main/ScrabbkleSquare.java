package pij.main;

public class ScrabbkleSquare implements Square {

    private ScrabbkleTile scrabbkleTile;
    private String printLabel;
    private final String filePrintLabel;
    private int premiumLetterValue;
    private int premiumWordValue;


    public ScrabbkleSquare(String printLabel) {
        this.printLabel = printLabel; //String from input file
        this.filePrintLabel = this.printLabel; // used to remember original print label
        premiumLetterValue = convertPremiumLetter(this.printLabel);
        premiumWordValue = convertPremiumWord(this.printLabel);

    }


    // Convert file format to print label
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


    // Convert file format to print label
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


    // Remove a tile from a square when move is invalid
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
