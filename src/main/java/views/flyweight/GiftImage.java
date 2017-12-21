package views.flyweight;

import javafx.scene.image.Image;
import views.DynamicLinkage.Flyweight;

public class GiftImage implements Flyweight {

    private static Image image;

    static {
        image = new Image(GiftImage.class.getResourceAsStream("/images/RegularMode/mazeObjects/gift.png"));
    }

    public static Image getImage() {
        return image;
    }


    @Override
    public void load(final Image image) {
        this.image = image;
    }
}
