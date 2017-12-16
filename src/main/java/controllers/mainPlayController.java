package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import models.facade.DrawObserver;
import views.Drawable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class mainPlayController implements Initializable , DrawObserver{

    @FXML
    private Canvas canvas;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void notifyDraw(ArrayList<Drawable> drawables) {
        int listSize = drawables.size();
        GraphicsContext canvas2D = canvas.getGraphicsContext2D();
        for(int i = 0; i < listSize; i++) {

        }
    }
}
