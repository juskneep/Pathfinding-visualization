package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import algorithms.Node;
import services.ControlHandlerService;

public class Frame extends JPanel {
	public static final int rowSize = 51;
	public static final int colSize = 26;
	public static int size = 25;

	public JFrame window;

	ControlHandlerService controlHandlerService;
	public Node startNode;
	public Node goalNode;

	public Frame() {
		setFocusable(true);
		setLayout(null);

		window = new JFrame();
		window.setContentPane(this);
		window.setTitle("Pathfinding Visualization");
		window.getContentPane().setPreferredSize(new Dimension(1920, 1080));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		controlHandlerService = new ControlHandlerService(this);
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

		//
		if(controlHandlerService == null) return;

		// Draws borders
		g.setColor(Color.black);
		for (Node node : controlHandlerService.algorithm.getBorders()) {
			g.fillRect(node.getX() * size, node.getY() * size, size, size);
		}

		// Draws the start node
		if (controlHandlerService.algorithm.startNode != null) {
			g.setColor(Color.green);
			g.fillRect(controlHandlerService.algorithm.startNode.getX() * size, controlHandlerService.algorithm.startNode.getY() * size, size, size);
		}

		// Draws the goal node
		if (controlHandlerService.algorithm.goalNode != null) {
			g.setColor(Color.red);
			g.fillRect(controlHandlerService.algorithm.goalNode.getX() * size, controlHandlerService.algorithm.goalNode.getY() * size, size, size);
		}

		g.setColor(new Color(175, 238, 238)); // Light blue
		for (Node node : this.controlHandlerService.algorithm.openList) {
			if (node.getX() == controlHandlerService.algorithm.startNode.getX() && node.getY() == controlHandlerService.algorithm.startNode.getY())
				continue;

			g.fillRect(node.getX() * size, node.getY() * size, size - 1, size - 1);
		}

		g.setColor(Color.yellow); // Light yellow
		for (Node node : this.controlHandlerService.algorithm.pathToGoal) {
			if (node.getX() == controlHandlerService.algorithm.startNode.getX() && node.getY() == controlHandlerService.algorithm.startNode.getY())
				continue;
			g.fillRect(node.getX() * size, node.getY() * size, size - 1, size - 1);
		}
	}

	public void setFrameStartPoint(Node startNode) {
		this.startNode = startNode;
	}

	public void setFrameGoalPoint(Node goalNode) {
		this.goalNode = goalNode;
	}
}