package controllers.command;

import models.charcter.states.StateFactory;
import models.charcter.weapons.bullets.BulletImpl;

import static models.charcter.states.Directions.movingNorth;
import static models.charcter.states.Directions.reset;

public class MoveIdleCommand implements Command{

    @Override
    public void execute(final Receiver receiver) {
        receiver.setState(StateFactory.getState(reset));
    }

    public void executeBullet(final BulletImpl bullet) {
        bullet.setState(StateFactory.getState(reset));
    }
}
