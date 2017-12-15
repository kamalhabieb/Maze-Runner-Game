package models.charcter.states;

import models.engine.Engine;
import models.engine.Matter;

public class MoveWest extends AbstractState {

    @Override
    public void update(final Matter matter, final Engine engine) {
        engine.moveWest(matter);

    }
}
