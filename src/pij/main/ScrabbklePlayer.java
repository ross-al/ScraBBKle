package pij.main;

abstract class ScrabbklePlayer implements Player{ //abstract class? need to be public?

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
