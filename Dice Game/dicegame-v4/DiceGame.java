import java.util.ArrayList;
import java.awt.event.*;

/**
 * The main class and driver of a dice game to be played with two six-sided dice.
 * 
 * @author Bennett Alex Myers
 * @version 2015.11.08 (4.0)
 */
public class DiceGame
{
    public static final String VERSION = "4.0";
    private ArrayList<Dice> dice;
    private Player player;
    private DiceGameGUI ui;
    private int initialStake;  // The number of tokens with which the player starts.
    private int costOfPlay;    // The number of tokens it costs to play a round.
    private int numberOfDice;  // The number of dice with which the game is played.
    private int numberOfFaces; // The number of faces of each die.
    private int currentRoll;   // The most recent result of rolling the dice.
    private boolean hasPoint;

    /**
     * The default constructor for an instance of the dice game. i.e. create a dice game played
     * with two six-sided dice where the player starts with 100 tokens.
     */
    public DiceGame()
    {
        this.initialStake = 100;
        this.costOfPlay = 10;
        this.numberOfDice = 2;
        this.numberOfFaces = 6;
        this.currentRoll = 0;
        this.dice = new ArrayList<Dice>();
        this.player = new Player(initialStake);
        this.ui = new DiceGameGUI();
        for (int i = 1; i <= numberOfDice; i++) {
            this.dice.add(new Dice(numberOfFaces));
        }
    }

    /**
     * @return the list of dice in the game
     */
    public ArrayList<Dice> getDice()
    {
        return this.dice;
    }
    
    /**
     * @return the most recent result of rolling the dice.
     */
    public int getCurrentRoll()
    {
        return this.currentRoll;
    }
    
    /**
     * Roll each die in the game once and sum their values.
     */
    public void rollDice()
    {
        int total = 0;
        for (int i = 0; i < numberOfDice; i++) {
            total += this.dice.get(i).roll();
        }
        this.currentRoll = total;
    }
    
    /**
     * Roll the dice and summarize what happens.
     * 
     * @return a string summarizing the results of the roll
     */
    private String rollString()
    {
        rollDice();
        return "Rolling... You rolled a " + currentRoll + "!";
    }

    /**
     * Main play routine; welcome user and initiate play loop.
     */
    public void play()
    {
        ui.display("Welcome to the Dice Game!");
        while (player.getStake() > 0) {
            promptBet();
            getRoll();
            executeRound();
        }    
        ui.display("You no longer have enough tokens to play.");
        ui.display("You retired with " + player.getStake() + " tokens.\n"
                       + "Thank you for playing.");
    }
    
    private void getRoll() {
        ui.waitForRoll();
        costOfPlay = ui.getBet();
        ui.toggleRollStatus(false);
    }
    
    /**
     * Prompt the user to place a bet.
     */
    private void promptBet()
    {
        ui.display("You have " + player.getStake() + " tokens. Place your bet and roll the dice.");
        ui.setBetLimit(player.getStake());
        ui.betEnabled(true);
        ui.rollEnabled(true);
    }
    
    /**
     * Parse an input String as a decimal integer.
     * 
     * @param inString String to be parsed as integer
     * @return the integer value of the input String
     */
    private Integer parseInt(String inString) {
        Integer intValue = 0;
        try {
            intValue = Integer.parseInt(inString);
        } catch (NumberFormatException nfe) {
            return null;
        }
        return intValue;
    }
    
    /**
     * Main round execution sequence. Decrease the user's stake by the cost of play, roll the dice,
     * and interpret the outcome.
     */
    private void executeRound()
    {
        player.decreaseStake(costOfPlay);
        boolean rolling = true;   // Whether or not the player has to make another roll.
        int point = 0;
        ui.display(rollString());
        while (rolling) {
            if (!hasPoint) {
                switch (currentRoll) {
                    case 7:
                    case 11:
                        win();
                        rolling = false;
                        break;
                    case 2:
                    case 3:
                    case 12:
                        lose();
                        rolling = false;
                        break;
                    default:
                        point = currentRoll;
                        hasPoint = true;
                        ui.display("Your point is " + point + ". Roll the dice.");
                        break;
                }
            }
            else {
                getRoll();
                ui.display(rollString());
                if (currentRoll == 7) {
                    lose();
                    rolling = false;
                }
                if (currentRoll == point) {
                    win();
                    rolling = false;
                }
            }
        }
    }
    
    /**
     * Sequence run if a player wins a round. Increase their stake by twice the cost of play
     * and show the transcript of that round through the UI.
     */
    private void win()
    {
        player.increaseStake(2 * costOfPlay);
        ui.display("You win!");
    }
    
    /**
     * Sequence run if a player loses a round. Shows the transcript of that round through the UI.
     */
    private void lose()
    {
        ui.display("You lost!");
    }
    
    /**
     * Main method. Instantiate a diceGame and run its play method.
     */
    public static void main(String[] args)
    {
        DiceGame diceGame = new DiceGame();
        diceGame.play();
    }
}
