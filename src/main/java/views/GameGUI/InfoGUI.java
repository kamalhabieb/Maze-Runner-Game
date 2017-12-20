package views.GameGUI;

import controllers.MainPlayController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.*;
import sun.applet.Main;

// Java 8 code
public class InfoGUI extends Application {

    private static final int shadowSize = 50;
    private static Scene scene;
    @Override public void start(final Stage stage) {
        stage.initStyle(StageStyle.TRANSPARENT);

        StackPane stackPane = new StackPane(createShadowPane());
        stackPane.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.5);" +
                        "-fx-background-insets: " + shadowSize + ";"
        );

        scene = new Scene(stackPane, 800, 800);
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                close();
            }
        });
        int playerHealth = MainPlayController.facade.getMetadata().getHealth();
        int playerAmmo = MainPlayController.facade.getMetadata().getAmmo();
        int playerLives = MainPlayController.facade.getMetadata().getLives();
        int playerScore = MainPlayController.facade.getMetadata().getScore();


        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    // Create a shadow effect as a halo around the pane and not within
    // the pane's content area.
    public static void close() {
        scene.getWindow().hide();
    }
    private Pane createShadowPane() {
        Pane shadowPane = new Pane();
        // a "real" app would do this in a CSS stylesheet.
        shadowPane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-effect: dropshadow(gaussian, orange, " + shadowSize + ", 0, 0, 0);" +
                        "-fx-background-insets: " + shadowSize + ";"
        );

        Rectangle innerRect = new Rectangle();
        Rectangle outerRect = new Rectangle();
        shadowPane.layoutBoundsProperty().addListener(
                (observable, oldBounds, newBounds) -> {
                    innerRect.relocate(
                            newBounds.getMinX() + shadowSize,
                            newBounds.getMinY() + shadowSize
                    );
                    innerRect.setWidth(newBounds.getWidth() - shadowSize * 2);
                    innerRect.setHeight(newBounds.getHeight() - shadowSize * 2);

                    outerRect.setWidth(newBounds.getWidth());
                    outerRect.setHeight(newBounds.getHeight());

                    Shape clip = Shape.subtract(outerRect, innerRect);
                    shadowPane.setClip(clip);
                }
        );

        return shadowPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}