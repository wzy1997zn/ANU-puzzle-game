package comp1110.ass2.gui;

import comp1110.ass2.FocusGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * the visible edition of focus game
 * code skeleton by Ziyue Wang
 */
public class Board extends Application {

    /* below are some const of path and relative path */
    private static final String URI_BASE = "assets/";
    private static final String URI_TILES = "tiles/";
    private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "board.png" ).toString();

    /* below are some basic const of board scale and position */
    private static final double SCALE_RATE = 0.5; // scale all the things to ensure they can be show in 933*700 window
                                                  // including tile img, board img, square size
    private static final double TILE_RATE = 0.70; // for scaling the tile image
    private static final int SQUARE_SIZE = (int)(70 * SCALE_RATE);

    private static final int BOARD_X = 286; // baseboard position
    private static final int BOARD_Y = 350;

    private static final int BASEBOARD_WIDTH = (int)(720 * SCALE_RATE); // baseboard scale
    private static final int BASEBOARD_HEIGHT = (int)(463 * SCALE_RATE);

    private static final int MARGIN_X = 30; // for tiles
    private static final int MARGIN_Y = 30;

    private static final int TILES_X = 20; // for tiles
    private static final int TILES_Y = 50;

    private static final int ZERO_X = BOARD_X + (int)(50 * SCALE_RATE); // the origin position of the baseboard
    private static final int ZERO_Y = BOARD_Y + (int)(90 * SCALE_RATE);

    /* the given width and height of the window */
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    /* scene components */
    private final Group root = new Group();
    private final Group tiles = new Group();
    private final Group board = new Group();

    //a list storing whether tile is used, can also get the state by tiles.getChildren().get(i).placed
    private boolean[] tilePlaced = new boolean[10];
    // main game
    private FocusGame focusGame;
    // a string storing current placement on board
    private String currentPlacement = "";



    // FIXME Task 8: Implement challenges (you may use challenges and assets provided for you in comp1110.ass2.gui.assets: sq-b.png, sq-g.png, sq-r.png & sq-w.png)

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FocusGame Board - 1.0");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().add(tiles);
        root.getChildren().add(board);

        makeBoard();
        makeTiles();
        newGame();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void newGame() {
        focusGame = new FocusGame();
    }

    /**
     * generate draggable imgs of tiles
     */
    private void makeTiles() {
        tiles.getChildren().clear();
        for (char i = 'a'; i <= 'j'; i++) {
            tiles.getChildren().add(new DraggableTile(i));
        }
    }

    /**
     * set up baseboard
     */
    private void makeBoard() {
        board.getChildren().clear();
        
        ImageView baseBoard = new ImageView();
        baseBoard.setImage(new Image(BASEBOARD_URI));
        baseBoard.setFitWidth(BASEBOARD_WIDTH);
        baseBoard.setFitHeight(BASEBOARD_HEIGHT);
        baseBoard.setX(BOARD_X);
        baseBoard.setY(BOARD_Y);

        board.getChildren().add(baseBoard);
        board.toBack();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Graphical tiles of the game
     * Coded by Ziyue Wang
     * Refer to ass1
     */
    class GTile extends ImageView {
        int tileID;

        GTile(char tile) {
            if (tile > 'j' || tile < 'a') {
                throw new IllegalArgumentException("Bad tile: \"" + tile + "\"");
            }
            this.tileID = tile - 'a';

        }
    }

    /**
     * adding actions to GTile
     */
    class DraggableTile extends GTile {
        int homeX;
        int homeY;

        double currentX = getLayoutX();
        double currentY = getLayoutY();

        double lastMouseX;
        double lastMouseY;

        int gridX = -1;
        int gridY = -1;

        int orientation = 0;

        boolean placed = false;

        Image[] image = new Image[4];

        DraggableTile(char tile) {
            super(tile);

            // 4 imgs for 4 orientations
            for (int i = 0; i < 4; i++) {
                Image curImg = new Image(Board.class.getResource(URI_BASE + URI_TILES + tile + i + ".png").toString());
                image[i] = curImg;
            }

            // set up image
            setImage(image[0]);
            setFitWidth((int)(getImage().getWidth() * SCALE_RATE * TILE_RATE));
            setFitHeight((int)(getImage().getHeight() * SCALE_RATE * TILE_RATE));
            orientation = 0;
            tilePlaced[tile - 'a'] = false;

            //make the components to the suitable place
            homeX = TILES_X + MARGIN_X + ((tile - 'a') % 5) * 5 * SQUARE_SIZE;
            setLayoutX(homeX);
            currentX = homeX;
            homeY = TILES_Y + MARGIN_Y + ((tile - 'a') / 5) * 3 * SQUARE_SIZE;
            setLayoutY(homeY);
            currentY = homeY;

            /*
                event handlers
             */
            setOnScroll(event -> {
                rotate();
                while (!updatePlacement()) {
                    fitToHome();
                }
            });

            setOnMousePressed(event -> {
                // record the origin mouse position
                lastMouseX = event.getSceneX();
                lastMouseY = event.getSceneY();
            });

            setOnMouseDragged(event -> {
                toFront();
                // get the moved xy of mouse and add them to img layout
                double movedX = event.getSceneX() - lastMouseX;
                double movedY = event.getSceneY() - lastMouseY;
                currentX = getLayoutX() + movedX;
                currentY = getLayoutY() + movedY;
                setLayoutX(currentX);
                setLayoutY(currentY);
                // update the last mouse position
                lastMouseX = event.getSceneX();
                lastMouseY = event.getSceneY();
            });

            setOnMouseReleased(event -> {
                fitToGrid();
                updateStates();
                while (!updatePlacement()) {
                    fitToHome();
                }
            });
        }

        /**
         * change use states
         */
        private void updateStates() {
            if (this.gridX != -1 && this.gridY != -1) {
                placed = true;
                tilePlaced[this.tileID] = true;
            } else {
                placed = false;
                tilePlaced[this.tileID] = false;
            }
            System.out.println("Putting: " + this);
        }

        /**
         * fit the tile img to the grid on board
         */
        private void fitToGrid() {
            if (onBoard()) {
                gridX = (int)Math.round((currentX - ZERO_X) / SQUARE_SIZE);
                gridY = (int)Math.round((currentY - ZERO_Y) / SQUARE_SIZE);

                currentX = gridX * SQUARE_SIZE + ZERO_X;
                currentY = gridY * SQUARE_SIZE + ZERO_Y;

                setLayoutX(currentX);
                setLayoutY(currentY);

            } else {
                // if not on board, go back
                fitToHome();
            }
        }

        /**
         * let the tile back to home
         */
        private void fitToHome() {
            setLayoutX(homeX);
            setLayoutY(homeY);
            currentX = homeX;
            currentY = homeY;
            gridX = -1;
            gridY = -1;
            updateStates();
        }

        /**
         * check whether the tile is in the board area (roughly)
         * @return
         */
        private boolean onBoard() {
            currentX = getLayoutX();
            currentY = getLayoutY();
            if (currentX > ZERO_X - 0.5 * SQUARE_SIZE && currentX < ZERO_X + 9.5 * SQUARE_SIZE) {
                if (currentY > ZERO_Y - 0.5 * SQUARE_SIZE && currentY < ZERO_Y + 5.5 * SQUARE_SIZE) {
                    return true;
                }
            }
            return false;
        }

        /**
         * rotate the tile img
         */
        private void rotate() {
            // rotating is shown by changing the img
            // also should make the xy right (have not checked)
            this.orientation = (this.orientation + 1) % 4;
            this.setImage(image[orientation]);
            setFitWidth((int)(getImage().getWidth() * SCALE_RATE * TILE_RATE));
            setFitHeight((int)(getImage().getHeight() * SCALE_RATE * TILE_RATE));
            toFront();
        }

        /**
         * to generate placement and easy for debugging
         */
        @Override
        public String toString() {
            return "" + (char)(tileID + 'a') + gridX + gridY + orientation;
        }
    }

    /**
     * use the method completed in task 5 to check whether the placement is vaild
     * if not, make last place to home position
     * @return
     */
    private boolean updatePlacement() {
        // FIXME Task 7: Implement a basic playable Focus Game in JavaFX that only allows pieces to be placed in valid places
        String lastPlacement = currentPlacement;
        currentPlacement = "";
        for (Node node: tiles.getChildren()) {
            DraggableTile tile = (DraggableTile)node;
            if (tile.placed) {
                currentPlacement += tile;
            }
        }
        if (FocusGame.isPlacementStringValid(currentPlacement)) {
            System.out.println("Current placement: " + currentPlacement);
            return true;
        } else {
            currentPlacement = lastPlacement;
            return false;
        }
    }
}
