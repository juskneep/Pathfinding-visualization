package buttons;

import javax.swing.JButton;

import constants.ApplicationConstants;

public class Button extends JButton {

    public Button(String title, int positionX, int positionY) {
        super(title);
        setBounds(positionX, positionY, ApplicationConstants.buttonWidth, ApplicationConstants.buttonHeight);
        setFocusable(false);
    }
}