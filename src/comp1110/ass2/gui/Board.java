package comp1110.ass2.gui;

import comp1110.ass2.FocusGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Set;

import static comp1110.ass2.FocusGame.*;
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

    // F
    private static final int BUTTON_X = 80;
    private static final int BUTTON_Y = 450;

    /* the given width and height of the window */
    private static final int BOARD_WIDTH = 933;
    private static final int BOARD_HEIGHT = 700;

    /* scene components */
    public final Group root = new Group();
    public final Group tiles = new Group();
    public final Group board = new Group();

    // F
    private final Group choices = new Group();
    private final Group challenges = new Group();
    private String challengeString = "";
    private String[] challengeSets = {"RRRBWBBRB","RWWRRRWWW"};

    //a list storing whether tile is used, can also get the state by tiles.getChildren().get(i).placed
    private boolean[] tilePlaced = new boolean[10];
    // main game
    public FocusGame focusGame;
    // a string storing current placement on board
    public String currentPlacement = "";



    // FIXME Task 8: Implement choices (you may use choices and assets provided for you in comp1110.ass2.gui.assets: sq-b.png, sq-g.png, sq-r.png & sq-w.png)

    // FIXME Task 10: Implement hints

    // FIXME Task 11: Generate interesting choices (each challenge may have just one solution)

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FocusGame Board - 1.0");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().add(tiles);
        root.getChildren().add(board);

        // F
        root.getChildren().addAll(choices,challenges);
        makeChallengeBox();

        makeBoard();
        makeTiles();
        newGame();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newGame() {
        focusGame = new FocusGame();
    }

    // F
    private void makeChallenges(ChoiceBox<String> choices) {
        String choice = choices.getValue();
        challengeString = challengeSets[choice.charAt(choice.length()-1) - '0' - 1];
        challenges.getChildren().clear();
        for (int i = 0; i < challengeString.length(); i++) {
            ImageView square = new ImageView(new Image(Board.class.getResource(
                    URI_BASE + "sq-" + Character.toLowerCase(challengeString.charAt(i)) + ".png").toString()));
            square.setFitHeight(SQUARE_SIZE);
            square.setFitWidth(SQUARE_SIZE);
//            int X = TILES_X + MARGIN_X + ((tile - 'a') % 5) * 5 * SQUARE_SIZE;
//            setLayoutX(homeX);

            int X = ZERO_X + 3*SQUARE_SIZE + (i % 3)*SQUARE_SIZE;
            int Y = ZERO_Y + SQUARE_SIZE + (i/3)*SQUARE_SIZE;
            square.setX(X);
            square.setY(Y);
            challenges.getChildren().add(square);
//            currentY = gridY * SQUARE_SIZE + ZERO_Y;


        }
    }

    private boolean isValidCenter(String piecePlacement, String placement) {
//        Set<String> validPlacements = getViablePiecePlacements(placement, challengeString, 0, 0);
//        for (String vPlcement: validPlacements) {
//            if (!vPlcement.equals(piecePlacement)) {
//                return false;
//            }
//        }
//        challenge not done yet just make other method works update by Ziyue Wang
        return true;

    }

    private void makeChallengeBox() {
        ChoiceBox<String> choices = new ChoiceBox<>();
        choices.setLayoutX(BUTTON_X);
        choices.setLayoutY(BUTTON_Y-50);
        choices.getItems().addAll("Challenge 1", "Challenge 2");
        this.choices.getChildren().add(choices);
        System.out.println(choices.getValue());


        Button changeLevel = new Button("Start");
        changeLevel.setLayoutX(BUTTON_X);
        changeLevel.setLayoutY(BUTTON_Y);
        changeLevel.setOnAction(e -> makeChallenges(choices));
        this.choices.getChildren().add(changeLevel);

    }

    /**
     * generate draggable imgs of tiles
     */
    public void makeTiles() {
        tiles.getChildren().clear();
        for (char i = 'a'; i <= 'j'; i++) {
            tiles.getChildren().add(new DraggableTile(i));
        }
    }

    /**
     * set up baseboard
     */
    public void makeBoard() {
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
            if (onBoard()) { // F
                gridX = (int) Math.round((currentX - ZERO_X) / SQUARE_SIZE);
                gridY = (int) Math.round((currentY - ZERO_Y) / SQUARE_SIZE);

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
        public void rotate() {
            // rotating is shown by changing the img
            // also should make the xy right (checked)
            // FIXME should exist a time interval to ensure not to rotate the tile so quickly
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
                if (isCenterCovered() && isValidCenter(tile.toString(), currentPlacement)) {
                    currentPlacement += tile;
                    makePlacementWellFormed();
                }
            }
        }
        if (FocusGame.isPlacementStringValid(currentPlacement)) {
            System.out.println("Current placement: " + currentPlacement);
            return true;
        } else {
            if (currentPlacement.equals("")) {
                System.out.println("Current placement: " + currentPlacement);
                return true;
            }
            currentPlacement = lastPlacement;
            return false;
        }
    }

    //F
    // make the currentPlacement well formed
    private void makePlacementWellFormed() {

    }

    private boolean isCenterCovered() {
        return true; // not done yet but let other method work updated by Ziyue Wang
    }
}
