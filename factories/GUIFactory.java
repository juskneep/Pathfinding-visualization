package factories;

import java.lang.reflect.Field;
import javax.swing.JFrame;

import buttons.Button;
import buttons.CheckBox;
import buttons.ComboBox;
import buttons.Panel;
import buttons.Slider;
import constants.AlgorithmsEnum;
import constants.ApplicationConstants;
import frame.Frame;

public class GUIFactory {

    private JFrame frame;

    private Panel mainControlPane, algorithmsPane;

    private Button startButton, clearButton;
    private CheckBox dialognals;
    private Slider speedSlider;
    private ComboBox<AlgorithmsEnum> availableAlgorithms;

    public GUIFactory(Frame rootPanel, String title) {
        this.frame = new JFrame();
		this.frame.setContentPane(rootPanel);
        this.frame.setTitle(title);

        showFrame();
        initializeButtons();
        initializeControls();
    }

    private void showFrame() {
		this.frame.setPreferredSize(ApplicationConstants.dimension);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    private void initializeButtons() {
        this.mainControlPane = new Panel(ApplicationConstants.mainControlPaneX, ApplicationConstants.mainControlPaneY);
        this.algorithmsPane = new Panel(ApplicationConstants.algorithmsPaneX, ApplicationConstants.algorithmsPaneY,
                ApplicationConstants.algorithmPanelWidth, ApplicationConstants.algorithmPanelHeight);
    
        this.startButton = new Button("Start", 200, 100);
        this.clearButton = new Button("Clear", 25, 100);
        this.dialognals = new CheckBox("Diagonals", 25, 70);
        this.speedSlider = new Slider(50, 10);
        this.availableAlgorithms = new ComboBox<AlgorithmsEnum>(AlgorithmsEnum.values(), 25, 10);
        
        this.mainControlPane.addComponents(this.startButton, this.clearButton, this.dialognals, this.speedSlider);
        this.algorithmsPane.addComponents(this.availableAlgorithms);
    }

    private void initializeControls() {
        for (Field component : this.getClass().getDeclaredFields()) {
            component.setAccessible(true);

            if (Panel.class.isAssignableFrom(component.getType())) {
                Panel field = null;

                try {
                    field = (Panel) component.get(this);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (field != null)
                    this.frame.add(field);
            }
        }

        this.frame.repaint();
    }

    public boolean getDiagonalPref() {
        return this.dialognals.isSelected();
    }

    public AlgorithmsEnum getSelectedAlgorithm() {
        return (AlgorithmsEnum) availableAlgorithms.getSelectedItem();
    }

    public int getSpeedValue() {
        return 100 - this.speedSlider.getValue();
    }

    public ComboBox<AlgorithmsEnum> getAlgorithmDropDown() {
        return this.availableAlgorithms;
    }

    public Button getStartButton() {
        return this.startButton;
    }
    
    public Button getClearButton() {
        return this.clearButton;
    }

    public Slider getSpeedSlider() {
        return this.speedSlider;
    }

    public CheckBox getDiagonalBox() {
        return this.dialognals;
    }
}