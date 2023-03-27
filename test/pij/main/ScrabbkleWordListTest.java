package pij.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScrabbkleWordListTest {
    ScrabbkleWordList wordList;

    @BeforeEach
    void buildUp(){
        wordList = new ScrabbkleWordList();
    }

    @Test
    void shouldReturnTrueIfWordIsInList() {
        String input = "abjections";
        wordList.isWord(input);
        assertTrue(wordList.isWord(input));
    }

    @Test
    void shouldReturnFalseIfWordIsNotInList() {
        String input = "abcedfghijk";
        wordList.isWord(input);
        assertFalse(wordList.isWord(input));
    }
}