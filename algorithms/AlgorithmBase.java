package algorithms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import constants.ApplicationConstants;
import exceptions.InvalidStartPointException;
import shared.Algorithm;

public abstract class AlgorithmBase implements Algorithm {
	ArrayList<Node> borderCollection;
	ArrayList<Node> pathToGoal;
	boolean isRunning;
	boolean diagonal;
	Node startNode;
	Node goalNode;

	private AlgorithmBase() {
		borderCollection = new ArrayList<>();
		pathToGoal = new ArrayList<>();
	}

	protected AlgorithmBase(Node startNode, Node goalNode, Collection<Node> borders, boolean diagonalPref) {
		this();
		this.startNode = startNode;
		this.goalNode = goalNode;
		this.diagonal = diagonalPref;
		this.borderCollection = new ArrayList<>(borders);
	}


	public void run() {
		this.isRunning = true;
	}

	public void stop() {
		this.isRunning = false;
	}

	protected void generatePathToGoal(Node node) {
		Node currentNode = node;
		while ((currentNode = currentNode.getParent()) != null)
			pathToGoal.add(currentNode);
	}

	public Collection<Node> getPathToGoalCollection() {
		return this.pathToGoal;
	}

	private boolean isStartCell(int x, int y) {
		return startNode.getX() == x && startNode.getY() == y;
	}

	private boolean exist(int rowIndex, int colIndex) {
		return rowIndex >= 0 && rowIndex < ApplicationConstants.rowSize && colIndex >= 0 && colIndex < ApplicationConstants.colSize;
	}

	private boolean isWall(int rowIndex, int colIndex) {
		return this.borderCollection.stream().anyMatch(node -> node.getX() == rowIndex && node.getY() == colIndex);
	}

	public ArrayList<Node> getBorders() {
		return this.borderCollection;
	}

	public void addBorder(Node node) {
		this.borderCollection.add(node);
	}

	public void removeBorder(int xCoor, int yCoor) {
		this.borderCollection.removeIf(node -> node.getX() == xCoor && node.getY() == yCoor);
	}

	protected List<Node> getNeighbors(Node node) {
		List<Node> neighborList = new ArrayList<Node>();
		int nodeRowIndex = node.getX();
		int nodeColumnIndex = node.getY();

		int topCellRow = nodeRowIndex - 1;
		int leftCellCol = nodeColumnIndex - 1;
		int rightCellCol = nodeColumnIndex + 1;
		int bottomCellRow = nodeRowIndex + 1;

		for (int row = topCellRow; row <= bottomCellRow; row++)
			for (int column = leftCellCol; column <= rightCellCol; column++)
				if (exist(row, column) && !isWall(row, column) && !isStartCell(row, column) && checkDiagonal(row, column, nodeRowIndex, nodeColumnIndex))
					neighborList.add(new Node(row, column, node));

		return neighborList;
	}

	private boolean checkDiagonal(int row, int column, int nodeRowIndex, int nodeColumnIndex) {
		if(this.diagonal) return true;

		return row == nodeRowIndex || nodeColumnIndex == column;
	}

	public boolean isRunning() {
		return this.isRunning;
	}

	public void addStartPoint(Node node) {
		this.startNode = node;
	}

	public void addEndPoint(Node node) {
		this.goalNode = node;
	}

	public void changeDiagonalPref(boolean diagonal) {
		this.diagonal = diagonal;
	}

	public void findPath() throws InvalidStartPointException {
		if(this.startNode == null || this.goalNode == null) throw new InvalidStartPointException(startNode, goalNode);
	};
	abstract public Collection<Node> getOpenList();
}