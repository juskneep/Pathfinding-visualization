package algorithms;

import java.util.HashMap;
import java.util.PriorityQueue;
import javax.swing.JFrame;

public class Dijkstra extends Algorithm {
    HashMap<Node, Integer> cost = new HashMap<>();

    public Dijkstra(JFrame frame, Node startNode, Node goalNode) {
        super(frame, startNode, goalNode);
        openList = new PriorityQueue<Node>();
    }

    @Override
    public void findPath() {
        if (!openList.isEmpty()) {
            Node currentNode = ((PriorityQueue<Node>) openList).peek();
            openList.remove(currentNode);

            for (Node neighbor : getNeighbors(currentNode)) {
                if (neighbor.getX() == goalNode.getX() && neighbor.getY() == goalNode.getY()) {
                    getPathToGoal(neighbor);
                    isRunning = false;
                    return;
                }

                int newCost = cost.get(currentNode) + 1;
                if (cost.containsKey(neighbor) && newCost >= cost.get(neighbor))
                    return;

                neighbor.setH(newCost);
                neighbor.setfCost(newCost + 1);

                cost.put(neighbor, neighbor.getfCost());
                openList.add(neighbor);
            }
        }
    }

    @Override
    public void addStartPoint(Node node) {
        this.startNode = node;
        this.cost.put(node, 0);
    }
}