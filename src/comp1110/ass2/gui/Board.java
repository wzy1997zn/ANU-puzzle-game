package comp1110.ass2.gui;

import comp1110.ass2.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.*;

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
    public final Group hints = new Group();

    // F
    private final Group choices = new Group();
    private final Group challenges = new Group();
    private String challengeString = "";
    private String[] challengeSets = {"RRRBWBBRB","RWWRRRWWW"};

    private final Group difficulties = new Group();
    private final String[] difficultySets = {"easy", "medium", "hard"};

    private final boolean USE_DIFFICULTY = true;

    //a list storing whether tile is used, can also get the state by tiles.getChildren().get(i).placed
    private boolean[] tilePlaced = new boolean[10];
    // main game
    public FocusGame focusGame;
    // a string storing current placement on board
    public String currentPlacement = "";

    private DraggableTile[] draggableTiles = new DraggableTile[10]; // more easy way to access to the tiles



    // FIXME Task 8: Implement choices (you may use choices and assets provided for you in comp1110.ass2.gui.assets: sq-b.png, sq-g.png, sq-r.png & sq-w.png)


    //The issue that places in the gitlab provide a way to get the challenge->TestUtillity, so we just need to copy the code in the TestUtillity and rename it as ChallengeUtility
    // Since teacher updated Solution, we turn to use Solution.
    //This way uses the given SolutionSet to initialize the challengeSet.
    private void initChallenges() {
        List<String> challengeStrings = new ArrayList<>();
        for (Solution solution: ChallengeUtility.SOLUTIONS) {
            challengeStrings.add(solution.objective);
        }
        challengeSets = new String[challengeStrings.size()];
        challengeSets = challengeStrings.toArray(challengeSets);
        challengeSets = new String[ChallengeUtility.SOLUTIONS.length];
        for (int i = 0; i < ChallengeUtility.SOLUTIONS.length;i++
        ) {
            challengeSets[i] = ChallengeUtility.SOLUTIONS[i].objective;
        }
    }

    // FIXME Task 11: Generate interesting choices (each challenge may have just one solution)

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FocusGame Board - 1.0");
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);

        root.getChildren().add(tiles);
        root.getChildren().add(board);
        root.getChildren().add(hints);

        if (USE_DIFFICULTY) { // Using the global variable to choose difficulties or levels
            root.getChildren().addAll(difficulties, challenges); // It similar to the function of challenge
            makeDifficultBox();
        } else {
            // F
            initChallenges(); // Initializing the array of challenge
            root.getChildren().addAll(choices,challenges); //Adding the component group that related to the challenge to GUI
            makeChallengeBox(); //Initializing the related component group
        }

        makeBoard();
        initBoardStates(); //Integrate the code for initializing the board state into one

        addKeyHandler(scene);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initBoardStates() {
        makeTiles();
        newGame();
        currentPlacement = "";
    }


    private void addKeyHandler(Scene scene) {
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.SLASH)) {

                if (hints.getChildren().isEmpty()) {
//                    System.out.println("hint");
                    showHint();
                }
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.SLASH)) {
//                System.out.println("hint over");
                hideHint();
            }
        });
    }

    public void newGame() {
        focusGame = new FocusGame();
    }

    // F
    // Separating the method of setting challengeString and makeChallenge, them we can reuse the code of makeChallenge.
    private void setChallengeString(ChoiceBox<String> choices) {
        String choice = choices.getValue();
//        challengeString = challengeSets[choice.charAt(choice.length()-1) - '0' - 1];
        challengeString = challengeSets[Integer.parseInt(choice.split(" ")[1])];
    }

    private void makeChallenges() {

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
            square.setOpacity(0.6); // Changing the transparency of the scene
            challenges.getChildren().add(square);
//            currentY = gridY * SQUARE_SIZE + ZERO_Y;
        }
        challenges.toFront(); // It used to ensure that the movable tiles are on the upper level of the challenges
        tiles.toFront(); // Otherwise, it will cause the challenges to be overlaid on the tiles and we can't control the tiles
    }

    private void makeDifficulty(ChoiceBox<String> choices) { // Firstly, setting up the challengestring Secondly, draw the challenge
        String diffChoice = choices.getValue();

        // easy——releasing 7 pieces medium--releasing 4 pieces hard--releasing 0 pieces

        int[] preSetNums = {7,4,0};
        int index = -1;
        if (diffChoice.equals("easy")){
            index = 0;
        } else if (diffChoice.equals("medium")) {
            index = 1;
        } else if (diffChoice.equals("hard")) {
            index = 2;
        }
        int preSetNum = preSetNums[index];


        Random r = new Random();

        int challengeIndex = r.nextInt(Solution.SOLUTIONS.length);

        // 1. Setting up the challengestring 2. Drawing the challenge We randomly set the target, then we can use the previous method that we have written
        challengeString = Solution.SOLUTIONS[challengeIndex].objective;
        makeChallenges();

        // Randomly generate a list of tiles to be placed according to the number of placements of different difficulty levels
        // Randomly choose n element from the list
        String solutionString = FocusGame.getSolution(challengeString);
        List<String> solutionTiles = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            solutionTiles.add(solutionString.substring(i*4,(i+1)*4));
        }
        List<String> preSetTiles = new ArrayList<>();
        for (int i = 0; i < preSetNum; i++) {
            int preSetIndex = r.nextInt(solutionTiles.size());
            String preSetTile = solutionTiles.remove(preSetIndex);
            preSetTiles.add(preSetTile);
        }

        // Placing tiles by list
        preSetTileOnBoard(preSetTiles);

    }

    private void preSetTileOnBoard(List<String> preSetTiles) { // Preset pieces
        initBoardStates(); // If we don't initialize, the remaining tile on the top will keep decreasing, until blank

        for (String tileString:
                preSetTiles) {
            int tileIndex = tileString.charAt(0) - 'a';
            int gridX = tileString.charAt(1) - '0';
            int gridY = tileString.charAt(2) - '0';
            int rotateTime = tileString.charAt(3) - '0';
            DraggableTile curTile = draggableTiles[tileIndex];

            // 预置过程
            curTile.currentX = gridX * SQUARE_SIZE + ZERO_X;
            curTile.currentY = gridY * SQUARE_SIZE + ZERO_Y;
            curTile.setLayoutX(curTile.currentX);
            curTile.setLayoutY(curTile.currentY); // Set up xy

            curTile.orientation = 0; // Initializing
            for (int i = 0; i < rotateTime; i++) {
                curTile.rotate();
            }

            // Change the state of board
            currentPlacement += tileString;
            focusGame.addTileToBoard(tileString);

        }
        // Check for correctness
        System.out.println("Current placement: " + currentPlacement);
        focusGame.outputStates();
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
        String[] challengeChoiceStrings = new String[challengeSets.length];
        for (int i = 0; i < challengeChoiceStrings.length; i++) {
            challengeChoiceStrings[i] = "Challenge " + i;
        }
        choices.getItems().addAll(challengeChoiceStrings);
        this.choices.getChildren().addAll(choices);
        System.out.println(choices.getValue());


        Button changeLevel = new Button("Start");
        changeLevel.setLayoutX(BUTTON_X);
        changeLevel.setLayoutY(BUTTON_Y);
        changeLevel.setOnAction(e -> {
            setChallengeString(choices);
            makeChallenges();

        });
        this.choices.getChildren().add(changeLevel);

    }


    private void makeDifficultBox() { // Set up the button
        ChoiceBox<String> choices = new ChoiceBox<>();
        choices.setLayoutX(BUTTON_X);
        choices.setLayoutY(BUTTON_Y-50);
        choices.getItems().addAll(difficultySets);
        this.difficulties.getChildren().addAll(choices);

        Button changeDiff = new Button("Start");
        changeDiff.setLayoutX(BUTTON_X);
        changeDiff.setLayoutY(BUTTON_Y);
        changeDiff.setOnAction(e -> {
            makeDifficulty(choices);
        });
        this.difficulties.getChildren().add(changeDiff);
    }



    /**
     * generate draggable imgs of tiles
     */
    public void makeTiles() {
        tiles.getChildren().clear();
        draggableTiles = new DraggableTile[10];
        for (char i = 'a'; i <= 'j'; i++) {
            DraggableTile tile = new DraggableTile(i);
            tiles.getChildren().add(tile);
            draggableTiles[i - 'a'] = tile;
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


    // FIXME Task 10: Implement hints
    /**
     * add a valid tile to board to show possible placement as a hint
     */
    private void showHint() {
        String hintPlacement = getHint();
        if (hintPlacement.equals("")) {
            return; // no hint
        }

        String tileChar = "" + hintPlacement.charAt(0);
        int ori = (hintPlacement.charAt(3) - '0');
        ImageView hintImage = new ImageView();
        hintImage.setImage(new Image(Board.class.getResource(URI_BASE + URI_TILES + tileChar + ori + ".png").toString()));
        hintImage.setFitWidth((int)(hintImage.getImage().getWidth() * SCALE_RATE * TILE_RATE));
        hintImage.setFitHeight((int)(hintImage.getImage().getHeight() * SCALE_RATE * TILE_RATE));

        hintImage.setOpacity(0.5);

        double currentX = (hintPlacement.charAt(1) - '0') * SQUARE_SIZE + ZERO_X;
        double currentY = (hintPlacement.charAt(2) - '0') * SQUARE_SIZE + ZERO_Y;

        hintImage.setLayoutX(currentX);
        hintImage.setLayoutY(currentY);

        hints.getChildren().add(hintImage);
        hints.toFront();

    }

    private String getHint() {
        if (challengeString.equals("")) { // no selected challenge
            return ""; // no hint
        }
        String hintPlacement = "";
        int hintX = 0;
        int hintY = 0;
        while (hintPlacement.equals("")) {
            Set<String> hints = FocusGame.getViablePiecePlacements(currentPlacement, challengeString, hintX, hintY );
            if (hints != null && !hints.isEmpty()){
                hintPlacement = (String)(hints.toArray()[0]);
            } else {
                hintX++;
                if (hintX == 9) {
                    hintX = 0;
                    hintY++;
                }
                if (hintY == 5) {
                    return ""; // no possible hint
                }
            }
        }
        return hintPlacement;
    }

    /**
     * hide the hint
     */
    private void hideHint() {
        hints.getChildren().clear();
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

        long lastRotateTime = System.currentTimeMillis();
        private final long ALLOWED_INTERVAL = 50;

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
                long curTime = System.currentTimeMillis();
                if (curTime - lastRotateTime > ALLOWED_INTERVAL) {
                    lastRotateTime = curTime;
                    String oldPlacement = this.toString();
                    rotate();
                    while (!updatePlacement(this.toString())) {
                        fitToHome();
                    }
                    String newPlacement = this.toString();
                    updateFocusGame(oldPlacement, newPlacement);
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
                String oldPlacement = this.toString();
                fitToGrid();
                updateStates();
                while (!updatePlacement(this.toString())) {
                    fitToHome();
                }
                String newPlacement = this.toString();
                updateFocusGame(oldPlacement, newPlacement);
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
            // should exist a time interval to ensure not to rotate the tile so quickly. FIXED
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

    private void updateFocusGame(String oldPlacement, String newPlacement) {
        if (oldPlacement.length() != 4) { // was not on board
            if (newPlacement.length() != 4) { // is not on board
                // nothing to do
            } else { // is on board
                focusGame.addTileToBoard(newPlacement);
            }
        } else { // was on board
            if (newPlacement.length() != 4) { // is not on board
                focusGame.deleteTileFromBoard(oldPlacement);
            } else { // is on board
                focusGame.updateTileOnBoard(oldPlacement, newPlacement);
            }
        }
        focusGame.outputStates();
    }

    /**
     * use the method completed in task 5 to check whether the placement is vaild
     * if not, make last place to home position
     * @return
     */
    private boolean updatePlacement(String currentPutting) {
        // FIXME Task 7: Implement a basic playable Focus Game in JavaFX that only allows pieces to be placed in valid places

//        String lastPlacement = currentPlacement;
//        currentPlacement = "";
//        for (Node node: tiles.getChildren()) {
//            DraggableTile tile = (DraggableTile)node;
//            if (tile.placed) {
//                if (isCenterCovered() && isValidCenter(tile.toString(), currentPlacement)) {
//                    currentPlacement += tile;
//
//                    makePlacementWellFormed();
//                }
//            }
//        }

        String lastPlacement = currentPlacement;
        char tileName = currentPutting.charAt(0);
        int indexCurTile = currentPlacement.indexOf(tileName);
        if (currentPutting.charAt(1) == '-') { // putting a tile back
            if (!(indexCurTile == -1)) { // current putting tile is on board
                currentPlacement = currentPlacement.substring(0,indexCurTile) + currentPlacement.substring(indexCurTile+4); // delete related string
            }
        } else {
            if (indexCurTile != -1) { // update a tile placement
                currentPlacement = currentPlacement.substring(0,indexCurTile) + currentPlacement.substring(indexCurTile+4); // delete old first
            }
            // For task8, it need to check whether the addition or change meets the requirement of challenge. If it is not satisfied, the placement cannot be updated, and the tile is returned to the initial point.
            // It can use the function getViablePiecePlacements() in the FocusGame
            if (!challengeString.equals("")) {
                int col = 0;
                int row = 0;
                for (Map.Entry<Location, State> info: new Tile(currentPutting).getTileInfoLocation().entrySet()) {
                    Location loc = info.getKey();
                    State state = info.getValue();
                    if (state != null) {
                        col = loc.getY();
                        row = loc.getX();
                    }
                }

                Set<String> viablePuttingSet = FocusGame.getViablePiecePlacements(currentPlacement, challengeString, col, row);
                if (viablePuttingSet == null || !viablePuttingSet.contains(currentPutting)) { // If the viable is empty, it means this put is not satisfy the requirement, then return false
                    currentPlacement = lastPlacement; // If this put is not satisfy the requirement, the current placement will not change
                    return false;
                }
            }

            currentPlacement += currentPutting; // adding a tile
        }

        if (FocusGame.isPlacementStringValid(currentPlacement)) { // valid placement
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


    }

