package algorithms;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class BreadthFirstSearch extends Algorithm {
    List<Node> visitedNodes = new ArrayList<Node>();

    public BreadthFirstSearch(JFrame frame, Node startNode, Node goalNode) {
        super(frame, startNode, goalNode);
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
        this.startNode = node;
        this.visitedNodes.add(node);
    }
}