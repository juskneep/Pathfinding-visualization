import java.util.ArrayList;
import java.util.PriorityQueue;

import javax.swing.JFrame;

public class AStar extends Algorithm {
	ArrayList<Node> closedList = new ArrayList<>();

	public AStar(JFrame frame, Node startNode, Node endNode) {
		super(frame, startNode, endNode);
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

				neighbor.setH(getH(neighbor, goalNode));
				neighbor.setfCost(neighbor.getH() + neighbor.getG());

				if (openList.stream().anyMatch(node -> node.getX() == neighbor.getX() && node.getY() == neighbor.getY()
						&& node.getfCost() < neighbor.getfCost()))
					continue;

				if (closedList.stream().anyMatch(node -> node.getX() == neighbor.getX()
						&& node.getY() == neighbor.getY() && node.getfCost() < neighbor.getfCost()))
					continue;
				else
					openList.add(neighbor);
			}
			closedList.add(currentNode);
		}
	}

	public int getH(Node current, Node goal) {
		int deltaX = Math.abs(current.getX() - goal.getX());
		int deltaY = Math.abs(current.getY() - goal.getY());

		if (deltaX > deltaY)
			return 14 * deltaY + 10 * (deltaX - deltaY);
		return 14 * deltaX + 10 * (deltaY - deltaX);
	}
}