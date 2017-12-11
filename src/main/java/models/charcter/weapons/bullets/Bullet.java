package models.charcter.weapons.bullets;

import models.matter.Matter;

public interface Bullet extends Matter{

    int getDamageRate();

    int getLifetime();

    void setDamageRate(int damageRate);

    void setLifetime(int lifetime);

    void setBirthtime(int starttime);

    int getBirthtime();
}
