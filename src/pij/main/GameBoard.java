package pij.main;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;


//read second line onwards
//iterate through to create array
//need to group strings e.g. {1} . (2)

//method to iterate through board and store letter squares and letter words in hash map?
//would need to store index and bonus value

//need to update board with tiles (and print updated version)

public class GameBoard implements Board{
    private File inputFile;
    private int boardSize;
    private int columns;
    private int rows;
    private String[][] myBoard;

    public GameBoard() {
        this.inputFile = getInputFile(); //move this to main
        this.boardSize = getBoardSize(); //move this to main
        //prefer to handle if clause in method outside constructor...
        //also clearer if we remove conditions and keep simple
        //put most conditions in main?
        if (!(isValidSize())) {
            System.out.println();
            System.out.println("Sorry, your board needs to be between 12-26 squares wide");
            getInputFile();
        }
        rows = 1 + boardSize;
        columns = rows;
        this.myBoard = new String[rows][columns];
        convertToBoard(myBoard);
    }

    //change array type to generic or Tile?
    //convert file into arraylist, and then array list into 2d array?
    //ignore A[0][0] as this will have file size, so start from A[0][1]?
    public String[][] convertToBoard(String[][] myBoard) {
        myBoard[0][0] = "";
        char c = 'a';
        for (int a = 1; a < myBoard.length; a++) {
            String d = String.valueOf(c);
            myBoard[0][a] = d;
            c++;
        }
        for (int b = 1; b < myBoard.length; b++) {
            String q = Integer.toString(b);
            myBoard[b][0] = q;
        }
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                reader.readLine(); //to skip the first line with board size?
                String line;
                while ((line = reader.readLine()) != null) {
                        for (int i = 1; i < myBoard.length; i++) {
                            for (int j = 1; j < myBoard.length; j++) {
                                int charCount = 0;
                                String sub = "x";
                                if (line.charAt(charCount) == '{') {
                                    sub = line.substring(charCount, charCount + 3);
                                    charCount = charCount + 3;
                                }
                                else if (line.charAt(charCount) == '(') {
                                    sub = line.substring(charCount, charCount + 3);
                                    charCount = charCount + 3;
                                }
                              else if (line.charAt(charCount) == '.') {
                                   sub = line.substring(charCount, charCount + 1);
                                   charCount = charCount + 1;
                               }
                              myBoard[i][j] = sub;

                            }
                        }
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        System.out.println("File name: " + inputFile);
        System.out.println("Board size: " + boardSize + "x" + boardSize);
        System.out.println("Valid board size: " + isValidSize());
        System.out.println("Your board is ready! GAME ON!");
        System.out.println();
        return myBoard;
    }


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
        return boardSize >= 12 && boardSize <= 26;
    }


    public void printBoard(String[][] board) {
        System.out.println("Your current board: ");
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + "\t"); //can we right-justify row nums? + "\t"
            }
            System.out.println();
        }
    }


    public String[][] getMyBoard() {
        return myBoard;
    }
}
