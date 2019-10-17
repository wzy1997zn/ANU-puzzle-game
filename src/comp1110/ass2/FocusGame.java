package comp1110.ass2;

import java.util.*;

/**
 * This class provides the text interface for the IQ Focus Game
 * <p>
 * The game is based directly on Smart Games' IQ-Focus game
 * (https://www.smartgames.eu/uk/one-player-games/iq-focus)
 */
public class FocusGame {
    /**
     * An idea from ass1 and coded by Ziyue Wang and modified by Zeming Wang
     * Because we defined y for col and x for row,
     * the array should be 9*5 including 2 invalid blocks.
     * 4,0 & 4,8 should be null in the game.
     */
    public State[][] boardStates = new State[5][9];

    /**
     * record whether the tiles has been used
     */
    private boolean[] tileUsed = {false, false, false, false, false, false, false, false, false, false};

    /**
     * constructor
     */
    public FocusGame() {
        //add code here
    }

    /**
     * Check if that type of tile has been put on the board
     * @author Zeming Wang
     * @param type
     * @return if the tile has been put on the board
     */
    public boolean isTileTypeUsed(int type){
        return tileUsed[type];
    }

    /**
     * Modified by Ziyue Wang, need to use tileUsed, so remove static
     * @author Zeming Wang
     * check if a tile has been put on the board
     * @param tile the tile you want to check
     * @return if the tile has been put on the board
     */
    public boolean isTileUsed(Tile tile){
        // add code here
        TileType type = tile.getTileType();
        int typeIndex = type.getIndex();

        return tileUsed[typeIndex];
    }

    /**
     * Update the tile data structure with a new tile placement.
     * @param tile The tile being placed
     */
    private void updateTiles(Tile tile) {
        // implemented by Ziyue
        int typeIndex = tile.getTileType().getIndex();
        tileUsed[typeIndex-1] = true;
    }

    private void cleanTile(Tile tile) {
        int typeIndex = tile.getTileType().getIndex();
        tileUsed[typeIndex-1] = false;
    }

    /**
     * Update the boardstates data structure due to a valid (correct) new
     * tile placement.
     * @param tile The tile being placed
     */
    private void updateBoardStates(Tile tile){
        // implemented by Ziyue
        HashMap<Location, State> tileInfo = tile.getTileInfoLocation();
        for (Map.Entry<Location, State> info: tileInfo.entrySet()) {
            Location loc = info.getKey();
            State state = info.getValue();
            boardStates[loc.getX()][loc.getY()] = state;
        }

    }

    private void cleanBoardStates(Tile tile) {
        HashMap<Location, State> tileInfo = tile.getTileInfoLocation();
        for (Map.Entry<Location, State> info: tileInfo.entrySet()) {
            Location loc = info.getKey();
//            State state = info.getValue();
            boardStates[loc.getX()][loc.getY()] = null;
        }
    }

    /**
     * Add a new tile placement to the board state, updating
     * all relevant data structures.
     * @param placement The placement to add.
     */
    public void addTileToBoard(String placement) {
        // implemented by Ziyue
        Tile tile = new Tile(placement);

        updateBoardStates(tile);
        updateTiles(tile);
    }

    /**
     * change a tile's state and all related states
     * @param oldPlacement The old placement to delete.
     * @param newPlacement The new placement to add.
     */
    public void updateTileOnBoard(String oldPlacement, String newPlacement) {
        deleteTileFromBoard(oldPlacement);
        addTileToBoard(newPlacement);
    }

    /**
     * delete a tile's state from board
     * @param placement The placement to delete
     */
    public void deleteTileFromBoard(String placement) {
        Tile tile = new Tile(placement);

        cleanBoardStates(tile);
        cleanTile(tile);

    }



    /**
     * Given a location, return its current state.
     * @param location  A location on the game board.
     * @return          An object of type `enum State`, representing the given location.
     */
    public State getLocationState(Location location) {
        int x = location.getX();
        int y = location.getY();
        return boardStates[x][y];
    }

