package algorithms;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.PriorityQueue;

import exceptions.InvalidStartPointException;

public class Dijkstra extends AlgorithmBase {
    HashMap<Node, Integer> cost;
    PriorityQueue<Node> openList;

    public Dijkstra(Node startNode, Node goalNode, Collection<Node> borders, boolean diagonalPref) {
        super(startNode, goalNode, borders, diagonalPref);
        openList = new PriorityQueue<Node>();
        cost = new HashMap<Node, Integer>();
    }

    @Override
    public void findPath() throws InvalidStartPointException {
        super.findPath();
        
        if (!openList.isEmpty()) {
            Node currentNode = openList.peek();
            openList.remove(currentNode);

            for (Node neighbor : getNeighbors(currentNode)) {
                if (neighbor.getX() == goalNode.getX() && neighbor.getY() == goalNode.getY()) {
                    generatePathToGoal(neighbor);
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

    @Override
    public void addStartPoint(Node node) {
        super.addStartPoint(node);
        this.cost.clear();
        this.openList.clear();
        
        this.cost.put(node, 0);
        this.openList.add(node);
    }

    @Override
    public Collection<Node> getOpenList() {
        return this.openList;
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
    public Collection<Node> getClosedList() {
        return this.cost.keySet();
    }
}