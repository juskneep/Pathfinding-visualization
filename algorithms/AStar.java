package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;

public class AStar extends Algorithm {
	ArrayList<Node> closedList = new ArrayList<>();

	public AStar(Node startNode, Node endNode, Collection<Node> borders) {
		super(startNode, endNode, borders);
		openList = new PriorityQueue<Node>();
	}

	@Override
	public void findPath() {
		if (!this.openList.isEmpty()) {
			Node currentNode = ((PriorityQueue<Node>) openList).peek();
			openList.remove(currentNode);
			closedList.add(currentNode);

			if (currentNode.getX() == goalNode.getX() && currentNode.getY() == goalNode.getY()) {
				getPathToGoal(currentNode);
				isRunning = false;
				return;
			}

			for (Node neighbor : getNeighbors(currentNode)) {

				neighbor.setH(getH(neighbor, goalNode));
				neighbor.setfCost(neighbor.getH() + neighbor.getG());

				if (openList.stream().anyMatch(node -> node.getX() == neighbor.getX() && node.getY() == neighbor.getY()
						&& node.getfCost() <= neighbor.getfCost()))
					continue;

				if (closedList.stream().anyMatch(node -> node.getX() == neighbor.getX()
						&& node.getY() == neighbor.getY() && node.getfCost() <= neighbor.getfCost()))
					continue;
				else
					openList.add(neighbor);
			}
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