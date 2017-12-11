package models.charcter.weapons.bullets;

import models.engine.Matter;

public interface Bullet extends Matter{

    int getDamageRate();

    int getLifetime();

    void setDamageRate(int damageRate);

    void setLifetime(int lifetime);

    void setBirthtime(int starttime);

    int getBirthtime();
}
