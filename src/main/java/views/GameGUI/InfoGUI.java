package views.GameGUI;

import controllers.MainPlayController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.*;

import java.io.IOException;

// Java 8 code
public class InfoGUI extends Application {

    private static final int shadowSize = 50;
    private static Scene scene;
    @Override public void start(final Stage stage) throws IOException {
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
        int playerLives = MainPlayController.facade.getMetadata().getLives() + 1;
        int playerScore = MainPlayController.facade.getMetadata().getScore();
        //Label health = new Label(String.valueOf(playerHealth));health.setTranslateX(0);health.setTranslateY(0);
        Label healthBar = new Label();healthBar.setTranslateX(40);healthBar.setTranslateY(-300);
        Label ammo = new Label();ammo.setTranslateX(0);ammo.setTranslateY(-100);
        Label lives = new Label();lives.setTranslateX(0);lives.setTranslateY(100);
        Label score = new Label(String.valueOf(playerScore));score.setTranslateX(0);score.setTranslateY(250);
        score.setFont(new Font("Sawasdee",80));


        int indexHealth = 1;
        if(playerHealth <= 20 ) {
            indexHealth = 1;
        }
        else if(playerHealth <= 40 ) {
            indexHealth = 2;
        }
        else if(playerHealth <= 60 ) {
            indexHealth = 3;
        }
        else if(playerHealth <= 80 ) {
            indexHealth = 4;
        }
        else if(playerHealth > 80 ) {
            indexHealth = 5;
        }
        Image img = new Image("/images/RegularMode/characterInfo/health/health" +indexHealth+".png");
        healthBar.setGraphic(new ImageView(img));
        if(!(playerAmmo <= 0) && !(playerAmmo > 6)) {
            Image imgAmmo = new Image("/images/RegularMode/characterInfo/ammo/ammo" +playerAmmo+".png");
            ammo.setGraphic(new ImageView(imgAmmo));
        }
        if(!(playerLives <= 0) && !(playerLives > 5)) {
            Image imgLives = new Image("/images/RegularMode/characterInfo/lives/lives" +playerLives+".png");
            lives.setGraphic(new ImageView(imgLives));
        }


        stackPane.getChildren().add(score);
        stackPane.getChildren().add(lives);
        stackPane.getChildren().add(ammo);
        stackPane.getChildren().add(healthBar);

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