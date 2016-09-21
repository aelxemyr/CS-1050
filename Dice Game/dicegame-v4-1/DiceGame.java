
/**
 * The main class of the dice game.
 * 
 * @author Bennett Alex Myers
 * @version 2015.11.08 (v4.1)
 */
public class DiceGame
{
    private DiceGameEngine engine;
    private DiceGameInterface gui;
    
    /**
     * Create a new instance of the Dice Game.
     */
    public DiceGame() {
        engine = new DiceGameEngine();
        gui = new DiceGameInterface(engine);
    }
    
    /**
     * Main play sequence of the Dice Game.
     */
    public void play() {
        gui.display("Welcome to the Dice Game!");
        gui.display(engine.promptRound());
        gui.betEnabled(true);
        gui.rollEnabled(true);
    }
    
    public static void main(String[] args) {
        DiceGame diceGame = new DiceGame();
        diceGame.play();
    }
}
