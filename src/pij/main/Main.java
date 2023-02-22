package pij.main;

public class Main {
    public static void main(String[] args) {
        Main launcher = new Main();
        launcher.launch();
    }

    public void launch() {
        ScrabbkleWordList myWordList = new ScrabbkleWordList();
        ScrabbkleWord myWord = new ScrabbkleWord("apple"); //only prints string. need Tile input?
        System.out.println(myWord); //won't print type Word
        System.out.println(myWordList.isWord(myWord)); //won't print type Word
        System.out.println(myWordList.isWord("aa"));


        //System.out.println(myTile.toString());
        //Tile myTile2 = new Tile('d', 2);
        //System.out.println(myTile2.toString());


        ScrabbkleBoard board = new ScrabbkleBoard();
        board.printBoard();
        System.out.println("Square 00: ");
        System.out.println(board.getMyBoard()[0][0].getPremiumLetterValue());
        System.out.println("Should be 3: ");
        System.out.println(board.getMyBoard()[1][1].getPremiumWordValue());

        System.out.println();
        System.out.println("input : Z");
        ScrabbkleTile myScrabbkleTile = new ScrabbkleTile('Z');
        System.out.println(myScrabbkleTile.getValue());
        board.getMyBoard()[1][1].setTile(myScrabbkleTile);
        System.out.println("expected : [Z10]");
        System.out.println(board.getMyBoard()[1][1].getPrintLabel());
        System.out.println("expected : Z");
        System.out.println(board.getMyBoard()[1][1].getTile(myScrabbkleTile).getLetter());
        System.out.println("expected : 10");
        System.out.println(board.getMyBoard()[1][1].getTile(myScrabbkleTile).getValue());
        ScrabbkleTile myScrabbkleTile2 = new ScrabbkleTile('O');
        board.getMyBoard()[2][1].setTile(myScrabbkleTile2);
        ScrabbkleTile myScrabbkleTile3 = new ScrabbkleTile('O');
        board.getMyBoard()[3][1].setTile(myScrabbkleTile3);

        board.printBoard();
        ScrabbkleTileBag tileBag = new ScrabbkleTileBag();
        System.out.println("bag is empty :" + tileBag.isEmtpy());
        System.out.println("bag contains: " + tileBag.getTileCounter() + " tiles");



        //gameplay code here

        }



}