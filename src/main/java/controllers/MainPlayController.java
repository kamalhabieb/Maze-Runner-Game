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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.facade.DrawObserver;
import models.facade.Facade;
import views.Drawable;
import views.GameGUI.GameGUI;
import views.GameGUI.InfoGUI;
import views.GameGUI.WinGUI;
import views.GameGUI.loseGUI;
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
        canvas.setWidth(facade.currentMazeWidth * 30 * 2);
        canvas.setHeight(facade.currentMazeLength * 30 * 2);
        /*staticCanvas.setLayoutX(850);
        staticCanvas.setLayoutY(500);*/
        staticCanvas.setWidth(facade.currentMazeWidth * 30 * 2);
        staticCanvas.setHeight(facade.currentMazeLength * 30 * 2);
        translateCamera();
        /*canvas.setLayoutX(850);
        canvas.setLayoutY(500);*/
        //todo Get constants not numbers

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
        Platform.runLater(() -> {
            loseGUI lose = new loseGUI();
            try {
                URL resource = getClass().getResource("/music/lose.mp3");
                Media media = new Media(resource.toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(media);
                mediaPlayer2.play();
                lose.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void notifyDrawWin(ArrayList<Drawable> drawables) {
        Platform.runLater(() -> {
            WinGUI win = new WinGUI();
            try {
                URL resource = getClass().getResource("/music/win.mp3");
                Media media = new Media(resource.toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(media);
                mediaPlayer2.play();
                win.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    final URL resource = getClass().getResource("/music/shootSound.mp3");
    final Media media = new Media(resource.toString());
    final MediaPlayer mediaPlayerShoot = new MediaPlayer(media);

    final URL resource2 = getClass().getResource("/music/walkSound.mp3");
    final Media media2 = new Media(resource2.toString());
    final MediaPlayer mediaPlayerWalk = new MediaPlayer(media2);

    final URL resource3 = getClass().getResource("/music/jumpSound.mp3");
    final Media media3 = new Media(resource3.toString());
    final MediaPlayer mediaPlayerJump = new MediaPlayer(media3);

    final URL resource4 = getClass().getResource("/music/game.mp3");
    final Media media4 = new Media(resource4.toString());
    final MediaPlayer mediaBackGround = new MediaPlayer(media4);

    final URL resource5 = getClass().getResource("/music/noAmmo.mp3");
    final Media media5 = new Media(resource5.toString());
    final MediaPlayer noAmmo = new MediaPlayer(media5);

    public static int flag = 0;
    public void onKeyPressed(KeyEvent keyEvent) {
        keyCode.add(keyEvent.getCode());


        if (keyCode.size() == 1) {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                if(facade.player.getAmmo() == 0) {
                    noAmmo.setVolume(40);
                    noAmmo.stop();
                    noAmmo.play();
                }
                else {
                    mediaPlayerShoot.setVolume(40);
                    mediaPlayerShoot.stop();
                    mediaPlayerShoot.play();
                }
                facade.excute(CommandFactory.getCommand(shootABullet));

            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                if(flag == 0) {
                    mediaBackGround.setCycleCount(100);
                    mediaBackGround.setVolume(1000);
                    mediaBackGround.play();
                    flag = 1;
                }

                facade.excute(CommandFactory.getCommand(moveEast));
                mediaPlayerWalk.setCycleCount(100);
                mediaPlayerWalk.play();
                translateCamera();
            } else if (keyEvent.getCode() == KeyCode.LEFT) {
                facade.excute(CommandFactory.getCommand(moveWest));
                mediaPlayerWalk.setCycleCount(100);
                mediaPlayerWalk.play();
                translateCamera();
            } else if (keyEvent.getCode() == KeyCode.UP) {
                mediaPlayerJump.setVolume(0.1);
                facade.excute(CommandFactory.getCommand(moveNorth));
                mediaPlayerJump.setCycleCount(100);
                mediaPlayerJump.play();
                translateCamera();
            } else if (keyEvent.getCode() == KeyCode.DOWN) {
                facade.excute(CommandFactory.getCommand(moveSouth));
                mediaPlayerJump.setCycleCount(100);
                mediaPlayerJump.setVolume(0.1);
                mediaPlayerJump.play();
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
            if (keyEvent.getCode() == KeyCode.M) {
                mediaBackGround.stop();
                mediaBackGround.play();

            }
        } else if (keyCode.size() == 2) {
            if(keyCode.contains(KeyCode.SPACE) && keyCode.contains(KeyCode.RIGHT)) {
                facade.excute(CommandFactory.getCommand(moveEast));
                facade.excute(CommandFactory.getCommand(shootABullet));
                if(facade.player.getAmmo() == 0) {
                    noAmmo.setVolume(40);
                    noAmmo.stop();
                    noAmmo.play();
                }
                else {
                    mediaPlayerShoot.setVolume(40);
                    mediaPlayerShoot.stop();
                    mediaPlayerShoot.play();
                }
                translateCamera();
            } else if(keyCode.contains(KeyCode.SPACE) && keyCode.contains(KeyCode.LEFT)) {
                if(facade.player.getAmmo() == 0) {
                    noAmmo.setVolume(40);
                    noAmmo.stop();
                    noAmmo.play();
                }
                else {
                    mediaPlayerShoot.setVolume(40);
                    mediaPlayerShoot.stop();
                    mediaPlayerShoot.play();
                }
                facade.excute(CommandFactory.getCommand(moveWest));
                facade.excute(CommandFactory.getCommand(shootABullet));
                translateCamera();
            } else if(keyCode.contains(KeyCode.SPACE) && keyCode.contains(KeyCode.UP)) {
                facade.excute(CommandFactory.getCommand(moveNorth));
                facade.excute(CommandFactory.getCommand(shootABullet));
                if(facade.player.getAmmo() == 0) {
                    noAmmo.setVolume(40);
                    noAmmo.stop();
                    noAmmo.play();
                }
                else {
                    mediaPlayerShoot.setVolume(40);
                    mediaPlayerShoot.stop();
                    mediaPlayerShoot.play();
                }
                translateCamera();
            } else if(keyCode.contains(KeyCode.SPACE) && keyCode.contains(KeyCode.DOWN)) {
                if(facade.player.getAmmo() == 0) {
                    noAmmo.setVolume(40);
                    noAmmo.stop();
                    noAmmo.play();
                }
                else {
                    mediaPlayerShoot.setVolume(40);
                    mediaPlayerShoot.stop();
                    mediaPlayerShoot.play();
                }
                facade.excute(CommandFactory.getCommand(moveSouth));
                facade.excute(CommandFactory.getCommand(shootABullet));
                translateCamera();
            }

        } else if (keyCode.size() == 3) {
            if(keyCode.contains(KeyCode.A) && keyCode.contains(KeyCode.M) && keyCode.contains(KeyCode.O)) {
                facade.player.affectAmmo(6);

            }
            else if(keyCode.contains(KeyCode.H) && keyCode.contains(KeyCode.L) && keyCode.contains(KeyCode.T)) {
                facade.player.affectHealthBy(100);
            }
            else if(keyCode.contains(KeyCode.S) && keyCode.contains(KeyCode.P) && keyCode.contains(KeyCode.D)) {
                facade.player.setVelocity(10);
            }
            else if(keyCode.contains(KeyCode.L) && keyCode.contains(KeyCode.V) && keyCode.contains(KeyCode.E)) {
                facade.player.setLives(5);
            }
            else if(keyCode.contains(KeyCode.E) && keyCode.contains(KeyCode.N) && keyCode.contains(KeyCode.D)) {
                facade.player.setPosition((facade.getEndPoint().getX()-1)*30 , facade.getEndPoint().getY()*30);
                translateCamera();
            }

        }
    }


    public static void translateCamera() {
        TranslateTransition t = new TranslateTransition(Duration.millis(100), GameGUI.camera);
        //t.setFromX(GameGUI.camera.getTranslateX());
       if(gameModeController.difficulty.equalsIgnoreCase("easy")) {
           t.setToX(facade.player.getDestinationX() /*+ 1366 / 2 - 600*/);
           t.setToY(facade.player.getDestinationY()/* + 900 / 2 - 400*/);
       } else if(gameModeController.difficulty.equalsIgnoreCase("medium")) {
           t.setToX(facade.player.getDestinationX() -600);
           t.setToY(facade.player.getDestinationY()-400);
       } else if(gameModeController.difficulty.equalsIgnoreCase("hard")) {
           t.setToX(facade.player.getDestinationX() -1250);
           t.setToY(facade.player.getDestinationY()-850);
       }
        t.setInterpolator(Interpolator.EASE_BOTH);
        t.setDelay(Duration.ZERO);
        t.play();
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        keyCode.remove(keyEvent.getCode());
        mediaPlayerJump.stop();
        mediaPlayerWalk.stop();
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
