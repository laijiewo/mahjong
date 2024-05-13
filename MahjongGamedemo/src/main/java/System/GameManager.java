package System;

import Display.*;
import Module.*;

import java.util.ArrayList;

/**
 * This class controls the game flow and the game state.
 *
 * @author Jingwang Li
 */
public class GameManager {
    enum Game_state {Waiting, InProgress, Paused, Ended}

    private final ArrayList<Player> players;
    private Player dealer;
    private final Game game;
    private final Screen gameScreen;
    private final Screen loginScreen;
    private final Screen menuScreen;
    private final Screen scoreScreen;
    private Screen rulesScreen;

    /**
     * Constructor for the GameManager class.
     * This class should be created only once in the application start class.
     *
     * @param game        The instance of the MahjongGame class.
     * @param gameScreen  The instance of the Screen class for the game.
     * @param loginScreen The instance of the Screen class for the login screen.
     * @param menuScreen  The instance of the Screen class for the menu screen.
     * @param scoreScreen The instance of the Screen class for the score screen.
     * @param rulesScreen The instance of the Screen class for the rules screen.
     */
    public GameManager(Game game, Screen gameScreen, Screen loginScreen, Screen menuScreen, Screen scoreScreen, Screen rulesScreen) {
        players = new ArrayList<>();
        this.game = game;
        this.gameScreen = gameScreen;
        this.loginScreen = loginScreen;
        this.menuScreen = menuScreen;
        this.scoreScreen = scoreScreen;
        this.rulesScreen = rulesScreen;
    }

    public void checkVictory() {
    }

    public void calculateScores() {
    }

    public void pauseGame() {
    }

    public void resumeGame() {
    }
}
