package models.charcter;


import models.charcter.states.Machine;
import models.charcter.states.State;
import models.charcter.states.StateFactory;
import models.engine.Matter;

import java.awt.*;

import static models.charcter.states.StateFactory.state.*;

public abstract class Person implements AliveObject, Machine, Matter, Armored {
    private int health;
    private final int MAX_HEALTH = 100;
    private final int MIN_HEALTH = 0;
    private Point position;
    private int velocity;
    private int acceleration;
    private State state;

    public Person() {
        health = MAX_HEALTH;
        position = new Point();
        state = StateFactory.getState(reset);
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
}
