package models.search;

import java.util.Arrays;
import java.util.Stack;

public class DFS extends SearchAbstract {

    private Graph graph;
    private int i = 0;

    /**
     * Constructor of the search class.
     *
     * @param g        graph to traverse.
     * @param startArg start point.
     * @param endArg   end node.
     */
    public DFS(final Graph g, final int startArg, final int endArg) {
        super(g, startArg, endArg);
    }

    @Override
    protected void traverse(final Graph g, final int start,
                            final int end) {
        this.visited[start] = true;
        for (int i = 0; i < g.getAdjacent(start).length; i++) {
            final int neighbor = g.getAdjacent(start)[i];
            if (!this.visited[neighbor]) {
                this.edgeTo[neighbor] = start;
                traverse(g, neighbor, end);
            }
        }

    }

    @Override
    public Path getPath(final Graph graph, final int start, final int end) {
        Stack path = super.pathTo(start, end);
        if (path == null) return null;
        Path2DBuilder builder = new Path2DBuilder();
        path.forEach(n -> builder.add(graph.getPoint((Integer) n)));
        return builder.createPath();
    }

    private int[] dfs(final int start, final int end, final boolean[] visited, final int[] path) {
        visited[start] = true;
        path[i] = start;
        if (start == end) return path;
        i++;
        int[] adj = graph.getAdjacent(start);
        for (int n = 0; n < adj.length; i++) {
            if (!visited[adj[n]]) {
                return dfs(adj[n], end, visited, path);
            }
        }

        i--;
        return path;
    }
}
