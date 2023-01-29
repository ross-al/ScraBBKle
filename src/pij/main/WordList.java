package pij.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordList {
    private final List<String> WORD_LIST;

    public WordList() {
        this.WORD_LIST = convertToList();
    }

    /**
     * @return an ArrayList of input txt file
     * @throws RuntimeException if the input file does not exist in given directory
     */

    public List<String> convertToList(){ //update to take type Word
        List<String> wordList = new ArrayList<>(); //update to take type Word
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./resources/wordlist.txt"));
            String str;
            while ((str = reader.readLine()) != null) {
                wordList.add(str);
            }
            reader.close();
        }
        catch (IOException e) { //need a throw here to say file doesn't exist (see week 11 reading)?
            //update the catch exception type?
            e.printStackTrace(); //DO NOT WRITE EMPTY CATCH BLOCKS!
            throw new RuntimeException(e);
        }
        return wordList;
    }

    public boolean isWord(String word){ //update to take type Word
        return WORD_LIST.contains(word);
    }
}
