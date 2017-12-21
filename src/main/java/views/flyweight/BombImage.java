package views.flyweight;

import javafx.scene.image.Image;
import views.DynamicLinkage.Flyweight;

public class BombImage implements Flyweight {
    private static Image image;

    static {
        image = new Image(BombImage.class.getResourceAsStream("/images/RegularMode/mazeObjects/bomb.png"));
    }

    public static Image getImage() {
        return image;
    }


    public static void load(final Image image) {
        BombImage.image = image;
    }
}
