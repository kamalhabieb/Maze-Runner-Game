package models.search;

import java.awt.geom.Point2D;

public interface Graph {
    Object getVertex(int v);

    void setDelimiter(Class delimiter);

    int[] getAdjacent(int v);

    int getVertices();

    Point2D getPoint(int n);

    Path getPath(Point2D start, Point2D end, SearchStrategies bfs);
}
