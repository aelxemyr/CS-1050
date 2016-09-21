import java.util.ArrayList;

/**
 * Write a description of class DiceGame here.
 * 
 * @author Bennett Alex Myers
 * @version 2015.10.30 (1.0)
 */
public class DiceGame
{
    private ArrayList<Dice> dice;
    private Player player;
    private UserInterface UI;
    private static final int INITIAL_STAKE   = 100;
    private static final int STAKE_INCREMENT = 10;
    private static final int NUMBER_OF_DICE  = 2;
    private static final int NUMBER_OF_FACES = 6;

    public DiceGame()
    {
        this.dice = new ArrayList<Dice>();
        this.player = new Player(INITIAL_STAKE);
        for (int i = 1; i <= NUMBER_OF_DICE; i++) {
            this.dice.add(new Dice(NUMBER_OF_FACES));
        }
        this.UI = null;
    }

    public ArrayList<Dice> getDice()
    {
        return this.dice;
    }

    public int roll()
    {
        UI.showMessage("Rolling the dice...");
        int total = 0;
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            total += this.dice.get(i).roll();
        }
        UI.showMessage("You rolled a " + total + " !");
        return total;
    }

    public void play()
    {
        welcome();
        boolean playing = promptNextRound();
        while (playing && player.getStake() >= STAKE_INCREMENT) {
            executeRound();
            if (player.getStake() >= STAKE_INCREMENT) {
                playing = promptNextRound();
            }
        }
    }

    public void welcome()
    {
        UI = new DialogUserInterface("The Dice Game!");
        boolean defaultUI = UI.askYesNoQuestion("Welcome to the Dice Game!\n\n" +
                                                "Would you like to play the game in " +
                                                "dialog mode?\n" +
                                                "Select 'Yes' to continue in dialog mode " +
                                                "or select 'No' to play in console mode.");
        if (!defaultUI) {
            UI = new ConsoleUserInterface("The Dice Game!");
        }
    }

    public boolean promptNextRound()
    {
        return UI.askYesNoQuestion("You have " + this.player.getStake() + " tokens.\n" +
                                   "Playing a round will cost " + STAKE_INCREMENT + " tokens.\n" +
                                   "Would you like to play?");
    }

    public void executeRound()
    {
        player.decreaseStake(STAKE_INCREMENT);
        boolean rolling = true;
        boolean hasPoint = false;
        int point = 0;
        int roll = roll();
        while (rolling) {
            if (!hasPoint) {
                switch (roll) {
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
                        point = roll;
                        hasPoint = true;
                        UI.showMessage("Your point is " + point + ".");
                        break;
                }
            }
            else {
                roll = roll();
                if (roll == 7) {
                    lose();
                    rolling = false;
                }
                if (roll == point) {
                    win();
                    rolling = false;
                }
            }
        }
    }
    
    public void win()
    {
        player.increaseStake(2 * STAKE_INCREMENT);
        UI.showMessage("You won!");
    }
    
    public void lose()
    {
        UI.showMessage("You lost!");
    }
    
    public static void main(String[] args)
    {
        DiceGame diceGame = new DiceGame();
        diceGame.play();
    }
}
