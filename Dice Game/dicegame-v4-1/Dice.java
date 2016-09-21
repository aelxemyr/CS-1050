import java.util.Random;
import javax.swing.ImageIcon;

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
    private ImageIcon diceImage;

    /**
     * Create a new Dice object with a given number of sides.
     * 
     * @param numberOfSides the number of sides of this die
     */
    public Dice(int numberOfSides)
    {
        this.numberOfSides = numberOfSides;
        this.rng = new Random();
        this.diceImage = new ImageIcon("dice-1.png");
    }

    /**
     * Roll the die.
     * 
     * @return the result of the roll; an integer between 1 and the number of sides (inclusive)
     */
    public int roll()
    {
        int value = this.rng.nextInt(numberOfSides) + 1;
        diceImage = new ImageIcon("dice-" + value + ".png");
        return value;
    }
    
    /**
     * @return the ImageIcon associated with the last dice roll.
     */
    public ImageIcon getDiceImage() {
        return diceImage;
    }
}
