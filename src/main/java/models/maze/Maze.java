package models.maze;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public interface Maze {

    /**Add new object to the array list of maze objects.
     * @param object the maze object which will be added to the maze objects list.
     * @param relativePosition the relative position of the object in the maze.
     */
    void addMazeObjectWithRelativePosition(MazeObject object, Point relativePosition);

    /**
     * Add new object to the array list of maze objects.
     *
     * @param object           the maze object which will be added to the maze objects list.
     * @param absolutePosition the absolute position of the object in the maze.
     */
    void addMazeObjectWithAbsolutePosition(MazeObject object, Point absolutePosition);

    /**
     * Remove object from the array list of maze objects.
     *
     * @param object the maze object which will be removed from the maze objects list.
     * @param relativePosition the relative position of the object in the maze.
     */
    void RemoveMazeObjectWithRelativePosition(MazeObject object, Point relativePosition);

    /**
     * Remove object from the array list of maze objects.
     *
     * @param object           the maze object which will be removed from the maze objects list.
     * @param absolutePosition the absolute position of the object in the maze.
     */
    void RemoveMazeObjectWithAbsolutePosition(MazeObject object, Point absolutePosition);

    /**
     * get maze object at a position on the maze.
     * @param relativePosition the relative position of the wanted object.
     * @return the wanted maze object.
     */
    MazeObject getMazeObjectAtRelativePosition(Point relativePosition);

    /**
     * get maze object at a position on the maze.
     *
     * @param absolutePosition the absolute position of the wanted object.
     * @return the wanted maze object.
     */
    MazeObject getMazeObjectAtAbsolutePosition(Point absolutePosition);

}
