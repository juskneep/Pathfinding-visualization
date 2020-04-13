import javax.swing.JFrame;

public class AlgorithmFactory {
    public static Algorithm createAlgorithm(String algorithmName, JFrame frame) {
        switch (algorithmName) {
            case "AStar":
                return new AStar(frame);
            case "BreadthFirstSearch":
                return new BreadthFirstSearch(frame);
            case "Dijkstra":
                return new Dijkstra(frame);
            default:
                // throw new InvalidAlgorithmException();
                return null;
        }
    }
}