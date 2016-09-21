import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Illustrate the layout style of a BorderLayout.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class DiceGameGUI
{
    private JFrame frame;
    private GamePanel gamePanel;
    private JTextArea textBox;
    private JScrollPane textPane;
    private JLabel statusLabel;
    private JSpinner betSpinner;
    private RollButton rollButton;

    /**
     * Constructor for objects of class BorderLayoutExample
     */
    public DiceGameGUI()
    {
        makeFrame();
    }

    /**
     * Place five components in the available regions.
     */
    private void makeFrame()
    {
        frame = new JFrame("The Dice Game");
        frame.setResizable(false);
        gamePanel = new GamePanel();
        
        makeMenuBar();
        
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout(6, 6));
        statusLabel = new JLabel("The Dice Game version " + DiceGame.VERSION);
        contentPane.add(statusLabel, BorderLayout.SOUTH);
        contentPane.add(gamePanel, BorderLayout.WEST);
        contentPane.add(new JLabel(""), BorderLayout.EAST);
        
        makeTextPane();
        Container eastPane = new Container();
        eastPane.setLayout(new BorderLayout(6, 2));
        eastPane.add(textPane, BorderLayout.NORTH);
        Container betPane = new Container();
        Container rollPane = new Container();
        betPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        rollPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        SpinnerModel betModel = new SpinnerNumberModel(10, 0, 100, 1);
        betSpinner = new JSpinner(betModel);
        betEnabled(false);
        betPane.add(new JLabel("Bet: "));
        betPane.add(betSpinner);
        rollButton = new RollButton("     Roll     ");
        rollButton.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { rollButton(); }
                           });
        rollEnabled(false);
        rollPane.add(rollButton);
        eastPane.add(betPane, BorderLayout.CENTER);
        eastPane.add(rollPane, BorderLayout.SOUTH);
        contentPane.add(eastPane, BorderLayout.CENTER);
        
        frame.pack();
        frame.setVisible(true);
    }
    
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
    
    private void makeTextPane() {
        textBox = new JTextArea(20, 30);
        textBox.setLineWrap(true);
        textBox.setEditable(false);
        textPane = new JScrollPane(textBox);
    }
    
    // =============================================================================================
    
    public void display(String text) {
        textBox.append(text + "\n");
    }
    
    private void newGame() {
        display("You started a new game.\n");
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
    
    public void waitForRoll() {
        while (rollButton.getStatus() == false) {
            
        }
        rollButton.setStatus(false);
    }
    
    public void rollButton() {
        rollButton.setStatus(true);
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
    
    public void setBetLimit(int limit) {
        betSpinner.setModel(new SpinnerNumberModel(10, 0, limit, 1));
    }
    
    public boolean getRollStatus() {
        return rollButton.getStatus();
    }
    
    public void toggleRollStatus(boolean status) {
        rollButton.setStatus(status);
    }
}
