package models.charcter;

import javafx.scene.image.Image;
import models.facade.ControlTower;

public class Monster extends Person {
    public Monster(final ControlTower controlTower) {
        super(controlTower);
    }

    @Override
    public Image getImage() {
        //TODO set monster actions
        return null;
    }
}
