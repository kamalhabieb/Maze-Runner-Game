package controllers.command;

import models.charcter.states.StateFactory;
import models.charcter.weapons.bullets.BulletImpl;

import static models.charcter.states.Directions.movingNorth;
import static models.charcter.states.Directions.movingSouth;

public class MoveNorthCommand implements Command{

    @Override
    public void execute(final Receiver receiver) {
        receiver.setState(StateFactory.getState(movingNorth));
    }

    public void executeBullet(final BulletImpl bullet) {
        bullet.setState(StateFactory.getState(movingNorth));
    }
}
