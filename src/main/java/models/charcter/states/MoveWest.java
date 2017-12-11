package models.charcter.states;

import models.engine.Engine;
import models.engine.Matter;

public class MoveWest extends AbstractState {
    public MoveWest(final Engine engine, final Matter matter) {
        super(engine, matter);
    }

    @Override
    public void update() {
        engine.moveWest(matter);
    }
}
