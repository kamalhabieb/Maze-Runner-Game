package views.flyweight;

import javafx.scene.image.Image;

public class BombImage {
    private static final Image image;

    static {
        image = new Image(BombImage.class.getResourceAsStream("/images/RegularMode/mazeObjects/bomb.png"));
    }

    public static Image getImage() {
        return image;
    }

}
