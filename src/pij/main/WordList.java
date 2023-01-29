package pij.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordList {
    private final List<String> WORD_LIST;

    public WordList() {
        this.WORD_LIST = convertToList();
    }

    /**
     * @return an ArrayList of input txt file
     * @throws FileNotFoundException if the input file does not exist in given directory
     * @throws IOException if error when accessing file data
     */

    //should use an exist() method to see if file exists first?

    public List<String> convertToList(){ //update to take type Word
        List<String> wordList = new ArrayList<>(); //update to take type Word
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

    public boolean isWord(String word){ //update to take type Word
        return WORD_LIST.contains(word);
    }
}
