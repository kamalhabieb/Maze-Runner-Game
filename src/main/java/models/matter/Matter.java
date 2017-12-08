package models.matter;

import java.awt.*;

public interface Matter {


    /**
     * set the object's position
     *
     * @param x coordinate
     * @param y coordinate
     */
    void setPosition(int x, int y);

    /**
     * gets the object's position as a point
     *
     * @return Point(x, y) indicating the object's position
     */
    Point getPosition();

    /**
     * gets the velocity of the object
     *
     * @return velocity
     */
    int getVelocity();

    /**
     * sets the velocity of the object
     *
     * @param velocity of the object
     */
    void setVelocity(int velocity);

    /**
     * returns the acceleration of the object
     *
     * @return acceleration
     */
    int getAcceleration();

    /**
     * gets the acceleration of the object
     *
     * @param acceleration of the object
     */
    void setAcceleration(int acceleration);
}
