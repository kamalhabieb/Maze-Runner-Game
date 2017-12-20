package models.charcter.weapons.bullets;

import models.engine.Engine;
import models.engine.Matter;
import views.Drawable;

public interface Bullet extends Matter {
    //Todo person observe pullet.

    int getDamageRate();

    int getLifetime();

    void setDamageRate(int damageRate);

    void setLifetime(int lifetime);

    void setBirthtime(int starttime);

    int getBirthtime();

    void update(Engine engine);
}
