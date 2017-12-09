package models.engine;

import models.matter.Matter;

public interface Engine {

    /**
     * Moves the Object to the north
     *
     * @param object of the type matter
     */
    void moveNorth(Matter object);

    /**
     * Moves the Object to the east
     *
     * @param object of the type matter
     */
    void moveEast(Matter object);

    /**
     * Moves the Object to the south
     *
     * @param object of the type matter
     */

    void moveSouth(Matter object);

    /**
     * Moves the Object to the west
     *
     * @param object of the type matter
     */
    void moveWest(Matter object);

}
