package models.mazeObjects.space;

import models.engine.Matter;
import models.maze.MazeObject;
import models.mazeObjects.Visitor;

import java.awt.*;

public class Space implements MazeObject, Matter {
    private Point position;

    public Space() {
        position = new Point();
    }

    @Override
    public void accept(Visitor visitor) {

    }


    @Override
    public void setPosition(int x, int y) {
        position.x = x;
        position.y = y;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public int getVelocity() {
        return 0;
    }

    @Override
    public void setVelocity(int velocity) {

    }

    @Override
    public int getAcceleration() {
        return 0;
    }

    @Override
    public void setAcceleration(int acceleration) {

    }
}
