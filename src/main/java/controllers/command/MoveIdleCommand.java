package controllers.command;

import models.charcter.states.StateFactory;

import static models.charcter.states.Directions.reset;

public class MoveIdleCommand implements Command{

    @Override
    public void execute(final Receiver receiver) {
        receiver.setState(StateFactory.getState(reset));
    }
}
