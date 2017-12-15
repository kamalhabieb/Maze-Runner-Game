package controllers.command;

import models.charcter.states.State;

public interface Receiver {
    void setState(State state);
}
