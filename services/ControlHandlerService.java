package services;

import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;
import javax.swing.Timer;

import algorithms.Algorithm;
import algorithms.Node;
import constants.AlgorithmsEnum;
import factories.AlgorithmFactory;
import factories.GUIFactory;
import frame.Frame;

public class ControlHandlerService implements ActionListener, KeyListener, MouseInputListener {
	Frame frame;
	GUIFactory guiFactory;
	Algorithm algorithm;
	char currentKey = (char) 0;
	AlgorithmsEnum selectedAlgorithm = AlgorithmsEnum.AStar;
	Timer timer = new Timer(50, this);

	public ControlHandlerService(Frame frame) {
		this.frame = frame;
		guiFactory = new GUIFactory(frame.getFrame());

		frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
		frame.addKeyListener(this);

		algorithm = AlgorithmFactory.createAlgorithm(selectedAlgorithm, frame.getFrameStartPoint(), frame.getFrameGoalPoint(), guiFactory.getDiagonalPref());

		attachEventListeners();
	}

	private void handleMouseClick(MouseEvent e) {
		int xPosition = Math.round(e.getX() / Frame.size);
		int yPosition = Math.round(e.getY() / Frame.size);

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
			frame.repaint();
		}
	}

	private void attachEventListeners() {
		guiFactory.getStartButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!!algorithm.isRunning())
						return;

					algorithm.Run();
					timer.start();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		guiFactory.getSpeSlider().addChangeListener(new ChangeListener() {
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
				algorithm = AlgorithmFactory.createAlgorithm(selectedAlgorithm, frame.getFrameStartPoint(), frame.getFrameGoalPoint(), guiFactory.getDiagonalPref(), algorithm.getBorders());
				frame.clearFrame();
			}
		});

		guiFactory.getAlgorithmDropDown().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				selectedAlgorithm = guiFactory.getSelectedAlgorithm();
				algorithm = AlgorithmFactory.createAlgorithm(selectedAlgorithm, frame.getFrameStartPoint(), frame.getFrameGoalPoint(), guiFactory.getDiagonalPref(), frame.getBorderCollection());

				//If you have already set a start point
				if(frame.getFrameStartPoint() != null) setStartPoint(frame.getFrameStartPoint());
			}
		});
	}

	private void setGoalPoint(Node goalNode) {
		algorithm.addEndPoint(goalNode);
		frame.setFrameGoalPoint(goalNode);
	}

	private void setStartPoint(Node startNode) {
		algorithm.addStartPoint(startNode);
		frame.setFrameStartPoint(startNode);
	}

	private void addBorder(Node node) {
		frame.addBorder(node);
		algorithm.addBorder(node);
	}

	private void removeBorder(int xPosition, int yPosition) {
		frame.removeBorder(xPosition, yPosition);
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
			if (algorithm.isRunning()) {
				this.algorithm.findPath();
				this.frame.synchronizeOpenList(algorithm.getOpenList());
			} else {
				this.frame.synchronizePathToGoal(algorithm.getPathToGoalCollection());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.handleMouseClick(e);

	}

	@Override public void mouseMoved(MouseEvent e) {}
	@Override public void mousePressed(MouseEvent e) {}
	@Override public void mouseReleased(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
	@Override public void keyTyped(KeyEvent e) {}
}