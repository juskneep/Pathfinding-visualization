package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BreadthFirstSearch extends Algorithm {
    List<Node> visitedNodes = new ArrayList<Node>();

    public BreadthFirstSearch(Node startNode, Node goalNode, Collection<Node> borders) {
        super(startNode, goalNode, borders);
        openList = new ArrayList<Node>();        
    }

    @Override
    public void findPath() {
		if (!this.openList.isEmpty()) {
            Node currentNode = ((ArrayList<Node>) openList).get(0);
            openList.remove(currentNode);
            
            for (Node neighbor : getNeighbors(currentNode)) {
				if (neighbor.getX() == goalNode.getX() && neighbor.getY() == goalNode.getY()) {
					getPathToGoal(neighbor);
					isRunning = false;
					return;
                }
                
                if(!isVisited(neighbor)) {
                    openList.add(neighbor);
                    visitedNodes.add(neighbor);
                }
            }
        }
    }

    public boolean isVisited(Node node) {
        return visitedNodes.stream().anyMatch(currentNode -> currentNode.getX() == node.getX() && currentNode.getY() == node.getY());
    }

    @Override
    public void addStartPoint(Node node) {
        super.addStartPoint(node);
        this.visitedNodes.clear();
        this.visitedNodes.add(node);
    }
}