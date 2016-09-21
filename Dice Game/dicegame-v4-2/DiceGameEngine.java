import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * The engine of the dice game.
 * 
 * @author Bennett Alex Myers
 * @version 2015.11.09 (v4.2)
 */
public class DiceGameEngine
{
    private ArrayList<Dice> dice;
    private Player player;
    private int numberOfDice;
    private int numberOfFaces;
    private int initialStake;
    private int costOfPlay;
    private int currentRoll;
    private boolean hasPoint;
    private int point;
    private boolean isRolling;
    
    /**
     * The default constructor for an instance of the dice game. i.e. create a dice game 
     * played with two six-sided dice where the player starts with 100 tokens.
     */
    public DiceGameEngine() {
        this.dice = new ArrayList<Dice>();
        this.initialStake = 100;
        this.costOfPlay = 10;
        this.player = new Player(this.initialStake);
        this.numberOfDice = 2;
        this.numberOfFaces = 6;
        for (int i = 1; i <= numberOfDice; i++) {
            this.dice.add(new Dice(numberOfFaces));
        }
        this.currentRoll = 0;
        this.hasPoint = false;
        this.point = 0;
        this.isRolling = true;
    }
    
    /**
     * Roll the dice and update currentRoll.
     */
    public String rollDice() {
        int total = 0;
        for (int i = 0; i < numberOfDice; i++) {
            total += this.dice.get(i).roll();
        }
        this.currentRoll = total;
        return "Rolling... You rolled a " + currentRoll + "!";
    }
    
    /**
     * Get the image of the nth die.
     *
     * @param n the number of the die
     * @return the ImageIcon associated with the nth die
     */
    public ImageIcon getDiceImage(int n) {
        return dice.get(n - 1).getDiceImage();
    }
    
    /**
     * Prompt the user to place a bet and roll the dice.
     */
    public String promptRound() {
        if (player.getStake() > 0) {
            return "You have " + player.getStake() + " tokens. " +
                   "Place your bet and roll the dice.";
        }
        else {
            return "You no longer have enough tokens to play.\nThank you for playing.";
        }
    }
    
    /**
     * Roll the dice and interpret the results.
     */
    public String roll(int bet) {
        costOfPlay = bet;
        String transcript = rollDice();
        isRolling = true;
        if (!hasPoint) {
            player.decreaseStake(costOfPlay);
            switch (currentRoll) {
                case 7:
                case 11:
                    this.isRolling = false;
                    return win(transcript);
                case 2:
                case 3:
                case 12:
                    this.isRolling = false;
                    return lose(transcript);
                default:
                    this.point = currentRoll;
                    this.hasPoint = true;
                    return transcript + "\nYour point is " + point + ". Roll the dice.";
            }
        }
        if (currentRoll == 7) {
            this.isRolling = false;
            this.hasPoint = false;
            return lose(transcript);
        }
        if (currentRoll == point) {
            this.isRolling = false;
            this.hasPoint = false;
            return win(transcript);
        }
        return transcript;
    }
    
    /**
     * @return the string to be displayed if the player wins the round
     */
    public String win(String transcript) {
        player.increaseStake(2 * costOfPlay);
        return transcript + " You win!\n" + promptRound();
    }
    
    /**
     * @return the string to be displayed if the player wins the round
     */
    public String lose(String transcript) {
        return transcript + " You lose!\n" + promptRound();
    }
    
    /**
     * @return whether or not the player needs to roll the dice
     */
    public boolean isRolling() {
        return this.isRolling;
    }
    
    /**
     * @return whether or not a point has been established.
     */
    public boolean hasPoint() {
        return this.hasPoint;
    }
    
    /**
     * @return the player's current stake
     */
    public int getPlayerStake() {
        return player.getStake();
    }
}