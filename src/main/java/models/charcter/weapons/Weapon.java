package models.charcter.weapons;

import models.charcter.weapons.bullets.Bullet;
import models.engine.Matter;
import models.facade.ControlTower;

public interface Weapon extends Matter {

    int getMaximumAmmo();

    int getRemainingAmmo();

    Bullet Shoot(ControlTower controlTower) throws NoRemainingAmmoException;

    boolean affectAmmo(int effect);
}
