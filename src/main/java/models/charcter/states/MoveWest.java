package models.charcter.states;

import javafx.scene.image.Image;
import models.engine.Engine;
import models.engine.Matter;
import views.flyweight.PlayerImage;

public class MoveWest extends AbstractState {

    @Override
    public void update(final Matter matter, final Engine engine) {
        engine.moveWest(matter);

    }

    @Override
    public Image getImage() {
        return PlayerImage.getImage(PlayerImage.WEST);
    }

    @Override
    public Directions getType() {
        return Directions.movingWest;
    }
}
