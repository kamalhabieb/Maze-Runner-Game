package controllers.command;

import models.charcter.states.StateFactory;
import models.charcter.weapons.bullets.BulletImpl;

import static models.charcter.states.Directions.movingEast;
import static models.charcter.states.Directions.movingWest;

public class MoveWestCommand implements Command{

    @Override
    public void execute(final Receiver receiver) {
        receiver.setState(StateFactory.getState(movingWest));
    }

    public void executeBullet(final BulletImpl bullet) {
        bullet.setState(StateFactory.getState(movingWest));
    }
}
