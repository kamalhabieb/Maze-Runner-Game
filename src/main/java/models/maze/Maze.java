package models.maze;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public interface Maze {

    ArrayList getObjectsArray();

    int getHeight();

    int getWidth();

    int getCellSize();

    Point2D getStartPoint();

    Point2D getEndPoint();

    /**Add new object to the array list of maze objects.
     * @param object the maze object which will be added to the maze objects list.
     * @param relativePosition the relative position of the object in the maze.
     * @return true if the object was added successfully and other wise.
     */
    boolean addMazeObjectWithRelativePosition(MazeObject object, Point2D relativePosition) throws InvalidPositionException;

    /**
     * Add new object to the array list of maze objects.
     *
     * @param object           the maze object which will be added to the maze objects list.
     * @param absolutePosition the absolute position of the object in the maze.
     * @return true if the object was added successfully and other wise.

     */
    boolean addMazeObjectWithAbsolutePosition(MazeObject object, Point2D absolutePosition) throws InvalidPositionException;

    /**
     * Remove object from the array list of maze objects.
     *
     * @param object the maze object which will be removed from the maze objects list.
     * @param relativePosition the relative position of the object in the maze.
     * @return true if the object was removed successfully and other wise.
     */
    boolean RemoveMazeObjectWithRelativePosition(MazeObject object, Point2D relativePosition) throws InvalidPositionException;

    /**
     * Remove object from the array list of maze objects.
     *
     * @param object           the maze object which will be removed from the maze objects list.
     * @param absolutePosition the absolute position of the object in the maze.
     * @return true if the object was removed successfully and other wise.
     */
    boolean RemoveMazeObjectWithAbsolutePosition(MazeObject object, Point2D absolutePosition) throws InvalidPositionException;

    /**
     * get maze object at a position on the maze.
     * @param relativePosition the relative position of the wanted object.
     * @return the wanted maze object.
     */
    MazeObject getMazeObjectAtRelativePosition(Point2D relativePosition) throws InvalidPositionException;

    /**
     * get maze object at a position on the maze.
     * @param absolutePosition the absolute position of the wanted object.
     * @return the wanted maze object.
     */
    MazeObject getMazeObjectAtAbsolutePosition(Point2D absolutePosition) throws InvalidPositionException;

    /**
     * get array list with the maze objects.
     * @return array of objects.
     */
    ArrayList getMazeObjectsArray();

}
