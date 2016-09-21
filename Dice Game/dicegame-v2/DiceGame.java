import java.util.ArrayList;

/**
 * The main class and driver of a dice game to be played with two six-sided dice.
 * 
 * @author Bennett Alex Myers
 * @version 2015.11.05 (v2)
 */
public class DiceGame
{
    private ArrayList<Dice> dice;
    private Player player;
    private UserInterface ui;
    private int initialStake;  // The number of tokens with which the player starts.
    private int costOfPlay;    // The number of tokens it costs to play a round.
    private int numberOfDice;  // The number of dice with which the game is played.
    private int numberOfFaces; // The number of faces of each die.
    private int currentRoll;   // The most recent result of rolling the dice.

    /**
     * The default constructor for an instance of the dice game. i.e. create a dice game played
     * with two six-sided dice where the player starts with 100 tokens and each round costs 10
     * tokens.
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
        this.ui = null;
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
     * @return the user interface currently associated with the game.
     */
    public UserInterface getUI()
    {
        return this.ui;
    }

    /**
     * Roll each die in the game once and sum their values.
     */
    public void roll()
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
        roll();
        return "Rolling... You rolled a " + currentRoll + "!\n";
    }
    
    /**
     * Choose a user interface for the game.
     * 
     * @param defaultUi true to choose dialog UI, false to choose console UI
     */
    public void chooseUserInterface(boolean defaultUI) {   
        if (!defaultUI) {
            this.ui = new ConsoleUserInterface("The Dice Game!");
        }
        else if (defaultUI && this.ui == null) {
            this.ui = new DialogUserInterface("The Dice Game!");
        }
    }

    /**
     * Main play routine; welcome user and initiate play loop.
     */
    public void play()
    {
        chooseUserInterface(welcome());
        boolean playing = promptNextRound();
        while (playing && player.getStake() >= costOfPlay) {
            executeRound();
            if (player.getStake() < costOfPlay) {
                ui.showMessage("You no longer have enough tokens to play.");
            }
            else {
                playing = promptNextRound();
            }
        }
        ui.showMessage("You retired with " + player.getStake() + " tokens.\n"
                       + "Thank you for playing.");
    }

    /**
     * Welcome the user and prompt them to either continue in dialog mode or console mode.
     */
    private boolean welcome()
    {
        this.ui = new DialogUserInterface("The Dice Game!");
        return ui.askYesNoQuestion("Welcome to the Dice Game!\n\n" +
                                   "Would you like to play the game in " +
                                   "dialog mode?\n" +
                                   "Select 'Yes' to continue in dialog mode " +
                                   "or select 'No' to play in console mode.");
    }

    /**
     * Inform the user of their current stake and prompt them to play another round.
     */
    private boolean promptNextRound()
    {
        return ui.askYesNoQuestion("You have " + this.player.getStake() + " tokens.\n" +
                                   "Playing a round will cost " + costOfPlay + " tokens.\n" +
                                   "Would you like to play?");
    }

    /**
     * Main round execution sequence. Decrease the user's stake by the cost of play, roll the dice,
     * and interpret the outcome.
     */
    private void executeRound()
    {
        player.decreaseStake(costOfPlay);
        boolean rolling = true;   // Whether or not the player has to make another roll.
        boolean hasPoint = false; // Whether or not a point has been established.
        int point = 0;
        ui.showMessage("\nComeout roll! Roll the dice.");
        String transcript = rollString();
        while (rolling) {
            if (!hasPoint) {
                switch (currentRoll) {
                    case 7:
                    case 11:
                        win(transcript);
                        rolling = false;
                        break;
                    case 2:
                    case 3:
                    case 12:
                        lose(transcript);
                        rolling = false;
                        break;
                    default:
                        point = currentRoll;
                        hasPoint = true;
                        ui.showMessage(transcript + "Your point is " + point 
                                                  + ". Roll the dice.\n");
                        break;
                }
                transcript = ""; // Clear the transcript
            }
            else {
                transcript += rollString();
                if (currentRoll == 7) {
                    lose(transcript);
                    rolling = false;
                }
                if (currentRoll == point) {
                    win(transcript);
                    rolling = false;
                }
            }
        }
    }
    
    /**
     * Sequence run if a player wins a round. Increase their stake by twice the cost of play
     * and show the transcript of that round through the UI.
     */
    private void win(String transcript)
    {
        player.increaseStake(2 * costOfPlay);
        ui.showMessage(transcript + "You win!\n");
    }
    
    /**
     * Sequence run if a player loses a round. Shows the transcript of that round through the UI.
     */
    private void lose(String transcript)
    {
        ui.showMessage(transcript + "You lost!\n");
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
