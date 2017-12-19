package models.charcter.autonomous;

import models.search.Path;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Moth {
    Point2D getPosition();

    void setPathToFlame(Path path);
}
