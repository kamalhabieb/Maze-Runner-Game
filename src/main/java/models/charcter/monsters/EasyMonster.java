package models.charcter.monsters;

import javafx.scene.image.Image;
import models.charcter.AliveObject;
import models.facade.ControlTower;
import models.mazeObjects.Host;

public class EasyMonster extends AutonomousMonster {
    @Override
    public Image getImage() {
        return super.getImage();
    }

    public EasyMonster(ControlTower controlTower) {
        super(controlTower);
    }


}
