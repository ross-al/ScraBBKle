package pij.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordList {
    private final List<String> wordList; //should be final or static?

    public WordList() {
        this.wordList = convertToList();
    }

    /**
     * @return an ArrayList of input txt file
     * @throws IndexOutOfBoundsException if the input file does not exist in given directory
     */

    public List<String> convertToList(){ //update to take type Word
        List<String> wordList = new ArrayList<>(); //update to take type Word
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/wordlist.txt"));
            String str;
            while ((str = reader.readLine()) != null) {
                wordList.add(str);
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    public boolean isWord(String word){ //update to take type Word
        return wordList.contains(word);
    }
}
