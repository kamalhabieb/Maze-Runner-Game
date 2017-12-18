package models.engine;


import java.awt.geom.Point2D;

public interface Matter {


    /**
     * set the object's position
     *
     * @param x coordinate
     * @param y coordinate
     */
    void setPosition(double x, double y);

    /**
     * gets the object's position as a point
     *
     * @return Point(x, y) indicating the object's position
     */
    Point2D getPosition();

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
     *
     * @param acceleration
     */
    void setAcceleration(int acceleration);
}
