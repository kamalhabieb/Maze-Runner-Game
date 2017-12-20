package views.flyweight;

import controllers.gameModeController;
import javafx.scene.image.Image;
import models.facade.Facade;

public class PlayerImage {
    //TODO set correct resource paths Kamal On it
    //TODO 1- North and South sprites , 2- No Running is covered
        public final static String IDLE = "/images/RegularMode/characterIslam/right/characterStop.png";
        public static final String NORTH = "/images/RegularMode/characterIslam/right/characterUp.png";
        public static final String SOUTH = "/images/RegularMode/characterIslam/right/characterDown.png";
        public static final String EAST = "/images/RegularMode/characterIslam/right/characterRun.png";
        public static final String WEST = "/images/RegularMode/characterIslam/left/characterWalk.png";
        public static final String shooting = "/images/RegularMode/characterIslam/right/characterShoot.png";

    private static final Image idleImage;
    private static final Image eastImage;
    private static final Image westImage;
    //private static final Image upImage;
    private static final Image downImage;
    private static final Image shootingImage;

    static {
        idleImage = new Image(PlayerImage.class.getResourceAsStream(IDLE));
        eastImage = new Image(PlayerImage.class.getResourceAsStream(EAST));
        westImage = new Image(PlayerImage.class.getResourceAsStream(WEST));
        downImage = new Image (PlayerImage.class.getResourceAsStream(SOUTH));
        //upImage   = new Image(PlayerImage.class.getResourceAsStream(NORTH));
        shootingImage= new Image (PlayerImage.class.getResourceAsStream(shooting));
    }

    public static Image getImage(String state) {
        switch (state) {
            case shooting:
                /*final URL resource = getClass().getResource("/music/shootSound.mp3");
        final Media media = new Media(resource.toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();*/
                return shootingImage;
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
