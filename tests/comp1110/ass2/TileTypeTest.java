package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.*;

public class TileTypeTest {

    @Test
    public void testRotate90_2DStateArray() {
        State[][] jori = {//j
            {State.G, State.G, State.W, State.R},
            {State.G, null,    null,    null,  }
        };
        State[][] jrotate1 = {//j
                {State.G, State.G},
                {null,    State.G},
                {null,    State.W},
                {null,    State.R}
        };
        State[][] jrotate2 = {//j
                {null,    null,       null,       State.G},
                {State.R, State.W,    State.G,    State.G,  }
        };
        State[][] jrotate3 = {//j
                {State.R, null   },
                {State.W, null   },
                {State.G, null   },
                {State.G, State.G}
        };

        State[][] test = jori;
        test = TileType.rotate90_2DStateArray(test);
        assertArrayEquals("rotate 1 time",jrotate1,test);
        test = TileType.rotate90_2DStateArray(test);
        assertArrayEquals("rotate 2 time",jrotate2,test);
        test = TileType.rotate90_2DStateArray(test);
        assertArrayEquals("rotate 3 time",jrotate3,test);
        test = TileType.rotate90_2DStateArray(test);
        assertArrayEquals("rotate 4 time",jori,test);

    }
}