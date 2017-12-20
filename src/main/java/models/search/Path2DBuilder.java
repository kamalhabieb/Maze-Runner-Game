package models.search;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Path2DBuilder {
    private ArrayList<Point2D> path;

    public Path2DBuilder() {
        path = new ArrayList<>();
    }

    public Path2DBuilder setPath(final ArrayList<Point2D> path) {
        this.path = path;
        return this;
    }

    public Path2DBuilder add(Point2D point2D) {
        this.path.add(point2D);
        return this;
    }

    public Path2DBuilder remove(Point2D point2D) {
        this.path.remove(point2D);
        return this;
    }

    public Path2D createPath() {
        return new Path2D(path);
    }
}