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

    Panel mainControlPane = new Panel(ApplicationConstants.mainControlPaneX, ApplicationConstants.mainControlPaneY);
    Panel algorithmsPane = new Panel(ApplicationConstants.algorithmsPaneX, ApplicationConstants.algorithmsPaneY);
    Panel pathPane = new Panel(ApplicationConstants.pathPaneX, ApplicationConstants.pathPaneY);

    public Button startButton = new Button("Start", 200, 50);
    public Button clearButton = new Button("Clear", 25, 50);
    Button savePathButton = new Button("Save Path", 25, 25);
    Button generatePathButton = new Button("Generate Path", 150, 50);
    public CheckBox dialognals = new CheckBox("Diagonals", 25, 10);
    Slider speedSlider = new Slider(150, 10);
    public ComboBox<AlgorithmsEnum> availableAlgorithms = new ComboBox<AlgorithmsEnum>(AlgorithmsEnum.values(), 25, 10);

    public GUIFactory(JFrame frame) throws IllegalArgumentException, IllegalAccessException {
        initializeButtons();
        initializeControls(frame);
    }

    public void initializeButtons() {
        mainControlPane.addComponents(startButton, clearButton, dialognals, speedSlider);
        algorithmsPane.addComponents(availableAlgorithms);
        pathPane.addComponents(savePathButton, generatePathButton);
    }

    public void initializeControls(JFrame frame) throws IllegalArgumentException, IllegalAccessException {
        for (Field component : this.getClass().getDeclaredFields()) {
            component.setAccessible(true);

            if (Panel.class.isAssignableFrom(component.getType())) {
                Panel field = (Panel) component.get(this);
                frame.add(field);
            }
        }

        frame.repaint();
    }
}