package buttons;

import javax.swing.JComponent;
import javax.swing.JPanel;

import constants.ApplicationConstants;

import java.awt.Color;

public class Panel extends JPanel {
    private Color color = new Color(0, 0, 0, 80);

    public Panel(int positionX, int positionY) {
        super();
        setBackgroundColor(color);
        setBounds(positionX, positionY, ApplicationConstants.panelWidth, ApplicationConstants.panelHeight);
        setFocusable(false);
    }

    public Panel(int positionX, int positionY, int panelWidth, int panelHeight) {
        this(positionX, positionY);
        setBounds(positionX, positionY, panelWidth, panelHeight);
    }

    public Panel(int positionX, int positionY, Color color) {
        this(positionX, positionY);
        setBackgroundColor(color);
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