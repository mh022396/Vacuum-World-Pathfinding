package vacuum_world.Search.Algorithms.Heuristics;

public class HeurisitcFactory {
    public Heuristic GetHeuristic(String heuristic){
        Heuristic h;
        switch (heuristic) {
            case "h0":
                h = new Heuristic_0();
                break;
            case "h1":
                h = new Heuristic_1();
                break;
            case "h2":
                h = new Heuristic_2();
                break;
            default:
                h = new Heuristic_1();
                break;
        }
        return h;
    }
}
