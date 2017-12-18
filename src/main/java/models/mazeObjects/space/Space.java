package models.mazeObjects.space;

import models.engine.Matter;
import models.maze.MazeObject;
import models.mazeObjects.Visitor;

import java.awt.geom.Point2D;

public class Space implements MazeObject, Matter {
    private Point2D position;

    public Space() {
        position = new Point2D.Double();
    }

    @Override
    public void accept(Visitor visitor) {

    }


    @Override
    public void setPosition(final double x, final double y) {
        position.setLocation(x, y);
    }

    @Override
    public Point2D getPosition() {
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
