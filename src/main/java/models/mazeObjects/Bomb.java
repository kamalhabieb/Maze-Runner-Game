package models.mazeObjects;

import models.maze.MazeObject;

public interface Bomb extends MazeObject {
    /**
     * gets the damaging effect
     *
     * @return int quantity affecting health
     */
    int getDamageRate();

    /**
     * sets the damage power of the bomb
     * by units of health ( int ) not %
     */
    void setDamageRate(int damageValue);

    /**
     * get time needed to explode
     *
     * @return time of type int
     */
    int getTimer();

    /**
     * sets time the bomb takes to explode
     *
     * @param time in seconds of type int
     */

    void setTimer(int time);
}
