package controllers;

import controllers.command.CommandFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import models.charcter.states.MoveEast;
import models.facade.DrawObserver;
import models.facade.Facade;
import views.Drawable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static controllers.command.CommandFactory.commands.*;

public class mainPlayController implements Initializable, DrawObserver {

    @FXML
    private Canvas canvas;
    @FXML
    private Canvas staticCanvas;

    private Facade facade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facade = new Facade();
        facade.initializeGame(Facade.EASY);

        canvas.setLayoutX(850);
        canvas.setLayoutY(500);
        canvas.setWidth(31 * 20);
        canvas.setHeight(31 * 20);

        staticCanvas.setLayoutX(850);
        staticCanvas.setLayoutY(500);
        staticCanvas.setWidth(31 * 20);
        staticCanvas.setHeight(31 * 20);

        facade.registerObserver(this);
       /* facade.populateDrawables();
        facade.notifyDraw();*/
    }

    @Override
    public void notifyDraw(ArrayList<Drawable> drawables) {
        int listSize = drawables.size();
        GraphicsContext canvas2D = canvas.getGraphicsContext2D();
        canvas2D.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
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
            canvas2D.drawImage(icon, sx, sy, sw, sh, dx, dy, dw, dh);
            //canvas2D.drawImage(icon, 0, 0, 40, 40, 10 *( i % 31),  10*(i/31), 10, 10);
        }
    }

    @Override
    public void notifyDrawStatic(ArrayList<Drawable> drawables) {
        System.out.println(drawables.size());
        int listSize = drawables.size();
        GraphicsContext canvas2D = staticCanvas.getGraphicsContext2D();
        canvas2D.clearRect(0, 0, staticCanvas.getWidth(), staticCanvas.getHeight());
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
            canvas2D.drawImage(icon, sx, sy, sw, sh, dx, dy, dw, dh);
        }
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.RIGHT) {
            facade.excute(CommandFactory.getCommand(moveEast));
        }
        else if (keyEvent.getCode() == KeyCode.LEFT) {
            facade.excute(CommandFactory.getCommand(moveWest));
        }
        else if (keyEvent.getCode() == KeyCode.UP) {
            facade.excute(CommandFactory.getCommand(moveNorth));
        }
        else if (keyEvent.getCode() == KeyCode.DOWN) {
            facade.excute(CommandFactory.getCommand(moveSouth));
        }

    }

    public void onKeyReleased(KeyEvent keyEvent) {
        facade.excute(CommandFactory.getCommand(idle));
    }
}
