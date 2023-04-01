package pij.main;

public class ScrabbkleSquare implements Square{

    private ScrabbkleTile scrabbkleTile;
    //private ScrabbkleTile nextTile;
    private ScrabbkleTile aboveTile;
    private ScrabbkleTile belowTile;
    private ScrabbkleTile leftTile;
    private ScrabbkleTile rightTile;
    private String printLabel;
    private String filePrintLabel;
    private int premiumLetterValue;
    private int premiumWordValue;
    private boolean premiumLetterUsed;
    private boolean premiumWordUsed;


    public ScrabbkleSquare(String printLabel){
        this.printLabel = printLabel; //String from input file
        this.filePrintLabel = this.printLabel; // used to remember original print label
        premiumLetterValue = convertPremiumLetter(this.printLabel);
        premiumWordValue = convertPremiumWord(this.printLabel);
        premiumLetterUsed = false;
        premiumWordUsed = false;
    }

    public int convertPremiumWord(String printLabel){
        String sub = String.valueOf(0);
        int value;
        if (printLabel.contains("(")){  //e.g. (3)
            if (printLabel.charAt(2) == ')') {
                sub = String.valueOf(printLabel.charAt(1)); //e.g. 3
            }
            if (printLabel.charAt(2) != ')'){ //e.g. (99
                sub = String.valueOf(printLabel.substring(1)); //e.g. 99
            }

         }
        value = Integer.parseInt(sub);
        return value;
    }

    public int convertPremiumLetter(String printLabel){
        String sub = String.valueOf(0);
        int value;
        if (printLabel.contains("{")){  //e.g. {3}
            if (printLabel.charAt(2) == '}') {
                sub = String.valueOf(printLabel.charAt(1)); //e.g. 3
            }
            if (printLabel.charAt(2) != '}'){ //e.g. {99
                sub = String.valueOf(printLabel.substring(1)); //e.g. 99
            }

        }
        value = Integer.parseInt(sub);
        return value;
    }

    public int getPremiumLetterValue(){
        return premiumLetterValue;
    }

    public int getPremiumWordValue(){
        return premiumWordValue;
    }

    public String getPrintLabel(){
        return printLabel;
    }

    public void setTile(ScrabbkleTile tile){
        scrabbkleTile = tile;
        setPrintLabel(tile);
    }

    public void setPrintLabel(ScrabbkleTile tile){
        printLabel = tile.getPrintOnBoardFormat();
    }
    public ScrabbkleTile getTile(){
        return scrabbkleTile;
    }

   /* public ScrabbkleTile getNextTile(){
        return nextTile;
    }

    public void setNextTile(ScrabbkleTile nextTile){
        this.nextTile = nextTile;
    }*/

    public void setAboveTile(ScrabbkleTile tile){
        this.aboveTile = tile;
    }

    public void setBelowTile(ScrabbkleTile tile){
        this.belowTile = tile;
    }

    public void setRightTile(ScrabbkleTile tile){
        this.rightTile = tile;
    }

    public void setLeftTile(ScrabbkleTile tile){
        this.leftTile = tile;
    }

    public ScrabbkleTile getAboveTile(){
        return aboveTile;
    }

    public ScrabbkleTile getBelowTile() {
        return belowTile;
    }

    public ScrabbkleTile getLeftTile() {
        return leftTile;
    }

    public ScrabbkleTile getRightTile() {
        return rightTile;
    }

    public void removeTile(){
        // Remove tile from the Square
       scrabbkleTile = null;
       // Reset print label
       printLabel = filePrintLabel;

    }

}
