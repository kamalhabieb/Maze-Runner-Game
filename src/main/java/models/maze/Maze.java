package models.maze;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public interface Maze {
    /**
     * Gets the size of the maze.
     * @return size maze.
     */
    int getSize();

    /**Add new object to the array list of maze objects.
     * @param object the maze object which will be added to the maze objects list.
     * @param position the relative position in the maze.
     */
    void addMazeObject(MazeObject object, Point position);

    /**
     * Remove object from the array list of maze objects.
     *
     * @param object the maze object which will be removed from the maze objects list.
     */
    void RemoveMazeObject(MazeObject object);

    /**
     * Gets an array list holds all of the maze objects.
     * @return arrayList of the maze objects.
     */
    ArrayList getMazeObjects();

    /**
     * get maze object at a position on tha maze.
     * @param position the relative position of the wanted object.
     * @return the wanted maze object.
    /home/islam/IdeaProjects/CRT     */
    MazeObject getMazeObjectAt(Point position);

}
