package models.search;

import models.charcter.states.Directions;

import java.awt.geom.Point2D;

public interface Path {
    Directions getNextDirection();

    Point2D getNextTarget();
}
