package buttons;

import java.awt.Color;

import javax.swing.JSlider;

import constants.ApplicationConstants;

public class Slider extends JSlider {

    public Slider(int positionX, int positionY) {
        super();
        setBounds(positionX, positionY, ApplicationConstants.sliderWidth, ApplicationConstants.sliderHeight);
        setFocusable(false);
        setPaintTrack(true);
        setPaintTicks(true);
        setPaintLabels(true);
        setMajorTickSpacing(50);
        setMinorTickSpacing(5);
    }
}