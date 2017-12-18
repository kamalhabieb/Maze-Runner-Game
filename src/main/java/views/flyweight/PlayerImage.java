package views.flyweight;

import javafx.scene.image.Image;
import models.facade.Facade;

public class PlayerImage {
    //TODO set correct resource paths Kamal On it
    //TODO 1- North and South sprites(DONE by islam) , 2- No Running is covered

    public final static String IDLE  = "/images/RegularMode/character/right/characterStop.png";
    public static final String NORTH = "/images/RegularMode/character/right/characterUp.png";
    public static final String SOUTH = "/images/RegularMode/character/right/characterDown.png";
    public static final String EAST  = "/images/RegularMode/character/right/characterRun.png";
    public static final String WEST  = "/images/RegularMode/character/left/characterWalk.png";

    private static final Image idleImage;
    private static final Image eastImage;
    private static final Image westImage;
    //private static final Image upImage;
    private static final Image downImage;


    static {
        idleImage = new Image(PlayerImage.class.getResourceAsStream(IDLE));
        eastImage = new Image(PlayerImage.class.getResourceAsStream(EAST));
        westImage = new Image(PlayerImage.class.getResourceAsStream(WEST));
        downImage = new Image (PlayerImage.class.getResourceAsStream(SOUTH));
        //upImage   = new Image(PlayerImage.class.getResourceAsStream(NORTH));
    }

    public static Image getImage(String state) {
        switch (state) {
            case IDLE:
                return idleImage;
            case EAST:
                return eastImage;
            case WEST:
                return westImage;
            case NORTH:
                return downImage;
               // return upImage;
            case SOUTH:
                return downImage;
            default:
                return idleImage;
        }
    }

}
