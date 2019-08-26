package comp1110.ass2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

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
     */private boolean[] tileUsed = {false, false, false, false, false, false, false, false, false, false};

    /**
     * constructor
     */
    public FocusGame() {
        //add code here
    }

    /**
     * Modified by Ziyue Wang, need to use tileUsed, so remove static
     * check if a tile has been put on the board
     * @param tile the tile you want to check
     * @return if the tile has been put on the board
     */
    private boolean isTileUsed(Tile tile){
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

    /**
     * Add a new tile placement to the board state, updating
     * all relevant data structures.
     * @param placement The placement to add.
     */
    private void addTileToBoard(String placement) {
        // implemented by Ziyue
        Tile tile = new Tile(placement);

        updateBoardStates(tile);
        updateTiles(tile);
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
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        // FIXME Task 2: determine whether a piece placement is well-formed
        if(piecePlacement.length() != 4)
            return false;
        String reg = "[a-j][0-8][0-4][0-3]";
        return piecePlacement.matches(reg);
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 10);
     * - each piece placement is well-formed
     * - no shape appears more than once in the placement
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */
    public static boolean isPlacementStringWellFormed(String placement) {
        // FIXME Task 3: determine whether a placement is well-formed
        boolean[] flag = new boolean[10];
        for(int i = 0; i < 10; i++)
            flag[i] = true;
        int len = placement.length();
        if(len == 0)
            return false;
        if(len%4 != 0)
            return false;
        else {
            for(int i = 0; i < len; i+=4){
                if(isPiecePlacementWellFormed(placement.substring(i,i+4)) == false)
                    return false;
                int n = placement.charAt(i)-'a';
                if(flag[n])
                    flag[n] = false;
                else
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
        // Ziyue TODO haven't finish

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
     * which cover a specific board cell.
     *
     * For a piece placement to be viable
     * - it must be valid
     * - it must be consistent with the challenge
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
     * @param col      The cell's column.
     * @param row      The cell's row.
     * @return A set of viable piece placements, or null if there are none.
     */
    static Set<String> getViablePiecePlacements(String placement, String challenge, int col, int row) {
        // FIXME Task 6: determine the set of all viable piece placements given existing placements and a challenge
        return null;
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
     * @param challenge A challenge string.
     * @return A placement string describing a canonical encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        // FIXME Task 9: determine the solution to the game, given a particular challenge
        return null;
    }

}
