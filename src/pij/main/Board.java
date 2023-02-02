package pij.main;

import java.io.*;
import java.util.Arrays;


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
    private String[][] myBoard;

    public Board() {
        this.myBoard = convertToBoard();//create constructor for board using array
    }

    public String[][] convertToBoard() {
        getInputFile();
        int columns = 1 + getBoardSize();
        int rows = 1 + getBoardSize();
        String[][] myArray = new String[columns][rows];
        System.out.println(Arrays.deepToString(myArray));
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
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        System.out.println("Please enter your choice (l/d):");
        String boardInput = System.console().readLine();
        String boardType = boardInput.toLowerCase();
        while (!(boardType.equals("l") || boardType.equals("d"))) {
            System.out.println("Choice not recognised. Please enter your choice (l/d):");
            boardInput = System.console().readLine(); //repeated code. Optimise!
            boardType = boardInput.toLowerCase();
        }
        if (!(boardType.equals("d"))) {
            boolean legitFile = false;
            while (!legitFile) {
                System.out.println("Please enter the file name of the board: ");
                fileName = System.console().readLine();
                if (inputFile.exists()) {
                    legitFile = true;
                    System.out.println("Thank you. Your file is loading");
                } else {
                    System.out.println("The file you entered does not exist");
                }
            }
        }
        if(!(isValidSize())){
            System.out.println("Sorry, your board needs to be between 12-26 squares wide");
            getInputFile();
        }
        return inputFile;
    }


    public int getBoardSize() {
        int boardSize = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(getInputFile()))) {
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
        return 12 >= getBoardSize() && getBoardSize() <= 26;
    }

}
