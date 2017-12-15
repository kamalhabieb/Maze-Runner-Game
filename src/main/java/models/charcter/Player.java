package models.charcter;

import javafx.scene.image.Image;
import models.facade.ControlTower;

import java.awt.Point;

public class Player extends Person {
    public Player(final ControlTower controlTower) {
        super(controlTower);
    }

    @Override
    public void setPosition(final int x, final int y) {
        if(controlTower.grantPermission(this,new Point(x,y))){
            super.setPosition(x, y);
        }
    }

    @Override
    public Image getImage() {
        return state.getImage();
    }
}
