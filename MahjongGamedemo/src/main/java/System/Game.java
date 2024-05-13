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
     * This method will be called in the game first round starts to determine the dealer for this round.
     *
     * @return the player object of the dealer for this round.
     */
    public Player determineDealer();

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
     * This method is used to shuffle the deck, usually at the beginning of a turn.
     */
    public void shuffleTiles();

    /**
     * This method is used to start a new game.
     */
    public void startNewGame();

    /**
     * This method is used to check whether the game is over or not.
     *
     * @return true if the game is over, false otherwise.
     */
    public boolean isGameOver();

    /**
     * This method is used to pause the game.
     */
    public void pauseGame();

    /**
     * This method is used to check whether the game is still paused or not.
     *
     * @return true if the game is paused, false otherwise.
     */
    public boolean checkForPause();

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
