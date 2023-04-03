package pij.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ScrabbkleWordList is a WordList for the Scrabbkle game
 * It contains all the words that are allowed in the game
 */


public class ScrabbkleWordList implements WordList {

    /**
     * a list of all allowed words
     */
    private final List<String> wordList;


    /**
     * Constructor
     */
    public ScrabbkleWordList() {
        this.wordList = convertToList();
    }

    /**
     * Convert a given txt file into a word list
     *
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

    /**
     * Check if a given word is in the list
     *
     * @param word the word to check in the list
     * @return boolean
     */
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
