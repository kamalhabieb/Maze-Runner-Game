package models.maze;

import java.io.File;
import java.util.ArrayList;

public interface Maze {
    /**
     * Save a template of solvable maze in jason file.
     *
     * @param file the file will contain the template.
     */
    Void saveTemplate(File file);

    /**
     * Load a template which is saved in a jason file.
     *
     * @return Array List of the maze objects (walls) with its position.
     */
    ArrayList loadRandomTemplate();

    /**
     * Build the maze and distribute the walls and the gifts and the bombs in their place.
     * it also can rebuild the maze when changing any maze object on it
     *@param mazeObjects arrayList of maze object which will be distributed on the maze.
     * @return 2D array of the maze.
     */
    int[][] mazeBuilder(ArrayList mazeObjects);


}
