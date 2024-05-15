package System;

import Display.*;
import Module.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Stack;

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
     */
    public GameManager(Game game) {
        players = new ArrayList<>();
        this.game = game;
        this.gameScreen = new GameScreen((MahjongGame) game);
        this.loginScreen = new LoginScreen();
        this.menuScreen = new MenuScreen();
        this.scoreScreen = new ScoreScreen();
        this.rulesScreen = new RuleScreen();
    }

    /**
     * This method controls the execution of the game.
     */
    public void run(Stage stage) {
        loginScreen.loadWindow(stage);
    }

}
