package models.charcter.weapons.bullets;

import javafx.scene.image.Image;
import models.charcter.AliveObject;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;
import views.Drawable;

import java.awt.geom.Point2D;

public class BulletImpl extends Drawable implements Bullet,Visitor {
    private int damageRate;
    private int lifetime;
    private int birthTime;
    private Point2D position;
    private int velocity;
    private int acceleration;

    public BulletImpl() {
        position= new Point();
        damageRate = 5;
        lifetime = 5;
        position = new Point2D.Double();
    }

    @Override
    public int getDamageRate() {
        return damageRate;
    }

    @Override
    public int getLifetime() {
        return lifetime;
    }

    @Override
    public void setDamageRate(final int damageRate) {
        this.damageRate = damageRate;
    }

    @Override
    public void setLifetime(final int lifetime) {
        this.lifetime = lifetime;
    }

    @Override
    public void setBirthtime(final int starting) {
        birthTime = starting;
    }

    @Override
    public int getBirthtime() {
        return birthTime;
    }

    @Override
    public void setPosition(final double x, final double y) {
        position.setLocation(x, y);
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public int getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(final int velocity) {

        this.velocity = velocity;
    }

    @Override
    public int getAcceleration() {
        return acceleration;
    }

    @Override
    public void setAcceleration(final int acceleration) {

        this.acceleration = acceleration;
    }

    @Override
    public void visit(final Host host) {
        try {
            AliveObject aliveObject = (AliveObject) host;
            aliveObject.affectHealthBy(this.getDamageRate());
        } catch (ClassCastException e) {
            //TODO handle object is not alive
        }
    }

    @Override
    public  Image getImage() {
        return (BulletImage.getImage());
    }
}
