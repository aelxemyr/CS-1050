

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PlayerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerTest
{
    private Player player1;

    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
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
        player1 = new Player(100);
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
    public void newPlayerTest()
    {
        assertEquals(100, player1.getStake());
    }

    @Test
    public void increaseStakeTest()
    {
        player1.increaseStake(10);
        assertEquals(110, player1.getStake());
        player1.increaseStake(100);
        assertEquals(210, player1.getStake());
    }

    @Test
    public void decreaseStakeTest()
    {
        player1.decreaseStake(10);
        assertEquals(90, player1.getStake());
        player1.decreaseStake(90);
        assertEquals(0, player1.getStake());
    }
}