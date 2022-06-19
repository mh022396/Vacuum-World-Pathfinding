package vacuum_world.Search.Algorithms.Heuristics;

import vacuum_world.Search.World.Node;

public class Heuristic_1 extends Heuristic{
    /**
     * Heuristic function always returning 1. Enables Uniform Cost in A*
     *
     * @param n node being evaluated
     * @return heuristic evaluation (always 1)
     */
    @Override
    public double HeuristicFunction(Node n){
        return 1.0;
    }
}
