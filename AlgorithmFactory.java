import javax.swing.JFrame;

public class AlgorithmFactory {
    public static Algorithm createAlgorithm(AlgorithmsEnum algorithmName, JFrame frame, Node startNode, Node goalNode) {
        switch (algorithmName) {
            case AStar:
                return new AStar(frame, startNode, goalNode);
            case Dijkstra:
                return new Dijkstra(frame, startNode, goalNode);
            case BreadthFirstSearch:
                return new BreadthFirstSearch(frame);
            default:
                // throw new InvalidAlgorithmException();
                return null;
        }
    }
}