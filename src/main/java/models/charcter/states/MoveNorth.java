package models.charcter.states;

import models.engine.Engine;
import models.engine.Matter;

public class MoveNorth extends AbstractState {
    public MoveNorth(final Engine engine, final Matter matter) {
        super(engine, matter);
    }

    @Override
    public void update() {
        engine.moveNorth(matter);
    }
}
