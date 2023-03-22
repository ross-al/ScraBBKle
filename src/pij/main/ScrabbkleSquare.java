package pij.main;

public class ScrabbkleSquare implements Square{

    private ScrabbkleTile scrabbkleTile;
    private String printLabel; //must override printLabel with Tile if tile != null
    private int premiumLetterValue;
    private int premiumWordValue;


    public ScrabbkleSquare(String printLabel){
        scrabbkleTile = null;
        this.printLabel = printLabel; //String from input file
        premiumLetterValue = convertPremiumLetter(printLabel);
        premiumWordValue = convertPremiumWord(printLabel);
    }

    public int convertPremiumLetter(String printLabel){
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

    public int convertPremiumWord(String printLabel){
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
        this.scrabbkleTile = tile;
        printLabel = scrabbkleTile.getPrintOnBoardFormat();
    }

    public ScrabbkleTile getTile(){
        return scrabbkleTile;
    }

}
