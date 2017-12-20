package controllers;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import views.transientGUI.GameModesGUI;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    @FXML
    private Label welcomingLabel;

    @FXML
    private Label loadLabel;

    @FXML
    private Label startLabel;

    @FXML
    private Label exitLabel;
    @FXML
    void onMouseClickedStart(MouseEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().setOnHidden(e -> Platform.exit());

        GameModesGUI chooseMode = new GameModesGUI();
        Stage s = new Stage();
        try {
            chooseMode.start(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void onMouseEnteredWelcome(MouseEvent event) {
        ((Label)event.getSource()).setTextFill(Color.web("#00ff00"));
    }
    @FXML
    void onMouseExitedWelcome(MouseEvent event) {
        ((Label)event.getSource()).setTextFill(Color.web("#ffffff"));
    }
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
            startLabel.setContentDisplay(ContentDisplay.CENTER);
            exitLabel.setContentDisplay(ContentDisplay.CENTER);
        PathTransition transition = new PathTransition();
        PathTransition transition2 = new PathTransition();
        PathTransition transition3 = new PathTransition();
        Circle c = new Circle(100);
        transition.setDuration(Duration.seconds(2));
        transition2.setDuration(Duration.seconds(2));
        transition3.setDuration(Duration.seconds(2));
        transition.setCycleCount(1);
        transition2.setCycleCount(1);
        transition3.setCycleCount(1);
        transition.setPath(c);
        transition2.setPath(c);
        transition3.setPath(c);
        transition.setNode(startLabel);
        transition.play();
        transition2.setNode(loadLabel);
        transition2.play();
        transition3.setNode(exitLabel);
        transition3.play();
    }
}
