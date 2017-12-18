package controllers;

import controllers.command.CommandFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.charcter.states.MoveEast;
import models.facade.DrawObserver;
import models.facade.Facade;
import views.Drawable;
import views.GameGUI.GameGUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static controllers.command.CommandFactory.commands.*;

public class mainPlayController implements Initializable, DrawObserver {

    private static final int CAMERA_MOVE = 100;
    @FXML
    private Canvas canvas;
    @FXML
    private Canvas staticCanvas;

    private Facade facade;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //GUI COMPONENTS INITIALIZATION COMES FIRST
        canvas.setLayoutX(850);
        canvas.setLayoutY(500);
        //todo Get constants not numbers
        canvas.setWidth(31 * 30);
        canvas.setHeight(31 * 30);

        staticCanvas.setLayoutX(850);
        staticCanvas.setLayoutY(500);
        staticCanvas.setWidth(31 * 30);
        staticCanvas.setHeight(31 * 30);



        facade = new Facade();
        facade.registerObserver(this);
        facade.initializeGame(Facade.EASY);


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
            int dx = (int) currentObject.getDestinationX();
            int dy = (int) currentObject.getDestinationY();
            int sw = currentObject.getSrcWidth();
            int sh = currentObject.getSrcHeight();
            int dw = currentObject.getDestinationWidth();
            int dh = currentObject.getDestinationHeight();
            canvas2D.drawImage(icon, sx, sy, sw, sh, dx, dy, dw, dh);
            //canvas2D.drawImage(icon, 0, 0, 40, 40, 10 *( i % 31),  10*(i/31), 10, 10);
        }
    }

    @Override
    public void notifyDrawStatic(List<Drawable> drawables) {
        System.out.println(drawables.size());
        int listSize = drawables.size();
        GraphicsContext canvas2D = staticCanvas.getGraphicsContext2D();
        canvas2D.clearRect(0, 0, staticCanvas.getWidth(), staticCanvas.getHeight());
        for (int i = 0; i < listSize; i++) {
            Drawable currentObject = drawables.get(i);
            Image icon = currentObject.getImage();
            int sx = currentObject.getSrcX();
            int sy = currentObject.getSrcY();
            int dx = (int) currentObject.getDestinationX();
            int dy = (int) currentObject.getDestinationY();
            int sw = currentObject.getSrcWidth();
            int sh = currentObject.getSrcHeight();
            int dw = currentObject.getDestinationWidth();
            int dh = currentObject.getDestinationHeight();
            canvas2D.drawImage(icon, sx, sy, sw, sh, dx, dy, dw, dh);
        }
    }

    public void onKeyPressed(KeyEvent keyEvent) {

        if (keyEvent.getCode()==KeyCode.SPACE)
        {
            facade.excute(CommandFactory.getCommand(shootABullet));
        }
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
        else if (keyEvent.getCode() == KeyCode.SPACE) {
            //facade.excute(CommandFactory.getCommand());
        }
        if (keyEvent.getCode() == KeyCode.P) {
            GameGUI.camera.setTranslateZ(GameGUI.camera.getTranslateZ() + CAMERA_MOVE);
        }
        else if (keyEvent.getCode() == KeyCode.MINUS) {
            GameGUI.camera.setTranslateZ(GameGUI.camera.getTranslateZ() - CAMERA_MOVE);
        }
        else if (keyEvent.getCode() == KeyCode.W) {
            if(!(GameGUI.camera.getTranslateY() - CAMERA_MOVE < 900/2)) {
                GameGUI.camera.setTranslateY(GameGUI.camera.getTranslateY() - CAMERA_MOVE);
            }
        }
        else if (keyEvent.getCode() == KeyCode.S) {
            GameGUI.camera.setTranslateY(GameGUI.camera.getTranslateY() + CAMERA_MOVE);
        }
        else if (keyEvent.getCode() == KeyCode.A) {
            if(!(GameGUI.camera.getTranslateX() - CAMERA_MOVE < 1366/2)) {
                GameGUI.camera.setTranslateX(GameGUI.camera.getTranslateX() - CAMERA_MOVE);
            }
        }
        else if (keyEvent.getCode() == KeyCode.D) {
            GameGUI.camera.setTranslateX(GameGUI.camera.getTranslateX() + CAMERA_MOVE);
        }
        else {
            GameGUI.camera.setTranslateX(facade.player.getDestinationX()+ 1366 / 2);
            GameGUI.camera.setTranslateY(facade.player.getDestinationY() + 900 / 2);
        }

    }

    public void onKeyReleased(KeyEvent keyEvent) {
        facade.excute(CommandFactory.getCommand(idle));
    }

    public void clickOnNewGame(MouseEvent mouseEvent) {
       /* ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        GameGUI newGame = new GameGUI();
        Stage s = new Stage();
        try {
            newGame.start(s);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void stop() {
        facade.shutdown();
    }
}
