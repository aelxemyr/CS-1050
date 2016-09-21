import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * A simulation of a game played with two six-sided dice.
 * 
 * @author Bennett Alex Myers
 * @version 2015.11.08 (v4.1)
 */
public class DiceGameInterface
{
    private static final String VERSION = "4.1";
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

    /**
     * Create a new DiceGameInterface
     */
    public DiceGameInterface(DiceGameEngine engine)
    {
        this.engine = engine;
        makeFrame();
    }
    
    // =================================================================================
    
    /**
     * Make the main window for the Dice Game.
     */
    private void makeFrame()
    {
        frame = new JFrame("The Dice Game");
        frame.setResizable(false);
        

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
        diceBox.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        dice1 = new JLabel(new ImageIcon("dice-1.png"));
        dice2 = new JLabel(new ImageIcon("dice-1.png"));
        diceBox.add(dice1);
        diceBox.add(dice2);
        gamePanel.add(diceBox);
        makeStatusBox();
        gamePanel.add(statusBox);
    }
    
    /**
     * Create the player status box.
     */
    private void makeStatusBox() {
        statusBox = new JPanel();
        statusBox.setPreferredSize(new Dimension(10, 10));
        
        statusBox.setBorder(BorderFactory.createCompoundBorder(
                             BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0, 153, 0)),
                             BorderFactory.createMatteBorder(2, 2, 2, 2, Color.YELLOW)));
        statusBox.setBackground(new Color(0, 153, 0));
        JLabel statusTitle = new JLabel("Player Stats");
        statusTitle.setForeground(Color.YELLOW);
        statusBox.add(statusTitle, BorderLayout.NORTH);
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
        betEnabled(false);
        betPane.add(new JLabel("Bet: "));
        betPane.add(betSpinner);
        rollButton = new JButton("     Roll     ");
        rollButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { roll(); }
                           });
        rollEnabled(false);
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
        menu.add(item);
        
        item = new JMenuItem("The Rules");
        menu.add(item);
    }
    
    // =================================================================================
    
    public void display(String text) {
        textBox.append(text + "\n");
        JScrollBar scr = textPane.getVerticalScrollBar();
        int max = scr.getMaximum();
        int ext = scr.getVisibleAmount();
        scr.setValue(max + ext + ext);
    }
    
    private void newGame() {
        textBox.setText("");
        dice1 = new JLabel(new ImageIcon("dice-1.png"));
        dice2 = new JLabel(new ImageIcon("dice-1.png"));
        display("Welcome to the Dice Game!");
        display(engine.promptRound());
    }
    
    private void save() {
        display("Game saved.\n");
        showStatus("Game saved.");
    }
    
    private void open() {
        display("You loaded a saved game.\n");
        showStatus("Game loaded.");
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit() {
        System.exit(0);
    }
    
    /**
     * Display a status message in the frame's status bar.
     * @param text The status message to be displayed.
     */
    private void showStatus(String text) {
        statusLabel.setText(text);
    }
    
    public void roll() {
        if (checkStake()) {
            betSpinner.setEnabled(false);
            display(engine.roll(getBet()));
            dice1.setIcon(engine.getDiceImage(1));
            dice2.setIcon(engine.getDiceImage(2));
            if (!engine.isRolling()) {
                betSpinner.setEnabled(true);
                setBetLimit();
            }
        }
        if (!checkStake()) {
            betSpinner.setEnabled(false);
            rollButton.setEnabled(false);
            display("You no longer have enough tokens to play.\nThank you for playing.");
        }
    }
    
    public boolean checkStake() {
        return engine.getPlayerStake() > 0;
    }
    
    public void betEnabled(boolean status) {
        betSpinner.setEnabled(status);
    }
    
    public void rollEnabled(boolean status) {
        rollButton.setEnabled(status);
    }
    
    public int getBet() {
        return (Integer) betSpinner.getValue();
    }
    
    public void setBetLimit() {
        betSpinner.setModel(new SpinnerNumberModel(10, 0, engine.getPlayerStake(), 1));
    }
}
