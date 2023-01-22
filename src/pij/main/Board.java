package pij.main;

import java.io.File;

public class Board {
    private File file;

    public Board(File file){ //need to import file
        this.file = file;
    }


    //SxS where S as an int between 12-26
    //columns must be represented by letter a-z
    //rows by numbers 1-26
    //left most column is a
    //top row is 1

    //needs to either load the defaultBoard (see txt file already provided)
    //or needs to load a user-made board from a txt file

    //method to iterate through board and store letter squares and letter words in hash map?
    //would need to store index and bonus value

}
