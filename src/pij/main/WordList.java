package pij.main;

import java.util.List;

public interface WordList {

    public List<String> convertToList();
    public boolean isWord(String word);
    public boolean isWord(ScrabbkleWord word);
    public List<String> getWordList();
}
