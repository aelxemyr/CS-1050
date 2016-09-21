import java.awt.*;
import javax.swing.*;

public class RollButton extends JButton
{
    private boolean pressedStatus;
    
    public RollButton(String label) {
        super(label);
        pressedStatus = false;
    }
    
    public boolean getStatus() {
        return pressedStatus;
    }
    
    public void setStatus(boolean status) {
        pressedStatus = status;
    }
}