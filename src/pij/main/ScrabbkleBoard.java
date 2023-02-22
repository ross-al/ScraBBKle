package pij.main;

import java.io.*;
import java.util.Scanner;


//convert to type Square? YES!
//square should take: premiumWord, premiumLetter, defaultSquare, Tile

//need to update board with tiles (and print updated version)

public class ScrabbkleBoard implements Board {
    private File inputFile;
    private int boardSize;
    private ScrabbkleSquare[][] myBoard;

    public ScrabbkleBoard() {
        this.inputFile = getInputFile();
        this.boardSize = getBoardSize();
        if (!(isValidSize())) {
            System.out.println();
            System.out.println("Sorry, your board needs to be between 12-26 squares wide");
            //this doesn't update the inputFile and will load the failed board!
            getInputFile();
        }
        int rows = 1 + boardSize;
        int columns = rows;
        this.myBoard = new ScrabbkleSquare[rows][columns];
        convertToBoard(myBoard);
    }


    public ScrabbkleSquare[][] convertToBoard(ScrabbkleSquare[][] myBoard) {
        myBoard[0][0] = new ScrabbkleSquare(" ");
        char c = 'a';
        for (int a = 1; a < myBoard.length; a++) {
            String d = String.valueOf(c);
            myBoard[0][a] = new ScrabbkleSquare(d);
            c++;
        }
        for (int b = 1; b < myBoard.length; b++) {
            String q = Integer.toString(b);
            myBoard[b][0] = new ScrabbkleSquare(q);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            reader.readLine(); //to skip the first line with board size
            String line;
            String sub = "x";
            for (int i = 1; i < myBoard.length; i++) {
                int charCount = 0;
                line = reader.readLine();
                for (int j = 1; j < myBoard.length; j++) {
                    char ch = line.charAt(charCount);
                    switch (ch) {
                        case '{':
                            sub = line.substring(charCount, charCount + 3);
                            charCount = charCount + 3;
                            break;
                        case '(':
                            sub = line.substring(charCount, charCount + 3);
                            charCount = charCount + 3;
                            break;
                        case '.':
                            sub = line.substring(charCount, charCount + 1);
                            charCount = charCount + 1;
                            break;
                    }
                    myBoard[i][j] = new ScrabbkleSquare(sub);
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

    public void printBoard() {
        System.out.println("Your current board: ");
        System.out.println();
        for (int i = 0; i < myBoard.length; i++) {
            for (int j = 0; j < myBoard.length; j++) {
                System.out.print(myBoard[i][j].getPrintLabel() + "\t"); //can we right-justify row nums? + "\t"
            }
            System.out.println();
        }
    }

    public ScrabbkleSquare[][] getMyBoard() {
        return myBoard;
    }
}
