package controllers.command;

import models.charcter.states.StateFactory;

import static models.charcter.states.Directions.shooting;


public class Shooting implements Command {

    @Override
    public void execute(final Receiver receiver) {
        receiver.setState(StateFactory.getState(shooting));
    }


}
