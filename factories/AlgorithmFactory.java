package factories;

import java.util.ArrayList;
import java.util.Collection;

import algorithms.Dijkstra;
import algorithms.AStar;
import algorithms.Node;
import constants.AlgorithmsEnum;
import algorithms.Algorithm;
import algorithms.BreadthFirstSearch;
import algorithms.DepthFirstSearch;

public class AlgorithmFactory {
    public static Algorithm createAlgorithm(AlgorithmsEnum algorithmName, Node startNode, Node goalNode, Collection<Node> borders) {
        switch (algorithmName) {
            case AStar:
                return new AStar(startNode, goalNode, borders);
            case Dijkstra:
                return new Dijkstra(startNode, goalNode, borders);
            case BreadthFirstSearch:
                return new BreadthFirstSearch(startNode, goalNode, borders);
            case DepthFirstSearch:
                return new DepthFirstSearch(startNode, goalNode, borders);
            default:
                return null;
        }
    }

    public static Algorithm createAlgorithm(AlgorithmsEnum algorithmName, Node startNode, Node goalNode) {
        return AlgorithmFactory.createAlgorithm(algorithmName, startNode, goalNode, new ArrayList<Node>());
    }
}