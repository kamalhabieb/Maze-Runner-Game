package models.search;

import java.util.Stack;

public abstract class SearchAbstract implements Search {
    /**
     * Array of visited nodes during the traverse process.
     */
    protected boolean[] visited;
    /**
     * Array containing the nodes from which the index node is
     * visited.
     */
    protected int[] edgeTo;
    /**
     * Ending node.
     */
    protected final int end;
    /**
     * Starting node.
     */
    protected final int start;

    /**
     * Constructor of the search class.
     * @param g graph to traverse.
     * @param startArg start point.
     * @param endArg end node.
     */
    public SearchAbstract(final Graph g, final int startArg,
                  final int endArg) {
        this.visited = new boolean[g.getVertices()];
        this.start = startArg;
        this.end = endArg;
        this.edgeTo = new int[g.getVertices()];
        traverse(g, this.start, this.end);
    }

    /**
     * Traverse a given graph from a starting node to end end
     * node.
     * @param g graph to traverse.
     * @param startArg node.
     * @param endArg node.
     */
    protected abstract void traverse(Graph g, int startArg,
                                     int endArg);

    /**
     * Checks if the starting node has path to a given node.
     * @param v to check if there is path to.
     * @return true if there is path, false otherwise.
     */
    protected boolean hasPathTo(final int v) {
        return this.visited[v];
    }

    /**
     * Gets the path from source to destination.
     * @param startArg node.
     * @param endArg node.
     * @return a stack containing path from start to end, null
     *         uuf no path found.
     */
    public Stack pathTo(final int startArg, final int endArg) {
        if (!hasPathTo(endArg)) {
            return null;
        }
        final Stack path = new Stack();

        for (int i = endArg; i != startArg; i = this.edgeTo[i]) {
            path.push(i);
        }
        path.push(startArg);
        return path;
    }
}
