package models.charcter.weapons.bullets;

import javafx.scene.image.Image;
import views.flyweight.BombImage;

public class BulletImage {

    private static final Image image;

    static {
        image = new Image(BulletImage.class.getResourceAsStream("/images/RegularMode/mazeObjects/bullet.png"));
    }

    public static Image getImage() {
        return image;
    }
}
