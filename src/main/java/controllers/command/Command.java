package controllers.command;

import models.charcter.weapons.bullets.BulletImpl;

public interface Command {
    void execute(Receiver receiver);

}
