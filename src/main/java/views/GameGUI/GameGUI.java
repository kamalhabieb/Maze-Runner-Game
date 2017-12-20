package views.GameGUI;

import controllers.MainPlayController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameGUI extends Application {

    private MainPlayController controller;

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
        controller  = (MainPlayController)loader.getController();

        scene.setOnKeyPressed(controller::onKeyPressed);
        scene.setOnKeyReleased(controller::onKeyReleased);
        camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-2400);
        camera.setNearClip(0.1);
        camera.setFarClip(10000);
        camera.setTranslateX(primaryStage.getWidth()/2);
        camera.setTranslateY(primaryStage.getHeight()/2);
        camera.setFieldOfView(30);
        scene.setCamera(camera);
        //primaryStage.setFullScreen(true);



    }

    @Override
    public void stop() throws Exception {
        super.stop();
        controller.stop();
    }
}
