package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BreadthFirstSearch extends Algorithm {
    List<Node> visitedNodes;
    ArrayList<Node> openList;

    public BreadthFirstSearch(Node startNode, Node goalNode, Collection<Node> borders, boolean diagonalPref) {
        super(startNode, goalNode, borders, diagonalPref);
        openList = new ArrayList<Node>();
        visitedNodes = new ArrayList<Node>();
    }

    @Override
    public void findPath() {
        if (!this.openList.isEmpty()) {
            Node currentNode = openList.get(0);
            openList.remove(currentNode);

            for (Node neighbor : getNeighbors(currentNode)) {
                if (neighbor.getX() == goalNode.getX() && neighbor.getY() == goalNode.getY()) {
                    generatePathToGoal(neighbor);
                    isRunning = false;
                    return;
                }

                if (!isVisited(neighbor)) {
                    openList.add(neighbor);
                    visitedNodes.add(neighbor);
                }
            }
        }
    }

    @Override
    public void addStartPoint(Node node) {
        super.addStartPoint(node);
        this.openList.clear();
        this.visitedNodes.clear();
        
        this.visitedNodes.add(node);
		this.openList.add(node);
    }

    @Override
    public Collection<Node> getOpenList() {
        return this.openList;
    }

    private boolean isVisited(Node node) {
        return visitedNodes.stream()
                .anyMatch(currentNode -> currentNode.getX() == node.getX() && currentNode.getY() == node.getY());
    }
}