package pij.main;

import java.io.*;
import java.util.Arrays;
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
    private int[][] myBoard; //change array type to tile?
    private File inputFile;
    private int boardSize;
    private int columns;
    private int rows;

    public Board() {
        this.inputFile = getInputFile();
        this.boardSize = getBoardSize();
        if(!(isValidSize())){
            System.out.println();
            System.out.println("Sorry, your board needs to be between 12-26 squares wide");
            getInputFile();
        }
        else {
            this.myBoard = convertToBoard();
            rows = 1 + boardSize;
            columns = rows;
        }
    }

    //change array type to generic?
    public int[][] convertToBoard() {
        //int columns = 1 + boardSize;
        //int rows = 1 + boardSize;
        int[][] myArray = new int[rows][columns];
        System.out.println("File name: " + inputFile);
        System.out.println("Size: " + boardSize);
        System.out.println("Valid size: " + isValidSize());
        return myArray;
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
        String boardInput = input.nextLine();
        String boardType = boardInput.toLowerCase();
        while (!(boardType.equals(defaultBoard) || boardType.equals(loadBoard))) {
            System.out.println();
            System.out.println("Choice not recognised. Please enter your choice (l/d):");
            boardInput = input.nextLine(); //repeated code. Optimise!
            boardType = boardInput.toLowerCase();
        }
        if (boardType.equals(loadBoard)) {
            boolean legitFile = false;
            while (!legitFile) {
                System.out.println();
                System.out.println("Please enter the file name of the board: ");
                fileName = input.nextLine();
                if (inputFile.exists()) { //this doesn't work
                    legitFile = true;
                    System.out.println();
                    System.out.println("Thank you. Your file is loading");
                } else {
                    System.out.println("The file you entered does not exist");
                }
            }
        }
        return inputFile;
    }


    //need to test this method with diff file size
    public int getBoardSize() {
        int boardSize = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String text = reader.readLine();
            try {
                int size = Integer.parseInt(text);
                boardSize = size;
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

/*    public void printBoard() {
*//*        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                myBoard[i][j] = 0;*//*

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(myBoard[i][j]);
            }
            System.out.println();
        }
    }*/
}
