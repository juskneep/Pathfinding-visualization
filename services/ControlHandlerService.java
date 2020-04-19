package services;

import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
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
    public Algorithm algorithm;
    char currentKey = (char) 0;
    AlgorithmsEnum selectedAlgorithm = AlgorithmsEnum.AStar;
    Timer timer = new Timer(30, this);

    public ControlHandlerService(Frame frame) {
        this.frame = frame;
        guiFactory = new GUIFactory(frame.window);
        
        frame.addMouseListener(this);
		frame.addMouseMotionListener(this);
        frame.addKeyListener(this);

        algorithm = AlgorithmFactory.createAlgorithm(selectedAlgorithm, frame.window, frame.startNode, frame.goalNode);

        controlHandler();
        frame.repaint();
    }

    public void handleMouseClick(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
			int xPosition = Math.round(e.getX() / Frame.size);
			int yPosition = Math.round(e.getY() / Frame.size);

			if (currentKey == 's') {
				Node startNode = new Node(xPosition, yPosition);
				algorithm.addStartPoint(startNode);
				algorithm.addToOpenList(algorithm.startNode);
				frame.setFrameStartPoint(startNode);
			} else if (currentKey == 'e') {
				Node goalNode = new Node(xPosition, yPosition);
				algorithm.goalNode = new Node(xPosition, yPosition);
				frame.setFrameGoalPoint(goalNode);
			} else {
				this.algorithm.addBorder(xPosition, yPosition);
			}
			frame.repaint();
		} else if (SwingUtilities.isRightMouseButton(e)) {
			int xPosition = Math.round(e.getX() / Frame.size);
			int yPosition = Math.round(e.getY() / Frame.size);

			this.algorithm.removeBorder(xPosition, yPosition);
			frame.repaint();
		}
	}

	public void controlHandler() {
		guiFactory.startButton.addActionListener(new ActionListener() {
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

		guiFactory.dialognals.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				algorithm.changeDiagonalPref();
			}			
		});

		guiFactory.clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setFrameStartPoint(null);
				frame.setFrameGoalPoint(null);
				algorithm = AlgorithmFactory.createAlgorithm(selectedAlgorithm, frame.window, frame.startNode, frame.goalNode);
				timer.stop();
				frame.repaint();
			}
		});

		guiFactory.availableAlgorithms.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				AlgorithmsEnum algName = (AlgorithmsEnum) guiFactory.availableAlgorithms.getSelectedItem();
				algorithm = AlgorithmFactory.createAlgorithm(algName, frame.window, frame.startNode, frame.goalNode);
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
			if (algorithm.isRunning()) {
				this.algorithm.findPath();
				frame.repaint();
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