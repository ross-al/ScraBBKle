package pij.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScrabbklePlayer implements Player{
    protected boolean isPlayerTurn;
    private int score;
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

    public void fillRack(){
        if (!tileBag.isEmpty()) {
            while (tileRack.size()<7){
                addTileToRack();
            }
        }
    }


    @Override
    public void addTileToRack() {
        ScrabbkleTile myTile = tileBag.getRandomTile();
        tileRack.add(myTile);
    }


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


    public ScrabbkleBoard getBoard(){
        return board;
    }

    public ArrayList<ScrabbkleTile> getTileRack(){
        return tileRack;
    }

    public ScrabbkleWordList getWordList(){
        return wordList;
    }



    // Check if any words are adjacent to player word and reject if so
    public boolean formsMultipleWords(ArrayList<int[]> moveSquares, String moveDirection) {
        boolean hasAdjacentWord = false;
        // Create lists of positions where parallel words could exist
        ArrayList<int[]> leftColumn = new ArrayList<>();
        ArrayList<int[]> rightColumn = new ArrayList<>();
        ArrayList<int[]> aboveRow = new ArrayList<>();
        ArrayList<int[]> belowRow = new ArrayList<>();
        if (moveDirection.equals("d")){
            //  If move is down, calculate corresponding columns either side
            for (int[] movePositions : moveSquares) {
                int[] adjacentPositions = {movePositions[0], movePositions[1]-1};
                leftColumn.add(adjacentPositions);
            }
            for (int[] movePositions : moveSquares) {
                int[] adjacentPositions = {movePositions[0], movePositions[1]+1};
                rightColumn.add(adjacentPositions);
            }
            // Check if left column has two or more touching tiles
            for(int[] leftPositions : leftColumn){
                int row = leftPositions[0];
                int col = leftPositions[1];
                if(squaresAreInBounds(leftColumn)){
                    if(board.getBoard()[row][col].getTile() != null) {
                        if (board.getBoard()[row][col].getTile().getLeftTile() != null) {
                            hasAdjacentWord = true;
                        }
                    }
                }
            }
            // Check if right column has two or more touching tiles
            for(int[] rightPositions : rightColumn){
                int row = rightPositions[0];
                int col = rightPositions[1];
                if(squaresAreInBounds(rightColumn)){
                    if(board.getBoard()[row][col].getTile() != null) {
                        if (board.getBoard()[row][col].getTile().getRightTile() != null) {
                            hasAdjacentWord = true;
                        }
                    }
                }
            }
        } else {
            // If move is right, calculate corresponding above and below rows
            for (int[] movePositions : moveSquares) {
                int[] adjacentPositions = {movePositions[0]-1, movePositions[1]};
                aboveRow.add(adjacentPositions);
            }
            for (int[] movePositions : moveSquares) {
                int[] adjacentPositions = {movePositions[0]+1, movePositions[1]};
                belowRow.add(adjacentPositions);
            }
            // Check if top or bottom rows have adjacent words
            for(int[] abovePositions : aboveRow){
                int row = abovePositions[0];
                int col = abovePositions[1];
                if(squaresAreInBounds(aboveRow)){
                    if(board.getBoard()[row][col].getTile() != null) {
                        if (board.getBoard()[row][col].getTile().getAboveTile() != null) {
                            hasAdjacentWord = true;
                        }
                    }
                }
            }
            for(int[] belowPositions : belowRow){
                int row = belowPositions[0];
                int col = belowPositions[1];
                if(squaresAreInBounds(belowRow)){
                    if(board.getBoard()[row][col].getTile() != null) {
                        if (board.getBoard()[row][col].getTile().getBelowTile() != null) {
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
            if (getBoard().getBoard()[row - 1][col].getTile() != null) {
                aboveTile = getBoard().getBoard()[row - 1][col].getTile();
                currentTile.setAboveTile(aboveTile);
                // Then set the aboveTile's belowTile to currentTile
                aboveTile.setBelowTile(currentTile);
            } // belowTile
            if (getBoard().getBoard()[row + 1][col].getTile() != null) {
                belowTile = getBoard().getBoard()[row + 1][col].getTile();
                currentTile.setBelowTile(belowTile);
                belowTile.setAboveTile(currentTile);
            } // leftTile
            if (getBoard().getBoard()[row][col - 1].getTile() != null) {
                leftTile = getBoard().getBoard()[row][col - 1].getTile();
                currentTile.setLeftTile(leftTile);
                leftTile.setRightTile(currentTile);
            } // rightTile
            if (getBoard().getBoard()[row][col + 1].getTile() != null) {
                rightTile = getBoard().getBoard()[row][col + 1].getTile();
                currentTile.setRightTile(rightTile);
                rightTile.setLeftTile(currentTile);
            }
        }
    }

    // Check if tile has at least one other tile touching it
    // i.e. to show player word is connected existing words and not
    // an isolated word
    public boolean intersectsWord(ArrayList<int[]> moveSquares) {
        boolean intersectsWord = false;
        for (int[] moveSquare : moveSquares) {
            int row = moveSquare[0];
            int col = moveSquare[1];
            // Check if at least one tile above, below, to right or to left has a tile
            if (board.getBoard()[row][col].getTile() != null) {
                if ((board.getBoard()[row][col].getTile().getAboveTile() != null)
                        || (board.getBoard()[row][col].getTile().getBelowTile() != null)
                        || (board.getBoard()[row][col].getTile().getLeftTile() != null)
                        || (board.getBoard()[row][col].getTile().getRightTile() != null)
                ) {
                    intersectsWord = true;
                }
            }

        } return intersectsWord;
    }

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
                getBoard().getBoard()[row][col].removeTile();
            }
        }
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


    public int calculateTileScores(ScrabbkleTile tile){
        int tileValue = tile.getValue();
        int premiumLetterValue = tile.getPremiumLetterValue();
        // Reset premiumLetterValue to default
        tile.setPremiumLetterValue(1);
        return tileValue * premiumLetterValue;
    }

    public int calculatePremiumWordScore(ScrabbkleTile tile){
        int premiumWordValue = tile.getPremiumWordValue();
        // Reset premiumWordValue to default
        tile.setPremiumWordValue(1);
        return premiumWordValue;
    }


    @Override
    public int getPlayerScore() {
        return score;
    }

}

/*    // Set aboveTile, belowTile, leftTile and rightTile
    public void setNeighbouringTiles(int row, int col) {
        ScrabbkleTile aboveTile;
        ScrabbkleTile belowTile;
        ScrabbkleTile leftTile;
        ScrabbkleTile rightTile;
        // Check if aboveTile is within bounds
        if (positionIsInBounds(row - 1, col)) {
            // Check if aboveTile already has a tile present and set to aboveTile if so
            if (getBoard().getBoard()[row - 1][col].getTile() != null) {
                aboveTile = getBoard().getBoard()[row - 1][col].getTile();
                getBoard().getBoard()[row][col].setAboveTile(aboveTile);
            }
        } // belowTile
        if (positionIsInBounds(row + 1, col)) {
            if (getBoard().getBoard()[row + 1][col].getTile() != null) {
                belowTile = getBoard().getBoard()[row + 1][col].getTile();
                getBoard().getBoard()[row][col].setBelowTile(belowTile);
            }
        } // leftTile
        if (positionIsInBounds(row, col - 1)) {
            if (getBoard().getBoard()[row][col - 1].getTile() != null) {
                leftTile = getBoard().getBoard()[row][col - 1].getTile();
                getBoard().getBoard()[row][col].setLeftTile(leftTile);
            }
        } // rightTile
        if (positionIsInBounds(row, col + 1)) {
            if (getBoard().getBoard()[row][col + 1].getTile() != null) {
                rightTile = getBoard().getBoard()[row][col + 1].getTile();
                getBoard().getBoard()[row][col].setRightTile(rightTile);
            }
        }
    }*/