package views.GameGUI;

import controllers.mainPlayController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public static PerspectiveCamera camera;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        scene.setOnKeyPressed(((mainPlayController)loader.getController())::onKeyPressed);
        scene.setOnKeyReleased(((mainPlayController)loader.getController())::onKeyReleased);
        camera = new PerspectiveCamera(true);
        //camera.setF
        camera.setTranslateZ(-2400);
        camera.setNearClip(0.1);
        camera.setFarClip(10000);
        camera.setTranslateX(primaryStage.getWidth()/2);
        camera.setTranslateY(primaryStage.getHeight()/2);

        camera.setFieldOfView(30);


        scene.setCamera(camera);
        //primaryStage.setFullScreen(true);



    }
}
