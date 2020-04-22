package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import algorithms.Node;
import services.ControlHandlerService;

public class Frame extends JPanel {
	private static final long serialVersionUID = 3952620062540448650L;

	public static final int rowSize = 51;
	public static final int colSize = 26;
	public static int size = 25;

	JFrame window;
	Node startNode;
	Node goalNode;
	ArrayList<Node> borders, openList, pathToGoal;

	public Frame() {
		setFocusable(true);
		setLayout(null);

		this.window = new JFrame();
		window.setContentPane(this);
		window.setTitle("Pathfinding Visualization");
		this.setPreferredSize(new Dimension(1920, 1080));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		this.borders = new ArrayList<Node>();
		this.openList = new ArrayList<Node>();
		this.pathToGoal = new ArrayList<Node>();

		new ControlHandlerService(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draws grid
		g.setColor(Color.lightGray);
		for (int j = 0; j < this.getHeight(); j += size) {
			for (int i = 0; i < this.getWidth(); i += size) {
				g.drawRect(i, j, size, size);
			}
		}

		// Draws borders
		g.setColor(new Color(128, 128, 128));
		for (Node node : borders) {
			g.fillRect(node.getX() * size, node.getY() * size, size, size);
		}

		// Draws the start node
		if (startNode != null) {
			g.setColor(new Color(0, 221, 0));
			g.fillRect(startNode.getX() * size, startNode.getY() * size, size, size);
		}

		// Draws the goal node
		if (goalNode != null) {
			g.setColor(new Color(238, 68, 0));
			g.fillRect(goalNode.getX() * size, goalNode.getY() * size, size, size);
		}

		//Nodes so far
		g.setColor(new Color(175, 238, 238)); // Light blue
		for (Node node : openList) {
			if (isStartNode(node))
				continue;

			g.fillRect(node.getX() * size, node.getY() * size, size - 1, size - 1);
		}

		//Path to goal
		g.setColor(new Color(255, 254, 106)); // Light yellow
		for (Node node : pathToGoal) {
			if (isStartNode(node))
				continue;

			g.fillRect(node.getX() * size, node.getY() * size, size - 1, size - 1);
		}
	}

	private boolean isStartNode(Node node) {
		return this.startNode != null && node.getX() == this.startNode.getX() && node.getY() == this.startNode.getY();
	}

	public void addToOpenList(Node node) {
		this.openList.add(node);		
	}

	public Collection<Node> getBorderCollection() {
		return this.borders;
	}

	public void addBorder(Node node) {
		this.borders.add(node);
		repaint();
	}

	public void removeBorder(int positionX, int positionY) {
		this.borders.removeIf(node -> node.getX() == positionX && node.getY() == positionY);
		repaint();
	}

	public void setPathToGoal(Collection<Node> path) {
		this.pathToGoal.addAll(path);
	}

	public void setFrameStartPoint(Node startNode) {
		this.startNode = startNode;
		repaint();
	}

	public void setFrameGoalPoint(Node goalNode) {
		this.goalNode = goalNode;
		repaint();
	}

	public Node getFrameStartPoint() {
		return this.startNode;
	}

	public Node getFrameGoalPoint() {
		return this.goalNode;
	}

	public JFrame getFrame() {
		return this.window;
	}

	public void clearFrame() {
		this.borders = new ArrayList<Node>();
		this.openList = new ArrayList<Node>();
		this.pathToGoal = new ArrayList<Node>();
		setFrameGoalPoint(null);
		setFrameStartPoint(null);
		repaint();
	}

	public void synchronizeOpenList(Collection<Node> openList) {
		this.openList = new ArrayList<>(openList);
		repaint();
	}

	public void synchronizePathToGoal(Collection<Node> pathToGoal) {
		this.pathToGoal = new ArrayList<>(pathToGoal);
		repaint();
	}
}