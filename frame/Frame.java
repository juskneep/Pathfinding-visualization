package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;

import algorithms.Algorithm;
import algorithms.Node;
import constants.AlgorithmsEnum;
import constants.ApplicationConstants;
import factories.AlgorithmFactory;
import factories.GUIFactory;

public class Frame extends JPanel implements ActionListener, KeyListener, MouseInputListener {
	private static final long serialVersionUID = 3952620062540448650L;

	GUIFactory guiFactory;
	Algorithm algorithm;
	char currentKey = (char) 0;
	AlgorithmsEnum selectedAlgorithm = AlgorithmsEnum.AStar;
	Timer timer = new Timer(50, this);

	JFrame window;
	Node startNode;
	Node goalNode;

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

		this.guiFactory = new GUIFactory(window);

		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);

		this.algorithm = AlgorithmFactory.createAlgorithm(selectedAlgorithm, startNode, goalNode, false);

		attachEventListeners();
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

	private void attachEventListeners() {
		guiFactory.getStartButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (algorithm.isRunning())
						return;

					algorithm.Run();
					timer.start();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		guiFactory.getSpeedSlider().addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				timer.setDelay(guiFactory.getSpeedValue());
			}
		});

		guiFactory.getDiagonalBox().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				algorithm.changeDiagonalPref(guiFactory.getDiagonalPref());
			}
		});

		guiFactory.getClearButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				algorithm = AlgorithmFactory.createAlgorithm(selectedAlgorithm, startNode, goalNode,
						guiFactory.getDiagonalPref());
				repaint();
			}
		});

		guiFactory.getAlgorithmDropDown().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				selectedAlgorithm = guiFactory.getSelectedAlgorithm();
				algorithm = AlgorithmFactory.createAlgorithm(selectedAlgorithm, startNode, goalNode,
						guiFactory.getDiagonalPref(), algorithm.getBorders());

				// If you have already set a start point
				if (startNode != null)
					setStartPoint(startNode);
			}
		});
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
		try {
			if (algorithm.isRunning())
				this.algorithm.findPath();

			repaint();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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