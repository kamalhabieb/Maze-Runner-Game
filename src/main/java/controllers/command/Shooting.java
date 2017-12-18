package controllers.command;

import models.charcter.states.StateFactory;

import static models.charcter.states.StateFactory.state.movingEast;
import static models.charcter.states.StateFactory.state.movingSouth;
import static models.charcter.states.StateFactory.state.shooting;

public class Shooting implements Command {

    @Override
    public void execute(final Receiver receiver) {
        receiver.setState(StateFactory.getState(shooting));
        receiver.fireWeapon();
    }
}
