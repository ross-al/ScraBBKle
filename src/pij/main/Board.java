package pij.main;

import java.io.*;
import java.util.Scanner;


//get file input and put into array, must have row/col labels
//check if file input is valid using method (i.e. size etc.)
//create constructor for board using array
//need a get file method for non-default boards

//needs to either load the defaultBoard (see txt file already provided)
//or needs to load a user-made board from a txt file

//method to iterate through board and store letter squares and letter words in hash map?
//would need to store index and bonus value

//need to print board
//need to update board with tiles (and print updated version)

public class Board {
    private File inputFile;
    private int boardSize;
    private int columns;
    private int rows;
    private String[][] myBoard;

    public Board() {
        this.inputFile = getInputFile();
        this.boardSize = getBoardSize();
        //prefer to handle if clause in method outside constructor...
        if(!(isValidSize())){
            System.out.println();
            System.out.println("Sorry, your board needs to be between 12-26 squares wide");
            getInputFile();
        }
        rows = 1+ boardSize;
        columns = rows;
        this.myBoard = new String[rows][columns];
        convertToBoard(myBoard);
    }

    //change array type to generic or Tile?
    public String[][] convertToBoard(String[][] myBoard) {
        for (int i = 0; i < myBoard.length; i++) {
            for (int j = 0; j < myBoard.length; j++) {
                myBoard[i][j] = "x";
            }
        }
        System.out.println("File name: " + inputFile);
        System.out.println("Board size: " + boardSize + "x" + boardSize);
        System.out.println("Valid board size: " + isValidSize());
        System.out.println("Your board is ready! GAME ON!");
        System.out.println();
        //printBoard(myBoard);
        return myBoard;
        }

        //getinput file
        //check size is ok
        //read second line onwards
        //iterate through to create array
        //columns must be represented by letter a-z
        //rows by numbers 1-26
        //left most column is a
        //top row is 1
        //need to group strings e.g. {1} . (2)

    public File getInputFile() {
        String fileName = "./resources/defaultBoard.txt";
        File inputFile = new File(fileName);
        String defaultBoard = "d";
        String loadBoard = "l";
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        System.out.println("Please enter your choice (l/d):");
        System.out.println();
        Scanner input = new Scanner(System.in);
        String boardType = input.nextLine().toLowerCase();
        while (!(boardType.equals(defaultBoard) || boardType.equals(loadBoard))) {
            System.out.println();
            System.out.println("Choice not recognised. Please enter your choice (l/d):");
            boardType = input.nextLine().toLowerCase();
        }
        if (boardType.equals(loadBoard)) {
            boolean legitFile = false;
            while (!legitFile) {
                System.out.println();
                System.out.println("Please enter the file name of the board: ");
                fileName = input.nextLine();
                inputFile = new File(fileName);
                if (inputFile.exists()) {
                    legitFile = true;
                    System.out.println();
                    System.out.println("Thank you. Your file is loading...");
                } else {
                    System.out.println("The file you entered does not exist");
                }
            }
        }
        return inputFile;
    }


    //need to test this method with diff file size
    public int getBoardSize() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String text = reader.readLine();
            try {
                boardSize = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                System.out.println("File not formatted correctly. First line must contain board size, e.g.'12'");
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return boardSize;
    }


    public boolean isValidSize() {
        return boardSize >= 12 && boardSize <= 26;//this is doesn't work
    }

    public void printBoard(String[][] board) {
        System.out.println("Your board: ");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public String[][] getMyBoard(){
        return myBoard;
    }
}
