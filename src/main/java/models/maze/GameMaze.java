package models.maze;

import models.mazeObjects.space.Space;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMaze implements Maze {
    final private MazeObject[][] mazeArray;
    final private ArrayList<MazeObject> objectsArray;
    final private int height;
    final private int width;
    final private int cellSize;
    final private Point startPoint;
    final private Point endPoint;
    private final MazeObject space = new Space();

    public GameMaze(int height, int width, int cellSize, Point startPoint, Point endPoint) {
        this.height = height;
        this.width = width;
        this.cellSize = cellSize;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.mazeArray = new MazeObject[height][width];
        this.initializeArray();
        objectsArray = new ArrayList<MazeObject>();
    }

    @Override
    public ArrayList getObjectsArray() {
        return this.objectsArray;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getCellSize() {
        return this.cellSize;
    }

    @Override
    public Point getStartPoint() {
        return this.startPoint;
    }

    @Override
    public Point getEndPoint() {
        return this.endPoint;
    }


    @Override
    public boolean addMazeObjectWithRelativePosition(MazeObject object, Point relativePosition) throws InvalidPositionException {
        this.testValidPoint(relativePosition);
        if (mazeArray[(int) relativePosition.getX()]
                [(int) relativePosition.getY()]
                != space) {
            return false;
        }
        mazeArray[(int) relativePosition.getX()]
                [(int) relativePosition.getY()]
                = object;
       // if (!object.getClass().getSimpleName().equalsIgnoreCase("wallcell")) {
            objectsArray.add(object);
        //}
        return true;

    }

    @Override
    public boolean addMazeObjectWithAbsolutePosition(MazeObject object, Point absolutePosition) throws InvalidPositionException {
        absolutePosition.x = (int) (absolutePosition.getX() / cellSize);
        absolutePosition.y = (int) (absolutePosition.getY() / cellSize);
        testValidPoint(absolutePosition);
        if (mazeArray[(int) absolutePosition.getX()]
                [(int) absolutePosition.getY()]
                != space) {
            return false;
        }
        mazeArray[(int) absolutePosition.getX()]
                [(int) absolutePosition.getY()]
                = object;
        //if (!object.getClass().getSimpleName().equalsIgnoreCase("wallcell")) {
            objectsArray.add(object);
        //}
        return true;
    }

    @Override
    public boolean RemoveMazeObjectWithRelativePosition(MazeObject object, Point relativePosition) throws InvalidPositionException {
        testValidPoint(relativePosition);
        if (mazeArray[(int) relativePosition.getX()]
                [(int) relativePosition.getY()]
                == space) {
            return false;
        }
        mazeArray[(int) relativePosition.getX()]
                [(int) relativePosition.getY()]
                = space;
        objectsArray.remove(object);
        return true;
    }

    @Override
    public boolean RemoveMazeObjectWithAbsolutePosition(MazeObject object, Point absolutePosition) throws InvalidPositionException {
        absolutePosition.x = (int) (absolutePosition.getX() / cellSize);
        absolutePosition.y = (int) (absolutePosition.getY() / cellSize);
        testValidPoint(absolutePosition);
        if (mazeArray[(int) absolutePosition.getX()]
                [(int) absolutePosition.getY()]
                == space) {
            return false;
        }
        mazeArray[(int) absolutePosition.getX()]
                [(int) absolutePosition.getY()]
                = space;
        objectsArray.remove(object);
        return true;

    }

    @Override
    public MazeObject getMazeObjectAtRelativePosition(Point relativePosition) throws InvalidPositionException {
        testValidPoint(relativePosition);
        return mazeArray[(int) relativePosition.getX()]
                [(int) relativePosition.getY()];
    }

    @Override
    public MazeObject getMazeObjectAtAbsolutePosition(Point absolutePosition) throws InvalidPositionException {
        absolutePosition.x = (int) ((absolutePosition.getX() + cellSize / 2) / cellSize);
        absolutePosition.y = (int) ((absolutePosition.getY() + cellSize / 2) / cellSize);
        testValidPoint(absolutePosition);
        return mazeArray[(int) absolutePosition.getX()]
                [(int) absolutePosition.getY()];

    }

    @Override
    public ArrayList getMazeObjectsArray() {
        return objectsArray;
    }

    private void initializeArray() {
        for (int i = 0; i < this.mazeArray.length; i++) {
            Arrays.fill(this.mazeArray[i], space);
        }
    }

    private void testValidPoint(Point point) throws InvalidPositionException {
        if (point.getX() > width - 1 || point.getY() > height - 1) {
            throw new InvalidPositionException();
        }
    }
}
