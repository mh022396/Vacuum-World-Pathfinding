package vacuum_world.Search.Algorithms.Heuristics;

import vacuum_world.Search.World.Node;

public class Heuristic_0 extends Heuristic{
    /**
     * Heuristic function always returning 0. Enables random performance
     *
     * @param n node being evaluated
     * @return heuristic evaluation (always 0)
     */
    @Override
    public double HeuristicFunction(Node n) {
        return 0;
    }
}
