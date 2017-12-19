package models.charcter.monsters;

import models.charcter.autonomous.Flame;
import models.charcter.autonomous.Moth;
import models.charcter.autonomous.Path;
import models.facade.ControlTower;

public class AutonomousMonster extends Monster implements Moth {
    public AutonomousMonster(final ControlTower controlTower) {
        super(controlTower);
    }


}
