import java.util.HashMap;
import java.util.PriorityQueue;

import javax.swing.JFrame;

public class BreadthFirstSearch extends Algorithm implements AlgorithmFactory {
    HashMap<Node, Node> visitedNodes = new HashMap<>();

    public BreadthFirstSearch(JFrame frame) {
        super(frame);
        openList = new PriorityQueue<Node>();        
    }

    @Override
    public void findPath() {
		if (!this.openList.isEmpty()) {
            Node currentNode = ((PriorityQueue<Node>) openList).peek();
            
            for (Node neighbor : getNeighbors(currentNode)) {
				if (neighbor.getX() == goalNode.getX() && neighbor.getY() == goalNode.getY()) {
					getPathToGoal(neighbor);
					isRunning = false;
					return;
                }
                
                if(!visitedNodes.containsKey(neighbor)) {
                    openList.add(neighbor);
                    visitedNodes.put(neighbor, currentNode);
                }
            }
        }
    }
    
}