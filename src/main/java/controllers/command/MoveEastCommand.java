package controllers.command;

import models.charcter.states.StateFactory;
import models.charcter.weapons.bullets.BulletImpl;
import models.mazeObjects.Visitor;

import static models.charcter.states.Directions.movingEast;

public class MoveEastCommand implements Command{

    @Override
    public void execute(final Receiver receiver) {
        receiver.setState(StateFactory.getState(movingEast));
    }

    public void executeBullet (final BulletImpl bullet) {
        bullet.setState(StateFactory.getState(movingEast));
    }

}
