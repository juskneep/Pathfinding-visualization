import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JFrame;

public class BreadthFirstSearch extends Algorithm {
    List<Node> visitedNodes = new ArrayList<Node>();

    public BreadthFirstSearch(JFrame frame) {
        super(frame);
        openList = new PriorityQueue<Node>();        
    }

    @Override
    public void findPath() {
		if (!this.openList.isEmpty()) {
            Node currentNode = ((PriorityQueue<Node>) openList).peek();
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

    public void addStartPoint(Node node) {
        this.startNode = node;
        this.visitedNodes.add(node);
    }
}