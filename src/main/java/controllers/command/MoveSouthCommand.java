package controllers.command;

import models.charcter.states.StateFactory;

import static models.charcter.states.StateFactory.state.movingNorth;
import static models.charcter.states.StateFactory.state.movingSouth;

public class MoveSouthCommand implements Command{

    @Override
    public void execute(final Reciever reciever) {
        reciever.setState(StateFactory.getState(movingSouth));
    }
}
