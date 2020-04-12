import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JFrame;

public class AStar {
	PriorityQueue<Node> openList = new PriorityQueue<>();
	ArrayList<Node> closedList = new ArrayList<>();
	ArrayList<Node> borderCollection = new ArrayList<>();
	ArrayList<Node> pathToGoal = new ArrayList<>();
	boolean isRunning;
	Node startNode;
	Node goalNode;
	JFrame frame;

	public AStar(JFrame frame) {
		this.frame = frame;
	}

	public void startAction() {
		//if(startNode == null || goalNode == null) throw new Exception("Please set up the start and the end point!");
		if(!openList.isEmpty()) {
			Node currentNode = openList.peek();
			openList.remove(currentNode);

			for (Node neighbor : getNeighbors(currentNode)) {
				if (neighbor.getX() == goalNode.getX() && neighbor.getY() == goalNode.getY()) {
					getPathToGoal(neighbor);
					isRunning = false;
					frame.repaint();
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
				else {
					openList.add(neighbor);					
				}
			}
			closedList.add(currentNode);
		}
		frame.repaint();
	}

	public List<Node> getNeighbors(Node node) {
		List<Node> neighborList = new ArrayList<Node>();
		int nodeRowIndex = node.getX();
		int nodeColumnIndex = node.getY();

		int topCellRow = nodeRowIndex - 1;
		int leftDiagCol = nodeColumnIndex - 1;
		int rightDiagCol = nodeColumnIndex + 1;
		int leftCellCol = nodeColumnIndex - 1;
		int rightCellCol = nodeColumnIndex + 1;
		int bottomCellRow = nodeRowIndex + 1;

		// Top cells
		if (exist(topCellRow, nodeColumnIndex) && !isWall(topCellRow, nodeColumnIndex)
				&& !isStartCell(topCellRow, nodeColumnIndex))
			neighborList.add(new Node(topCellRow, nodeColumnIndex, node));
		if (exist(topCellRow, leftDiagCol) && !isWall(topCellRow, leftDiagCol) && !isStartCell(topCellRow, leftDiagCol))
			neighborList.add(new Node(topCellRow, leftDiagCol, node));
		if (exist(topCellRow, rightDiagCol) && !isWall(topCellRow, rightDiagCol)
				&& !isStartCell(topCellRow, rightDiagCol))
			neighborList.add(new Node(topCellRow, rightDiagCol, node));

		// Next neighbors
		if (exist(nodeRowIndex, leftCellCol) && !isWall(nodeRowIndex, leftCellCol)
				&& !isStartCell(nodeRowIndex, leftCellCol))
			neighborList.add(new Node(nodeRowIndex, leftCellCol, node));
		if (exist(nodeRowIndex, rightCellCol) && !isWall(nodeRowIndex, rightCellCol)
				&& !isStartCell(nodeRowIndex, rightCellCol))
			neighborList.add(new Node(nodeRowIndex, rightCellCol, node));

		// Bottom neighbors
		if (exist(bottomCellRow, nodeColumnIndex) && !isWall(bottomCellRow, nodeColumnIndex)
				&& !isStartCell(bottomCellRow, nodeColumnIndex))
			neighborList.add(new Node(bottomCellRow, nodeColumnIndex, node));
		if (exist(bottomCellRow, rightDiagCol) && !isWall(bottomCellRow, rightDiagCol)
				&& !isStartCell(bottomCellRow, rightDiagCol))
			neighborList.add(new Node(bottomCellRow, rightDiagCol, node));
		if (exist(bottomCellRow, leftDiagCol) && !isWall(bottomCellRow, leftDiagCol)
				&& !isStartCell(bottomCellRow, leftDiagCol))
			neighborList.add(new Node(bottomCellRow, leftDiagCol, node));

		return neighborList;
	}

	public void getPathToGoal(Node node) {
		Node currentNode = node;
		while ((currentNode = currentNode.getParent()) != null) {
			pathToGoal.add(currentNode);
		}
	}

	private boolean isStartCell(int x, int y) {
		return startNode.getX() == x && startNode.getY() == y;
	}

	public boolean exist(int rowIndex, int colIndex) {
		return rowIndex >= 0 && rowIndex < Visualization.rowSize && colIndex >= 0 && colIndex < Visualization.colSize;
	}

	public boolean isWall(int rowIndex, int colIndex) {
		return borderCollection.stream().anyMatch(node -> node.getX() == rowIndex && node.getY() == colIndex);
	}

	public int getH(Node current, Node goal) {
		int deltaX = Math.abs(current.getX() - goal.getX());
		int deltaY = Math.abs(current.getY() - goal.getY());

		if (deltaX > deltaY)
			return 14 * deltaY + 10 * (deltaX - deltaY);
		return 14 * deltaX + 10 * (deltaY - deltaX);
	}

	public void addBorder(int xCoor, int yCoor) {
		// if (exist(row, col))
		borderCollection.add(new Node(xCoor, yCoor));
	}

	public void removeBorder(int xCoor, int yCoor) {
		borderCollection.removeIf(node -> node.getX() == xCoor && node.getY() == yCoor);
	}

	public void clearBorders() {
		borderCollection.clear();
	}

	public void addToOpenList(Node node) {
		openList.add(node);
	}

	public boolean isRunning() {
		return isRunning;
	}
}