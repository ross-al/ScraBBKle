package pij.main;

import java.util.List;

public interface WordList {

    /**
     *
     * @return
     */

    public List<String> convertToList();
    public boolean isWord(String word);
    public List<String> getWordList();
}
