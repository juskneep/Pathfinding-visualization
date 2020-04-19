package factories;

import javax.swing.JFrame;

import algorithms.Dijkstra;
import algorithms.AStar;
import algorithms.Node;
import constants.AlgorithmsEnum;
import algorithms.Algorithm;
import algorithms.BreadthFirstSearch;

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