package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Board extends Application {

    private static final double SCALE_RATE = 0.5; // scale all the things to ensure they can be show in 933*700 window
                                                  // including tile img, board img, square size
    private static final double TILE_RATE = 0.70; // for scaling the tile image
    private static final int SQUARE_SIZE = (int)(70 * SCALE_RATE);

    private static final int BOARD_X = 300;
    private static final int BOARD_Y = 100;

    private static final int BASEBOARD_WIDTH = (int)(720 * SCALE_RATE);
    private static final int BASEBOARD_HEIGHT = (int)(463 * SCALE_RATE);

    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    private static final String URI_BASE = "assets/";
    private static final String URI_TILES = "tiles/";
    private static final String BASEBOARD_URI = Board.class.getResource(URI_BASE + "board.png" ).toString();

    private static final int MARGIN_X = 30;
    private static final int MARGIN_Y = 30;
    private static final int OBJECTIVE_MARGIN_X = 100;
    private static final int OBJECTIVE_MARGIN_Y = 20;
    private static final int OBJECTIVE_WIDTH = 162;
    private static final int OBJECTIVE_HEIGHT = 150;

    private static final int ZERO_X = BOARD_X + (int)(50 * SCALE_RATE);
    private static final int ZERO_Y = BOARD_Y + (int)(90 * SCALE_RATE);

    private final Group root = new Group();
    private final Group tiles = new Group();
    private final Group board = new Group();

    private boolean[] tilePlaced = new boolean[10];

    // FIXME Task 7: Implement a basic playable Focus Game in JavaFX that only allows pieces to be placed in valid places

    // FIXME Task 8: Implement challenges (you may use challenges and assets provided for you in comp1110.ass2.gui.assets: sq-b.png, sq-g.png, sq-r.png & sq-w.png)

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting challenges (each challenge may have just one solution)

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FocusGame Board - 0.1");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().add(tiles);
        root.getChildren().add(board);

        makeBoard();
        makeTiles();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * generate imgs of tiles
     */
    private void makeTiles() {
        tiles.getChildren().clear();
        for (char i = 'a'; i <= 'j'; i++) {
            tiles.getChildren().add(new DraggableTile(i));
        }
    }

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
//            setFitWidth((int)(getFitWidth() * SCALE_RATE));
//            setFitHeight((int)(getFitHeight() * SCALE_RATE));

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

        Image[] image = new Image[4];

        DraggableTile(char tile) {
            super(tile);

            for (int i = 0; i < 4; i++) {
                Image curImg = new Image(Board.class.getResource(URI_BASE + URI_TILES + tile + i + ".png").toString());
                image[i] = curImg;
            }

            setImage(image[0]);
            setFitWidth((int)(getImage().getWidth() * SCALE_RATE * TILE_RATE));
            setFitHeight((int)(getImage().getHeight() * SCALE_RATE * TILE_RATE));
            orientation = 0;
            tilePlaced[tile - 'a'] = false;

            //FIXME not sure yet, just use ass1 set now
            homeX = MARGIN_X + ((tile - 'a') % 3) * SQUARE_SIZE;
            setLayoutX(homeX);
            currentX = homeX;
            homeY = OBJECTIVE_MARGIN_Y + OBJECTIVE_HEIGHT + MARGIN_Y + ((tile - 'a') / 3) * 2 * SQUARE_SIZE;
            setLayoutY(homeY);
            currentY = homeY;

            /*
                event handlers
             */
            setOnScroll(event -> {
                rotate();
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
            });
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

        @Override
        public String toString() {
            return "" + tileID + gridX + gridY + orientation;
        }
    }
}
