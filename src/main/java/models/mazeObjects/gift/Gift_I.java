package models.mazeObjects.gift;

import models.maze.MazeObject;

import java.awt.*;

public interface Gift_I extends MazeObject {

    /**
     * sets the type of the gift
     * Type can be "1 : health", "2 : ammo"
     */
    void assignGiftType(int giftType);
}
