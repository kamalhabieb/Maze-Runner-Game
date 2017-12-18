package models.charcter.states;

import javafx.scene.image.Image;
import models.engine.Engine;
import models.engine.Matter;
import views.flyweight.PlayerImage;

public class shootingGun1  extends AbstractState {
    @Override
    public void update(Matter matter, Engine engine) {

    }

    @Override
    public Image getImage() {
        return PlayerImage.getImage(PlayerImage.shooting);
    }
}
