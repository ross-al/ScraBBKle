package pij.main;



public class HumanPlayer extends ScrabbklePlayer {
    String playerName;
    int score;
    TileRack tileRack;

    public HumanPlayer(String name){
        this.playerName = name;
    }

    @Override
    public int calculateScore() {
        return 0;
    }

    @Override
    public int getCurrentScore() {
        return 0;
    }

    @Override
    public ScrabbkleTile fetchTile() {
        return null;
    }

    @Override
    public ScrabbkleTile placeTile() {
        return null;
    }

    @Override
    public ScrabbkleTile playWord() {
        return null;
    }
}
