package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends Algorithm {
    List<Node> visitedNodes;
    Stack<Node> openList;

    public DepthFirstSearch(Node startNode, Node goalNode, Collection<Node> borders, boolean diagonalPref) {
        super(startNode, goalNode, borders, diagonalPref);
        openList = new Stack<Node>();
        visitedNodes = new ArrayList<Node>();
    }

    @Override
    public void findPath() {
        if (!this.openList.isEmpty()) {
            Node currentNode = openList.pop();

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
    public Collection<Node> getOpenList() {
        return this.openList;
    }

    @Override
    public void addStartPoint(Node node) {
        super.addStartPoint(node);
        this.visitedNodes.clear();
        this.openList.clear();
        
        this.visitedNodes.add(node);
        this.openList.add(node);
    }

    private boolean isVisited(Node node) {
        return visitedNodes.stream()
                .anyMatch(currentNode -> currentNode.getX() == node.getX() && currentNode.getY() == node.getY());
    }
}