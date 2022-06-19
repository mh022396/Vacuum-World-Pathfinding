package vacuum_world.Search.Algorithms;

import vacuum_world.Search.Algorithms.Heuristics.Heuristic;
import vacuum_world.Search.World.Cell;
import vacuum_world.Search.World.Node;
import vacuum_world.Search.World.Position;

import java.util.*;

public class AStarSearch extends Search{

    private Heuristic h;

    public AStarSearch(int columns, int rows, Heuristic heuristic){
        super(columns, rows);
        h = heuristic;
    }

    /**
     * From the initial node expand neighbor nodes, give them a cost value, and to to a priority queue
     * then continue expanding lowest cost nodes till goal node is found.
     *
     * @param initNode Node to start search from
     * @return Goal Node or null value if goal never found
     */
    @Override
    public Node SearchNodes(Node initNode){

        PriorityQueue<Double> open = new PriorityQueue<>();
        Hashtable<Double, LinkedList<Node>> openTable = new Hashtable<Double, LinkedList<Node>>();
        Hashtable<Cell[][], HashSet<Position>> closedList = new Hashtable<Cell[][], HashSet<Position>>();
        LinkedList<Node> n_q = new LinkedList<>();

        initNode.cost = h.HeuristicFunction(initNode);
        n_q.add(initNode);
        open.add(initNode.cost);
        openTable.put(initNode.cost, n_q);

        HashSet<Position> p = new HashSet<Position>();
        p.add( initNode.getAgentPosition());
        closedList.put(initNode.getCells(), p);

        while (!open.isEmpty()){
            double index = open.poll();
            Node currentNode = openTable.get(index).remove();

            if(isGoal(currentNode)) {
                return currentNode;
            }

            LinkedList<Node> expansionNodes = Expand(currentNode);

            if(!expansionNodes.isEmpty()){
                for (Node expandedNode: expansionNodes) {
                    if(closedList.containsKey(expandedNode.getCells()) && closedList.get(expandedNode.getCells()).contains(expandedNode.getAgentPosition())) {
                        continue;
                    }
                    else if(closedList.containsKey(expandedNode.getCells()) && !closedList.get(expandedNode.getCells()).contains(expandedNode.getAgentPosition())) {

                        double f = CalculateCost(currentNode, expandedNode);
                        expandedNode.cost = f;
                        closedList.get(expandedNode.getCells()).add(expandedNode.getAgentPosition());
                        open.add(f);

                        if(openTable.containsKey(f)){
                            openTable.get(f).add(expandedNode);
                        }
                        else {
                            LinkedList<Node> q1 = new LinkedList<>(); q1.add(expandedNode);
                            openTable.put(f, q1);
                        }
                    }
                    else {
                        double f = CalculateCost(currentNode, expandedNode);
                        expandedNode.cost = f;

                        HashSet<Position> p1 = new HashSet<Position>();
                        p1.add( expandedNode.getAgentPosition());
                        closedList.put(expandedNode.getCells(), p1);

                        open.add(f);
                        if(openTable.containsKey(f)){
                            openTable.get(f).add(expandedNode);
                        }
                        else {
                            LinkedList<Node> q1 = new LinkedList<>(); q1.add(expandedNode);
                            openTable.put(f, q1);
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Set expanded nodes previous node. Calculate cost of expanded node using F = G + H
     * g = added cost of previous nodes
     * h = heuristic function applied to expanded node
     *
     * @param currentNode node that we are expanding
     * @param exapndedNode one of the expanded nodes of the currentNode
     * @return Cost of expanded node
     */
    private double CalculateCost(Node currentNode, Node exapndedNode) {
        exapndedNode.prev = currentNode;
        double h_n = h.HeuristicFunction(exapndedNode);
        int g = 0;
        Node t = exapndedNode;
        while (t.prev != null) {
            g += t.prev.cost;
            t = t.prev;
        }

        return h_n + g;
    }
}
