package models.charcter.states;

import javafx.scene.image.Image;
import models.engine.Engine;
import models.engine.Matter;
import views.flyweight.PlayerImage;

public class MoveNorth extends AbstractState {

    @Override
    public void update(final Matter matter, final Engine engine) {
        engine.moveNorth(matter);

    }

    @Override
    public Image getImage() {
        return PlayerImage.getImage(PlayerImage.NORTH);
    }

    @Override
    public Directions getType() {
        return Directions.movingNorth;
    }
}
