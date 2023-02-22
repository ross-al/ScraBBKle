package pij.main;

public interface Square {

    int convertPremiumLetter(String printLabel);
    int convertPremiumWord(String printLabel);
    int getPremiumLetterValue();
    int getPremiumWordValue();
    String getPrintLabel();
    void setTile(ScrabbkleTile tile);
    ScrabbkleTile getTile(ScrabbkleTile tile);

}
