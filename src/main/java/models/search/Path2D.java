package models.search;

import models.charcter.states.Directions;
import models.charcter.states.MoveEast;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Path2D implements Path {
    private final ArrayList<Point2D> path;
    private  Iterator iterator;
    private Point2D curr;
    private Point2D next;

    Path2D(final ArrayList<Point2D> path) {
        this.path = path;
        iterator = path.iterator();
        if(this.path.isEmpty()) return;
        next = (Point2D) iterator.next();
    }

    public Directions getNextDirection() {
        boolean result = setElements();
        if (!result) return Directions.reset;
        return GPS.getDirection(curr, next);

    }

    @Override
    public Point2D getNextTarget() {
        return next;
    }

    private boolean setElements() {
        try {
            curr = next;
            next = (Point2D) iterator.next();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    private static class GPS {

        public static Directions getDirection(final Point2D curr, final Point2D next) {
            if (Double.compare(curr.getY(), next.getY()) == 0) {
                if (Double.compare(curr.getX(), next.getX()) < 0)
                    return Directions.movingEast;
                else
                    return Directions.movingWest;
            }else{
                if (Double.compare(curr.getY(), next.getY()) < 0)
                    return Directions.movingSouth;
                else
                    return Directions.movingNorth;
            }
        }
    }
}
