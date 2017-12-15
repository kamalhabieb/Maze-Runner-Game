package models.charcter.states;

import models.engine.Engine;
import models.engine.Matter;

public interface State
{
    void update(Matter matter, Engine engine);

}
