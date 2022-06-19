package vacuum_world.Search.Algorithms;

import vacuum_world.Search.World.Cell;
import vacuum_world.Search.World.Node;
import vacuum_world.Search.World.Position;

import java.util.*;

public class DFSearch extends Search{

    //Closed list needs to be global for recursion or passed into the function
    Hashtable<Cell[][], HashSet<Position>> closedList = new Hashtable<Cell[][], HashSet<Position>>();

    public DFSearch(int columns, int rows){
        super(columns, rows);
    }

    /**
     * Recursively expand nodes till leaf node is reached then work way up and continue or till
     * goal node is found.
     *
     * @param currentNode Node to search from
     * @return Goal node or null if goal not found
     */
    @Override
    public Node SearchNodes(Node currentNode){

        if(closedList.containsKey(currentNode.getCells()) && closedList.get(currentNode.getCells()).contains(currentNode.getAgentPosition())) {
            return null;
        }

        else if(closedList.containsKey(currentNode.getCells()) && !closedList.get(currentNode.getCells()).contains(currentNode.getAgentPosition())) {
            closedList.get(currentNode.getCells()).add(currentNode.getAgentPosition());
            LinkedList<Node> expandedNodes =  Expand(currentNode);
            if(expandedNodes.isEmpty())
                return currentNode;
            for (Node n: expandedNodes) {
                n.prev = currentNode;
                if(isGoal(n))
                    return n;
                Node x = SearchNodes(n);
                if(x != null)
                    return x;
            }
        }
        else {
            HashSet<Position> p1 = new HashSet<Position>();
            p1.add( currentNode.getAgentPosition());
            closedList.put(currentNode.getCells(), p1);

            LinkedList<Node> expandedNodes =  Expand(currentNode);

            if(expandedNodes.isEmpty())
                return currentNode;

            for (Node n: expandedNodes) {
                n.prev = currentNode;
                if(isGoal(n))
                    return n;
                Node x = SearchNodes(n);
                if(x != null)
                    return x;
            }
        }
        return null;
    }
}
