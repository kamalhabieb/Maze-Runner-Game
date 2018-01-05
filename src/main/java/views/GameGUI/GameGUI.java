package views.GameGUI;

import controllers.MainPlayController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import views.welcomeGUI.WelcomeGui;

import java.io.IOException;
import java.net.URL;

public class GameGUI extends Application {

    private MainPlayController controller;

    public static void main(String[] args) {
        launch(args);
    }
    public static PerspectiveCamera camera;

    @Override
    public void start(Stage primaryStage) throws IOException {
        camera = new PerspectiveCamera(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        camera.setNearClip(0.1);
        camera.setFarClip(10000);
        camera.setTranslateZ(-2400);
        /*camera.setTranslateX(primaryStage.getWidth()/2 - 600);
        camera.setTranslateY(primaryStage.getHeight()/2 - 400);*/
        camera.setFieldOfView(30);

        scene.setCamera(camera);

        controller  = (MainPlayController)loader.getController();

        scene.setOnKeyPressed(controller::onKeyPressed);
        scene.setOnKeyReleased(controller::onKeyReleased);

        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });




        URL resource = getClass().getResource("/music/transient.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        //primaryStage.setFullScreen(true);



    }

    @Override
    public void stop() throws Exception {
        super.stop();
        controller.stop();
    }
}
