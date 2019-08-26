package comp1110.ass2;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    @Test
    public void equals1() {
        assertEquals(new Location(0,1), new Location(0,1));
    }
}