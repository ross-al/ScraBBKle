package pij.main;

import java.io.*;
import java.util.Scanner;

/**
 * ScrabbkleBoard implements Board. Used during game to place tiles on board.
 * ScrabbkleBoard creates a 2D array of ScrabbkleSquares as the board
 */


public class ScrabbkleBoard implements Board {

    /**
     * the size of the board, must be between 12-26
     */
    private int boardSize;

    /**
     * 2D array of ScrabbkleSquares
     */
    private ScrabbkleSquare[][] board;

    /**
     * position of centre square for given board size
     */
    private int[] centreSquare;


    /**
     * Ask for player board type choice
     *
     * @return player input as string
     */
    public String getBoardChoice() {
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        System.out.println("Please enter your choice (l/d):");
        System.out.println();
        Scanner input = new Scanner(System.in);
        return input.nextLine().toLowerCase();
    }

    /**
     * Calculate input file
     *
     * @param boardChoice user input for default or loaded board
     * @return input file for board
     */
    public File getInputFile(String boardChoice) {
        // All board files should be in ./resources:
        String defaultPath = "." + File.separator + "resources" + File.separator;
        // Default file is: "./resources/defaultBoard.txt";
        String fileName = defaultPath + "defaultBoard.txt";
        File inputFile = new File(fileName);
        String loadBoard = "l";
        // If player selects to load a board, get new file name
        if (boardChoice.equals(loadBoard)) {
            System.out.println();
            System.out.println("Please enter the file name of the board: ");
            Scanner input = new Scanner(System.in);
            String userFileName = input.nextLine();
            String loadFileName = defaultPath + userFileName;
            inputFile = new File(loadFileName);
        }
        return inputFile;
    }


    /**
     * Check inputFile exists in resources folder
     *
     * @param inputFile file to check
     * @return boolean true if file exists
     */
    public boolean inputFileExists(File inputFile) {
        return (inputFile.exists());
    }


    /**
     * Calculate the board size by reading first line in file
     *
     * @param inputFile file from which to read size on first line
     * throws exception if file not found
     */
    public void calculateBoardSize(File inputFile) {
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
    }


    /**
     * Check if loaded board meets size requirements
     *
     * @return boolean true if between or equal to 12-26
     */
    public boolean isValidSize() {
        return boardSize >= 12 && boardSize <= 26;
    }


    /**
     * Convert the txt file into a board
     *
     * @param inputFile file from which to load board
     */
    public void convertToBoard(File inputFile) {
        int rows = 1 + boardSize;
        int columns = rows;
        this.board = new ScrabbkleSquare[rows][columns];
        board[0][0] = new ScrabbkleSquare(" ");
        char c = 'a';
        for (int a = 1; a < board.length; a++) {
            String d = String.valueOf(c);
            board[0][a] = new ScrabbkleSquare(d);
            c++;
        }
        for (int b = 1; b < board.length; b++) {
            String q = Integer.toString(b);
            board[b][0] = new ScrabbkleSquare(q);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            reader.readLine(); //to skip the first line with board size
            String line;
            String sub = "x";
            for (int i = 1; i < board.length; i++) {
                int charCount = 0;
                line = reader.readLine();
                for (int j = 1; j < board.length; j++) {
                    char ch = line.charAt(charCount);
                    switch (ch) {
                        case '{', '(' -> {
                            sub = line.substring(charCount, charCount + 3);
                            charCount = charCount + 3;
                        }
                        case '.' -> {
                            sub = line.substring(charCount, charCount + 1);
                            charCount = charCount + 1;
                        }
                    }
                    board[i][j] = new ScrabbkleSquare(sub);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        centreSquare = calculateCentreSquare(boardSize);
    }


    /**
     * To print updated board after every move
     */
    public void printBoard() {
        System.out.println("Your current board: ");
        System.out.println();
        for (ScrabbkleSquare[] scrabbkleSquares : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(scrabbkleSquares[j].getPrintLabel() + "\t");
            }
            System.out.println();
        }
    }


    /**
     * Calculate the centre square for human player's first move
     *
     * @param boardSize size of given board
     * @return int[] of centre square row & col positions
     */
    public int[] calculateCentreSquare(int boardSize) {
        int row;
        int col;
        // If board size is even:
        if (boardSize % 2 == 0) {
            row = boardSize / 2;
            col = boardSize / 2;
        }
        // If board size is odd:
        else {
            int input = boardSize + 1;
            row = input / 2;
            col = row;
        }
        centreSquare = new int[]{row, col};
        return centreSquare;
    }

    /**
     * Print the centre square
     *
     * @param centreSquare int[] containing int row and col positions
     * @return String value of centreSquare, e.g. 'h7'
     */
    public String printCentreSquare(int[] centreSquare) {
        int row = centreSquare[0];
        int col = centreSquare[1];
        return convertRowAndColToString(row, col);
    }


    /**
     * Calculate movePosition (e.g. 'h7') based on given int row and int col
     *
     * @param row index for row in board
     * @param col index for column in board
     * @return String concat value of col and row, e.g. h7
     */
    public String convertRowAndColToString(int row, int col) {
        char c = getColumnLetter(col);
        String r = String.valueOf(Integer.valueOf(row));
        return c + r;
    }

    /**
     * Convert the int provided into a letter for board index position
     *
     * @param col int value to be converted to char
     * @return char value of int
     */
    public char getColumnLetter(int col) {
        return (char) ('a' + col - 1);
    }



    // Getters

    public ScrabbkleSquare[][] getBoard() {
        return board;
    }

    public int[] getCentreSquare() {
        return this.centreSquare;
    }

    public int getBoardSize() {
        return boardSize;
    }


    // used for JUnit testing only
    public void setBoardSize(int size) {
        this.boardSize = size;
    }

}

//test files (all validated):
// ./resources/testLoadedBoard.txt
// ./resources/testLoadedBoard2.txt
// ./resources/testBoardShouldFail.txt
// ./resources/defaultBoard.txt