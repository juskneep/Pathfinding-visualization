package factories;

import java.lang.reflect.Field;

import javax.swing.JComponent;
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

    public Button startButton = new Button("Start", ApplicationConstants.mainControlPaneX + 200,
            ApplicationConstants.mainControlPaneY + 50);
    public Button clearButton = new Button("Clear", ApplicationConstants.mainControlPaneX + 25,
            ApplicationConstants.mainControlPaneY + 50);
    // Button savePathButton = new Button("Save Path", 0, 0);
    // Button generatePathButton = new Button("Generate Path", 0, 0);
    public CheckBox dialognals = new CheckBox("Diagonals", ApplicationConstants.mainControlPaneX + 25,
            ApplicationConstants.mainControlPaneY + 10);
    Slider speedSlider = new Slider(ApplicationConstants.mainControlPaneX + 150,
            ApplicationConstants.mainControlPaneY + 10);
    public ComboBox<AlgorithmsEnum> availableAlgorithms = new ComboBox<AlgorithmsEnum>(AlgorithmsEnum.values(),
            ApplicationConstants.algorithmsPaneX + 25, ApplicationConstants.algorithmsPaneY + 10);

    public GUIFactory(JFrame frame) throws IllegalArgumentException, IllegalAccessException {
        initializeButtons();
        initializeControls(frame);
    }

    public void initializeButtons() {
        mainControlPane.addComponents(startButton, clearButton, dialognals, speedSlider);
        algorithmsPane.addComponents(availableAlgorithms);
        //pathPane.addComponents(savePathButton, generatePathButton);
    }

    public void initializeControls(JFrame frame) throws IllegalArgumentException, IllegalAccessException {
        for (Field component : this.getClass().getDeclaredFields()) {
            component.setAccessible(true);

            if (JComponent.class.isAssignableFrom(component.getType())) {
                JComponent field = (JComponent) component.get(this);
                frame.add(field);
            }            
        }
        /*
         * initializePanels(); initializeButtons(); initializeSliders();
         * initializeDropDowns();
         */

        frame.repaint();
    }
    /*
     * public void initializePanels() {
     * 
     * pathPane.setBackground(new Color(0, 0, 0, 80)); pathPane.setBounds(pathPaneX,
     * pathPaneY, 300, 100);
     * 
     * algorithmsPane.setBackground(new Color(0, 0, 0, 80));
     * algorithmsPane.setBounds(algorithmsPaneX, algorithmsPaneY, 300, 100);
     * 
     * mainControlPane.setBackground(new Color(0, 0, 0, 80));
     * mainControlPane.setBounds(mainControlPaneX, mainControlPaneY, 300, 100);
     * 
     * frame.add(mainControlPane); frame.add(algorithmsPane); frame.add(pathPane);
     * 
     * frame.repaint(); } public void initializeButtons() { // Main control buttons
     * startButton.setBounds(mainControlPaneX + 200, mainControlPaneY + 50, 75, 25);
     * clearButton.setBounds(mainControlPaneX + 25, mainControlPaneY + 50, 75, 25);
     * dialognals.setBounds(mainControlPaneX + 25, mainControlPaneY + 10, 75, 25);
     * startButton.setFocusable(false); clearButton.setFocusable(false);
     * dialognals.setFocusable(false);
     * 
     * frame.add(startButton); frame.add(clearButton); frame.add(dialognals); }
     * 
     * public void initializeSliders() { speedSlider.setBounds(mainControlPaneX +
     * 150, mainControlPaneY + 10, 75, 25);
     * 
     * frame.add(speedSlider); }
     * 
     * public void initializeDropDowns() {
     * availableAlgorithms.setBounds(algorithmsPaneX + 25, algorithmsPaneY + 10,
     * 250, 25); availableAlgorithms.setFocusable(false);
     * frame.add(availableAlgorithms); }
     */
}