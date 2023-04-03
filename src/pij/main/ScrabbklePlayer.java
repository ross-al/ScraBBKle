package pij.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrabbklePlayer implements Player{
    protected boolean isPlayerTurn;
    private ArrayList<ScrabbkleTile> tileRack;
    private ScrabbkleBoard board;
    private final ScrabbkleWordList wordList;
    private ScrabbkleTileBag tileBag;

    public ScrabbklePlayer(ScrabbkleBoard board, ScrabbkleWordList wordList, ScrabbkleTileBag tileBag){
        this.board = board;
        this.wordList = wordList;
        this.tileBag = tileBag;
        tileRack = new ArrayList<>();
    }

    // Fill the player's rack after every move
    public void fillRack(){
        if (!tileBag.isEmpty()) {
            while (tileRack.size()<7){
                addTileToRack();
            }
        }
    }


    @Override
    // Get a random tile from the tile bag and add to tile rack
    public void addTileToRack() {
        ScrabbkleTile myTile = tileBag.getRandomTile();
        tileRack.add(myTile);
    }

    // Print the rack in tile format, e.g. [C3]
    public void printRack(){
        System.out.println();
        if (!(tileRack.isEmpty())) {
            System.out.println("It's your turn! Your tiles: ");
            System.out.print(tileRack.get(0).getPrintInTileRackFormat());
            for (int i = 1; i < tileRack.size(); i++) {
                System.out.print(", " + tileRack.get(i).getPrintInTileRackFormat());
            }
        }
        else {
            System.out.println("Your rack is empty");
        }
        System.out.println();
        System.out.println();
    }

    // Convert the 'column' letter provided into int for index position
    public int getPositionColumn(String movePosition) {
        char position = movePosition.charAt(0);
        int col = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if (position == c) {
                col = c - 'a' + 1;
            }
        }
        return col;
    }

    // Convert the row number provided from string to int for index position
    public int getPositionRow(String movePosition) {
        String str = movePosition.substring(1);
        return Integer.parseInt(str);
    }


    // Change connectsToExistingWord on valid tiles to true
    public void connectToWord(String movePosition, String moveDirection){
        // Calculate the player's intended word on board to check if in dictionary
        // Convert the 'cr' format provided into int for index positions
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        // Create square to track position in linked list
        ScrabbkleTile currentTile;
        // If word direction is down
        if (moveDirection.equals("d")) {
            // Find the top-most tile in the word on the board
            // First find if there is a tile on top of player's position and find top-most tile if so
            // This will be the beginning of the word
            currentTile = getBoard().getBoard()[row][col].getTile();
            while (currentTile.getAboveTile() != null) {
                currentTile = currentTile.getAboveTile();
            }
            while (currentTile != null) {
                currentTile.setConnectsToExistingWord(true); // change flag to true
                currentTile = currentTile.getBelowTile(); // move to the next tile to the tile below
            }
            // if word direction is right
        } else {
            // Find the left-most tile in the word on the board
            // First find if there is a tile to the left of player's position and find left-most tile if so
            // This will be the beginning of the word
            currentTile = getBoard().getBoard()[row][col].getTile();
            while (currentTile.getLeftTile() != null) {
                currentTile = currentTile.getLeftTile();
            }
            while (currentTile != null) {
                currentTile.setConnectsToExistingWord(true); //change flag to true
                currentTile = currentTile.getRightTile(); // move to the next tile to the right
            }
        }
    }


    // Calculate the player's intended word on board to check if in dictionary
    public String calculateFinalWord(String movePosition, String moveDirection) {
        // Convert the 'cr' format provided into int for index positions
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        // Create empty string for final word
        String finalWord = "";
        char c;
        // Create square to track position in linked list
        ScrabbkleTile currentTile;
        // If word direction is down
        if (moveDirection.equals("d")) {
            // Find the top-most tile in the word on the board
            // First find if there is a tile on top of player's position and find top-most tile if so
            // This will be the beginning of the word
            currentTile = getBoard().getBoard()[row][col].getTile();
            while (currentTile.getAboveTile() != null) {
                currentTile = currentTile.getAboveTile();
            }
            while (currentTile != null) {
                finalWord += currentTile.getLetter(); // append the letter to the finalWord string
                currentTile = currentTile.getBelowTile(); // move to the next tile to the tile below
            }
            // if word direction is right
        } else {
            // Find the left-most tile in the word on the board
            // First find if there is a tile to the left of player's position and find left-most tile if so
            // This will be the beginning of the word
            currentTile = getBoard().getBoard()[row][col].getTile();
            while (currentTile.getLeftTile() != null) {
                currentTile = currentTile.getLeftTile();
            }
            while (currentTile != null) {
                finalWord += currentTile.getLetter(); // append the letter to the finalWord string
                currentTile = currentTile.getRightTile(); // move to the next tile to the right
            }
        }
        return finalWord;
    }


    // Get all the positions for the squares in the move
    public ArrayList<int[]> calculateMoveSquares(String moveWord, String movePosition, String moveDirection) {
        // Create ArrayList of int[] to store row and column positions for each tile in move
        ArrayList<int[]> moveSquares = new ArrayList<int[]>();
        // Get position for first tile
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        // If move is down
        if (moveDirection.equals("d")) {
            for (int i = 0; i < moveWord.length(); i++) {
                if(positionIsInBounds(row,col)) {
                    if (getBoard().getBoard()[row][col].getTile() != null) {
                        row++;
                        i--;
                    } else {
                        int[] positions = {row, col};
                        moveSquares.add(positions);
                        row++;
                    }
                }
            }
        }
        // If move is right
        for (int i = 0; i < moveWord.length(); i++) {
            if(positionIsInBounds(row, col)) {
                if (getBoard().getBoard()[row][col].getTile() != null) {
                    col++;
                    i--;
                } else {
                    int[] positions = {row, col};
                    moveSquares.add(positions);
                    col++;
                }
            }

        }
        return moveSquares;
    }


    // Place tiles for given word on board
    public void playWord(String moveWord, String movePosition, String moveDirection) {
        // Convert the 'cr' format provided into int for index positions
        int col = getPositionColumn(movePosition);
        int row = getPositionRow(movePosition);
        char c;
        // If move direction is down, find the next free row position to place tile
        if (moveDirection.equals("d")) {
            for (int i = 0; i < moveWord.length(); i++) {
                if(positionIsInBounds(row, col)) {
                    if (getBoard().getBoard()[row][col].getTile() != null) {
                        row++;
                        i--;
                    } else {
                        c = moveWord.charAt(i);
                        if (!tileRack.isEmpty()) {
                            ScrabbkleTile tile = getTileFromRack(c);
                            placeTile(tile, row, col);
                            row++;
                        }
                    }
                }
            }
        } else {
            // // If move direction is right, find the next free col position to place tile
            for (int i = 0; i < moveWord.length(); i++) {
                if(positionIsInBounds(row,col)) {
                    if (getBoard().getBoard()[row][col].getTile() != null) {
                        col++;
                        i--;
                    } else {
                        if (!tileRack.isEmpty()) {
                            c = moveWord.charAt(i);
                            ScrabbkleTile tile = getTileFromRack(c);
                            placeTile(tile, row, col);
                            col++;
                        }
                    }
                }
            }
        }
    }


    // Check if any words are adjacent to player word and reject if so
    public boolean formsMultipleWords(ArrayList<int[]> moveSquares){
        boolean hasAdjacentWord = false;
            for (int[] movePositions : moveSquares) {
                int row = movePositions[0];
                int col = movePositions[1];
                if(board.getBoard()[row][col].getTile() != null){
                    ScrabbkleTile currentTile = board.getBoard()[row][col].getTile();
                    if(currentTile.getLeftTile() != null){
                        if(currentTile.getLeftTile().getAboveTile() != null){
                            if(currentTile.getAboveTile() != null){
                                hasAdjacentWord = true;
                            }
                        }
                    }if(currentTile.getRightTile() != null){
                        if(currentTile.getRightTile().getAboveTile() != null){
                            if(currentTile.getAboveTile() != null){
                                hasAdjacentWord = true;
                            }
                        }
                    }if(currentTile.getLeftTile() != null){
                        if(currentTile.getLeftTile().getBelowTile() != null){
                            if(currentTile.getBelowTile() != null){
                                hasAdjacentWord = true;
                            }
                        }
                    }
                    if(currentTile.getRightTile() != null){
                        if(currentTile.getRightTile().getBelowTile() != null){
                            if(currentTile.getBelowTile() != null){
                                hasAdjacentWord = true;
                            }
                        }
                    }
                }
            }
        return hasAdjacentWord;
    }


    // Check if the given move is within the bounds of the board
    public boolean squaresAreInBounds(ArrayList<int[]> moveSquares) {
        int boardSize = getBoard().getBoardSize();
        for (int[] moveSquare : moveSquares) {
            int row = moveSquare[0];
            int col = moveSquare[1];
            // Bounds of board are 1,1 due to row and column labels
            if (row < 1 || row > boardSize || col < 1 || col > boardSize) {
                return false;
            }
        }
        return true;
    }

    // Check if individual positions for squares are within bounds
    // Used for setting nextTile
    public boolean positionIsInBounds(int row, int col) {
        int boardSize = getBoard().getBoardSize();
        // Bounds of board are 1,1 due to row and column labels
        return row >= 1 && row <= boardSize && col >= 1 && col <= boardSize;
    }

    // Set aboveTile, belowTile, leftTile and rightTile
    public void setNeighbouringTiles(ScrabbkleTile tile, int row, int col) {
        ScrabbkleTile currentTile = tile;
        ScrabbkleTile aboveTile;
        ScrabbkleTile belowTile;
        ScrabbkleTile leftTile;
        ScrabbkleTile rightTile;
        if (currentTile!= null){
            // Check if there is a tile above
            // Set to aboveTile if so
            if(positionIsInBounds(row-1,col)){
                if (getBoard().getBoard()[row - 1][col].getTile() != null) {
                    aboveTile = getBoard().getBoard()[row - 1][col].getTile();
                    currentTile.setAboveTile(aboveTile);
                    // Then set the aboveTile's belowTile to currentTile
                    aboveTile.setBelowTile(currentTile);
                }
            } // belowTile
            if(positionIsInBounds(row+1,col)){
                if (getBoard().getBoard()[row + 1][col].getTile() != null) {
                    belowTile = getBoard().getBoard()[row + 1][col].getTile();
                    currentTile.setBelowTile(belowTile);
                    belowTile.setAboveTile(currentTile);
                }
            }
            // leftTile
            if(positionIsInBounds(row,col-1)){
                if (getBoard().getBoard()[row][col - 1].getTile() != null) {
                    leftTile = getBoard().getBoard()[row][col - 1].getTile();
                    currentTile.setLeftTile(leftTile);
                    leftTile.setRightTile(currentTile);
                }
            }
             // rightTile
            if(positionIsInBounds(row,col+1)){
                if (getBoard().getBoard()[row][col + 1].getTile() != null) {
                    rightTile = getBoard().getBoard()[row][col + 1].getTile();
                    currentTile.setRightTile(rightTile);
                    rightTile.setLeftTile(currentTile);
                }
            }
        }
    }




    // Change connectsToExistingWord on valid tiles to true
    public void connectToWord(int row, int col, String moveDirection){
        // Create square to track position in linked list
        ScrabbkleTile currentTile;
        // If word direction is down
        if (moveDirection.equals("d")) {
            // Find the top-most tile in the word on the board
            // First find if there is a tile on top of player's position and find top-most tile if so
            // This will be the beginning of the word
            currentTile = getBoard().getBoard()[row][col].getTile();
            while (currentTile.getAboveTile() != null) {
                currentTile = currentTile.getAboveTile();
            }
            while (currentTile != null) {
                currentTile.setConnectsToExistingWord(true); // change flag to true
                currentTile = currentTile.getBelowTile(); // move to the next tile to the tile below
            }
            // if word direction is right
        } else {
            // Find the left-most tile in the word on the board
            // First find if there is a tile to the left of player's position and find left-most tile if so
            // This will be the beginning of the word
            currentTile = getBoard().getBoard()[row][col].getTile();
            while (currentTile.getLeftTile() != null) {
                currentTile = currentTile.getLeftTile();
            }
            while (currentTile != null) {
                currentTile.setConnectsToExistingWord(true); //change flag to true
                currentTile = currentTile.getRightTile(); // move to the next tile to the right
            }
        }
    }


    // Check if word connects to at least one word on the board
    public boolean intersectsWord(ArrayList<int[]> moveSquares) {
        boolean intersectsWord = false;
        for (int[] moveSquare : moveSquares) {
            int row = moveSquare[0];
            int col = moveSquare[1];
            List<ScrabbkleSquare> adjacentSquares = getAdjacentTiles(row, col);
            for (Square adjacentSquare : adjacentSquares) {
                if (adjacentSquare.getTile() != null) {
                    if(adjacentSquare.getTile().isConnectsToExistingWord()) {
                        intersectsWord = true;
                        break;
                    }
                }
            }
            if (intersectsWord) {
                break;
            }
        }
        return intersectsWord;
    }

    // Helper method to calculate adjacent squares
    public List<ScrabbkleSquare> getAdjacentTiles(int row, int col) {
        List<ScrabbkleSquare> adjacentSquares = new ArrayList<>();
        if (row > 1) {
            adjacentSquares.add(board.getBoard()[row - 1][col]);
        }
        if (row < board.getBoardSize() - 1) {
            adjacentSquares.add(board.getBoard()[row + 1][col]);
        }
        if (col > 1) {
            adjacentSquares.add(board.getBoard()[row][col - 1]);
        }
        if (col < board.getBoard()[row].length - 1) {
            adjacentSquares.add(board.getBoard()[row][col + 1]);
        }
        return adjacentSquares;
    }

    // Check if all squares in move are free
    public boolean moveSquaresOccupied(ArrayList<int[]> moveSquares){
        boolean moveSquaresOccupied = false;
        for(int[] square : moveSquares){
            int row = square[0];
            int col = square[1];
            if(getBoard().getBoard()[row][col].getTile() != null){
                moveSquaresOccupied = true;
            }
        }
        return moveSquaresOccupied;
    }

    // Check if word is in provided dictionary
    public boolean isValidWord(String word) {
        return getWordList().isWord(word);
        //return true; //for testing
    }

    // Remove tiles from board and reset pointers, row and col values
    public void removeTilesFromBoard(ArrayList<int[]> moveSquares){
        for(int[] square : moveSquares){
            int row = square[0];
            int col = square[1];
            if(getBoard().getBoard()[row][col].getTile() != null) {
                // Get the tile form the board
                ScrabbkleTile tile = getBoard().getBoard()[row][col].getTile();
                // Reset its premium values
                tile.setPremiumLetterValue(1);
                tile.setPremiumWordValue(1);
                // Remove tile from board
                removeNeighbouringTiles(tile);
                getBoard().getBoard()[row][col].removeTile();


            }
        }
    }


    // Reset neighbouring tile when move is invalid
    public void removeNeighbouringTiles(ScrabbkleTile tile) {
        ScrabbkleTile aboveTile;
        ScrabbkleTile belowTile;
        ScrabbkleTile leftTile;
        ScrabbkleTile rightTile;

        if (tile.getAboveTile() != null) {
            aboveTile = tile.getAboveTile();
            aboveTile.resetBelowTile();
        }
        if (tile.getBelowTile() != null){
            belowTile = tile.getBelowTile();
            belowTile.resetAboveTile();
        }
        if(tile.getLeftTile() != null){
            leftTile = tile.getLeftTile();
            leftTile.resetRightTile();
        }
        if(tile.getRightTile() != null){
            rightTile = tile.getRightTile();
            rightTile.resetLeftTile();
        }

        // remove all the pointers on the current tile
        tile.resetAboveTile();
        tile.resetBelowTile();
        tile.resetLeftTile();
        tile.resetRightTile();
    }

    // Check if the player's rack has all the necessary tiles, including duplicates and wildCards
    public boolean hasAllTilesAvailable(String moveWord, ArrayList<ScrabbkleTile> tileRack) {
        Map<Character, Integer> charCounts = new HashMap<>();
        int wildcardCount = 0;
        // Count the number of times each character appears in the moveWord
        for (char c : moveWord.toCharArray()) {
            if (Character.isLowerCase(c)) {
                wildcardCount++;
            } else {
                charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
            }
        }
        // Check if there are enough tiles for each regular letter
        for (char c : charCounts.keySet()) {
            int requiredCount = charCounts.get(c);
            int tileRackCount = 0;
            // Count the number of tiles in the tile rack
            for (ScrabbkleTile tile : tileRack) {
                if (tile.getLetter() == c) {
                    tileRackCount++;
                }
            }
            if (tileRackCount < requiredCount) {
                return false;
            }
        }
        // Check if there are enough tiles for the wildcards
        if (wildcardCount > 0) {
            int tileRackCount = 0;
            for (ScrabbkleTile tile : tileRack) {
                if (tile.getLetter() == ' ') {
                    tileRackCount++;
                }
            }
            if (tileRackCount < wildcardCount) {
                return false;
            }
        }
        return true;
    }

    // Find the tile in the rack and return it, including wildcards
    public ScrabbkleTile getTileFromRack(char c) {
        ScrabbkleTile tile = null;
        if (!tileRack.isEmpty()) {
            // If character is lower case, it's a wildcard
            // get wildCard
            if (Character.isLowerCase(c)) {
                for (ScrabbkleTile tileInRack : tileRack) {
                    if (tileInRack.isWildCard()) {
                        tile = tileInRack;
                        // Assign the wildcard letter to the tile
                        tile.assignWildCard(c);
                    }
                }
            } else {
                for (ScrabbkleTile tileInRack : tileRack) {
                    if (tileInRack.getLetter() == c) {
                        tile = tileInRack;
                    }
                }
            }
        } return tile;
    }

    // Place tiles on a board, set neighbouring tiles for placed tile
    // add premium letter/word values to tile
    @Override
    public void placeTile(ScrabbkleTile tile, int row, int col) {
        // Place the tile on the board
        board.getBoard()[row][col].setTile(tile);

        // Set its neighbouring tiles for linked lists
        setNeighbouringTiles(tile,row,col);

        // Assign any premium values of the square to the tile
        int premiumWordValue = board.getBoard()[row][col].getPremiumWordValue();
        board.getBoard()[row][col].getTile().setPremiumWordValue(premiumWordValue);

        int premiumLetterValue = board.getBoard()[row][col].getPremiumLetterValue();
        board.getBoard()[row][col].getTile().setPremiumLetterValue(premiumLetterValue);
    }

    // Remove tiles from rack
    public void removeTilesFromRack(String moveWord) {
        ScrabbkleTile tile = null;
        char c;
        for(int i = 0; i < moveWord.length(); i++){
            c = moveWord.charAt(i);
            tile = getTileFromRack(c);
            tileRack.remove(tile);
        }
    }

    // calculate word score for valid word
    @Override
    public int calculateWordScore(String moveDirection, int row, int col, int tileCounter) {
        int wordScore = 0;
        int premiumWordFactor = 1 ;
        // Create square to track position in linked list
        ScrabbkleTile currentTile = getBoard().getBoard()[row][col].getTile();
        // If direction is down
        if (moveDirection.equals("d")) {
            // Find the top most tile in a word
            while(currentTile.getAboveTile() != null){
                currentTile = currentTile.getAboveTile();
            }
            while (currentTile  != null) {
                // Calculate score of tile * premiumLetterValue for each tile
                int letterScore = calculateTileScores(currentTile);

                // Add the letter score to the current wordScore total
                wordScore = wordScore + letterScore;

                // Calculate the premiumWordFactor
                premiumWordFactor = premiumWordFactor * calculatePremiumWordScore(currentTile);

                // move to the next tile to the below tile
                currentTile = currentTile.getBelowTile();
            }
        } else {
            // If direction is right
            while(currentTile.getLeftTile() != null){
                currentTile = currentTile.getLeftTile();
            }

            while (currentTile  != null) {

                int letterScore = calculateTileScores(currentTile);
                wordScore = wordScore + letterScore;

                premiumWordFactor = premiumWordFactor * calculatePremiumWordScore(currentTile);

                // move to the next tile to the right
                currentTile = currentTile.getRightTile();
            }
        }
        // Multiply the word score by premiumWordFactor
        wordScore = wordScore * premiumWordFactor;

        // Apply bonus points if all 7 tiles used from tile rack
        if(tileCounter == 7){
            wordScore = wordScore + 70;
        }
        return wordScore;
    }




    // Calculate tile scores for letter values and premium letter values
    // Reset premium values to 1 once used
    public int calculateTileScores(ScrabbkleTile tile){
        int tileValue = tile.getValue();
        int premiumLetterValue = tile.getPremiumLetterValue();
        // Reset premiumLetterValue to default
        tile.setPremiumLetterValue(1);
        return tileValue * premiumLetterValue;
    }

    // Calculate premium word values
    // Reset premium values to 1 once used
    public int calculatePremiumWordScore(ScrabbkleTile tile){
        int premiumWordValue = tile.getPremiumWordValue();
        // Reset premiumWordValue to default
        tile.setPremiumWordValue(1);
        return premiumWordValue;
    }


    // Print move summary, scores and updated board
    public void printMoveSummary(String moveWord, String movePosition, String moveDirection) {
        System.out.print("The move is: " + moveWord);
        System.out.print(" at position " + movePosition + ",");
        System.out.print(" direction: " + getMoveDirectionPrintFormat(moveDirection));
    }


    // Get print format for move direction for move summary in console
    public String getMoveDirectionPrintFormat(String moveDirection) {
        if (moveDirection.equals("d")) {
            return "down";
        } else {
            return "right";
        }
    }


    // Getters
    public ScrabbkleBoard getBoard(){
        return board;
    }

    public ArrayList<ScrabbkleTile> getTileRack(){
        return tileRack;
    }

    public ScrabbkleWordList getWordList(){
        return wordList;
    }
}

