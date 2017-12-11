package models.charcter.decorations;

import models.charcter.Decoration;
import models.charcter.Character;

import java.awt.*;

public abstract class AbstractDecoration implements Decoration {
    protected int health;
    private Character character;


    public AbstractDecoration(final Character character) {
        this.character = character;
        health = getMaxHealth();
    }

    protected abstract int getMaxHealth();
    protected abstract int getMinHealth();

    @Override
    public int getHealth() {
        return health + character.getHealth();
    }

    @Override
    public boolean affectHealthBy(final int effect) {
        int newHealth = effect + health;
        if (newHealth < getMinHealth()) newHealth = getMinHealth();
        if (newHealth > getMaxHealth()) newHealth = getMaxHealth();
        int diff = Math.abs(newHealth - health);
        health = newHealth;
        if (diff == effect) return true;
        if (diff == 0) return character.affectHealthBy(effect);
        character.affectHealthBy(effect - diff);
        return true;
    }

    @Override
    public void setPosition(final int x, final int y) {
        character.setPosition(x, y);
    }

    @Override
    public Point getPosition() {
        return character.getPosition();
    }

    @Override
    public int getVelocity() {
        return character.getVelocity();
    }

    @Override
    public void setVelocity(final int velocity) {
        character.setVelocity(velocity);
    }

    @Override
    public int getAcceleration() {
        return character.getAcceleration();
    }

    @Override
    public void setAcceleration(final int acceleration) {
        character.setAcceleration(acceleration);
    }
}
