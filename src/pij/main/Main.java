package pij.main;

public class Main {
    public static void main(String[] args) {
        Main launcher = new Main();
        launcher.launch();
    }

    public void launch() {
        System.out.println("hello world");
        Tile myTile = new Tile('c', 3);
        System.out.println(myTile.toString());
        Tile myTile2 = new Tile('d', 2);
        System.out.println(myTile2.toString());
        Word myWord = new Word("aa");
        System.out.println(myWord.isWord());

        //gameplay code here
    }
}