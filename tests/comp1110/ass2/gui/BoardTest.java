package comp1110.ass2.gui;


import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * author Ziyue Wang
 */
public class BoardTest{

    static Board board = new Board();

    @Before
    public void virtualStart() {

        board.newGame();

    }

    @Test
    public void newGameTest() {
        // I am not sure how to test JavaFX components so just test this
        assertNotNull("no available game", board.focusGame);
    }
}