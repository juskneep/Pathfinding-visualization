package buttons;

import javax.swing.JCheckBox;

import constants.ApplicationConstants;

public class CheckBox extends JCheckBox {
    private static final long serialVersionUID = 2534689359380598327L;

    public CheckBox(String title, int positionX, int positionY) {
        super(title);
        setBounds(positionX, positionY, ApplicationConstants.checkBoxWidth, ApplicationConstants.checkBoxHeight);
        setFocusable(false);
    }
}