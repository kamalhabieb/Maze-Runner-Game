package models.charcter.states;

import javafx.scene.image.Image;
import models.engine.Engine;
import models.engine.Matter;
import views.flyweight.PlayerImage;

public class Reset extends AbstractState {


    @Override
    public void update(final Matter matter, final Engine engine) {
        engine.stop(matter);
    }

    @Override
    public Image getImage() {
        return PlayerImage.getImage(PlayerImage.IDLE);
    }

    @Override
    public Directions getType() {
        return Directions.reset;
    }
}
