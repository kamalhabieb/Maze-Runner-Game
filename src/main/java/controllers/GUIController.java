package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
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
    }
}
