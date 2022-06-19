package vacuum_world.Search;

import vacuum_world.Search.Algorithms.Search;
import vacuum_world.Search.Algorithms.SearchFactory;
import vacuum_world.Search.World.Action;
import vacuum_world.Search.World.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        //Parse the Vacuum World from standard input for Row and Column size
        String colS = input.readLine();
        String rowS = input.readLine();

        //Parse the Vacuum World grid from standard input
        int columns = Integer.parseInt(colS);
        int rows = Integer.parseInt(rowS);
        StringBuilder initString = new StringBuilder();
        for(int i = 3; i < rows+3; i ++){
            initString.insert(0, input.readLine());
        }

        //Create the initial Node using the initial state
        Node initNode = new Node(columns,rows, initString.toString(), null);
        Search search;

        //This will be the final node
        Node path;

        //Determine search and if needed heuristic from command line
        SearchFactory searchFactory = new SearchFactory();
        search = searchFactory.GetSearch(args[0], args[1], columns, rows);
        path = search.SearchNodes(initNode);

        //Get Path Nodes and Actions
        Stack<Action> actionPath = new Stack<Action>();
        List<Node> pathNodes = new LinkedList<Node>();

        while (path.getAction() != null){
            pathNodes.add(path);
            actionPath.add(path.getAction());
            path = path.prev;
        }

        //Print actions for path found
        StringBuilder actionString = new StringBuilder();
        while (!actionPath.isEmpty()) {
            actionString.append(actionPath.pop()).append(" ");
        }
        System.out.println("Path: " + actionString);

        //Print grid with path
        Collections.reverse(pathNodes);
        initNode.PrintPath(pathNodes);
    }
}
