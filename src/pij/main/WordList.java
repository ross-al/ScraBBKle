package pij.main;

import java.util.List;

/**
 * A WordList is a dictionary for a game. Words must be in the WordList
 */

public interface WordList {

    /**
     * Converts the default file into list of words
     *
     * @return list of words as strings
     */
    List<String> convertToList();

    /**
     * Checks if a given word is in the word list
     *
     * @param word the word to check
     * @return boolean true if word is in the word list
     */
    boolean isWord(String word);

}
