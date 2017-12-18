package models.charcter.decorations;

import models.charcter.Person;

import java.awt.geom.Point2D;

public abstract class AbstractDecoration extends Person {
    protected int health;
    protected Person person;


    public AbstractDecoration(final Person person) {
        super(person.getControlTower());
        this.person = person;
        health = getMaxHealth();
    }

    protected abstract int getMaxHealth();
    protected abstract int getMinHealth();

    @Override
    public int getHealth() {
        return health + person.getHealth();
    }

    @Override
    public boolean affectHealthBy(final int effect) {
        int newHealth = effect + health;
        if (newHealth < getMinHealth()) newHealth = getMinHealth();
        if (newHealth > getMaxHealth()) newHealth = getMaxHealth();
        int diff = Math.abs(newHealth - health);
        health = newHealth;
        if (diff == effect) return true;
        if (diff == 0) return person.affectHealthBy(effect);
        person.affectHealthBy(effect - diff);
        return true;
    }

    @Override
    public void setPosition(final double x, final double y) {
        person.setPosition(x, y);
    }

    @Override
    public Point2D getPosition() {
        return person.getPosition();
    }

    @Override
    public int getVelocity() {
        return person.getVelocity();
    }

    @Override
    public void setVelocity(final int velocity) {
        person.setVelocity(velocity);
    }

    @Override
    public int getAcceleration() {
        return person.getAcceleration();
    }

    @Override
    public void setAcceleration(final int acceleration) {
        person.setAcceleration(acceleration);
    }

    @Override
    public int getAmmo() {
        return person.getAmmo();
    }

    @Override
    public boolean affectAmmo(final int effect) {
        return person.affectAmmo(effect);
    }
}
