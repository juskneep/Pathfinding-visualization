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

public class GUIFactory {

    //Do not initialize here pls :(
    Panel mainControlPane = new Panel(ApplicationConstants.mainControlPaneX, ApplicationConstants.mainControlPaneY);
    Panel algorithmsPane = new Panel(ApplicationConstants.algorithmsPaneX, ApplicationConstants.algorithmsPaneY,
            ApplicationConstants.algorithmPanelWidth, ApplicationConstants.algorithmPanelHeight);

    Button startButton = new Button("Start", 200, 100);
    Button clearButton = new Button("Clear", 25, 100);
    CheckBox dialognals = new CheckBox("Diagonals", 25, 70);
    Slider speedSlider = new Slider(50, 10);
    ComboBox<AlgorithmsEnum> availableAlgorithms = new ComboBox<AlgorithmsEnum>(AlgorithmsEnum.values(), 25, 10);

    public GUIFactory(JFrame frame) {
        initializeButtons();
        initializeControls(frame);
    }

    private void initializeButtons() {
        mainControlPane.addComponents(startButton, clearButton, dialognals, speedSlider);
        algorithmsPane.addComponents(availableAlgorithms);
    }

    private void initializeControls(JFrame frame) {
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
                    frame.add(field);
            }
        }

        frame.repaint();
    }

    public boolean getDiagonalPref() {
        return this.dialognals.isSelected();
    }

    public AlgorithmsEnum getSelectedAlgorithm() {
        return (AlgorithmsEnum) availableAlgorithms.getSelectedItem();
    }

    public int getSpeedValue() {
        return 100 - speedSlider.getValue();
    }

    public ComboBox<AlgorithmsEnum> getAlgorithmDropDown() {
        return availableAlgorithms;
    }

    public Button getStartButton() {
        return startButton;
    }
    
    public Button getClearButton() {
        return clearButton;
    }

    public Slider getSpeedSlider() {
        return speedSlider;
    }

    public CheckBox getDiagonalBox() {
        return dialognals;
    }
}