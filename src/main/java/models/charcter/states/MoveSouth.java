package models.charcter.states;

import models.engine.Engine;
import models.engine.Matter;

public class MoveSouth extends AbstractState {
    public MoveSouth(final Engine engine, final Matter matter) {
        super(engine, matter);
    }

    @Override
    public void update() {
        engine.moveSouth(matter);
    }
}
