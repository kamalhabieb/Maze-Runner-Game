package models.charcter.states;

import models.engine.Engine;
import models.engine.Matter;

public class MoveEast extends AbstractState {


    @Override
    public void update(final Matter matter, final Engine engine) {
        engine.moveEast(matter);
    }
}
