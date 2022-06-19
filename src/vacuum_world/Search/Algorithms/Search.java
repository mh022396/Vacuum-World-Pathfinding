package vacuum_world.Search.Algorithms;

import vacuum_world.Search.World.*;

import java.util.*;

public abstract class Search {
    protected int columns;
    protected int rows;


    public Search(int columns, int rows){
        this.columns = columns;
        this.rows = rows;
    }

    public abstract Node SearchNodes(Node initNode);


    /**
     * For every possible action generate a new node (state) for the vacuum world
     * check if it is a legal state add it to the list of expanded nodes.
     *
     * @param n Node to be expanded
     * @return List of expanded Nodes
     */
    protected LinkedList<Node> Expand(Node n){

        LinkedList<Node> nodes = new LinkedList<Node>();

        //Store state of world, position of agent, remaining pieces of dirt and the positions of dirt remaining
        Cell[][] cells = n.getCells();
        Position agentPosition = n.getAgentPosition();
        int dirtCount = n.getDirtCount();
        List<Position> dirtPositions = n.getDirtPosition();

        //For every action, if action is legal, create new node and store new state values and add node to list of expanded nodes
        for (Action a: Action.values()) {
            switch (a){
                case N :
                    if(isInBounds(agentPosition.x, agentPosition.y + 1) && !cells[agentPosition.x][agentPosition.y + 1].isBlocked){
                        nodes.add(new Node(cells, new Position(agentPosition.x, agentPosition.y+1), Action.N, dirtCount, dirtPositions));
                    }
                    break;
                case E :
                    if(isInBounds(agentPosition.x + 1, agentPosition.y) && !cells[agentPosition.x+1][agentPosition.y].isBlocked){
                        nodes.add(new Node(cells, new Position(agentPosition.x +1, agentPosition.y), Action.E, dirtCount, dirtPositions));
                    }
                    break;
                case S :
                    if(isInBounds(agentPosition.x, agentPosition.y - 1) && !cells[agentPosition.x][agentPosition.y - 1].isBlocked){
                        nodes.add(new Node(cells, new Position(agentPosition.x, agentPosition.y - 1), Action.S, dirtCount, dirtPositions));
                    }
                    break;
                case W :
                    if(isInBounds(agentPosition.x - 1, agentPosition.y) && !cells[agentPosition.x-1][agentPosition.y].isBlocked){
                        nodes.add(new Node(cells, new Position(agentPosition.x - 1, agentPosition.y), Action.W, dirtCount, dirtPositions));
                    }
                    break;
                case V :
                    if(!cells[agentPosition.x][agentPosition.y].isClean) {
                        Cell[][] newStateCells = new Cell[columns][rows];
                        for (int x = 0; x < columns; x++) {
                            for (int y = 0; y < rows; y++) {
                                newStateCells[x][y] = new Cell(cells[x][y].charVisual, x, y);
                            }
                        }
                        newStateCells[agentPosition.x][agentPosition.y].isClean = true;
                        newStateCells[agentPosition.x][agentPosition.y].charVisual = CellCharacter.clean;
                        dirtPositions.remove(new Position(agentPosition.x, agentPosition.y));
                        nodes.add(new Node(newStateCells, new Position(agentPosition.x, agentPosition.y), Action.V, dirtCount-1, dirtPositions));
                    }
                    break;
            }
        }
        return nodes;
    }
    protected boolean isInBounds(int x, int y){
        return x >= 0 && x < columns && y >= 0 && y < rows;
    }
    protected boolean isGoal(Node node){
        if(node.getDirtCount() <= 0)
            return true;
        return false;
    }
}
