package models.mazeObjects;

import models.charcter.AliveObject;

public interface Host extends AliveObject {
    void accept(Visitor visitor);

    void affectAmmo(int ammo);
}
