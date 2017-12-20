package controllers.command;

import models.charcter.states.StateFactory;
import models.charcter.weapons.bullets.BulletImpl;

import static models.charcter.states.Directions.movingSouth;
import static models.charcter.states.Directions.movingWest;

public class MoveSouthCommand implements Command{

    @Override
    public void execute(final Receiver receiver) {
        receiver.setState(StateFactory.getState(movingSouth));
    }

    public void executeBullet(final BulletImpl bullet) {
        bullet.setState(StateFactory.getState(movingSouth));
    }
}
