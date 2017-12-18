package views.flyweight;

import javafx.scene.image.Image;

public class PlayerImage {
    //TODO set correct resource paths Kamal On it
    //TODO 1- North and South sprites , 2- No Running is covered

    public final static String IDLE = "/images/RegularMode/character/right/characterStop.png";
    public static final String NORTH = "/character/right/characterStop.png";
    public static final String SOUTH = "/character/right/characterStop.png";
    public static final String EAST = "/images/RegularMode/character/right/characterWalk.png";
    public static final String WEST = "/images/RegularMode/character/left/characterWalk.png";
    public static final String shooting  = "/images/RegularMode/character/right/characterShoot.png";

    private static final Image idleImage;
    private static final Image eastImage;
    private static final Image westImage;
    private static final Image shootingImage;

    static {
        idleImage = new Image(PlayerImage.class.getResourceAsStream(IDLE));
        eastImage = new Image(PlayerImage.class.getResourceAsStream(EAST));
        westImage = new Image(PlayerImage.class.getResourceAsStream(WEST));
        shootingImage= new Image (PlayerImage.class.getResourceAsStream(shooting));
    }

    public static Image getImage(String state) {
        switch (state) {
            case shooting:
                return shootingImage;
            case IDLE:
                return idleImage;
            case EAST:
                return eastImage;
            case WEST:
                return westImage;
            default:
                return idleImage;
        }
    }

}
