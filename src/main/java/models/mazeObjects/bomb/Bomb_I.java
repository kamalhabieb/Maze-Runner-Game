package models.mazeObjects.bomb;

import models.maze.MazeObject;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Bomb_I extends MazeObject {
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

    void setPosition(final double x, final double y);

    Point2D getPosition();

    long getExplodeTime();

    void setExplodeTime(long birthTime);

    void explode();
}
