package pij.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScrabbkleWordList implements WordList {
    private final List<String> wordList;


    public ScrabbkleWordList() {
        this.wordList = convertToList();
    }

    /**
     * @return an ArrayList of input txt file
     * @throws FileNotFoundException if the input file does not exist in given directory
     * @throws IOException           if error when accessing file data
     */
    public List<String> convertToList() {
        List<String> wordList = new ArrayList<>();
        File file = new File("./resources/wordlist.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = reader.readLine()) != null) {
                wordList.add(str);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File " + file + " does not exist.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return wordList;
    }

    public boolean isWord(String word) {
        int length = word.length();
        // Words must be 2+ characters
        if (length > 1) {
            // Check if word exists in wordList
            // Cast to lower case as contains() method is case-sensitive
            return wordList.contains(word.toLowerCase());
        } else {
            return false;
        }
    }


}