    /**
     * Determine whether a piece placement is well-formed according to the
     * following criteria:
     * - it consists of exactly four characters
     * - the first character is in the range a .. j (shape)
     * - the second character is in the range 0 .. 8 (column)
     * - the third character is in the range 0 .. 4 (row)
     * - the fourth character is in the range 0 .. 3 (orientation)
     *
     * @author Zeming Wang
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-formed
        if(piecePlacement.length() != 4) // check the length
            return false;
        String reg = "[a-j][0-8][0-4][0-3]"; // check the format with regular expression
        return piecePlacement.matches(reg);
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 10);
     * - each piece placement is well-formed
     * - no shape appears more than once in the placement
     *
     * @author Zeming Wang
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementStringWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed

        boolean[] flag = new boolean[10];
        // initial flag which is used for checking duplication
        for(int i = 0; i < 10; i++)
            flag[i] = true;

        int len = placement.length();
        // check the length
        if(len == 0)
            return false;
        if(len%4 != 0)
            return false;
        else {
            for(int i = 0; i < len; i+=4){
                if(isPiecePlacementWellFormed(placement.substring(i,i+4)) == false)
                    return false;
                int n = placement.charAt(i)-'a';
                if(flag[n]) // check for duplication
                    flag[n] = false;
                else // if it the tile has been used
                    return false;
            }
        }
        return true;
    }

    /**
     * Determine whether a placement string is valid.
     *
     * To be valid, the placement string must be:
     * - well-formed, and
     * - each piece placement must be a valid placement according to the
     *   rules of the game:
     *   - pieces must be entirely on the board
     *   - pieces must not overlap each other
     *
     * @param placement A placement string
     * @return True if the placement sequence is valid
     */
    public static boolean isPlacementStringValid(String placement) {
        // FIXME Task 5: determine whether a placement string is valid
        if (!isPlacementStringWellFormed(placement)) {
            return false;
        }

        // use a virtual board to test all the stuff
        // use addTileToBoard method to change states for next check
        FocusGame testingBoard = new FocusGame();

        for (int i = 0; i < placement.length(); i+=4) {
            String testingPlacement = placement.substring(i,i+4);
            Tile testingTile = new Tile(testingPlacement);
            HashMap<Location,State> testingInfo = testingTile.getTileInfoLocation();

            for (Map.Entry<Location, State> info: testingInfo.entrySet()) {
                Location loc = info.getKey();
                State state = info.getValue();

                // pieces must be entirely on the board
                if (!loc.isValid()) {
                    return false;
                }
                // pieces must not overlap each other
                if (testingBoard.boardStates[loc.getX()][loc.getY()] != null) {
                    return false;
                }
            }
            // adding current tile to testing board
            testingBoard.addTileToBoard(testingPlacement);

        }
        return true;
    }

    /**
     * Given a string describing a placement of pieces and a string describing
     * a challenge, return a set of all possible next viable piece placements
     * which cover a specific board location.
     *
     * For a piece placement to be viable
     * - it must be valid
     * - it must be consistent with the challenge
     *
     * @author Zeming Wang
     *
     * @param placement A viable placement string
     * @param challenge The game's challenge is represented as a 9-character string
     *                  which represents the color of the 3*3 central board area
     *                  squares indexed as follows:
     *                  [0] [1] [2]
     *                  [3] [4] [5]
     *                  [6] [7] [8]
     *                  each character may be any of
     *                  - 'R' = RED square
     *                  - 'B' = Blue square
     *                  - 'G' = Green square
     *                  - 'W' = White square
     * @param col      The location's column.
     * @param row      The location's row.
     * @return A set of viable piece placements, or null if there are none.
     */
    public static Set<String> getViablePiecePlacements(String placement, String challenge, int col, int row) {
        // FIXME Task 6: determine the set of all viable piece placements given existing placements and a challenge
        //if(isPlacementStringWellFormed(placement) == false)
        //    return null;
        State[][] challengeBoard = new State[5][9];
        int index = 0;
        // initial challenge board
        for(int r = 1; r < 4; r++){
            for(int c = 3; c < 6; c++){
                challengeBoard[r][c] = State.getState(challenge.charAt(index));
                index++;
            }
        }

        FocusGame testingBoard = new FocusGame();

        if(placement != null){
            // initial tile borad
            for (int i = 0; i < placement.length(); i+=4) {
                String testingPlacement = placement.substring(i,i+4);
                //Tile testingTile = new Tile(testingPlacement);
                //HashMap<Location,State> testingInfo = testingTile.getTileInfoLocation();

                testingBoard.addTileToBoard(testingPlacement);
            }
        }
        /**
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(testingBoard.boardStates[i][j]);
            }
            System.out.println();
        }
         */

        // assign a HashSet to store the result
        HashSet<String> res = new HashSet<>();

        // minimize the range
        int r_start = 0;
        int r_end = 4;
        int c_start = 0;
        int c_end = 8;

        if(row > 3)
            r_start = row - 4;
        if(row < 1)
            r_end = row + 4;
        if(col > 3)
            c_start = col - 4;
        if(col < 5)
            c_end = col + 4;

        //check all possible combinations
        for(int r = r_start; r <= r_end; r++){
            for(int c = c_start; c <= c_end; c++){
                for(int type = 1; type < 11; type++){
                    if(testingBoard.isTileTypeUsed(type-1))
                        continue;
                    for(int orientation = 0; orientation < 4; orientation++){
                        char ch = (char)('a'+type-1);

                        // check symmetry
                        //if(ch == 'f' || ch == 'j')
                        //    if(orientation == 2 || orientation == 3)
                        //        continue;
                        //System.out.println(String.format("%c%d%d%d",ch,col,row,orientation));
                        if(isTileValid(new Tile(String.format("%c%d%d%d",ch,c,r,orientation)),testingBoard.boardStates,challengeBoard,row,col))
                            res.add(String.format("%c%d%d%d",ch,c,r,orientation));
                    }
                }
            }
        }

        //System.out.println(res);
        if(res.isEmpty())
            return null;
        return res;
    }

