package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import views.GameGUI.GameGUI;
import views.transientGUI.GameModesGUI;

import java.io.IOException;

public class gameModeController {

    @FXML
    void onMouseClickedEasy(MouseEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
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

}
