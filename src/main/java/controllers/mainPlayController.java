package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import models.facade.DrawObserver;
import models.facade.Facade;
import views.Drawable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class mainPlayController implements Initializable, DrawObserver {

    @FXML
    private Canvas canvas;
    private Facade facade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facade = new Facade();
        facade.initializeGame(Facade.EASY);
        canvas.setLayoutX(750);
        canvas.setWidth(31 * 20);
        canvas.setHeight(31 * 20);
        facade.registerObserver(this);
        facade.populateDrawables();
        facade.notifyDraw();
    }

    @Override
    public void notifyDraw(ArrayList<Drawable> drawables) {
        int listSize = drawables.size();
        GraphicsContext canvas2D = canvas.getGraphicsContext2D();
        for (int i = 0; i < listSize; i++) {
            Drawable currentObject = drawables.get(i);
            Image icon = currentObject.getImage();
            int sx = currentObject.getSrcX();
            int sy = currentObject.getSrcY();
            int dx = currentObject.getDestinationX();
            int dy = currentObject.getDestinationY();
            int sw = currentObject.getSrcWidth();
            int sh = currentObject.getSrcHeight();
            int dw = currentObject.getDestinationWidth();
            int dh = currentObject.getDestinationHeight();
            canvas2D.drawImage(icon, sx,sy,sw,sh,dx,dy,dw,dh);
            //canvas2D.drawImage(icon, 0, 0, 40, 40, 10 *( i % 31),  10*(i/31), 10, 10);
        }
    }
}
