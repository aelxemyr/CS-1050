import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/** A dialog-based user interface. */
public class DialogUserInterface implements UserInterface {
    /** Contents of the title bar. */
    private String titleBarText = "";
    
    /** Icon for dialog. */
    private ImageIcon icon = new ImageIcon();
    
    /**
     * Constructs user interface with specified title bar text.
     * @param title text to be displayed in the title bar
     */
    public DialogUserInterface(final String title) {
        this.titleBarText = title;
    }
    
    /**
     * Show message requiring user only to acknowledge.
     * @param message the message to be shown
     */
    @Override
    public void showMessage(final String message) {
        JOptionPane.showMessageDialog(
            null,
            message,
            this.titleBarText,
            JOptionPane.PLAIN_MESSAGE);
    }
    
    /**
     * Ask a question and prompt with Yes and No buttons.
     * Return <code>true</code> if user selects Yes; <code>false</code> otherwise.
     * @param question the question to present to the user
     * @return <code>true</code> if and only if the user selects Yes.
     */
    @Override
    public boolean askYesNoQuestion(final String question) {
        int response = JOptionPane.showConfirmDialog(
                           null,
                           question,
                           this.titleBarText,
                           JOptionPane.YES_NO_OPTION,
                           JOptionPane.QUESTION_MESSAGE,
                           this.icon
                           );
        switch (response) {
            case  0: return true;  // Chose Yes
            case  1: return false; // Chose No
            case -1: return false; // Cancelled
            default: return false; // Error
        }
    }
    
    /**
     * Prompt then accept and return a user-entered string.
     * @param prompt message to show user
     * @return string entered by user
     */
    @Override
    public String requestString(final String prompt) {
        return JOptionPane.showInputDialog(
                    null,
                    prompt,
                    this.titleBarText,
                    JOptionPane.PLAIN_MESSAGE);
    }
}
