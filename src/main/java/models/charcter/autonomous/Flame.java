package models.charcter.autonomous;

import java.awt.*;
import java.awt.geom.Point2D;

public interface Flame {
    void draw(Moth moth);

    Point2D getPosition();
}
