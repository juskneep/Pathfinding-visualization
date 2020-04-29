package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;

import exceptions.InvalidStartPointException;

public class AStar extends AlgorithmBase {
	ArrayList<Node> closedList;
	PriorityQueue<Node> openList;

	public AStar(Node startNode, Node endNode, Collection<Node> borders, boolean diagonalPref) {
		super(startNode, endNode, borders, diagonalPref);
		openList = new PriorityQueue<Node>();
		closedList = new ArrayList<>();
	}

	@Override
	public void findPath() throws InvalidStartPointException {
		super.findPath();

		if (!this.openList.isEmpty()) {
			Node currentNode = openList.peek();
			openList.remove(currentNode);
			closedList.add(currentNode);

			if (currentNode.getX() == goalNode.getX() && currentNode.getY() == goalNode.getY()) {
				generatePathToGoal(currentNode);
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

	@Override
	public void addStartPoint(Node node) {
		super.addStartPoint(node);
		this.openList.clear();
		this.openList.add(node);
	}

	@Override
	public Collection<Node> getOpenList() {
		return this.openList;
	}

	private int getH(Node current, Node goal) {
		int deltaX = Math.abs(current.getX() - goal.getX());
		int deltaY = Math.abs(current.getY() - goal.getY());

		if (deltaX > deltaY)
			return 14 * deltaY + 10 * (deltaX - deltaY);
		return 14 * deltaX + 10 * (deltaY - deltaX);
	}

	@Override
	public Collection<Node> getClosedList() {
		return this.closedList;
	}
}