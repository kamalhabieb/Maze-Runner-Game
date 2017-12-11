package models.mazeObjects;

import models.maze.MazeObject;

public interface Gift extends MazeObject {

    /**
     * sets the type of the gift
     * Type can be "health", "ammo"
     */
    void assignGiftType(String giftType);

    /**
     * gets the healing rate
     * default zero incase of gift type " ammo "
     */
    int getHealRate();

    /**
     * sets the healing rate
     * default zero incase of gift type " ammo "
     */
    void setHealRate(int healingRate);

    /**
     * gets the ammo reloading count
     * default zero incase of gift type " health "
     */
    int getAmmoReload();

    /**
     * sets the ammo reloading count
     * default zero incase of gift type " health "
     */
    void setAmmoReload(int ammoCount);


}
