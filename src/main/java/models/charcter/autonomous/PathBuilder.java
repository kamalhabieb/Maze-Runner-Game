package models.charcter.autonomous;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PathBuilder {
    private ArrayList<Point2D> path;

    public PathBuilder setPath(final ArrayList<Point2D> path) {
        this.path = path;
        return this;
    }

    public PathBuilder add(Point2D point2D) {
        this.path.add(point2D);
        return this;
    }

    public Path createPath() {
        return new Path(path);
    }
}