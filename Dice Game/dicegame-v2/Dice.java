import java.util.Random;

/**
 * An n-sided, fair die.
 * 
 * @author Bennett Alex Myers
 * @version 2015.10.30
 */
public class Dice
{
    private int numberOfSides;
    private Random rng;

    /**
     * Create a new Dice object with a given number of sides.
     * 
     * @param numberOfSides the number of sides of this die
     */
    public Dice(int numberOfSides)
    {
        this.numberOfSides = numberOfSides;
        this.rng = new Random();
    }

    /**
     * Roll the die.
     * 
     * @return the result of the roll; an integer between 1 and the number of sides (inclusive)
     */
    public int roll()
    {
        return this.rng.nextInt(numberOfSides) + 1;
    }
}
