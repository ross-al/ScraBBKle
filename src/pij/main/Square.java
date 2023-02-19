package pij.main;

public class Square {

    private Tile tile;
    protected String printLabel; //maybe just change to label so can have rows etc
    private int premiumLetterValue;
    private int premiumWordValue;


    public Square(String printLabel){
        tile = null;
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

}
