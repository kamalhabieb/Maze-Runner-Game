package controllers;

import controllers.command.CommandFactory;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.facade.DrawObserver;
import models.facade.Facade;
import views.Drawable;
import views.GameGUI.GameGUI;
import views.GameGUI.InfoGUI;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static controllers.command.CommandFactory.commands.*;

public class MainPlayController implements Initializable, DrawObserver {

    private static final int CAMERA_MOVE = 2;
    @FXML
    private Canvas canvas;
    @FXML
    private Canvas staticCanvas;

    public static Facade facade;

    private static InfoGUI info;
    private boolean infoIsOpenned = false;

    private Set<KeyCode> keyCode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //GUI COMPONENTS INITIALIZATION COMES FIRST
        keyCode = new HashSet<>();


        facade = new Facade();
        facade.registerObserver(this);
        switch (gameModeController.difficulty) {
            case "easy":
                facade.initializeGame(Facade.EASY);
                break;
            case "medium":
                facade.initializeGame(Facade.MEDIUM);
                break;
            case "hard":
                facade.initializeGame(Facade.HARD);
                break;
            default:
                facade.initializeGame(Facade.MEDIUM);
                break;


        }
        translateCamera();
        canvas.setLayoutX(850);
        canvas.setLayoutY(500);
        //todo Get constants not numbers
        canvas.setWidth(facade.currentMazeWidth * 30);
        canvas.setHeight(facade.currentMazeLength * 30);

        staticCanvas.setLayoutX(850);
        staticCanvas.setLayoutY(500);
        staticCanvas.setWidth(facade.currentMazeWidth * 30);
        staticCanvas.setHeight(facade.currentMazeLength * 30);


       /* facade.populateDrawables();
        facade.notifyDraw();*/
    }

    @Override
    public void notifyDraw(ArrayList<Drawable> drawables) {
        GraphicsContext canvas2D = canvas.getGraphicsContext2D();
        draw(canvas2D, drawables);
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
        draw(canvas2D, drawables);
    }

    @Override
    public void notifyDrawGameOver(List<Drawable> drawables) {

    }

    @Override
    public void notifyDrawWin(ArrayList<Drawable> drawables) {
        System.out.println("win");
        //facade.shutdown();
        facade.initializeGame(Facade.MEDIUM);

    }

    public void onKeyPressed(KeyEvent keyEvent) {
        keyCode.add(keyEvent.getCode());
        if (keyCode.size() == 1) {
            if (keyEvent.getCode() == KeyCode.SPACE) {
             /*final URL resource = getClass().getResource("/music/shootSound.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();*/
                //todo el sound aho uncomment
                facade.excute(CommandFactory.getCommand(shootABullet));

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
            if (keyEvent.getCode() == KeyCode.EQUALS) {
                GameGUI.camera.setTranslateZ(GameGUI.camera.getTranslateZ() + CAMERA_MOVE + 20);
            } else if (keyEvent.getCode() == KeyCode.MINUS) {
                GameGUI.camera.setTranslateZ(GameGUI.camera.getTranslateZ() - CAMERA_MOVE - 20);
            } else if (keyEvent.getCode() == KeyCode.W) {
                if (!(GameGUI.camera.getTranslateY() - CAMERA_MOVE < 900 / 2)) {
                    GameGUI.camera.setTranslateY(GameGUI.camera.getTranslateY() - CAMERA_MOVE - 5);
                }
            } else if (keyEvent.getCode() == KeyCode.S) {
                GameGUI.camera.setTranslateY(GameGUI.camera.getTranslateY() + CAMERA_MOVE + 5);
            } else if (keyEvent.getCode() == KeyCode.A) {
                if (!(GameGUI.camera.getTranslateX() - CAMERA_MOVE < 1366 / 2)) {
                    GameGUI.camera.setTranslateX(GameGUI.camera.getTranslateX() - CAMERA_MOVE - 5);
                }
            } else if (keyEvent.getCode() == KeyCode.D) {
                GameGUI.camera.setTranslateX(GameGUI.camera.getTranslateX() + CAMERA_MOVE + 5);
            }
            if (keyEvent.getCode() == KeyCode.TAB) {
                info = new InfoGUI();
                infoIsOpenned = true;
                try {
                    info.start(new Stage());
                    keyCode.remove(keyEvent.getCode());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else if (keyCode.size() == 2) {
            if(keyCode.contains(KeyCode.SPACE) && keyCode.contains(KeyCode.RIGHT)) {
                facade.excute(CommandFactory.getCommand(moveEast));
                facade.excute(CommandFactory.getCommand(shootABullet));
                translateCamera();
            } else if(keyCode.contains(KeyCode.SPACE) && keyCode.contains(KeyCode.LEFT)) {
                facade.excute(CommandFactory.getCommand(moveWest));
                facade.excute(CommandFactory.getCommand(shootABullet));
                translateCamera();
            } else if(keyCode.contains(KeyCode.SPACE) && keyCode.contains(KeyCode.UP)) {
                facade.excute(CommandFactory.getCommand(moveNorth));
                facade.excute(CommandFactory.getCommand(shootABullet));
                translateCamera();
            } else if(keyCode.contains(KeyCode.SPACE) && keyCode.contains(KeyCode.DOWN)) {
                facade.excute(CommandFactory.getCommand(moveSouth));
                facade.excute(CommandFactory.getCommand(shootABullet));
                translateCamera();
            }

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
        keyCode.remove(keyEvent.getCode());
        facade.excute(CommandFactory.getCommand(idle));
        if(infoIsOpenned) {
            infoIsOpenned = false;
            try {
                info.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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
