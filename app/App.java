package app;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import constants.AlgorithmsEnum;
import factories.GUIFactory;
import frame.Frame;

public class App {
    public static final AlgorithmsEnum defaultAlgorithm = AlgorithmsEnum.AStar;
    public static final String title = "Pathfinding Visualization";

    private Frame frame;
    private GUIFactory guiFactory;

    public App() {
        this.frame = new Frame(defaultAlgorithm);
        this.guiFactory = new GUIFactory(this.frame, title);

        attachEventListeners();
    }

    private void attachEventListeners() {
		guiFactory.getStartButton().addActionListener(this.Run());
		guiFactory.getSpeedSlider().addChangeListener(this.changeDelay());
		guiFactory.getDiagonalBox().addActionListener(this.changeDiagonalPref());
		guiFactory.getClearButton().addActionListener(this.clear());
		guiFactory.getAlgorithmDropDown().addActionListener(this.switchAlgorithm());
	}

    private ActionListener Run() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    frame.start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }

    private ChangeListener changeDelay() {
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                frame.setDelay(guiFactory.getSpeedValue());
            }
        };
    }

    private ActionListener changeDiagonalPref() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.changeDiagonalPref(guiFactory.getDiagonalPref());
            }
        };
    }

    private ActionListener clear() {
        return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                frame.createAlgorithm(guiFactory.getSelectedAlgorithm(), guiFactory.getDiagonalPref());
				frame.stop();
            }
        };
    }

    private ActionListener switchAlgorithm() {
        return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                frame.stop();
                frame.changeAlgorithm(guiFactory.getSelectedAlgorithm(), guiFactory.getDiagonalPref());
			}
		};
    }
}