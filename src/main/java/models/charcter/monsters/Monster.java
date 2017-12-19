package models.charcter.monsters;

import javafx.scene.image.Image;
import models.charcter.Person;
import models.facade.ControlTower;

public abstract class Monster extends Person {
    public Monster(final ControlTower controlTower) {
        super(controlTower);
    }

    @Override
    public Image getImage() {
        //TODO set monster actions
        return null;
    }
}
