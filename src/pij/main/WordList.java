package pij.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordList {
    protected List<String> wordList;

    public WordList() {
        this.wordList = convertToList();
    }

    public List<String> convertToList(){
        List<String> wordList = new ArrayList<>();
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

    public boolean isWord(String word){
        if(wordList.contains(word)){ //only looks for if contains lower case (exact match)
            return true;
        }
        else{
            return false;
        }
    }
}