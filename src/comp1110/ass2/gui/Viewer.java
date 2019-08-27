package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * A very simple viewer for piece placements in the IQ-Focus game.
 * <p>
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int VIEWER_WIDTH = 720;
    private static final int VIEWER_HEIGHT = 480;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    private TextField textField;

    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */
    void makePlacement(String placement) {
        int position[][] = new int[10][3];
        String[] each = new String[10];
        for (int i = 0; i < placement.length() / 4; i++) {
            each[i] = placement.substring(i * 4, (i + 1) * 4);
        }
        for (int i = 0; i < placement.length() / 4; i++) {
            position[i][0] = Character.getNumericValue(each[i].charAt(1));
            position[i][1] = Character.getNumericValue(each[i].charAt(2));
            position[i][2] = Character.getNumericValue(each[i].charAt(3));
//
        }
        for (int i = 0; i < placement.length() / 4; i++) {
            ImageView imageView = new ImageView(new Image("comp1110/ass2/gui/assets/" + each[i].charAt(0) + ".png"));
//
            imageView.setRotate(position[i][2] * 90);
//    	    	imageView.setX(position[i][0]*70);
//    	    	imageView.setY(position[i][1]*70);
            int rate = 70;
            if (each[i].charAt(0) == 'e') {
                imageView.setX(3 * 70 - 35);
                imageView.setY(2 * 70 + 35);
            }

            switch (each[i].charAt(0)) {
                case 'a':
                    imageView.setFitHeight(rate * 2);
                    imageView.setFitWidth(rate * 3);
                    if (position[i][2] == 0 || position[i][2] == 2) {
                        imageView.setX(position[i][0] * rate);
                        imageView.setY(position[i][1] * rate);
                    } else {
                        imageView.setX(((double) position[i][0] - 0.5) * rate);
                        imageView.setY(((double) position[i][1] + 0.5) * rate);
                    }
                    break;
                case 'b':
                    imageView.setFitHeight(rate * 2);
                    imageView.setFitWidth(rate * 4);
                    if (position[i][2] == 0 || position[i][2] == 2) {
                        imageView.setX(position[i][0] * rate);
                        imageView.setY(position[i][1] * rate);
                    } else {
                        imageView.setX((position[i][0] - 1) * rate);
                        imageView.setY((position[i][1] + 1) * rate);
                    }
                    break;
                case 'c':
                    imageView.setFitHeight(rate * 2);
                    imageView.setFitWidth(rate * 4);
                    if (position[i][2] == 0 || position[i][2] == 2) {
                        imageView.setX(position[i][0] * rate);
                        imageView.setY(position[i][1] * rate);
                    } else {
                        imageView.setX((position[i][0] - 1) * rate);
                        imageView.setY((position[i][1] + 1) * rate);
                    }
                    break;
                case 'd':
                    imageView.setFitHeight(rate * 2);
                    imageView.setFitWidth(rate * 3);
                    if (position[i][2] == 0 || position[i][2] == 2) {
                        imageView.setX(position[i][0] * rate);
                        imageView.setY(position[i][1] * rate);
                    } else {
                        imageView.setX(((double) position[i][0] - 0.5) * rate);
                        imageView.setY(((double) position[i][1] + 0.5) * rate);
                    }
                    break;
                case 'e':
                    imageView.setFitHeight(rate * 2);
                    imageView.setFitWidth(rate * 3);
                    if (position[i][2] == 0 || position[i][2] == 2) {
                        imageView.setX(position[i][0] * rate);
                        imageView.setY(position[i][1] * rate);
                    } else {
                        imageView.setX(((double) position[i][0] - 0.5) * rate);
                        imageView.setY(((double) position[i][1] + 0.5) * rate);
                    }
                    break;
                case 'f':
                    imageView.setFitHeight(rate * 1);
                    imageView.setFitWidth(rate * 3);
                    if (position[i][2] == 0 || position[i][2] == 2) {
                        imageView.setX(position[i][0] * rate);
                        imageView.setY(position[i][1] * rate);
                    } else {
                        imageView.setX((position[i][0] - 1) * rate);
                        imageView.setY((position[i][1] + 1) * rate);
                    }
                    break;
                case 'g':
                    imageView.setFitHeight(rate * 2);
                    imageView.setFitWidth(rate * 3);
                    if (position[i][2] == 0 || position[i][2] == 2) {
                        imageView.setX(position[i][0] * rate);
                        imageView.setY(position[i][1] * rate);
                    } else {
                        imageView.setX(((double) position[i][0] - 0.5) * rate);
                        imageView.setY(((double) position[i][1] + 0.5) * rate);
                    }
                    break;
                case 'h':
                    imageView.setFitHeight(rate * 3);
                    imageView.setFitWidth(rate * 3);
                    imageView.setX(position[i][0] * rate);
                    imageView.setY(position[i][1] * rate);
                    break;
                case 'i':
                    imageView.setFitHeight(rate * 2);
                    imageView.setFitWidth(rate * 2);
                    imageView.setX(position[i][0] * rate);
                    imageView.setY(position[i][1] * rate);
                    break;
                case 'j':
                    imageView.setFitHeight(rate * 2);
                    imageView.setFitWidth(rate * 4);
                    if (position[i][2] == 0 || position[i][2] == 2) {
                        imageView.setX(position[i][0] * rate);
                        imageView.setY(position[i][1] * rate);
                    } else {
                        imageView.setX((position[i][0] - 1) * rate);
                        imageView.setY((position[i][1] + 1) * rate);
                    }
                    break;
                default:
                    break;
            }

//    	    	imageView.setFitHeight(100);
//    	    	imageView.setFitWidth(100);
            imageView.setPreserveRatio(false);
//    	    	System.out.println(position[i][0]);
            controls.getChildren().add(imageView);

        }




        // FIXME Task 4: implement the simple placement viewer
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                controls.getChildren().clear();
                HBox hb = new HBox();
                hb.getChildren().addAll(label1, textField, button);
                hb.setSpacing(10);
                hb.setLayoutX(130);
                hb.setLayoutY(VIEWER_HEIGHT - 50);
                controls.getChildren().add(hb);
                makePlacement(textField.getText());
                textField.clear();
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("FocusGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}