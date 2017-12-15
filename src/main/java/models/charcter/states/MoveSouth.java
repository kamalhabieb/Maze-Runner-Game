package models.charcter.states;

import javafx.scene.image.Image;
import models.engine.Engine;
import models.engine.Matter;
import views.flyweight.PlayerImage;

public class MoveSouth extends AbstractState {

    @Override
    public void update(final Matter matter, final Engine engine) {
        engine.moveSouth(matter);

    }

    @Override
    public Image getImage() {
        return PlayerImage.getImage(PlayerImage.SOUTH);
    }
}
