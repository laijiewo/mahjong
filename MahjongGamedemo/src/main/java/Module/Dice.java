package Module;

import java.util.Random;

/**
 * This class represents a six-sided dice that can be tossed to get a random outcome.
 */
public class Dice {
    private int pips; // Number of pips facing up after tossing the dice
    private Random rand; // Random number generator used for tossing the dice

    /**
     * Constructs a new Dice object with a random number generator initialized.
     */
    public Dice(){
        rand = new Random();
    }

    /**
     * Tosses the dice and returns the result.
     *
     * @return The number of pips facing up after tossing the dice.
     */
    public int toss(){
        pips = randomPip();
        return pips;
    }

    /**
     * Generates a random number representing the pips on the dice face (1 to 6).
     *
     * @return A random integer representing the pips on the dice face.
     */
    public int randomPip(){
        return rand.nextInt(6)+1;
    }
}

