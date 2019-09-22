package comp1110.ass2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public class LocationTest {

    @Rule
    public Timeout globalTimeout = Timeout.millis(500);

    @Test
    public void onBoardTest() {
        for(int r = 0; r < 5; r++){
            for(int c = 0; c < 9; c++){
                if(r == 4){
                    if(c == 0 || c == 8)
                        test(new Location(r,c), false);
                }
                else
                    test(new Location(r,c), true);
            }
        }
    }

    @Test
    public void offBoardTest(){
        for(int r = 0; r < 5; r++){
            test(new Location(r,-1),false);
            test(new Location(r,9),false);
        }
        for(int c = 0; c < 9; c++){
            test(new Location(-1,c),false);
            test(new Location(5,c),false);
        }
        test(new Location(4,0),false);
        test(new Location(4,9),false);
    }

    private void test(Location loc, boolean expected) {
        boolean out = loc.isValid();
        assertTrue("Location (" + loc.getX() + ", " + loc.getY() + "), expected " + expected + " but got " + out, out == expected);
    }
}