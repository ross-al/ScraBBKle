package pij.main;

public class ScrabbkleWord implements Word{
    private String word;

    //need method to iterate through tiles to make string and to get score

    public ScrabbkleWord(String word){ //param for testing word
        this.word = word;
        //takes an array as input and parse as string?
    }

    //public Word(Tile tile) //need to know how many params to take?

    //get user input for word
    //get computer input for word
    //should words be arrays?
    //i.e. so we can iterate through the words to check tile score
    //would need to turn string into array? or we can have link lists?
    //must check if isWord() using pre-defined list


    public String getWord(){
        return word;
    }


   // public String toString() {
        // String word =
        // return word;
    //}

   // public int calculateScore(){
     //   int score = //code here
        //iterate through each tile and getValue and sum?
     //   return score;
   // }

}