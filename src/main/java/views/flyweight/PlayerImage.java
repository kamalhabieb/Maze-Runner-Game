package views.flyweight;

import javafx.scene.image.Image;

public class PlayerImage {
    //TODO set correct resource paths
    public final static String IDLE = "/images/RegularMode/character/right/characterStop.png";
    public static final String NORTH = "/character/right/characterStop.png";
    public static final String SOUTH = "/character/right/characterStop.png";
    public static final String EAST = "/character/right/characterStop.png";
    public static final String WEST = "/character/left/characterStop.png";

    private static final Image idleImage;


    static {
        idleImage = new Image(PlayerImage.class.getResourceAsStream(IDLE));
    }

    public static Image getImage(String state) {
        switch (state) {
            case IDLE:
                return idleImage;
            default:
                return idleImage;
        }
    }

}
