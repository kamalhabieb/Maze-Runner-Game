package controllers.command;

import models.charcter.states.State;
import models.charcter.weapons.bullets.Bullet;

public interface Receiver {
    void setState(State state);

    Bullet fireWeapon();
}
