package buttons;

import javax.swing.JComboBox;

import constants.ApplicationConstants;

public class ComboBox<T> extends JComboBox<T> {

    public ComboBox(T[] values, int positionX, int positionY) {
        super(values);
        setBounds(positionX, positionY, ApplicationConstants.comboBoxWidth, ApplicationConstants.comboBoxHeight);
        setFocusable(false);
    }
}