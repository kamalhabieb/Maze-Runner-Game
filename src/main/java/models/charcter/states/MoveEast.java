package models.charcter.states;

import models.engine.Engine;
import models.engine.Matter;

public class MoveEast extends AbstractState {
    public MoveEast(final Engine engine, final Matter matter) {
        super(engine, matter);
    }

    @Override
    public void update() {
        engine.moveEast(matter);
    }
}
