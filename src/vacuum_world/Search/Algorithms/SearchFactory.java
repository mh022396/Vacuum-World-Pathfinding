package vacuum_world.Search.Algorithms;

import vacuum_world.Search.Algorithms.Heuristics.HeurisitcFactory;
import vacuum_world.Search.Algorithms.Heuristics.Heuristic;

public class SearchFactory {
    public Search GetSearch(String searchType, String heuristicType, int columns, int rows) {
        Search search;
        switch (searchType) {
            case "uniform-cost":
                search = new UniformSearch(columns, rows);
                break;
            case "depth-first":
                search = new DFSearch(columns, rows);
                break;
            case "a-star":
                HeurisitcFactory heurisitcFactory = new HeurisitcFactory();
                Heuristic h = heurisitcFactory.GetHeuristic(heuristicType);
                search = new AStarSearch(columns, rows, h);
                break;
            default:
                search = new UniformSearch(columns, rows);
                break;
        }
        return search;
    }
}
