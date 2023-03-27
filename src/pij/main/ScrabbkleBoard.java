package pij.main;

import java.io.*;
import java.util.Scanner;

//NEED TO FIX MESSAGING
//WHILE FALSE LOOP:
//"This is not a valid file. Please enter the file name of the board:"


public class ScrabbkleBoard implements Board {
    private File inputFile;
    private int boardSize;
    private ScrabbkleSquare[][] board;
    private int[] startSquare;

    // Ask for player board type choice

    public String getBoardChoice(){
        System.out.println("Would you like to _l_oad a board or use the _d_efault board?");
        System.out.println("Please enter your choice (l/d):");
        System.out.println();
        Scanner input = new Scanner(System.in);
        String boardChoice = input.nextLine().toLowerCase();
        return boardChoice;
    }

    // Calculate input file

    public File getInputFile(String boardChoice) {
        // Default file name:
        String fileName = "./resources/defaultBoard.txt";
        inputFile = new File(fileName);
        String defaultBoard = "d";
        String loadBoard = "l";
        // If player selects to load a board, get new file name
        if (boardChoice.equals(loadBoard)) {
            System.out.println();
            System.out.println("Please enter the file name of the board: ");
            Scanner input = new Scanner(System.in);
            fileName = input.nextLine();
            inputFile = new File(fileName);
        }
        return inputFile;
    }

    // Check inputFile exists in resources folder

    public boolean inputFileExists(File inputFile){
        return(inputFile.exists());
    }

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

    public boolean isValidSize() {
        return boardSize >= 12 && boardSize <= 26;
    }


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
                        case '{':
                        case '(':
                            sub = line.substring(charCount, charCount + 3);
                            charCount = charCount + 3;
                            break;
                        case '.':
                            sub = line.substring(charCount, charCount + 1);
                            charCount = charCount + 1;
                            break;
                    }
                    board[i][j] = new ScrabbkleSquare(sub);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        calculateStartSquare(boardSize);
    }

    // To print updated board after every move
    public void printBoard() {
        System.out.println();
        System.out.println("Your current board: ");
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j].getPrintLabel() + "\t");
            }
            System.out.println();
        }
    }

    public ScrabbkleSquare[][] getBoard() {
        return board;
    }

    public void calculateStartSquare(int boardSize){
        int input = boardSize -1;
        int rowAndCol = input/2;
        startSquare = new int[]{rowAndCol, rowAndCol};
    }


    public int[] getStartSquare(){
        return this.startSquare;
    }

    public int getBoardSize(){
        return boardSize;
    }
}
