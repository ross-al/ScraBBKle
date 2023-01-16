package pij.main;

public class Main {
    public static void main(String[] args) {
        Main launcher = new Main();
        launcher.launch();
    }

    public void launch() {
        System.out.println("hello world");
        Word word = new Word();
        System.out.println(word.isWord()); //need to create instance of word first
        //gameplay code here
    }
}