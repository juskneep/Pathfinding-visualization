package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;

import frame.Frame;

public abstract class Algorithm {
	public Collection<Node> openList;
	ArrayList<Node> borderCollection = new ArrayList<>();
	public ArrayList<Node> pathToGoal = new ArrayList<>();
	boolean isRunning;
	boolean diagonal;
	public Node startNode;
	public Node goalNode;
	JFrame frame;

	public Algorithm(final JFrame frame) {
		this.frame = frame;
	}

	public Algorithm(final JFrame frame, final Node startNode) {
		this(frame);
		this.startNode = startNode;
	}

	public Algorithm(final JFrame frame, final Node startNode, final Node goalNode) {
		this(frame, startNode);
		this.goalNode = goalNode;
	}

	public void Run() {
		this.isRunning = true;
	}

	public void getPathToGoal(final Node node) {
		Node currentNode = node;
		while ((currentNode = currentNode.getParent()) != null)
			pathToGoal.add(currentNode);
	}

	public boolean isStartCell(final int x, final int y) {
		return startNode.getX() == x && startNode.getY() == y;
	}

	public boolean exist(final int rowIndex, final int colIndex) {
		return rowIndex >= 0 && rowIndex <= Frame.rowSize && colIndex >= 0 && colIndex <= Frame.colSize;
	}

	public boolean isWall(final int rowIndex, final int colIndex) {
		return borderCollection.stream().anyMatch(node -> node.getX() == rowIndex && node.getY() == colIndex);
	}

	public ArrayList<Node> getBorders() {
		return borderCollection;
	}

	public void addBorder(final int xCoor, final int yCoor) {
		borderCollection.add(new Node(xCoor, yCoor));
	}

	public void removeBorder(final int xCoor, final int yCoor) {
		borderCollection.removeIf(node -> node.getX() == xCoor && node.getY() == yCoor);
	}

	public List<Node> getNeighbors(final Node node) {
		final List<Node> neighborList = new ArrayList<Node>();
		final int nodeRowIndex = node.getX();
		final int nodeColumnIndex = node.getY();

		final int topCellRow = nodeRowIndex - 1;
		final int leftCellCol = nodeColumnIndex - 1;
		final int rightCellCol = nodeColumnIndex + 1;
		final int bottomCellRow = nodeRowIndex + 1;

		for (int row = topCellRow; row <= bottomCellRow; row++)
			for (int column = leftCellCol; column <= rightCellCol; column++)
				if (exist(row, column) && !isWall(row, column) && !isStartCell(row, column) && checkDiagonal(row, column, nodeRowIndex, nodeColumnIndex))
					neighborList.add(new Node(row, column, node));

		return neighborList;
	}

	private boolean checkDiagonal(int row, int column, int nodeRowIndex, int nodeColumnIndex) {
		if(diagonal) return true;

		return row == nodeRowIndex || nodeColumnIndex == column;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void addToOpenList(final Node node) {
		openList.add(node);
	}

	public void addStartPoint(final Node node) {
		this.pathToGoal.clear();
		this.openList.clear();
		this.startNode = node;
	}

	public void addEndPoint(final Node node) {
		this.goalNode = node;
	}

	abstract public void findPath();

	public void changeDiagonalPref() {
		this.diagonal = !diagonal;
	}

}