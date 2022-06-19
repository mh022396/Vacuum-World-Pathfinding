package vacuum_world.Search.Algorithms.Heuristics;

import vacuum_world.Search.World.Node;
import vacuum_world.Search.World.Position;

public class Heuristic_2 extends Heuristic{

    /**
     * h2 function that uses manhattan distance to closest piece of dirt
     * (Inefficient for grids with many walls)
     *
     * @param n node where agent is currently positioned
     * @return heuristic value to be added to total cost
     */
    @Override
    public double HeuristicFunction(Node n) {
        if(n.getDirtPosition().size() < 1)
            return 0;

        int closestX = n.getDirtPosition().get(0).x;
        int closestY = n.getDirtPosition().get(0).y;
        int totalDistance = Integer.MAX_VALUE;

        for (Position dirtPos : n.getDirtPosition()) {
            int x = dirtPos.x;
            int y = dirtPos.y;
            int prevDis = totalDistance;
            int tx = Math.abs(x - n.getAgentPosition().x);
            int ty = Math.abs(y - n.getAgentPosition().y);
            totalDistance = Math.min(totalDistance, (tx + ty));
            if (prevDis >= totalDistance) {
                closestX = x;
                closestY = y;
            }
        }

        int h;
        h = Math.abs(closestX - n.getAgentPosition().x);
        h += Math.abs(closestY - n.getAgentPosition().y);
        return h;
    }
}
