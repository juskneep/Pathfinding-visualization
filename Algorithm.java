import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFrame;

public abstract class Algorithm {	
	Collection<Node> openList;
	ArrayList<Node> borderCollection = new ArrayList<>();
	ArrayList<Node> pathToGoal = new ArrayList<>();
	boolean isRunning;
	public Node startNode;
	public Node goalNode;
    JFrame frame;

    public Algorithm(JFrame frame) {
        this.frame = frame;
	}
	
	public Algorithm(JFrame frame, Node startNode) {
		this(frame);
		this.startNode = startNode;
	}

	public Algorithm(JFrame frame, Node startNode, Node goalNode) {
		this(frame, startNode);
		this.goalNode = goalNode;
	}
    
    public void Run() {
		this.isRunning = true;
    }
    
    public void getPathToGoal(Node node) {
		Node currentNode = node;
		while ((currentNode = currentNode.getParent()) != null)
			pathToGoal.add(currentNode);
	}
	
    public boolean isStartCell(int x, int y) {
        return startNode.getX() == x && startNode.getY() == y;
    }

	public boolean exist(int rowIndex, int colIndex) {
		return rowIndex >= 0 && rowIndex <= Visualization.rowSize && colIndex >= 0 && colIndex <= Visualization.colSize;
	}

	public boolean isWall(int rowIndex, int colIndex) {
		return borderCollection.stream().anyMatch(node -> node.getX() == rowIndex && node.getY() == colIndex);
	}
	
	public ArrayList<Node> getBorders() {
		return borderCollection;
	}
    
    public void addBorder(int xCoor, int yCoor) {
		borderCollection.add(new Node(xCoor, yCoor));
	}

	public void removeBorder(int xCoor, int yCoor) {
		borderCollection.removeIf(node -> node.getX() == xCoor && node.getY() == yCoor);
	}

	public List<Node> getNeighbors(Node node) {
		List<Node> neighborList = new ArrayList<Node>();
		int nodeRowIndex = node.getX();
		int nodeColumnIndex = node.getY();

		int topCellRow = nodeRowIndex - 1;
		int leftCellCol = nodeColumnIndex - 1;
		int rightCellCol = nodeColumnIndex + 1;
		int bottomCellRow = nodeRowIndex + 1;

		for (int row = topCellRow; row <= bottomCellRow; row++)
			for (int column = leftCellCol; column <= rightCellCol; column++)
				if (exist(row, column) && !isWall(row, column) && !isStartCell(row, column))
					neighborList.add(new Node(row, column, node));

		return neighborList;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void addToOpenList(Node node) {
		openList.add(node);
	}

	public void addStartPoint(Node node) {
		this.startNode = node;
	}

	public void addEndPoint(Node node) {
		this.goalNode = node;
	}

	public void findPath() {
	}
}