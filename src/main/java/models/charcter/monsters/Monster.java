package models.charcter.monsters;

import javafx.scene.image.Image;
import models.charcter.Person;
import models.facade.ControlTower;
import views.flyweight.PlayerImage;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Monster extends Person {
    public Monster(final ControlTower controlTower) {
        super(controlTower);
    }

    @Override
    public Image getImage() {
        //TODO set monster actions
        setDestinationWidth(30);
        setDestinationHeight(30);
        setSrcWidth(33);
        setSrcHeight(50);
        return PlayerImage.getImage(PlayerImage.IDLE);
    }

    @Override
    public Point2D getPosition(){
        return new Point2D.Double(destinationX,destinationY);
    }
}
