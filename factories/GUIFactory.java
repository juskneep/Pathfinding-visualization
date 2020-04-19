package factories;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

import constants.AlgorithmsEnum;

public class GUIFactory {
    private final int mainControlPaneX = 950;
    private final int mainControlPaneY = 25;

    private final int algorithmsPaneX = 950;
    private final int algorithmsPaneY = 525;

    private final int pathPaneX = 25;
    private final int pathPaneY = 525;

    JFrame frame;
    JPanel mainControlPane = new JPanel();
    JPanel algorithmsPane = new JPanel();
    JPanel pathPane = new JPanel();
    public JButton startButton = new JButton("Start");
    public JButton clearButton = new JButton("Clear");
    JButton savePathButton = new JButton("Save Path");
    JButton generatePathButton = new JButton("Generate Random Path");
    public JCheckBox dialognals = new JCheckBox("Diagonals");
    JSlider speedSlider = new JSlider();
    public JComboBox<AlgorithmsEnum> availableAlgorithms = new JComboBox(AlgorithmsEnum.values());
    // JComboBox savedPaths;

    public GUIFactory(JFrame frame) {
        this.frame = frame;
        initializeControls();
    }

    public void initializeControls() {
        initializePanels();
        initializeButtons();
        initializeSliders();
        initializeDropDowns();

        frame.repaint();
    }

    public void initializePanels() {
        pathPane.setBackground(new Color(0, 0, 0, 80));
        pathPane.setBounds(pathPaneX, pathPaneY, 300, 100);

        algorithmsPane.setBackground(new Color(0, 0, 0, 80));
        algorithmsPane.setBounds(algorithmsPaneX, algorithmsPaneY, 300, 100);
        
        mainControlPane.setBackground(new Color(0, 0, 0, 80));
        mainControlPane.setBounds(mainControlPaneX, mainControlPaneY, 300, 100);


        frame.add(mainControlPane);
        frame.add(algorithmsPane);
        frame.add(pathPane);
        
        frame.repaint();
    }

    public void initializeButtons() {
        //Main control buttons 
        startButton.setBounds(mainControlPaneX + 200, mainControlPaneY + 50, 75, 25);
        clearButton.setBounds(mainControlPaneX + 25, mainControlPaneY + 50, 75, 25);
        dialognals.setBounds(mainControlPaneX + 25, mainControlPaneY + 10, 75, 25);
        startButton.setFocusable(false);
        clearButton.setFocusable(false);
        dialognals.setFocusable(false);

        frame.add(startButton);
        frame.add(clearButton);
        frame.add(dialognals);
    }

    public void initializeSliders() {        
        speedSlider.setBounds(mainControlPaneX + 150, mainControlPaneY + 10, 75, 25);

        frame.add(speedSlider);
    }

    public void initializeDropDowns() {
        availableAlgorithms.setBounds(algorithmsPaneX + 25, algorithmsPaneY + 10, 250, 25);
        availableAlgorithms.setFocusable(false);
        frame.add(availableAlgorithms);
    }

}