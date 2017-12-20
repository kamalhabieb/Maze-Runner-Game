package models.maze;

import models.mazeObjects.space.Space;
import models.search.*;
import models.wall.Wall;
import models.wall.WallCell;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class GameMaze implements Maze {
    private static MazeObject[][] mazeArray;
    final private ArrayList<MazeObject> wallsArray;
    final private ArrayList<MazeObject> bombsGiftsArray;
    final private int height;
    final private int width;
    final private int cellSize;
    final private Point2D startPoint;
    final private Point2D endPoint;
    private final MazeObject space = new Space();

    public GameMaze(int height, int width, int cellSize, Point2D startPoint, Point2D endPoint) {
        this.height = height;
        this.width = width;
        this.cellSize = cellSize;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.mazeArray = new MazeObject[height][width];
        this.initializeArray();
        wallsArray = new ArrayList<MazeObject>();
        bombsGiftsArray = new ArrayList<MazeObject>();
    }

    @Override
    public ArrayList getWallsArray() {
        return this.wallsArray;
    }

    @Override
    public ArrayList getBombsGiftsArray() {
        return bombsGiftsArray;
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
    public Point2D getStartPoint() {
        return this.startPoint;
    }

    @Override
    public Point2D getEndPoint() {
        return this.endPoint;
    }


    @Override
    public boolean addMazeObjectWithRelativePosition(MazeObject object, Point2D relativePosition) throws InvalidPositionException {
        this.testValidPoint(relativePosition);
        if (mazeArray[(int) relativePosition.getX()]
                [(int) relativePosition.getY()]
                != space) {
            return false;
        }
        mazeArray[(int) relativePosition.getX()]
                [(int) relativePosition.getY()]
                = object;
        if (object instanceof Wall) {
            if (((Wall) object).isBreakable()) {
                bombsGiftsArray.add(object);
            } else {
                wallsArray.add(object);
            }
        } else
            bombsGiftsArray.add(object);
        return true;

    }

    @Override
    public boolean addMazeObjectWithAbsolutePosition(MazeObject object, Point2D absolutePosition) throws InvalidPositionException {
        int x = (int) (absolutePosition.getX() / cellSize);
        int y = (int) (absolutePosition.getY() / cellSize);
        testValidPoint(x, y);
        if (mazeArray[x][y] != space) {
            return false;
        }
        mazeArray[x][y] = object;
        if (object instanceof Wall) {
            if (((Wall) object).isBreakable()) {
                bombsGiftsArray.add(object);
            } else {
                wallsArray.add(object);
            }
        } else
            bombsGiftsArray.add(object);
        return true;
    }

    @Override
    public boolean RemoveMazeObjectWithRelativePosition(MazeObject object, Point2D relativePosition) throws InvalidPositionException {
        testValidPoint(relativePosition);
        if (mazeArray[(int) relativePosition.getX()]
                [(int) relativePosition.getY()]
                == space) {
            return false;
        }
        mazeArray[(int) relativePosition.getX()]
                [(int) relativePosition.getY()]
                = space;
        if (object instanceof Wall) {
            if (((Wall) object).isBreakable()) {
                bombsGiftsArray.remove(object);
            } else {
                wallsArray.remove(object);
            }
        } else
            bombsGiftsArray.remove(object);
        return true;
    }

    @Override
    public boolean RemoveMazeObjectWithAbsolutePosition(MazeObject object, Point2D absolutePosition) throws InvalidPositionException {
        int x = (int) (absolutePosition.getX() / cellSize);
        int y = (int) (absolutePosition.getY() / cellSize);
        testValidPoint(x, y);
        if (mazeArray[x][y] == space) {
            return false;
        }
        mazeArray[x][y] = space;
        if (object instanceof Wall) {
            if (((Wall) object).isBreakable()) {
                bombsGiftsArray.remove(object);
            } else {
                wallsArray.remove(object);
            }
        } else
            bombsGiftsArray.remove(object);
        return true;

    }

    @Override
    public MazeObject getMazeObjectAtRelativePosition(Point2D relativePosition) throws InvalidPositionException {
        testValidPoint(relativePosition);
        return mazeArray[(int) relativePosition.getX()]
                [(int) relativePosition.getY()];
    }

    @Override
    public MazeObject getMazeObjectAtAbsolutePosition(Point2D absolutePosition) throws InvalidPositionException {
        int x = (int) (absolutePosition.getX() + cellSize / 2) / cellSize;
        int y = (int) (absolutePosition.getY() + cellSize / 2) / cellSize;
      /*  System.out.print("1 ==>" +x + " ");
        System.out.println(y);
*/
        testValidPoint(x, y);
        return mazeArray[x][y];

    }

    @Override
    public Path getPath(final Point2D start, final Point2D end) {
        Graph graph = new MatrixGraph(mazeArray);
        graph.setDelimiter(Wall.class);
        return graph.getPath(new Point2D.Double(start.getX() / cellSize, start.getY() / cellSize),
                new Point2D.Double(end.getX() / cellSize, end.getY() / cellSize),
                SearchStrategies.BFS);
    }


    private void initializeArray() {
        for (int i = 0; i < this.mazeArray.length; i++) {
            Arrays.fill(this.mazeArray[i], space);
        }
    }

    private void testValidPoint(Point2D point) throws InvalidPositionException {
        testValidPoint((int) point.getX(), (int) point.getY());
    }

    private void testValidPoint(int x, int y) throws InvalidPositionException {
        if (x > width - 1 || y > height - 1 || x < 0 || y < 0) {
            throw new InvalidPositionException();
        }
    }
}
