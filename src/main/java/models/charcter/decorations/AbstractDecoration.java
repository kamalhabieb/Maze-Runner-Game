package models.charcter.decorations;

import models.charcter.Decoration;
import models.charcter.AliveObject;

import java.awt.*;

public abstract class AbstractDecoration implements Decoration {
    protected int health;
    private AliveObject aliveObject;


    public AbstractDecoration(final AliveObject aliveObject) {
        this.aliveObject = aliveObject;
        health = getMaxHealth();
    }

    protected abstract int getMaxHealth();
    protected abstract int getMinHealth();

    @Override
    public int getHealth() {
        return health + aliveObject.getHealth();
    }

    @Override
    public boolean affectHealthBy(final int effect) {
        int newHealth = effect + health;
        if (newHealth < getMinHealth()) newHealth = getMinHealth();
        if (newHealth > getMaxHealth()) newHealth = getMaxHealth();
        int diff = Math.abs(newHealth - health);
        health = newHealth;
        if (diff == effect) return true;
        if (diff == 0) return aliveObject.affectHealthBy(effect);
        aliveObject.affectHealthBy(effect - diff);
        return true;
    }

    @Override
    public void setPosition(final int x, final int y) {
        aliveObject.setPosition(x, y);
    }

    @Override
    public Point getPosition() {
        return aliveObject.getPosition();
    }

    @Override
    public int getVelocity() {
        return aliveObject.getVelocity();
    }

    @Override
    public void setVelocity(final int velocity) {
        aliveObject.setVelocity(velocity);
    }

    @Override
    public int getAcceleration() {
        return aliveObject.getAcceleration();
    }

    @Override
    public void setAcceleration(final int acceleration) {
        aliveObject.setAcceleration(acceleration);
    }
}
