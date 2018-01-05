package views.transientGUI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GameModesGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gameModes.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        URL resource = getClass().getResource("/music/entro.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mediaPlayer.stop();
            }
        });
        primaryStage.show();
        primaryStage.setResizable(false);

    }
}
