package vacuum_world.Search.World;

import java.util.*;

public class Node {

    public Node prev;
    public double cost = 0;

    private Action action;
    private Cell[][] cells;
    private Position agentPosition;
    private List<Position> dirtPositions;

    private int dirtCount;
    private int columns;
    private int rows;
    private int id;

    /**
     * Construct node from initState string with correct sizes. Dirt count and positions
     * are initialized from the initState
     *
     * @param columns number of columns in vw
     * @param rows number of rows in vw
     * @param initState vw graph as a string. 1D ordered
     * @param a action taken to get this node
     */
    public Node(int columns, int rows, String initState, Action a){
        this.columns = columns;
        this.rows = rows;
        dirtPositions = new LinkedList<Position>();
        cost = 0;
        if(a != null) action = a;
        cells = new Cell[columns][rows];
        dirtCount = 0;
        for(int x = 0; x < columns; x++){
            for(int y = 0; y < rows; y++){
                cells[x][y] = new Cell(initState.charAt(x + ((columns) * y)), x, y);
                if(cells[x][y].hasRobot) {
                    agentPosition = new Position(x, y);
                }
                if(!cells[x][y].isBlocked && !cells[x][y].isClean) {
                    dirtPositions.add(new Position(x,y));
                    dirtCount += 1;
                }
            }
        }
        id = Objects.hash(agentPosition.x, agentPosition.y, dirtCount);
    }

    /**
     * Construct node from an already populated cells array
     *
     * @param cells Fully populated cells 2D array
     * @param agentPosition agents current position
     * @param a action used to get to this node
     * @param dirtCount number of pieces of dirt in cells
     * @param dirtPositions positions of pieces of dirt in cells
     */
    public Node(Cell[][] cells, Position agentPosition, Action a, int dirtCount, List<Position> dirtPositions){
        this.dirtCount = dirtCount;
        this.dirtPositions = dirtPositions;
        this.cells = cells;
        this.agentPosition = agentPosition;
        if(a != null) action = a;
        id = Objects.hash(agentPosition.x, agentPosition.y, dirtCount);
    }

    /**
     * Takes the agent's position and action of the agent from the list of nodes 'path'
     * anf amends the visual for a Cell at the agents position in 'cells' based on the action
     * of the node
     *
     * @param path List of nodes that have most optimized path and actions
     */
    public void PrintPath(List<Node> path){
        for (Node curr : path) {
            char visual = 'X';

            switch (curr.action) {
                case N:
                    visual = CellCharacter.north;
                    break;
                case S:
                    visual = CellCharacter.south;
                    break;
                case E:
                    visual = CellCharacter.east;
                    break;
                case W:
                    visual = CellCharacter.west;
                    break;
                case V:
                    visual = CellCharacter.vacuum;
                    break;
                default:
                    break;
            }


            Position pos = curr.agentPosition;
            cells[pos.x][pos.y].charVisual = visual;
        }
        for(int y = rows-1; y >= 0; y--) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < columns; x++) {
                row.append(cells[x][y].charVisual);
            }
            System.out.println(row);
        }
    }

    public int getDirtCount() {
        return dirtCount;
    }
    public Action getAction(){
        return action;
    }
    public Position getAgentPosition() {
        return agentPosition;
    }
    public Cell[][] getCells(){
        return cells;
    }
    public List<Position> getDirtPosition() {
        return dirtPositions;
    }

    @Override
    public boolean equals(Object object){
        if(this == object)
            return true;
        if(object == null)
            return false;
        if(this.getClass() != object.getClass())
            return false;
        Node n = (Node)object;
        if(this.id != n.id)
            return false;
        return true;
    }

    @Override
    public int hashCode(){
        return id;
    }


}
