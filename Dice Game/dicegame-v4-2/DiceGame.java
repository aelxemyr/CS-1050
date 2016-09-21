import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

/**
 * A simulation of a game played with two six-sided dice.
 * 
 * @author Bennett Alex Myers
 * @version 2015.11.09 (v4.2)
 */
public class DiceGame
{
    private static final String VERSION = "4.2";
    private static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
    private DiceGameEngine engine;
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel statusBox;
    private Container rightPanel;
    private JTextArea textBox;
    private JScrollPane textPane;
    private JLabel statusLabel;
    private JSpinner betSpinner;
    private JButton rollButton;
    private JLabel dice1;
    private JLabel dice2;
    private JLabel playerStakeLabel;

    /**
     * Create a new DiceGameInterface
     */
    public DiceGame()
    {
        this.engine = new DiceGameEngine();
        makeFrame();
    }
    
    // =================================================================================
    
    /**
     * Make the main window for the Dice Game.
     */
    private void makeFrame()
    {
        frame = new JFrame("The Dice Game");
        frame.setResizable(false );
        frame.setIconImage(new ImageIcon("One-Dice.png").getImage());

        makeMenuBar();
        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout(6, 1));
        statusLabel = new JLabel("The Dice Game version " + VERSION);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        makeGamePanel();
        contentPane.add(gamePanel, BorderLayout.WEST);
        makeRightPanel();
        contentPane.add(rightPanel, BorderLayout.CENTER);
        contentPane.add(new JLabel(""), BorderLayout.EAST);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * Create the game panel of the main window
     */
    private void makeGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(540, 360));
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));
        gamePanel.setBackground(new Color(0, 153, 0));
        Container topFiller = new Container();
        gamePanel.add(topFiller);
        Container diceBox = new Container();
        diceBox.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 100));
        dice1 = new JLabel(new ImageIcon("dice-1.png"));
        dice2 = new JLabel(new ImageIcon("dice-1.png"));
        diceBox.add(dice1);
        diceBox.add(dice2);
        diceBox.setPreferredSize(new Dimension(500, 300));
        gamePanel.add(diceBox);
        makeStatusBox();
        gamePanel.add(statusBox);
    }
    
    /**
     * Create the player status box.
     */
    private void makeStatusBox() {
        statusBox = new JPanel();
        statusBox.setPreferredSize(new Dimension(10, 80));
        statusBox.setLayout(new GridLayout(3, 3));
        TitledBorder statusBorder = BorderFactory.createTitledBorder(
                             BorderFactory.createCompoundBorder(
                             BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0, 153, 0)),
                             BorderFactory.createMatteBorder(2, 2, 2, 2, Color.YELLOW)),
                             "Player Stats");
        statusBorder.setTitleJustification(TitledBorder.CENTER);
        statusBorder.setTitleColor(Color.YELLOW);
        statusBox.setBorder(statusBorder);
        statusBox.setBackground(new Color(0, 153, 0));
        playerStakeLabel = new JLabel("Tokens: " + engine.getPlayerStake());
        playerStakeLabel.setForeground(Color.YELLOW);
        statusBox.add(playerStakeLabel);
    }
    
    /**
     * Create the right panel of the main window: create the textbox, bet spinner, 
     * and roll button.
     */
    private void makeRightPanel() {
        rightPanel = new Container();
        rightPanel.setLayout(new BorderLayout(6, 2));
        makeTextPane();
        rightPanel.add(textPane, BorderLayout.NORTH);
        Container betPane = new Container();
        Container rollPane = new Container();
        betPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rollPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        SpinnerModel betModel = new SpinnerNumberModel(10, 0, 100, 1);
        betSpinner = new JSpinner(betModel);
        betSpinner.setEnabled(true);
        betPane.add(new JLabel("Bet: "));
        betPane.add(betSpinner);
        rollButton = new JButton("     Roll     ");
        rollButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { roll(); }
                           });
        rollButton.setEnabled(true);
        rollPane.add(rollButton);
        rightPanel.add(betPane, BorderLayout.CENTER);
        rightPanel.add(rollPane, BorderLayout.SOUTH);
    }
    
    /**
     * Create a scrollable textbox.
     */
    private void makeTextPane() {
        textBox = new JTextArea(20, 30);
        textBox.setLineWrap(true);
        textBox.setEditable(false);
        textPane = new JScrollPane(textBox);
    }
    
    /**
     * Create the menu bar and set up event handling.
     */
    private void makeMenuBar() {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        menu = new JMenu("File");
        menubar.add(menu);
        
        item = new JMenuItem("New Game");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { newGame(); }
                           });
        menu.add(item);
        
        item = new JMenuItem("Save");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { save(); }
                           });
        menu.add(item);
        
        item = new JMenuItem("Open");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { open(); }
                           });
        menu.add(item);
        
        menu.addSeparator();
        
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { quit(); }
                           });
        menu.add(item);
        
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { about(); }
                           });
        menu.add(item);
        
        item = new JMenuItem("The Rules");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { rulebook(); }
                           });
        menu.add(item);
    }
    
    // =================================================================================
    
    /**
     * Display a string to the textbox and ensure that the window is scrolled down.
     */
    public void display(String text) {
        textBox.append(text + "\n");
        textBox.setCaretPosition(textBox.getDocument().getLength());
    }
    
    /**
     * New game function: create a new instance of the dice game engine and start a new game.
     */
    private void newGame() {
        engine = new DiceGameEngine();
        textBox.setText("");
        showStatus("The Dice Game version " + VERSION);
        dice1.setIcon(engine.getDiceImage(1));
        dice2.setIcon(engine.getDiceImage(2));
        playerStakeLabel.setText("Tokens: " + engine.getPlayerStake());
        rollButton.setEnabled(true);
        betSpinner.setEnabled(true);
        betSpinner.setModel(new SpinnerNumberModel(10, 0, engine.getPlayerStake(), 1));
        display("Welcome to the Dice Game!");
        display(engine.promptRound());
    }
    
    /**
     * Save relevant state information to a text file.
     */
    private void save() {
        display("TO DO: implement save().");
        showStatus("The Dice Game version " + VERSION + " - Game saved.");
    }
    
    /**
     * Retrieve state information from a text file and run from that state.
     */
    private void open() {
        display("TO DO: implement open().");
        showStatus("The Dice Game version " + VERSION + " - Game loaded.");
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit() {
        System.exit(0);
    }
    
    /**
     * About function: show application information.
     */
    private void about() {
        JOptionPane.showMessageDialog(frame, 
                    "The Dice Game version " + VERSION + "\n" +
                    "Author: Bennett Alex Myers",
                    "About", 
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("One-Dice.png"));
    }
    
    /**
     * Rulebook function: show the rules of the game.
     */
    private void rulebook() {
        JOptionPane.showMessageDialog(frame, 
                    readText("rules.txt"),
                    "The Rules", 
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon("One-Dice.png"));
    }
    
    /**
     * Reads text from a file.
     * 
     * @param fileName name of text file to be read
     * @return a string of the text from the file
     */
    private String readText(String fileName) {
        String text = "";
        try {
            text = new TextReader().readFile(fileName);
        } catch (IOException ioe) {
            return null;
        }
        return text;
    }
    
    /**
     * Display a status message in the frame's status bar.
     * @param text the status message to be displayed.
     */
    private void showStatus(String text) {
        statusLabel.setText(text);
    }
    
    /**
     * Action performed when the roll button is clicked. Disable the bet spinner and tell
     * the engine to roll the dice and interpret the results.
     */
    public void roll() {
        if (checkStake()) {
            betSpinner.setEnabled(false);
            display(engine.roll(getBet()));
            dice1.setIcon(engine.getDiceImage(1));
            dice2.setIcon(engine.getDiceImage(2));
            playerStakeLabel.setText("Tokens: " + engine.getPlayerStake());
            if (!engine.isRolling()) {
                betSpinner.setEnabled(true);
                setBetLimit();
            }
        }
        if (!checkStake()) {
            betSpinner.setEnabled(false);
            rollButton.setEnabled(false);        }
    }
    
    /**
     * Check whether or not to allow the player to roll. If the player has no tokens, return false,
     * unless a point has been established.
     * @return true if the player has enough tokens or a point has been established, false otherwise
     */
    public boolean checkStake() {
        return engine.getPlayerStake() > 0 || engine.hasPoint();
    }
    
    /**
     * @return the current value of the bet spinner
     */
    public int getBet() {
        return (Integer) betSpinner.getValue();
    }
    
    /**
     * Set the maximum number on the bet spinner, based on the player's current stake.
     */
    public void setBetLimit() {
        try { 
            betSpinner.setModel(new SpinnerNumberModel(getBet(), 0, engine.getPlayerStake(), 1));
        } catch (IllegalArgumentException iae) {
            betSpinner.setValue(engine.getPlayerStake());
            betSpinner.setModel(new SpinnerNumberModel(engine.getPlayerStake(), 
                                                       0, engine.getPlayerStake(), 1));
        }
    }
    
    /**
     * Main method. Create an instance of DiceGame and create a new game.
     */
    public static void main(String[] args) {
        DiceGame diceGame = new DiceGame();
        diceGame.newGame();
    }
}
