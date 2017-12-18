package models.mazeObjects.gift;

import javafx.scene.image.Image;
import models.Observer.Observed;
import models.charcter.AliveObject;
import models.charcter.LifeObserver;
import models.engine.Matter;
import models.mazeObjects.Visitor;
import views.Drawable;
import views.flyweight.GiftImage;

import java.awt.geom.Point2D;


public abstract class Gift extends Drawable implements Gift_I, AliveObject,
        Matter, Visitor, Observed {
    private Point2D pos;
    private boolean isCovered = true;

    @Override
    public void accept(Visitor visitor) {
        if (isCovered) {
            this.isCovered = false;
        } else {
            // TODO: 11/12/17 (DO onVanishFunction to remove Gift from 2D Array)
        }
    }

    @Override
    public int getHealth() {
        return 1;
    }

    @Override
    public void setPosition(final double x, final double y) {
        this.pos = new Point2D.Double(x, y);
    }

    @Override
    public Point2D getPosition() {
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

    @Override
    public Image getImage() {
        return GiftImage.getImage();
    }

    @Override
    public void destroy() {
        observers.forEach(n->((LifeObserver) n).notifyFuneralOf(Gift.this));
    }

    @Override
    public void revive() {
        observers.forEach(n->((LifeObserver) n).notifyResurrectionOf(Gift.this));
    }
}
