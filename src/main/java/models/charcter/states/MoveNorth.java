package models.charcter.states;

import models.engine.Engine;
import models.engine.Matter;

public class MoveNorth extends AbstractState {

    @Override
    public void update(final Matter matter, final Engine engine) {
        engine.moveNorth(matter);

    }
}
