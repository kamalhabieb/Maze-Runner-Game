package views.welcomeGUI;

import controllers.GUIController;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        primaryStage.setScene(scene);
        primaryStage.show();
        URL resource = getClass().getResource("/music/welcome.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        primaryStage.setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                mediaPlayer.stop();
            }
        });

        primaryStage.setResizable(false);

        //primaryStage.setFullScreen(true);
    }
}
