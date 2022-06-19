package vacuum_world.Search.Algorithms;

import vacuum_world.Search.World.Cell;
import vacuum_world.Search.World.Node;
import vacuum_world.Search.World.Position;

import java.util.*;

public class UniformSearch extends Search{

    public UniformSearch(int columns, int rows){
        super(columns, rows);
    }

    @Override
    public Node SearchNodes(Node initNode){

        Queue<Node> openList = new LinkedList<Node>();
        openList.add(initNode);
        Hashtable<Cell[][], HashSet<Position>> closedList = new Hashtable<Cell[][], HashSet<Position>>();
        HashSet<Position> p = new HashSet<Position>();
        p.add( initNode.getAgentPosition());
        closedList.put(initNode.getCells(), p);

        while (!openList.isEmpty()){
            Node n = openList.remove();
            if(isGoal(n)) {
                return n;
            }


            LinkedList<Node> expansionNodes = Expand(n);
            if(!expansionNodes.isEmpty()){
                for (Node eNode: expansionNodes) {

                    if(closedList.containsKey(eNode.getCells()) && closedList.get(eNode.getCells()).contains(eNode.getAgentPosition())) {
                        continue;
                    }
                    else if(closedList.containsKey(eNode.getCells()) && !closedList.get(eNode.getCells()).contains(eNode.getAgentPosition())) {
                        closedList.get(eNode.getCells()).add(eNode.getAgentPosition());
                        eNode.prev = n;
                        openList.add(eNode);
                    }
                    else {
                        HashSet<Position> p1 = new HashSet<Position>();
                        p1.add( eNode.getAgentPosition());
                        closedList.put(eNode.getCells(), p1);
                        eNode.prev = n;
                        openList.add(eNode);
                    }
                }
            }
        }
        return null;
    }
}
