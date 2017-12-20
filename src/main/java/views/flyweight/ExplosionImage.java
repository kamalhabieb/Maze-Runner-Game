package views.flyweight;

import javafx.scene.image.Image;

public class ExplosionImage {
    public static final String EXPLOSION = "/images/RegularMode/mazeObjects/explosion3.png";
    private static final Image explosionImage;
    static {
        explosionImage = new Image(PlayerImage.class.getResourceAsStream(EXPLOSION));
    }

        public static Image getImage() {
        return explosionImage;
    }
}
