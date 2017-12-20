package models.maze;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MazeBuilder {

    private int length;
    private int width;
    private int cellSize;
    private Point startPoint;
    private Point endPoint;
    private Maze playingMaze;
    private Map<MazeObject, Point> mazeObjectsMap;

    public MazeBuilder() {
        this.length = 31;
        this.width = 31;
        this.cellSize = 40;
        mazeObjectsMap = new LinkedHashMap<MazeObject, Point>();
    }

    public MazeBuilder setLength(int length) {
        this.length = length;
        return this;
    }

    public MazeBuilder setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return this.width;
    }

    public MazeBuilder setCellSize(int cellSize) {
        this.cellSize = cellSize;
        return this;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public MazeBuilder addMazeObject(MazeObject object, Point relativePoint) {
        mazeObjectsMap.put(object, relativePoint);
        return this;
    }


    public Maze buildMaze() {
        playingMaze = new GameMaze(this.length, this.width, this.cellSize, this.startPoint, this.endPoint);
        for (Map.Entry<MazeObject, Point> entry : mazeObjectsMap.entrySet()) {
            try {
                playingMaze.addMazeObjectWithRelativePosition(entry.getKey(), entry.getValue());
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
        return playingMaze;
    }

}
