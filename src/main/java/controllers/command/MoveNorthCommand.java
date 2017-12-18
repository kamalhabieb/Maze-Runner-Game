package controllers.command;

import models.charcter.states.StateFactory;

import static models.charcter.states.Directions.movingNorth;

public class MoveNorthCommand implements Command{

    @Override
    public void execute(final Receiver receiver) {
        receiver.setState(StateFactory.getState(movingNorth));
    }
}
