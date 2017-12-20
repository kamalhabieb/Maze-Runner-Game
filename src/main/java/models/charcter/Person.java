package models.charcter;


import controllers.command.Receiver;
import models.charcter.states.Directions;
import models.charcter.states.Machine;
import models.charcter.states.State;
import models.charcter.states.StateFactory;
import models.charcter.weapons.Gun;
import models.charcter.weapons.NoRemainingAmmoException;
import models.charcter.weapons.Weapon;
import models.charcter.weapons.bullets.Bullet;
import models.charcter.weapons.bullets.BulletImage;
import models.charcter.weapons.bullets.BulletImpl;
import models.engine.Engine;
import models.engine.Matter;
import models.facade.ControlTower;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;
import views.Drawable;

import java.awt.geom.Point2D;

public abstract class Person extends Drawable implements AliveObject, Machine, Matter, Armored, Host, Receiver {
    protected final Weapon weapon;
    private int health;
    private final int MAX_HEALTH = 100;
    private final int MIN_HEALTH = 0;
    private Point2D position;
    private int velocity;
    private int acceleration;
    protected State state;
    protected ControlTower controlTower;
    protected int lives;

    public Person(ControlTower controlTower) {
        this.controlTower = controlTower;
        health = MAX_HEALTH;
        position = new Point2D.Double();
        state = StateFactory.getState(Directions.reset);
        weapon = new Gun();
        this.lives = 1;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean affectHealthBy(final int effect) {
        int newHealth = effect + health;
        if (newHealth < MIN_HEALTH) newHealth = MIN_HEALTH;
        if (newHealth > MAX_HEALTH) newHealth = MAX_HEALTH;
        int diff = Math.abs(newHealth - health);
        health = newHealth;
        return diff == 0;
    }

    @Override
    public void setPosition(final double x, final double y) {
        position.setLocation(x, y);
        destinationX = x;
        destinationY = y;
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
    public void setState(final State state) {
        this.state = state;
    }

    public State getState() {
        return this.state;
    }

    @Override
    public int getAmmo() {
        return weapon.getRemainingAmmo();
    }

    @Override
    public boolean affectAmmo(final int effect) {
        return weapon.affectAmmo(effect);
    }

    @Override
    public void accept(final Visitor visitor) {
        visitor.visit(this);
    }


    public ControlTower getControlTower() {
        return controlTower;
    }

    public void update(Engine engine) {
        state.update(this, engine);
    }

    @Override
    public void destroy() {
        controlTower.notifyFuneralOf(this);
        lives--;
        if (lives > 0) revive();
    }

    @Override
    public void revive() {
        this.health = MAX_HEALTH;
        controlTower.notifyResurrectionOf(this);
    }


    public Bullet fireWeapon() {
        try {
            weapon.setPosition(this.getPosition().getX() + 2, this.getPosition().getY() + 2);
            return weapon.Shoot(controlTower);
        } catch (NoRemainingAmmoException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void shoot() {
        BulletImpl bullet = (BulletImpl) fireWeapon();
        if (bullet == null) return;
        State state;
        if (isIdle()) {
            state = StateFactory.getState(Directions.movingEast);
        } else {
            state = this.state;
        }
        controlTower.addBullet(bullet);
        bullet.setState(state);
    }

    private boolean isIdle() {

        return state.getType().equals(Directions.reset);
    }
}
