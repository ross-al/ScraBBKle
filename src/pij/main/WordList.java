package pij.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordList {
    protected String[] wordList;

    public WordList() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/wordlist.txt"));
            List<String> word = new ArrayList<String>();
            String str;
            while ((str = reader.readLine()) != null) {
                word.add(str);
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}