    /**
     * Check whether the tile is valid to cover the given position
     *
     * @author Zeming Wang
     *
     * @param tile the tile you want to test
     * @param boardStates the board's state(which cells have been occupied and what color are they)
     * @param challengeBoard the board which stores the challenge
     * @param row the row of the exact cell
     * @param col the column of the exact cell
     * @return a boolean represent whether the tile is valid to cover the exact cell
     */
    private static boolean isTileValid(Tile tile, State[][] boardStates, State[][] challengeBoard, int row, int col){
        HashMap<Location,State> info = tile.getTileInfoLocation();
        boolean flag = false;

        for (Map.Entry<Location, State> i: info.entrySet()) {
            Location loc = i.getKey();
            State state = i.getValue();

            // pieces must be entirely on the board
            if (!loc.isValid()) {
                return false;
            }
            // pieces must not overlap each other
            if (boardStates[loc.getX()][loc.getY()] != null) {
                return false;
            }
            //pieces must cover the exact cell
            if(loc.getX() == row && loc.getY() == col)
                flag = true;
            // pieces must satisfy the challenge
            if(challengeBoard[loc.getX()][loc.getY()]!=null && state == challengeBoard[loc.getX()][loc.getY()])
                continue;
            else if(challengeBoard[loc.getX()][loc.getY()]!=null && state != challengeBoard[loc.getX()][loc.getY()])
                return false;
        }
        return flag;
    }

    /**
     * Return the canonical encoding of the solution to a particular challenge.
     *
     * A given challenge can only solved with a single placement of pieces.
     *
     * Since some piece placements can be described two ways (due to symmetry),
     * you need to use a canonical encoding of the placement, which means you
     * must:
     * - Order the placement sequence by piece IDs
     * - If a piece exhibits rotational symmetry, only return the lowest
     *   orientation value (0 or 1)
     *
     * @author Zeming Wang
     *
     * @param challenge A challenge string.
     * @return A placement string describing a canonical encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        // FIXME Task 9: determine the solution to the game, given a particular challenge
        String res = solve(null,challenge);
        TreeSet<String> treeSet = new TreeSet<>();
        for(int i = 0; i < res.length(); i+=4){
            treeSet.add(res.substring(i,i+4));
        }
        //System.out.println(res);
        //if(treeSet.contains("f801") && treeSet.contains("j703")){
         //   treeSet.remove("f801");
         //   treeSet.remove("j703");
          //  treeSet.add("f711");
          //  treeSet.add("j701");
        //}
        String finalResult = "";
        for(String str: treeSet){
            finalResult += str;
        }
        System.out.println(finalResult);
        //System.out.println(treeSet);
        return finalResult;
    }

    /**
     * A recursively solved function. Solve the given challenge.
     *
     * @author Zeming Wang
     *
     * @param placement a string of all tiles on the board
     * @param challenge a string of the challenge
     * @return a string of solution
     */
    public static String solve(String placement, String challenge){
        FocusGame testGame = new FocusGame();
        // initiate the board
        //System.out.println(placement);
        if(placement != null)
            for(int i = 0; i < placement.length(); i+=4) {
                testGame.addTileToBoard(placement.substring(i,i+4));
            }

        int row = 0, col = 0;
        boolean flag = true; // whether get final solution
        boolean flag2 = false; // whether found next cell to cover

        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 9; c++){
                if(r == 4 && (c == 0 || c == 8))
                    continue;
                if(testGame.boardStates[r][c] == null){ // find an unoccupied cell
                    flag = false;
                    row = r;
                    col = c;
                    flag2 = true;
                    break;
                }
            }
            if(flag2)
                break;
        }
        if(flag)
            return placement; // if get final solution, return it
/**
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 9; c++) {
                if(testGame.boardStates[r][c] == null)
                    System.out.print("_");
                else
                    System.out.print(testGame.boardStates[r][c]);
            }
            System.out.println();
        }
        System.out.println("Try to find: " + placement + " " + row + col);
 */
        Set<String> hashSet = getViablePiecePlacements(placement, challenge, col, row); // get a set of viable placements

        if(hashSet == null) // if not viable placement, return null
            return null;

        // try each placement
        for(String place: hashSet){
            if(place != null) {
                if((place.charAt(0) == 'g' || place.charAt(0) == 'f') && (place.charAt(3) == '2' ||place.charAt(3) == '3'))
                    continue;
                String solution = "";
                if(placement != null){
                    solution = solve(placement+place, challenge);
                }
                else{
                    solution = solve(place, challenge);
                }
                //System.out.println("placement: " + placement + " place: " + place + " solution: " + solution + " hashSet:" + hashSet);
                if(solution == null)
                    continue;
                else
                    return solution;
            }
        }

        return null;
    }

    /**
     * for easy debugging
     */
    public void outputStates() {
        for (int i = 0; i < boardStates.length; i++) {
            for (int j = 0; j < boardStates[0].length; j++) {
                if (boardStates[i][j] == null) {
                    System.out.print("O" + " ");
                } else {
                    System.out.print(boardStates[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
