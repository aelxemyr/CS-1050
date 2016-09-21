
/**
 * A general user interface specification.
 * 
 * @version Fall 2015 (1)
 */
public interface UserInterface
{
    /**
     * Shows a message to the user.
     * @param message the message to be shown
     */
    void showMessage(String message);
    
    /**
     * Asks a question and prompts for a Yes or No response. 
     * Returns true if user responds Yes; false otherwise.
     * @param question the question to present to the user
     * @return true if and only if the user indicates Yes
     */
    boolean askYesNoQuestion(String question);
    
    /**
     * Prompts then accepts and returns a user-entered string.
     * @param prompt message to show user
     * @return string entered by user
     */
    String requestString(String prompt);
}
