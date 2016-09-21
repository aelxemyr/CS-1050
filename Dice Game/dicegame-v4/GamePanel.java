import java.awt.*;
import javax.swing.*;

/**
 * Write a description of class GamePanel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GamePanel extends JPanel
{
    private int width;
    private int height;
    
    public GamePanel() {
        width = 540;
        height = 360;
        setBackground(new Color(0, 153, 0));
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(width, height);
    }
}
