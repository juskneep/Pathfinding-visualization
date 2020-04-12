public class Node implements Comparable<Node> {
	private int fCost;
	private int x, y, g, h;
	private Node parent;

	public Node(int x, int y, Node parent) {
		super();
		this.x = x;
		this.y = y;
		this.parent = parent;
		setG();
	}

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getG() {
		return this.g;
	}

	// Keep in mind for the start point with no parent element
	// Private due to auto generating
	private void setG() {
		if (parent == null)
			g = 0;
		g = parent.g + 1;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public int getfCost() {
		return this.fCost;
	}

	public void setfCost(int fCost) {
		this.fCost = fCost;
	}

	public int getH() {
		return this.h;
	}

	public void setH(int h) {
		this.h = h;
	}

	@Override
	public int compareTo(Node o) {
		/*
		 * if(this.getfCost() > o.getfCost()) return 1; 
		 * if(this.getfCost() < o.getfCost()) return -1 
		 * return 0;
		 */

		return this.getfCost() - o.getfCost();
	}
}
