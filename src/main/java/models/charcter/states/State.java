package models.charcter.states;

import javafx.scene.image.Image;
import models.engine.Engine;
import models.engine.Matter;

public interface State
{
    void update(Matter matter, Engine engine);

    Image getImage();

    Directions getType();
}
