package buttons;

import javax.swing.JComponent;
import javax.swing.JPanel;

import constants.ApplicationConstants;

import java.awt.Color;

public class Panel extends JPanel {
    private static final long serialVersionUID = -4084081114196804162L;
    
    private Color color = new Color(0, 0, 0, 80);

    public Panel(int positionX, int positionY) {
        super();
        setBounds(positionX, positionY, ApplicationConstants.panelWidth, ApplicationConstants.panelHeight);
        setBackgroundColor(color);
        setFocusable(false);
    }

    public Panel(int positionX, int positionY, int panelWidth, int panelHeight) {
        this(positionX, positionY);
        setBounds(positionX, positionY, panelWidth, panelHeight);
    }

    public void setBackgroundColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public void addComponents(JComponent... args) {
        for (JComponent comp : args) {
            add(comp);
        }
    }
}