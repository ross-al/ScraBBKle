package pij.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Word {
    //private Tile myTile;
    //takes type Tile
    //need method to iterate through tiles to make string and to get score
    private String word;


    public Word(String word){ //testing word
        this.word = word;
        //takes an array as input and parse as string?
    }

    //get user input for word
    //get computer input for word
    //should words be arrays?
    //i.e. so we can iterate through the words to check tile score
    //would need to turn string into array? or we can have link lists?
    //must check if isWord() using pre-defined list


    public String getWord(){
        //code to get human word
        //code to get computer word
        return word;
    }

    //this is too slow
    //so maybe we need to create string array first?
    public boolean isWord() {
        boolean legitWord = false;
        try {
            Scanner myScanner = new Scanner(new FileReader("resources/wordlist.txt"));
            String line = myScanner.nextLine();
            while (myScanner.hasNextLine()) {
                if (line.equalsIgnoreCase(this.getWord())) {
                    legitWord = true;
                } else {
                    legitWord = false;
                }
            }
            //reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return legitWord;
    }

   // public String toString() {
        // String word =
        // return word;
    //}

   // public int getScore(){
     //   int score = //code here
        //iterate through each tile and getValue and sum?
     //   return score;
   // }

}