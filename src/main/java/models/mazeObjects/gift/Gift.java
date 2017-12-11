package models.mazeObjects.gift;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;

import java.awt.*;

public abstract class Gift implements Gift_I {
    private Point pos;
    private boolean isCovered = true;

    @Override
    public void accept(Visitor visitor) {
        if(isCovered) {
            this.isCovered = false;
        }
        else {
            // TODO: 11/12/17 (DO onVanishFunction to remove Gift from 2D Array)
        }
    }

    @Override
    public int getHealth() {
        return 1;
    }

    @Override
    public void setPosition(int x, int y) {
        this.pos = new Point(x,y);
    }

    @Override
    public Point getPosition() {
        return this.pos;
    }

    @Override
    public int getVelocity() {
        return 0;
    }

    @Override
    public int getAcceleration() {
        return 0;
    }

    @Override
    public void setVelocity(int velocity) {
    }

    @Override
    public void setAcceleration(int acceleration) {
    }

}
