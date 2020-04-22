package buttons;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

import constants.ApplicationConstants;

public class Button extends JButton implements MouseListener {
    private static final long serialVersionUID = 7264149987653918498L;

    public Button(String title, int positionX, int positionY) {
        super();
        setBounds(positionX, positionY, ApplicationConstants.buttonWidth, ApplicationConstants.buttonHeight);
        setFocusable(false);
        setFocusPainted(false);
        setText(title.toUpperCase());
        setBorder(null);
        setForeground(ApplicationConstants.defaultTextColor);
        setBackground(ApplicationConstants.buttonBackgroundColor);
        setFont(ApplicationConstants.defaultTextFont);
        setOpaque(true);
        addMouseListener(this);
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) { 
        if (e.getSource()==this) {  
            this.setBackground(ApplicationConstants.buttonHoverColor); 
        }
    }
    @Override
    public void mouseExited(MouseEvent e) { 
        if (e.getSource()==this) { 
            this.setBackground(ApplicationConstants.buttonBackgroundColor); 
        }
    }
}