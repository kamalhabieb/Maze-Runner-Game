package models.charcter;

import models.facade.ControlTower;

import java.awt.*;

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
}
