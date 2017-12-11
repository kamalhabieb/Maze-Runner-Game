package models.charcter.states;

import models.engine.Engine;
import models.engine.Matter;

public class Reset extends AbstractState {
    public Reset(final Engine engine, final Matter matter) {
        super(engine, matter);
    }

    @Override
    public void update() {
        engine.stop(matter);
    }
}
