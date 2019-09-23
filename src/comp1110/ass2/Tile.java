package comp1110.ass2;

import java.util.HashMap;

/**
 * An idea from ass1 and coded by Ziyue Wang and modified by Zeming Wang.
 * It should be very similar with the Tile in ass1.
 * But I would like to add a method to let the Tile
 * has the ability to calculate or just hard coded
 * to show a 4*4 array of states that the tile could influence.
 *
 * e.g.
 * axy0 will have a states array like
 * [[G,   W,    R,  null]
 *  [null,R,   null,null]
 *  [null,null,null,null]
 *  [null,null,null,null]]
 *
 *  and bxy1 will have a states array like
 * [[W,   null,null,null]
 *  [W   ,B,   null,null]
 *  [null,G,   null,null]
 *  [null,G,   null,null]]
 *
 *
 *  All the array may be hard coded or have a method to calculate.
 *
 *  I think it will be easy for check board state if Tile has such function.
 *  If there is better idea, please just change it and push to remote repo.
 */
public class Tile {

    /**
     * the tpye of this tile a...j
     */
    private TileType tileType;

    /**
     * The tile's left-top location.
     */
    private Location location;

    /**
     * The tile's rotation. 0,1,2,3->N,E,S,W
     */
    private Orientation orientation;

    /**
     * use 3 static method to construct the Tile
     * @param placement
     */
    public Tile(String placement) {
        placement = placement.toLowerCase();
        this.tileType = placementToTileType(placement);
        this.location = placementToLocation(placement);
        this.orientation = placementToOrientation(placement);
    }

    //get methods
    public Location getLocation() {
        return location;
    }
    public Orientation getOrientation() {
        return orientation;
    }
    public TileType getTileType() {
        return tileType;
    }

    // util methods
    public static Orientation placementToOrientation(String placement) {
        // FIXME
        Orientation ori = null;
        int orientationIndex = placement.charAt(3)-'0';
        switch (orientationIndex) {
            case 0:
                ori = Orientation.NORTH;
                break;
            case 1:
                ori = Orientation.EAST;
                break;
            case 2:
                ori = Orientation.SOUTH;
                break;
            case 3:
                ori = Orientation.WEST;
                break;
            default:
                System.out.println("SHOULD NOT REACH HERE: " + orientationIndex);
                break;
        }
        return ori;
    }

    public static Location placementToLocation(String placement) {
        // Attention, in placement x,y refers to (col,row); in location x,y refers to (row col)
        int col = placement.charAt(1) - '0';
        int row = placement.charAt(2) - '0';
        return new Location(row,col);
    }

    public static TileType placementToTileType(String placement) {

        int typeIndex = placement.charAt(0)-'a';
        TileType type = null;
        switch (typeIndex){
            case 0:
                type = TileType.A;
                break;
            case 1:
                type = TileType.B;
                break;
            case 2:
                type = TileType.C;
                break;
            case 3:
                type = TileType.D;
                break;
            case 4:
                type = TileType.E;
                break;
            case 5:
                type = TileType.F;
                break;
            case 6:
                type = TileType.G;
                break;
            case 7:
                type = TileType.H;
                break;
            case 8:
                type = TileType.I;
                break;
            case 9:
                type = TileType.J;
                break;
            default:
                System.out.println("SHOULD NOT REACH HERE");
                break;
        }
        return type;
    }

    /**
     * Given a location, return its current state.
     * @param tileType the type of the tile
     * @param location  A location on the game board.
     * @param orientation the orientation of the tile
     * @return          A string represents which board squares it would occupy and what are those locations' colors.
     * MODIFIED by Ziyue Wang
     * Maybe Hashmap<Location, State> could be a better data structure to pass data
     */
    public static HashMap<Location, State> getTileInfoAtLocation(TileType tileType, Location location, Orientation orientation){
        // CODE
        HashMap<Location, State> tileInfo = new HashMap<>();

        State[][] originStates = TileType.originStates[tileType.getIndex()-1];
        State[][] rotatedStates = originStates;

        for (int i = 0; i < orientation.getIndex(); i++) {
            rotatedStates = TileType.rotate90_2DStateArray(rotatedStates);
        }

        int row_origin = location.getX();
        int col_origin = location.getY();

        for (int i = 0; i < rotatedStates.length; i++) {
            for (int j = 0; j < rotatedStates[0].length; j++) {
                if (rotatedStates[i][j] != null) {
                    tileInfo.put(new Location(row_origin + i, col_origin + j), rotatedStates[i][j]);
                }
            }
        }

        return tileInfo;
    }

    public HashMap<Location, State> getTileInfoLocation(Location location, Orientation orientation){
        return getTileInfoAtLocation(this.tileType, location, orientation);
    }

    public HashMap<Location, State> getTileInfoLocation(Orientation orientation){
        return getTileInfoAtLocation(this.tileType, this.location, orientation);
    }

    public HashMap<Location, State> getTileInfoLocation(){
        return getTileInfoAtLocation(this.tileType, this.location, this.orientation);
    }
}
