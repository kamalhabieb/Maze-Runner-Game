package views.flyweight;

import javafx.scene.image.Image;

public class PlayerImage {
    //TODO set correct resource paths
    public final static String IDLE = "/images/RegularMode/character/characterstop.png";
    public static final String NORTH = "/character/characterStop.png";
    public static final String SOUTH = "/character/characterStop.png";
    public static final String EAST = "/character/characterStop.png";
    public static final String WEST = "/character/characterStop.png";

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
