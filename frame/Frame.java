package frame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

import algorithms.Algorithm;
import algorithms.Node;
import constants.AlgorithmsEnum;
import constants.ApplicationConstants;
import exceptions.InvalidStartPointException;
import factories.AlgorithmFactory;

public class Frame extends JPanel implements ActionListener, KeyListener, MouseInputListener {
	private static final long serialVersionUID = 3952620062540448650L;

	private Algorithm algorithm;
	private char currentKey = (char) 0;
	private Timer timer = new Timer(50, this);

	private Node startNode;
	private Node goalNode;

	public Frame(AlgorithmsEnum selectedAlgorithm) {
		createAlgorithm(selectedAlgorithm, false);

		setFocusable(true);
		setLayout(null);

		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draws grid
		g.setColor(Color.lightGray);
		for (int j = 0; j < this.getHeight(); j += ApplicationConstants.size) {
			for (int i = 0; i < this.getWidth(); i += ApplicationConstants.size) {
				g.drawRect(i, j, ApplicationConstants.size, ApplicationConstants.size);
			}
		}

		if (algorithm == null)
			return;
		// Draws borders
		g.setColor(new Color(128, 128, 128));
		for (Node node : algorithm.getBorders()) {
			g.fillRect(node.getX() * ApplicationConstants.size, node.getY() * ApplicationConstants.size,
					ApplicationConstants.size, ApplicationConstants.size);
		}

		// Draws the start node
		if (startNode != null) {
			g.setColor(new Color(0, 221, 0));
			g.fillRect(startNode.getX() * ApplicationConstants.size, startNode.getY() * ApplicationConstants.size,
					ApplicationConstants.size, ApplicationConstants.size);
		}

		// Draws the goal node
		if (goalNode != null) {
			g.setColor(new Color(238, 68, 0));
			g.fillRect(goalNode.getX() * ApplicationConstants.size, goalNode.getY() * ApplicationConstants.size,
					ApplicationConstants.size, ApplicationConstants.size);
		}

		// Nodes so far
		g.setColor(new Color(175, 238, 238)); // Light blue
		for (Node node : algorithm.getOpenList()) {
			if (isStartNode(node))
				continue;

			g.fillRect(node.getX() * ApplicationConstants.size, node.getY() * ApplicationConstants.size,
					ApplicationConstants.size - 1, ApplicationConstants.size - 1);
		}

		// Path to goal
		g.setColor(new Color(255, 254, 106)); // Light yellow
		for (Node node : algorithm.getPathToGoalCollection()) {
			if (isStartNode(node))
				continue;

			g.fillRect(node.getX() * ApplicationConstants.size, node.getY() * ApplicationConstants.size,
					ApplicationConstants.size - 1, ApplicationConstants.size - 1);
		}
	}

	private void handleMouseClick(MouseEvent e) {
		int xPosition = Math.round(e.getX() / ApplicationConstants.size);
		int yPosition = Math.round(e.getY() / ApplicationConstants.size);

		if (SwingUtilities.isLeftMouseButton(e)) {
			if (currentKey == 's') {
				setStartPoint(new Node(xPosition, yPosition));
			} else if (currentKey == 'e') {
				setGoalPoint(new Node(xPosition, yPosition));
			} else {
				addBorder(new Node(xPosition, yPosition));
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			removeBorder(xPosition, yPosition);
		}
		repaint();
	}

	private boolean isStartNode(Node node) {
		return this.startNode != null && node.getX() == this.startNode.getX() && node.getY() == this.startNode.getY();
	}

	private void setGoalPoint(Node goalNode) {
		this.goalNode = goalNode;
		algorithm.addEndPoint(goalNode);
	}

	private void setStartPoint(Node startNode) {
		this.startNode = startNode;
		algorithm.addStartPoint(startNode);
	}

	private void addBorder(Node node) {
		algorithm.addBorder(node);
	}

	private void removeBorder(int xPosition, int yPosition) {
		algorithm.removeBorder(xPosition, yPosition);
	}

	// Shared methods
	public void start() {
		if (algorithm.isRunning())
			return;

		algorithm.run();
		this.timer.start();
		repaint();
	}

	public void stop() {
		this.algorithm.stop();
		this.timer.stop();
		repaint();
	}

	public void setDelay(int delay) {
		timer.setDelay(delay);
	}

	public void clearAlgorithm(AlgorithmsEnum selectedEnum, boolean diagonalPref) {
		this.goalNode = null;
		this.startNode = null;
		this.createAlgorithm(selectedEnum, diagonalPref);
	}

	public void createAlgorithm(AlgorithmsEnum selectedEnum, boolean diagonalPref) {
		this.algorithm = AlgorithmFactory.createAlgorithm(selectedEnum, this.startNode, this.goalNode, diagonalPref);
	}

	public void changeAlgorithm(AlgorithmsEnum selectedEnum, boolean diagonalPref) {
		this.algorithm = AlgorithmFactory.createAlgorithm(selectedEnum, this.startNode, this.goalNode, diagonalPref,
				algorithm.getBorders());

		if (this.startNode != null)
			setStartPoint(this.startNode);
	}

	public void changeDiagonalPref(boolean pref) {
		this.algorithm.changeDiagonalPref(pref);
	}

	// Event Listeners
	@Override
	public void keyPressed(KeyEvent e) {
		currentKey = e.getKeyChar();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		currentKey = (char) 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.handleMouseClick(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.handleMouseClick(e);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (algorithm.isRunning()) {
			try {
				this.algorithm.findPath();
			} catch (InvalidStartPointException ex) {
				stop();
				ex.printStackTrace();
			} catch (Exception ex) {
				stop();
				ex.printStackTrace();
			}
		}

		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.handleMouseClick(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}