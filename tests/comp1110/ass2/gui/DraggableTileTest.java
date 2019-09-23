package comp1110.ass2.gui;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * author Ziyue Wang
 */
public class DraggableTileTest {
    static Board board = new Board();

    @BeforeClass
    public static void initBoard() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                new JFXPanel();
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            board.start(new Stage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        thread.start();// Initialize the thread
        Thread.sleep(1000);
    }

    @Test
    public void BasicTest() {
        //        assertTrue("I do not know how to write Junit test for JavaFX. I put test information into Board class. So just run a Board, play it and print out some information", true);
        assertNotNull(board.root);
        assertEquals(10,board.tiles.getChildren().size());
    }
    @Test
    public void ToStringTest(){
        for (Node tileNode:board.tiles.getChildren()) {
            Board.DraggableTile tile = (Board.DraggableTile) tileNode;
            String tileString = tile.toString();
            String shouldbe = "" + (char)(tile.tileID + 'a') + "-1-10";
            assertEquals(shouldbe, tileString);
        }

    }
    @Test
    public void RotateTest(){
        for (Node tileNode:board.tiles.getChildren()) {
            Board.DraggableTile tile = (Board.DraggableTile) tileNode;
            String before = tile.toString();
            tile.rotate();
            String after = tile.toString();
            String shouldbe = before.substring(0,2) + (before.charAt(3) - '0') % 4;
            assertEquals(shouldbe, after);
        }

    }
}