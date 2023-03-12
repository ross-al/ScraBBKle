package pij.main;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        Main launcher = new Main();
        launcher.launch();
    }

    public void launch() {
        //create empty board based on default board or user provided board
        ScrabbkleBoard board = new ScrabbkleBoard();

        //create wordlist based on provided wordlist
        ScrabbkleWordList myWordList = new ScrabbkleWordList();

        //create tileBag and fill bag with tiles
        ScrabbkleTileBag tileBag = new ScrabbkleTileBag();
        tileBag.fillTileBag();

        //create one human player and one computer player
        HumanPlayer humanPlayer = new HumanPlayer(board, myWordList,tileBag);
        ComputerPlayer computerPlayer = new ComputerPlayer(board, myWordList,tileBag);

        //start game play
        boolean gameEnded = false;
        System.out.println();
        while (!gameEnded){
            humanPlayer.playMove();
            computerPlayer.playMove();
            gameEnded = true;
        }
        int humanFinalScore = humanPlayer.getPlayerScore();
        int computerFinalScore = computerPlayer.getPlayerScore();
        System.out.println("Game Over!");
        System.out.println("The human player scored " + humanFinalScore +" points.");
        System.out.println("The computer player scored " + computerFinalScore +" points.");
        if (humanFinalScore > computerFinalScore){
            System.out.println("The human player wins!");
        } else if (computerFinalScore > humanFinalScore){
            System.out.println("The computer player wins!");
        } else {
            System.out.println("It's a draw!");
        }













 /*       ScrabbkleWord myWord = new ScrabbkleWord("apple"); //only prints string. need Tile input?
        System.out.println(myWord); //won't print type Word
        System.out.println(myWordList.isWord(myWord)); //won't print type Word
        System.out.println(myWordList.isWord("aa"));*/


        //System.out.println(myTile.toString());
        //Tile myTile2 = new Tile('d', 2);
        //System.out.println(myTile2.toString());



    /*    board.printBoard();
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

        System.out.println("bag is empty: " + tileBag.isEmpty());
        System.out.println("bag contains: " + tileBag.getSize() + " tiles");*/



        }

}