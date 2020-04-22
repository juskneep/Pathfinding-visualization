package algorithms;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.PriorityQueue;
import javax.swing.JFrame;

public class Dijkstra extends Algorithm {
    HashMap<Node, Integer> cost = new HashMap<>();

    public Dijkstra(Node startNode, Node goalNode, Collection<Node> borders) {
        super(startNode, goalNode, borders);
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
                if (this.containsNode(neighbor) && newCost >= getNodeCost(neighbor))
                    continue;

                neighbor.setfCost(newCost);

                cost.put(neighbor, neighbor.getfCost());
                openList.add(neighbor);
            }
        }
    }

    private boolean containsNode(Node neighbor) {
        return this.cost.keySet().stream()
                .anyMatch(node -> node.getX() == neighbor.getX() && node.getY() == neighbor.getY());
    }

    private int getNodeCost(Node neighbor) {
        Optional<Node> result = this.cost.keySet().stream()
                .filter(node -> node.getX() == neighbor.getX() && node.getY() == neighbor.getY()).findFirst();

        if(!result.isPresent()) return Integer.MIN_VALUE;
        
        return this.cost.get(result.get());
    }

    @Override
    public void addStartPoint(Node node) {
        this.startNode = node;
        this.cost.put(node, 0);
    }
}