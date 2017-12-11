package models.mazeObjects;

import models.maze.MazeObject;
import models.mazeObjects.space.Space;
import models.mazeObjects.bomb.Bomb;
import models.mazeObjects.gift.AmmoGift;
import models.mazeObjects.gift.HealthGift;

import java.awt.*;

public class ObjectsFactory {

    public static final String BOMB = "bomb";
    public static final String HEAL_GIFT = "healthGift";
    public static final String AMMO_GIFT = "ammoGift";
    public static final String LIVE_GIFT = "liveGift";

    private static MazeObject obj;

    //"type" must be within range ]0,5]
    public static MazeObject produce(String product, int type, Point pos) {
        switch (product) {
            case BOMB:
                obj = new Bomb(type, pos);
                break;
            case HEAL_GIFT:
                obj = new HealthGift(type, pos);
                break;
            case AMMO_GIFT:
                obj = new AmmoGift(type, pos);
                break;
            case LIVE_GIFT:
                //obj = new LivesGift();
                break;
            default:
                obj = new Space();
                break;
        }
        return obj;
    }
}
