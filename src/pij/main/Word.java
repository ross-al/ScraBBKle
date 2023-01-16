package main;

import java.util.Scanner;  // Import the Scanner class
import java.io.File; //Import File class
import java.io.FileNotFoundException; //Import FileNotFoundException class

public class Word {
    //get user input for word
    //get computer input for word
    //should words be arrays?
    //i.e. so we can iterate through the words to check tile score
    //would need to turn string into array? or we can have link lists?
    //must check if isWord() using pre-defined list


    public String getWord(){
        String inputWord = "abas";
        //code to get human word
        //code to get computer word
        return inputWord;
    }

    public boolean isWord(){
        String word = getWord();

        File file = new File("wordlist.txt");

        try {
            Scanner scanner = new Scanner(file);

            //now read the file line by line...
            int lineNum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineNum++;
                if(word.equals(line)) {
                    //can we use if string equals getWord()?
                    return true;
                }
                else {
                    return false;
                }
            }
        } catch(FileNotFoundException e) {

        }
        return true;
    }


}