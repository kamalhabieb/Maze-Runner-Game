package controllers;

import controllers.command.CommandFactory;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
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
import javafx.util.Duration;
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

    private static final int CAMERA_MOVE = 2;
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
        translateCamera();

       /* facade.populateDrawables();
        facade.notifyDraw();*/
    }

    @Override
    public void notifyDraw(ArrayList<Drawable> drawables) {
        GraphicsContext canvas2D = canvas.getGraphicsContext2D();
        draw(canvas2D,drawables);
    }

    private void draw(GraphicsContext canvas2D, List<Drawable> drawables) {
        Platform.runLater(() -> {
            canvas2D.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            drawables.forEach(currentObject -> {
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
            });
        });
    }

    @Override
    public void notifyDrawStatic(List<Drawable> drawables) {
        GraphicsContext canvas2D = staticCanvas.getGraphicsContext2D();
        draw(canvas2D,drawables);
    }

    public void onKeyPressed(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.SPACE) {
            facade.excute(CommandFactory.getCommand(shootABullet));
            facade.fireWeapon();
        } else if (keyEvent.getCode() == KeyCode.RIGHT) {
            facade.excute(CommandFactory.getCommand(moveEast));
            translateCamera();
        } else if (keyEvent.getCode() == KeyCode.LEFT) {
            facade.excute(CommandFactory.getCommand(moveWest));
            translateCamera();

        } else if (keyEvent.getCode() == KeyCode.UP) {
            facade.excute(CommandFactory.getCommand(moveNorth));
            translateCamera();

        } else if (keyEvent.getCode() == KeyCode.DOWN) {
            facade.excute(CommandFactory.getCommand(moveSouth));
            translateCamera();

        }
        if (keyEvent.getCode() == KeyCode.P) {
            GameGUI.camera.setTranslateZ(GameGUI.camera.getTranslateZ() + CAMERA_MOVE);
        } else if (keyEvent.getCode() == KeyCode.MINUS) {
            GameGUI.camera.setTranslateZ(GameGUI.camera.getTranslateZ() - CAMERA_MOVE);
        } else if (keyEvent.getCode() == KeyCode.W) {
            if (!(GameGUI.camera.getTranslateY() - CAMERA_MOVE < 900 / 2)) {
                GameGUI.camera.setTranslateY(GameGUI.camera.getTranslateY() - CAMERA_MOVE);
            }
        } else if (keyEvent.getCode() == KeyCode.S) {
            GameGUI.camera.setTranslateY(GameGUI.camera.getTranslateY() + CAMERA_MOVE);
        } else if (keyEvent.getCode() == KeyCode.A) {
            if (!(GameGUI.camera.getTranslateX() - CAMERA_MOVE < 1366 / 2)) {
                GameGUI.camera.setTranslateX(GameGUI.camera.getTranslateX() - CAMERA_MOVE);
            }
        } else if (keyEvent.getCode() == KeyCode.D) {
            GameGUI.camera.setTranslateX(GameGUI.camera.getTranslateX() + CAMERA_MOVE);
        }


    }

    private void translateCamera() {
        TranslateTransition t = new TranslateTransition(Duration.millis(100), GameGUI.camera);
        //t.setFromX(GameGUI.camera.getTranslateX());
        t.setToX(facade.player.getDestinationX() + 1366 / 2);
        //t.setFromY(GameGUI.camera.getTranslateY());
        t.setToY(facade.player.getDestinationY() + 900 / 2);

        t.setInterpolator(Interpolator.EASE_BOTH);
        t.setDelay(Duration.ZERO);
        t.play();


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
