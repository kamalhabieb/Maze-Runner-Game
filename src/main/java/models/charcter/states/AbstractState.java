package models.charcter.states;

import models.engine.Engine;
import models.engine.Matter;

abstract class AbstractState implements State {
    protected Engine engine;
    protected Matter matter;

    public AbstractState(Engine engine,Matter matter) {
        this.engine = engine;
        this.matter = matter;
    }
}
