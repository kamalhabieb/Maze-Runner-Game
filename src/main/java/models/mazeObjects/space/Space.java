package models.mazeObjects.space;

import models.maze.MazeObject;
import models.mazeObjects.Visitor;

import java.awt.*;

public class Space implements MazeObject {
    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public void affectAmmo(int ammo) {

    }

    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public boolean affectHealthBy(int effect) {
        return false;
    }

    @Override
    public void setPosition(int x, int y) {

    }

    @Override
    public Point getPosition() {
        return null;
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
