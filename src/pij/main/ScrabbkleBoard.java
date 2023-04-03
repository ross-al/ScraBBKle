package pij.main;

import org.jetbrains.annotations.VisibleForTesting;

import java.io.*;
import java.util.Scanner;


public class ScrabbkleBoard implements Board {
    private int boardSize;
    private ScrabbkleSquare[][] board;
    private int[] centreSquare;

    // Ask for player board type choice
    public String getBoardChoice() {
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        System.out.println("Please enter your choice (l/d):");
        System.out.println();
        Scanner input = new Scanner(System.in);
        return input.nextLine().toLowerCase();
    }

    // Calculate input file
    public File getInputFile(String boardChoice) {
        // All board files should be in ./resources:
        String defaultPath = "." + File.separator + "resources" + File.separator;
        // Default file name:
        // default file is "./resources/defaultBoard.txt";
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

    // Check inputFile exists in resources folder
    public boolean inputFileExists(File inputFile) {
        return (inputFile.exists());
    }

    // Calculate the board size by reading first line in file
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

    // Check if loaded board meets size requirements
    public boolean isValidSize() {
        return boardSize >= 12 && boardSize <= 26;
    }


    // Convert the txt file into a board
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

    // To print updated board after every move
    public void printBoard() {
        System.out.println();
        System.out.println("Your current board: ");
        System.out.println();
        for (ScrabbkleSquare[] scrabbkleSquares : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(scrabbkleSquares[j].getPrintLabel() + "\t");
            }
            System.out.println();
        }
    }


    // Calculate the centre square for human player's first move
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
    @VisibleForTesting
    public void setBoardSize(int size) {
        this.boardSize = size;
    }
}

//test files (all validated):
// ./resources/testLoadedBoard.txt
// ./resources/testLoadedBoard2.txt
// ./resources/testBoardShouldFail.txt
// ./resources/defaultBoard.txt