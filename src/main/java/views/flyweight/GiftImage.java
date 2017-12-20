package views.flyweight;

import javafx.scene.image.Image;

public class GiftImage {
    public static final String Covered = "covered";
    public static final String unCovered = "uncovered";
    private static final Image image;
    private static final Image box;

    static {
        image = new Image(BombImage.class.getResourceAsStream("/images/RegularMode/mazeObjects/gift.png"));
        box = new Image(BombImage.class.getResourceAsStream("/images/RegularMode/mazeObjects/box.png"));
    }

    public static Image getImage(String state) {
        switch (state) {
            case Covered:
                return box;
            case unCovered:
                return image;
            default:
                return null;
        }

    }


}
