package comp1110.ass2;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TileTest {

    Tile tile = new Tile("i632");
    String placement = "i632";
    @Test
    public void testPlacementToOrientation() {
        Orientation ori = Orientation.SOUTH;
        assertEquals(ori,Tile.placementToOrientation(placement));
    }

    @Test
    public void testPlacementToLocation() {
        Location loc = new Location(3,6);
        assertEquals(loc.getX(),Tile.placementToLocation(placement).getX());
        assertEquals(loc.getY(),Tile.placementToLocation(placement).getY());
    }

    @Test
    public void testPlacementToTileType() {
        TileType type = TileType.I;
        assertEquals(Tile.placementToTileType(placement),type);
    }

    @Test
    public void testGetTileInfoAtLocation() {
        HashMap<Location, State> shouldbe = new HashMap<>();
        shouldbe.put(new Location(3,6), State.W);
        shouldbe.put(new Location(4,6), State.B);
        shouldbe.put(new Location(4,7), State.B);

        HashMap<Location, State> tileInfo = tile.getTileInfoLocation();
        for (Map.Entry<Location, State> info: tileInfo.entrySet()) {
            Location loc = info.getKey();
            State state = info.getValue();
            if (loc.equals(new Location(3,6))){
                assertEquals(state, State.W);
            } else if (loc.equals(new Location(4,6))) {
                assertEquals(state, State.B);
            } else if (loc.equals(new Location(4,7))) {
                assertEquals(state, State.B);
            } else {
                fail("Wrong key in hashmap");
            }
        }
    }
}