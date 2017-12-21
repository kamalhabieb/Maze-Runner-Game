package views.GameGUI;

import controllers.MainPlayController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import models.facade.Facade;

import java.io.IOException;

// Java 8 code
public class loseGUI extends Application {

    private static final int shadowSize = 50;
    private static Scene scene;
    @Override public void start(Stage stage) throws IOException {
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
            }
        });

        int playerScore = MainPlayController.facade.getMetadata().getScore();
        Label winPic = new Label();winPic.setTranslateX(0);winPic.setTranslateY(-200);
        Label nextLevel = new Label("Play Again");
        nextLevel.setFont(new Font("Sawasdee",80));
        nextLevel.setTextFill(Color.web("#ff0000"));
        nextLevel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainPlayController.facade.initializeGame(Facade.EASY);
                MainPlayController.translateCamera();
                stage.close();

            }
        });
        nextLevel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nextLevel.setTextFill(Color.web("#00ff00"));
            }
        });
        nextLevel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nextLevel.setTextFill(Color.web("#ff0000"));
            }
        });
        nextLevel.setStyle("-fx-text-inner-color: red;");
        Image img = new Image("/images/lose.png");
        winPic.setGraphic(new ImageView(img));
        Label score = new Label(String.valueOf(playerScore));score.setTranslateX(0);score.setTranslateY(250);
        score.setFont(new Font("Sawasdee",80));
        stackPane.getChildren().add(score);
        stackPane.getChildren().add(winPic);
        stackPane.getChildren().add(nextLevel);


        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    // Create a shadow effect as a halo around the pane and not within
    // the pane's content area.
    public static void close() {
        scene.getWindow().hide();
    }

    private Pane createShadowPane() throws IOException {
        Pane shadowPane = new Pane();
        // a "real" app would do this in a CSS stylesheet.
        shadowPane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-effect: dropshadow(gaussian, red, " + shadowSize + ", 0, 0, 0);" +
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