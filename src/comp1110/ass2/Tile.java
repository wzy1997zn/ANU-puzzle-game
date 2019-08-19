package comp1110.ass2;

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
        return null;
    }

    public static Location placementToLocation(String placement) {
        // FIXME
        return null;
    }

    public static TileType placementToTileType(String placement) {
        // FIXME
        return null;
    }

    /**
     * Given a location, return its current state.
     * @param tileType the type of the tile
     * @param location  A location on the game board.
     * @param orientation the orientation of the tile
     * @return          A string represents which board squares it would occupy and what are those locations' colors.
     */
    public static String getTileInfoAtLocation(TileType tileType, Location location, Orientation orientation){
        // CODE
        return null;
    }

    public String getTileInfoLocation(Location location, Orientation orientation){
        return getTileInfoAtLocation(this.tileType, location, orientation);
    }

    public String getTileInfoLocation(Orientation orientation){
        return getTileInfoAtLocation(this.tileType, this.location, orientation);
    }

    public String getTileInfoLocation(){
        return getTileInfoAtLocation(this.tileType, this.location, this.orientation);
    }
}
