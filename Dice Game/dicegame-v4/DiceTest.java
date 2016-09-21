

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;

/**
 * The test class DiceTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DiceTest
{
    /**
     * Default constructor for test class DiceTest
     */
    public DiceTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void rollTest()
    {
        Dice dice1 = new Dice(6);
        assertTrue(dice1.roll() > 0 && dice1.roll() < 7);
        assertTrue(dice1.roll() > 0 && dice1.roll() < 7);
        assertTrue(dice1.roll() > 0 && dice1.roll() < 7);
        assertTrue(dice1.roll() > 0 && dice1.roll() < 7);
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            int n = r.nextInt(100) + 1;
            Dice dice2 = new Dice(n);
            for (int j = 0; j < 20; j++) {
                assertTrue(dice2.roll() > 0 && dice2.roll() <= n);
            }
        }
    }
}

