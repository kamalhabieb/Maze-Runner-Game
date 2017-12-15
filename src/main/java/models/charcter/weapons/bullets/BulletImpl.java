package models.charcter.weapons.bullets;

import models.charcter.AliveObject;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;

import java.awt.*;

public class BulletImpl implements Bullet,Visitor {
    private int damageRate;
    private int lifetime;
    private int birthTime;
    private Point position;
    private int velocity;
    private int acceleration;

    public BulletImpl() {
        damageRate = 5;
        lifetime = 5;
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
    public void setPosition(final int x, final int y) {
        position.x = x;
        position.y = y;
    }

    @Override
    public Point getPosition() {
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
        try{
            AliveObject aliveObject = (AliveObject) host;
            aliveObject.affectHealthBy(this.getDamageRate());
        } catch (ClassCastException e){
            //TODO handle object is not alive
        }
    }
}
