package models.maze;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MazeBuilder {

    private int length;
    private int width;
    private int cellSize;
    private Maze playingMaze;
    private Map<MazeObject, Point> mazeObjectsMap;

    public MazeBuilder() {
        this.length = 30;
        this.width = 30;
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

    public MazeBuilder setCellSize(int cellSize) {
        this.cellSize = cellSize;
        return this;
    }

    public MazeBuilder addMazeObject(MazeObject object, Point relativePoint) {
        mazeObjectsMap.put(object, relativePoint);
        return this;
    }


    Maze buildMaze() {
        playingMaze = new GameMaze(this.length, this.width, this.cellSize);
        for (Map.Entry<MazeObject, Point> entry : mazeObjectsMap.entrySet()) {
            playingMaze.addMazeObjectWithRelativePosition(entry.getKey(), entry.getValue());
        }
        return playingMaze;
    }

}
