package vacuum_world.Search.Algorithms.Heuristics;

import vacuum_world.Search.World.Node;

public abstract class Heuristic {
    public abstract double HeuristicFunction(Node n);
}
