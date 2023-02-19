package pij.main;

public class Square {

    private Tile tile;
    private String premiumLabel;
    private int premiumLetter;
    private int premiumWord;

    public Square(String premiumLabel){
        tile = null;
        this.premiumLabel = premiumLabel; //String from input file
        premiumLetter = convertPremiumLetter(premiumLabel);
        premiumWord = convertPremiumWord(premiumLabel);
    }

    public int convertPremiumLetter(String premiumLabel){
        String sub = String.valueOf(0);
        int value;
        if (premiumLabel.contains("("){  //e.g. (3)
            if (premiumLabel.charAt(2) == ')') {
                sub = String.valueOf(premiumLabel.charAt(1)); //e.g. 3
            }
            if (premiumLabel.charAt(2) != ')'){ //e.g. (99
                sub = String.valueOf(premiumLabel.substring(1)); //e.g. 99
            }

         }
        value = Integer.parseInt(sub);
        return value;
    }

    public int convertPremiumWord(String premiumWord){
        String sub = String.valueOf(0);
        int value;
        if (premiumWord.contains("{"){  //e.g. {3}
            if (premiumWord.charAt(2) == '}') {
                sub = String.valueOf(premiumWord.charAt(1)); //e.g. 3
            }
            if (premiumWord.charAt(2) != '}'){ //e.g. {99
                sub = String.valueOf(premiumWord.substring(1)); //e.g. 99
            }

        }
        value = Integer.parseInt(sub);
        return value;
    }

}
