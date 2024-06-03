package System;

import Module.*;
/**
 * This interface defines the methods that a game object should implement.
 * This interface should be implemented by the MahjongGame class.
 *
 * @author Jingwang Li, Jie Mao
 */
public interface Game {

    /**
     * This method is used to initialize the game and is usually called when the game is just starting or restarting the game.
     */
    public void initializeGame();

    /**
     * This method is used to detect whether a player has won.
     *
     * @return the player object of the winner if there is a winner, null otherwise.
     */
    public Player checkVictory();

    /**
     * This method is used to calculate the scores of all players.
     */
    public void calculateScores();

    /**
     * This method is used to start a new game.
     */
    public void startNewGame();

    /**
     *This method is used to rotate dealers and is used at the beginning of each round.
     */
    public void swap();

    /**
     * This method is used to check whether the current round is over or not.
     *
     * @return true if the current round is over, false otherwise.
     */
    public boolean isRoundOver();

}
