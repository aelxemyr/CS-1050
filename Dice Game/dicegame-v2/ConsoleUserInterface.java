import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/** A console-based user interface. */
public class ConsoleUserInterface implements UserInterface
{
    /** The title. */
    String titleText = "";
    
    /** Output stream. */
    private static final PrintStream stdout = System.out;
    
    /** Input stream. */
    private static final BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    
    /** Yes/No prompt. */
    private static final String YES_NO_PROMPT = "Yes or No: ";
    
    /** String entry prompt. */
    private static final String STRING_PROMPT = ": ";
    
    /**
     * Constructs user interface with specified title text.
     * @param title text to be displayed upon construction
     */
    public ConsoleUserInterface(final String title) {
        this.titleText = title;
        showMessage("\n" + this.titleText + "\n");
    }
    
    /**
     * Shows a message to the user.
     * @param message the message to be shown
     */
    @Override
    public void showMessage(String message) {
        stdout.println(message);
    }
    
    /**
     * Asks a question and prompts for a Yes or No response.
     * Returns <code>true</code> if user responds Yes; <code>false</code> otherwise.
     * @param question the question to present to the user
     * @return <code>true</code> if and only if the user response begins with "Y" or "y"
     */
    @Override
    public boolean askYesNoQuestion(String question) {
        String reply;
        boolean affirmativeResponse = false;
        stdout.print(question + "\n" + YES_NO_PROMPT);
        try {
            reply = stdin.readLine();
        } catch (java.io.IOException ioe) {
            System.err.println(ioe);
            reply = null;
        }
        if (reply != null) {
            if (reply.startsWith("N") || reply.startsWith("n")) {
                affirmativeResponse = false;
            } else if (reply.startsWith("Y") || reply.startsWith("y")) {
                affirmativeResponse = true;
            }
        }
        return affirmativeResponse;
    }
    
    /**
     * Prompts then accepts and returns a user-entered string.
     * @param prompt message to show user
     * @return string entered by user
     */
    @Override
    public String requestString(String prompt) {
        String reply;
        System.out.print(prompt + STRING_PROMPT);
        try {
            reply = stdin.readLine();
        } catch (java.io.IOException ioe) {
            System.err.println(ioe);
            reply = null;
        }
        return reply;
    }
}
