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
    public static Algorithm createAlgorithm(AlgorithmsEnum algorithmName, Node startNode, Node goalNode, boolean diagonalPref, Collection<Node> borders) {
        switch (algorithmName) {
            case AStar:
                return new AStar(startNode, goalNode, borders, diagonalPref);
            case Dijkstra:
                return new Dijkstra(startNode, goalNode, borders, diagonalPref);
            case BreadthFirstSearch:
                return new BreadthFirstSearch(startNode, goalNode, borders, diagonalPref);
            case DepthFirstSearch:
                return new DepthFirstSearch(startNode, goalNode, borders, diagonalPref);
            default:
                return null;
        }
    }

    public static Algorithm createAlgorithm(AlgorithmsEnum algorithmName, Node startNode, Node goalNode, boolean diagonalPref) {
        return AlgorithmFactory.createAlgorithm(algorithmName, startNode, goalNode, diagonalPref, new ArrayList<Node>());
    }
}