package models.charcter;


import java.awt.*;

public abstract class Person implements AliveObject {
    private int health;
    private final int MAX_HEALTH = 100;
    private final int MIN_HEALTH = 0;
    private Point position;
    private int velocity;
    private int acceleration;

    public Person() {
        health = MAX_HEALTH;
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
}
