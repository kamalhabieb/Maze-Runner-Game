package models.charcter.decorations;

import models.charcter.Person;

public class Shield extends AbstractDecoration {
    private final int MAX_HEALTH = 50;
    private final int MIN_HEALTH = 0;

    public Shield(final Person person) {
        super(person);
        health = MAX_HEALTH;
    }

    @Override
    protected int getMaxHealth() {
        return MAX_HEALTH;
    }

    @Override
    protected int getMinHealth() {
        return MIN_HEALTH;
    }


}
