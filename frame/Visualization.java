package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.event.MouseInputListener;

import algorithms.Algorithm;
import algorithms.Node;
import constants.AlgorithmsEnum;
import factories.AlgorithmFactory;
import factories.GUIFactory;

public class Visualization extends JPanel implements ActionListener, KeyListener, MouseInputListener {
	public static final int rowSize = 51;
	public static final int colSize = 26;
	static int size = 25;

	JFrame window;
	char currentKey = (char) 0;
	Timer timer = new Timer(30, this);
	AlgorithmsEnum selectedAlgorithm = AlgorithmsEnum.AStar;
	Node startNode;
	Node goalNode;

	Algorithm pathfindAlg;
	GUIFactory guiFactory;

	public Visualization() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		setFocusable(true);
		setLayout(null);
		repaint();

		window = new JFrame();
		window.setContentPane(this);
		window.setTitle("Pathfinding Visualization");
		window.getContentPane().setPreferredSize(new Dimension(1920, 1080));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		guiFactory = new GUIFactory(window);
		pathfindAlg = AlgorithmFactory.createAlgorithm(selectedAlgorithm, window, startNode, goalNode);
		controlHandler();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					new Visualization();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
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
		g.setColor(Color.black);
		for (Node node : pathfindAlg.getBorders()) {
			g.fillRect(node.getX() * size, node.getY() * size, size, size);
		}

		// Draws the start node
		if (pathfindAlg.startNode != null) {
			g.setColor(Color.green);
			g.fillRect(pathfindAlg.startNode.getX() * size, pathfindAlg.startNode.getY() * size, size, size);
		}

		// Draws the goal node
		if (pathfindAlg.goalNode != null) {
			g.setColor(Color.red);
			g.fillRect(pathfindAlg.goalNode.getX() * size, pathfindAlg.goalNode.getY() * size, size, size);
		}

		g.setColor(new Color(175, 238, 238)); // Light blue
		for (Node node : this.pathfindAlg.openList) {
			if (node.getX() == pathfindAlg.startNode.getX() && node.getY() == pathfindAlg.startNode.getY())
				continue;

			g.fillRect(node.getX() * size, node.getY() * size, size - 1, size - 1);
		}

		g.setColor(Color.yellow); // Light yellow
		for (Node node : this.pathfindAlg.pathToGoal) {
			if (node.getX() == pathfindAlg.startNode.getX() && node.getY() == pathfindAlg.startNode.getY())
				continue;
			g.fillRect(node.getX() * size, node.getY() * size, size - 1, size - 1);
		}
	}

	public void handleMouseClick(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			int xPosition = Math.round(e.getX() / size);
			int yPosition = Math.round(e.getY() / size);

			if (currentKey == 's') {
				Node startNode = new Node(xPosition, yPosition);
				pathfindAlg.addStartPoint(startNode);
				pathfindAlg.addToOpenList(pathfindAlg.startNode);
				this.startNode = startNode;
			} else if (currentKey == 'e') {
				Node goalNode = new Node(xPosition, yPosition);
				pathfindAlg.goalNode = new Node(xPosition, yPosition);
				this.goalNode = goalNode;
			} else {
				this.pathfindAlg.addBorder(xPosition, yPosition);
			}
			repaint();
		} else if (SwingUtilities.isRightMouseButton(e)) {
			int xPosition = Math.round(e.getX() / size);
			int yPosition = Math.round(e.getY() / size);

			this.pathfindAlg.removeBorder(xPosition, yPosition);
			repaint();
		}
	}

	public void controlHandler() {
		guiFactory.startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!!pathfindAlg.isRunning())
						return;
						
					pathfindAlg.Run();
					timer.start();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		guiFactory.dialognals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pathfindAlg.changeDiagonalPref();
			}			
		});

		guiFactory.clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startNode = null;
				goalNode = null;
				pathfindAlg = AlgorithmFactory.createAlgorithm(selectedAlgorithm, window, startNode, goalNode);
				timer.stop();
				repaint();
			}
		});

		guiFactory.availableAlgorithms.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				AlgorithmsEnum algName = (AlgorithmsEnum) guiFactory.availableAlgorithms.getSelectedItem();
				pathfindAlg = AlgorithmFactory.createAlgorithm(algName, window, startNode, goalNode);
				selectedAlgorithm = algName;
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
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
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.handleMouseClick(e);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (pathfindAlg.isRunning()) {
				this.pathfindAlg.findPath();
				repaint();
			}
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
		// TODO Auto-generated method stub

	}
}