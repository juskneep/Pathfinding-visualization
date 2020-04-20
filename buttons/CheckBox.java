package buttons;

import javax.swing.JCheckBox;

import constants.ApplicationConstants;

public class CheckBox extends JCheckBox {

    public CheckBox(String title, int positionX, int positionY) {
        super(title);
        setOpaque(false);
        setBounds(positionX, positionY, ApplicationConstants.checkBoxWidth, ApplicationConstants.checkBoxHeight);
        setFocusable(false);
    }
}