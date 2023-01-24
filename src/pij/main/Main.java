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
        //Word myWord = new Word("aa");
        //System.out.println(myWord.isWord());
        WordList myWordList = new WordList();
        System.out.println(myWordList.isWord("zzzzza"));
        System.out.println(myWordList.isWord("zymurgies"));
        System.out.println(myWordList.isWord("deflocculates"));
        System.out.println(myWordList.isWord("fhwoifhvwvhwv"));
        System.out.println(myWordList.isWord("qi"));
        System.out.println(myWordList.isWord("QI"));
        //wordList is null??
        }

        //gameplay code here

}