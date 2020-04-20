package buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.border.Border;

import constants.ApplicationConstants;

public class Button extends JButton implements MouseListener {
    Font defaultFont = new Font("Gill Sans MT", Font.BOLD, 14);
    Color textColor = Color.decode("#ffffff");
    Color backgroundColor = Color.decode("#000000");
    Color hoverColor = Color.decode("#00aced");

    public Button(String title, int positionX, int positionY) {
        super(title);
        setBounds(positionX, positionY, ApplicationConstants.buttonWidth, ApplicationConstants.buttonHeight);
        setFocusable(false);
        this.setFocusPainted(false);
        this.setText(title.toUpperCase());
        this.setBorder(null);
        this.setForeground(textColor);
        this.setBackground(backgroundColor);
        this.setFont(defaultFont);
        this.setOpaque(true);
        addMouseListener(this);
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mousePressed(MouseEvent e) {}
    
    @Override
    public void mouseEntered(MouseEvent e) { 
        if (e.getSource()==this) {  
            this.setBackground(this.hoverColor); 
        }
    }
    @Override
    public void mouseExited(MouseEvent e) { 
        if (e.getSource()==this) { 
            this.setBackground(this.backgroundColor); 
        }
    }
}