
/**
 * A player in the Dice Game.
 * 
 * @author Bennett Alex Myers
 * @version 2015.10.30
 */
public class Player
{
    private int stake;
    
    /**
     * Create a new player with an initial stake value.
     * 
     * @param stake
     */
    public Player(int stake)
    {
        this.stake = stake;
    }
    
    /**
     * Return the player's current stake.
     */
    public int getStake()
    {
        return this.stake;
    }
    
    /**
     * Increase the player's stake by a given amount.
     * 
     * @param the amount by which to increase the stake
     */
    public void increaseStake(int amount)
    {
        this.stake += amount;
    }
    
    /**
     * Decrease the player's stake by a given amount.
     * 
     * @param the amount by which to decrease the stake
     */
    public void decreaseStake(int amount)
    {
        this.stake -= amount;
    }
}
