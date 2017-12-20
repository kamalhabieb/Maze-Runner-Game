package controllers;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import views.GameGUI.GameGUI;
import views.transientGUI.GameModesGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class gameModeController implements Initializable {

    @FXML
    private Label easyLabel;
    @FXML
    private Label mediumLabel;
    @FXML
    private Label hardLabel;
    @FXML
    private Label playerLabel;
    @FXML
    private Button rightButton;
    @FXML
    private Button leftButton;

    public static String player = "";
    public static String difficulty;

    public void onMouseClickRight(MouseEvent event) {
        Image image = new Image(getClass().getResourceAsStream("/images/playerIslam.png"));
        playerLabel.setGraphic(new ImageView(image));
        player = "Islam";
    }
    public void onMouseClickLeft(MouseEvent event) {
        Image image = new Image(getClass().getResourceAsStream("/images/playerDefault.png"));
        playerLabel.setGraphic(new ImageView(image));
        player = "Default";
    }

    @FXML
    void onMouseClicked(MouseEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().setOnHidden(e -> Platform.exit());
        if(((Label)event.getSource()).getText().equalsIgnoreCase("easy")) {
            difficulty = "easy";
        }
        else if(((Label)event.getSource()).getText().equalsIgnoreCase("medium")) {
            difficulty = "medium";
        }
        else {
            difficulty = "hard";
        }
        GameGUI chooseMode = new GameGUI();
        Stage s = new Stage();
        try {
            chooseMode.start(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void onMouseEnteredTransient(MouseEvent event) {
        ((Label)event.getSource()).setTextFill(Color.web("#00ff00"));
    }
    @FXML
    void onMouseExitedTransient(MouseEvent event) {
        ((Label)event.getSource()).setTextFill(Color.web("#ffffff"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PathTransition transition = new PathTransition();
        PathTransition transition2 = new PathTransition();
        PathTransition transition3 = new PathTransition();
        Circle c = new Circle(100);
        transition.setDuration(Duration.seconds(1.5));
        transition2.setDuration(Duration.seconds(1));
        transition3.setDuration(Duration.seconds(0.5));
        transition.setCycleCount(1);
        transition2.setCycleCount(1);
        transition3.setCycleCount(1);
        transition.setPath(c);
        transition2.setPath(c);
        transition3.setPath(c);
        transition.setNode(easyLabel);
        transition.play();
        transition2.setNode(mediumLabel);
        transition2.play();
        transition3.setNode(hardLabel);
        transition3.play();

        Image image = new Image(getClass().getResourceAsStream("/images/playerDefault.png"));
        playerLabel.setGraphic(new ImageView(image));
        player = "Default";
    }

}
