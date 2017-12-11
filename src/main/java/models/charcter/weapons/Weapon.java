package models.charcter.weapons;

import models.charcter.weapons.bullets.Bullet;
import models.matter.Matter;

public interface Weapon extends Matter {

    int getMaximumAmmo();

    int getRemainingAmmo();

    Bullet Shoot() throws NoRemainingAmmoException;

}
