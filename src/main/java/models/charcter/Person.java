package models.charcter;


import controllers.command.Receiver;
import models.charcter.states.Machine;
import models.charcter.states.State;
import models.charcter.states.StateFactory;
import models.charcter.weapons.Gun;
import models.charcter.weapons.Weapon;
import models.engine.Engine;
import models.engine.Matter;
import models.facade.ControlTower;
import models.mazeObjects.Host;
import models.mazeObjects.Visitor;
import views.Drawable;

import java.awt.*;

import static models.charcter.states.StateFactory.state.*;

public abstract class Person extends Drawable implements AliveObject, Machine, Matter, Armored, Host, Receiver {
    protected final Weapon weapon;
    private int health;
    private final int MAX_HEALTH = 100;
    private final int MIN_HEALTH = 0;
    private Point position;
    private int velocity;
    private int acceleration;
    protected State state;
    protected ControlTower controlTower;
    private int lives;

    public Person(ControlTower controlTower) {
        this.controlTower = controlTower;
        health = MAX_HEALTH;
        position = new Point();
        state = StateFactory.getState(reset);
        weapon = new Gun();
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
}
