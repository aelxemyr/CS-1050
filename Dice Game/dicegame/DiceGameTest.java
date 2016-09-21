

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class DiceGameTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DiceGameTest
{
    private DiceGame diceGame1;

    /**
     * Default constructor for test class DiceGameTest
     */
    public DiceGameTest()
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
        diceGame1 = new DiceGame();
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
    public void numberOfDiceTest()
    {
        assertEquals(diceGame1.getDice().size(), 2);
    }

    @Test
    public void rollTest()
    {
        for (int i = 0; i < 20; i++) {
            assertTrue(diceGame1.roll() >= 2 && diceGame1.roll() <= 12);
        }
    }
}


