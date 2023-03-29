package pij.main;

import java.util.ArrayList;

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

    @Override
    public int calculateWordScore() {
        //get tile values
        //get premium word and premium letter valuse
        //premiums can only be used once, so need isUsed flag
        //calculate 7 tile bonus
        if(sevenTileBonus()){
            //add 70 extra points to total
        };
        return 0;
    }

    @Override
    public int getPlayerScore() {
        return score;
    }

    @Override
    public void placeTile(ScrabbkleTile tile, int row, int col) {
        board.getBoard()[row][col].setTile(tile);
        setNeighbouringTiles(row,col);
        //must also setNextTile()

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

    //BELOW METHODS DO NOT WORK YET!

    @Override
    public ScrabbkleTile playWord() {
        //use this method to call placeTile method in board?
        //use loop to place each tile?
        //must also setNextTile();
        return null;
    }

    // Check if any words are adjacent to player word and reject if so
    public boolean hasAdjacentWords(ArrayList<int[]> moveSquares, String moveDirection) {
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
                    if(board.getBoard()[row][col].getNextTile() != null){
                        hasAdjacentWord = true;
                    }
                }
            }
            // Check if right column has two or more touching tiles
            for(int[] rightPositions : rightColumn){
                int row = rightPositions[0];
                int col = rightPositions[1];
                if(squaresAreInBounds(rightColumn)){
                    if(board.getBoard()[row][col].getNextTile() != null){
                        hasAdjacentWord = true;
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
                    if(board.getBoard()[row][col].getNextTile() != null){
                        hasAdjacentWord = true;
                    }
                }
            }
            for(int[] belowPositions : belowRow){
                int row = belowPositions[0];
                int col = belowPositions[1];
                if(squaresAreInBounds(belowRow)){
                    if(board.getBoard()[row][col].getNextTile() != null){
                        hasAdjacentWord = true;
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
        if (row < 1 || row > boardSize || col < 1 || col > boardSize) {
            return false;
        }
        return true;
    }

    // Set aboveTile, belowTile, leftTile and rightTile
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
    }

    // Check if tile has at least one other tile touching it
    // i.e. to show player word is connected existing words and not
    // an isolated word
    public boolean intersectsWord(ArrayList<int[]> moveSquares){
        boolean intersectsWord = false;
        for(int[] moveSquare: moveSquares){
            int row = moveSquare[0];
            int col = moveSquare[1];
            // Check if at least one tile above, below, to right or to left has a tile
            if(        (board.getBoard()[row][col].getAboveTile() != null)
                    || (board.getBoard()[row][col].getBelowTile() != null)
                    || (board.getBoard()[row][col].getLeftTile() != null)
                    || (board.getBoard()[row][col].getRightTile() != null) )
                intersectsWord = true;
        }
        return intersectsWord;
    }


    //words using all 7 tiles in rack score an extra 70 points
    public boolean sevenTileBonus(){
        //if all tiles used in rack
        return true;
    }
}
