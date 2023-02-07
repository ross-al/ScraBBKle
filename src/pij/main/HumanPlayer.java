package pij.main;

public class HumanPlayer implements Player {
    String playerName;
    int score;
    Tile tile;

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
    public Tile fetchTile() {
        return null;
    }

    @Override
    public Tile placeTile() {
        return null;
    }

    @Override
    public Tile playWord() {
        return null;
    }
}
