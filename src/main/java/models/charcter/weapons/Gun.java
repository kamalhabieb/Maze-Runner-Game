package models.charcter.weapons;

import models.charcter.weapons.bullets.Bullet;
import models.charcter.weapons.bullets.BulletImpl;
import models.charcter.weapons.bullets.BulletPool;
import models.facade.ControlTower;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Properties;

import static models.charcter.weapons.bullets.BulletPool.*;

public class Gun implements Weapon {

    private static final int MAXIMUM_AMMO = 6;
    private static final int Minimum_Ammo = 0;
    private static final Properties properties = new Properties();


    static {
        properties.put(DAMAGE_RATE, 10);
        properties.put(LIFETIME, 3000);
        properties.put(VELOCITY, 100);
        properties.put(ACCELERATION, 0);
    }

    private Point2D position;
    private int velocity;
    private int acceleration;
    private int currentAmmo;

    public Gun() {
        position = new Point();
        currentAmmo = MAXIMUM_AMMO;
        position = new Point2D.Double();
    }

    @Override
    public int getMaximumAmmo() {
        return MAXIMUM_AMMO;
    }

    @Override
    public int getRemainingAmmo() {
        return currentAmmo;
    }

    @Override
    public Bullet Shoot(ControlTower controlTower) throws NoRemainingAmmoException {
        if (currentAmmo == 0) throw new NoRemainingAmmoException();
        currentAmmo--;
        BulletImpl bullet = (BulletImpl) BulletPool.getInstance().checkOut(properties, controlTower);
        bullet.setDestinationX((int) position.getX());
        bullet.setDestinationY((int) position.getY());
        return bullet;
    }

    @Override
    public boolean affectAmmo(final int effect) {
        int newAmmo = effect + currentAmmo;
        if (newAmmo < Minimum_Ammo) newAmmo = Minimum_Ammo;
        if (newAmmo > MAXIMUM_AMMO) newAmmo = MAXIMUM_AMMO;
        int diff = Math.abs(newAmmo - currentAmmo);
        currentAmmo = newAmmo;
        return diff == 0;
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
}
