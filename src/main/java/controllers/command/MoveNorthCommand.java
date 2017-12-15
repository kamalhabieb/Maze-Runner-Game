package controllers.command;

import models.charcter.states.StateFactory;

import static models.charcter.states.StateFactory.state.movingEast;
import static models.charcter.states.StateFactory.state.movingNorth;

public class MoveNorthCommand implements Command{

    @Override
    public void execute(final Reciever reciever) {
        reciever.setState(StateFactory.getState(movingNorth));
    }
}
