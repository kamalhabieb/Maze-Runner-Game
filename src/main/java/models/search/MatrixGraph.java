package models.search;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class MatrixGraph implements Graph {
    private int width;
    private int height;
    private Object[][] graph;
    private Class delimiter;
    private static Point[] adj;

    static {
        adj = new Point[4];
        adj[0] = new Point(-1, 0);
        adj[1] = new Point(0, -1);
        adj[2] = new Point(1, 0);
        adj[3] = new Point(0, 1);
    }

    public MatrixGraph(final Object[][] graph) {
        this.graph = graph;
        this.width = graph[0].length;
        this.height = graph.length;
    }

    @Override
    public Object getVertex(final int v) {
        Point coordinates = toCoordinates(v, width, height);
        if (coordinates == null) return null;
        return graph[coordinates.x][coordinates.y];
    }

    @Override
    public void setDelimiter(final Class delimiter) {
        this.delimiter = delimiter;
    }

    public static Point toCoordinates(int v, int width, int height) {
        if (v >= width * height || v < 0) return null;
        int row = (v / width);
        int col = (v % width);
        return new Point(col, row);
    }


    @Override
    public int[] getAdjacent(final int v) {
        Point2D point = toCoordinates(v, width, height);
        ArrayList<Integer> adjac = new ArrayList<>();
        Arrays.stream(adj).forEach(n -> {
            try {
                Point2D candidate = new Point((int) (point.getX() + n.x), (int) (point.getY() + n.y));
                Object obj = graph[(int) candidate.getX()][(int) candidate.getY()];
                if (obj != null && !delimiter.isAssignableFrom(obj.getClass())) adjac.add(toVertex(candidate));
            } catch (IndexOutOfBoundsException e) {

            }
        });
        int[] adjArray = new int[adjac.size()];
        for (int i = 0; i < adjArray.length; i++) {
            adjArray[i] = adjac.get(i);
        }
        return adjArray;
    }

    @Override
    public int getVertices() {
        return width * height;
    }

    @Override
    public Point2D getPoint(final int n) {
        return toCoordinates(n, width, height);
    }

    @Override
    public Path getPath(final Point2D start, final Point2D end, final SearchStrategies strategy) {
        Search search = SearchFactory.get(strategy, this, toVertex(start), toVertex(end));
        return search.getPath(this, toVertex(start), toVertex(end));
    }

    private int toVertex(final Point2D point) {
        return (int) (Math.round(point.getY()) * width + Math.round(point.getX()));
    }
}
