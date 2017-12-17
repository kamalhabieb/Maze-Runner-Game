package views.welcomeGUI;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class WelcomeGui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/welcome.fxml"));
        Scene scene = new Scene(loader.load());
        /*final URL resource = getClass().getResource("/music/entro.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();*/
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        //primaryStage.setFullScreen(true);
    }
}
