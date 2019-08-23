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

        State[][] jrotate3 = {//j
                {State.R, null   },
                {State.W, null   },
                {State.G, null   },
                {State.G, State.G}
        };

        State[][] test = jori;
        for (int i = 0; i < 3; i++) {
            test = TileType.rotate90_2DStateArray(test);
        }
        assertArrayEquals("R,0,\nW,0\nG,0\nG,G",jrotate3,test);

    }
}