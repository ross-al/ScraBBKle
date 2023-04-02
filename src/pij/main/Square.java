package pij.main;

public interface Square {

    /**
     *
     * @param printLabel
     * @return
     */

    int convertPremiumLetter(String printLabel);
    int convertPremiumWord(String printLabel);
    int getPremiumLetterValue();
    int getPremiumWordValue();
    String getPrintLabel();
    void setTile(ScrabbkleTile tile);
    ScrabbkleTile getTile();

}
