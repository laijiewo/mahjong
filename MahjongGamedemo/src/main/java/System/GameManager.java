package System;

import Display.*;
import Module.*;
import javafx.stage.Stage;

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
     * Handles the action for the "Hu" button.
     * This method is triggered when a player wants to declare a win and end the current game round.
     */
    public static void handleHuButtonAction() {

    }
    /**
     * Handles the action for the "Kong" button.
     * This method is triggered when a player wants to perform a Kong, which is claiming four of the same tile.
     */
    public static void handleKongButtonAction() {

    }
    /**
     * Handles the action for the "Pung" button.
     * This method is triggered when a player wants to declare a Pung, which is claiming three of the same tile.
     */
    public static void handlePungButtonAction() {

    }
    /**
     * Handles the action for the "Chow" button.
     * This method is triggered when a player wants to declare a Chow, which is claiming a sequence of three tiles in the same suit.
     */
    public static void handleChowButtonAction() {

    }
    /**
     * Handles the action for the "Discard" button.
     * This method is triggered when a player chooses to discard a tile from their hand.
     */
    public static void handleDiscardButtonAction() {

    }
    /**
     * Handles the action for the "Pause" button.
     * This method is triggered when a player wants to pause the game.
     */
    public static void handlePauseButtonAction() {

    }
    /**
     * Handles the action for the "Continue" button.
     * This method is triggered when a player wants to resume the game after a pause.
     */
    public static void handleContinueButtonAction() {

    }
    /**
     * This method controls the execution of the game.
     */
    public void run(Stage stage) throws Exception {
        loginScreen.loadWindow(stage);
    }

}
