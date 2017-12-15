package views.flyweight;

import javafx.scene.image.Image;

public class BombImage {
    private static final Image image;

    static {
        image = new Image(BombImage.class.getResourceAsStream("/character/bomb"));
    }

    public static Image getImage() {
        return image;
    }

}
