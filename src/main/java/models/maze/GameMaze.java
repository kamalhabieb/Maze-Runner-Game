package models.maze;

import models.mazeObjects.Space;

import java.awt.*;
import java.util.Arrays;

public class GameMaze implements Maze {
    final private MazeObject[][] mazeArray;
    final private int length;
    final private int width;
    final private int cellSize;
    private final MazeObject space = new Space();
    public GameMaze(int length, int width, int cellSize) {
        this.length = length;
        this.width = width;
        this.cellSize = cellSize;
        this.mazeArray = new MazeObject[length][width];
        this.initializeArray(this.mazeArray);
    }


    @Override
    public void addMazeObjectWithRelativePosition(MazeObject object, Point relativePosition) {
        mazeArray[(int)relativePosition.getX()]
                [(int)relativePosition.getY()]
                = object;
    }

    @Override
    public void addMazeObjectWithAbsolutePosition(MazeObject object, Point absolutePosition) {
        mazeArray[(int)absolutePosition.getX() / cellSize]
                [(int)absolutePosition.getY() / cellSize]
                = object;

    }

    @Override
    public void RemoveMazeObjectWithRelativePosition(MazeObject object, Point relativePosition) {
        mazeArray[(int)relativePosition.getX()]
                [(int)relativePosition.getY()]
                = space;
    }

    @Override
    public void RemoveMazeObjectWithAbsolutePosition(MazeObject object, Point absolutePosition) {
        mazeArray[(int)absolutePosition.getX() / cellSize]
                 [(int)absolutePosition.getY() / cellSize]
                 = space;
    }

    @Override
    public MazeObject getMazeObjectAtRelativePosition(Point relativePosition) {
        return mazeArray[(int)relativePosition.getX()]
                        [(int)relativePosition.getY()];
    }

    @Override
    public MazeObject getMazeObjectAtAbsolutePosition(Point absolutePosition) {
        return mazeArray[(int)absolutePosition.getX() / cellSize]
                        [(int)absolutePosition.getY() / cellSize];
    }

    private void initializeArray (MazeObject[][] mazeArray){
        for (int i = 0; i < mazeArray.length; i++){
            Arrays.fill(mazeArray[i],space);
        }
    }
}
