package models.search;

public class SearchFactory {
    public static Search get(SearchStrategies strategy, Graph graph, int start, int end) {
        switch (strategy) {
            case BFS:
            case DFS:
            default:
                return new DFS(graph, start, end);
        }
    }
}
