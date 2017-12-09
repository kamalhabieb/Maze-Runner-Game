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
     * <p>
     * velocity range 40 to 100
     * <p>
     * mid (default) =  70
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
     * sets the acceleration of the object
     * <p>
     * default = 0
     * <p>
     * acceleration range -20 to +20
     *
     * @param acceleration of the object
     */
    void setAcceleration(int acceleration);
}
