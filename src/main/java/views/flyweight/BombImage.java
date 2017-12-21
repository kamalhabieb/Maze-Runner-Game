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

    @Override
    public void load(final Image image) {
        this.image = image;
    }
}
