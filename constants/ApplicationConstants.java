package constants;

import java.awt.Color;
import java.awt.Font;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ApplicationConstants {
    public static final int sliderWidth = 250;
    public static final int sliderHeight = 50;

    public static final int comboBoxWidth = 250;
    public static final int comboBoxHeight = 25;

    public static final int panelWidth = 350;
    public static final int panelHeight = 150;

    public static final int algorithmPanelWidth = 300;
    public static final int algorithmPanelHeight = 50;

    public static final int checkBoxWidth = 100;
    public static final int checkBoxHeight = 25;

    public static final int buttonWidth = 125;
    public static final int buttonHeight = 25;

    public static final int mainControlPaneX = 900;
    public static final int mainControlPaneY = 25;

    public static final int algorithmsPaneX = 950;
    public static final int algorithmsPaneY = 575;

    public static final int rowSize = 51;
	public static final int colSize = 26;
	public static int size = 25;

    public static final Font defaultTextFont = new Font("Gill Sans MT", Font.BOLD, 14);
    public static final Color defaultTextColor = Color.decode("#ffffff");
    public static final Color buttonBackgroundColor = Color.decode("#000000");
    public static final Color buttonHoverColor = Color.decode("#00aced");

    public static final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public static final Color openListColor = new Color(175, 238, 238);
    public static final Color closedListColor = new Color(144, 238, 144);
    public static final Color startNodeColor = new Color(0, 221, 0);
    public static final Color endNodeColor = new Color(238, 68, 0);
    public static final Color pathToGoalColor = new Color(255, 254, 106);
    public static final Color borderColor = new Color(128, 128, 128);
}