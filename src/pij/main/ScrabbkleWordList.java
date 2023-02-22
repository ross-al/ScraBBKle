package pij.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScrabbkleWordList implements WordList{
    private List<String> wordList;

    /**
     * @return an ArrayList of input txt file
     * @throws FileNotFoundException if the input file does not exist in given directory
     * @throws IOException if error when accessing file data
     */

    public ScrabbkleWordList(){
        this.wordList = convertToList();
    }

    public List<String> convertToList(){
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

    public boolean isWord(String word){ //update to take type Word
        return wordList.contains(word);
    }

    public boolean isWord(ScrabbkleWord word){
        return wordList.contains(word);
    }

    public List<String> getWordList(){
        return wordList;
    }

}
