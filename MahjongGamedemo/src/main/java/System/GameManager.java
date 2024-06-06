package System;

import Display.*;
import Display.GameScreenDisplay.LoginScreen;
import Module.Game.MahjongGame;
import Module.Game.Player;
import Module.Game.Site;
import Module.Tile.Tile;
import Message.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * This class controls the game flow and the game state.
 *
 * @author Jingwang Li
 */
public class GameManager {
    private static MahjongGame game = null;
    private final Screen loginScreen;
    private static final Site[] sites = {Site.East, Site.South, Site.West, Site.North};

    /**
     * Constructor for the GameManager class.
     * This class should be created only once in the application start class.
     *
     */
    public GameManager() {
        this.loginScreen = new LoginScreen();
    }
    /**
     * This method is called when there are enough players to play a game.
     *
     */
    public static void startNewGame() {
        game.initializeGame();
        startGame();
        // TODO: game应进行定庄、发牌、定混等操作

    }
    public static void updateScreen() {
        game.update();
    }
    public static void addPlayer(Player player) throws Exception{
        int index = game.getNumOfPlayers();
        player.setSite(sites[index]);
        game.addPlayer(player);
        System.out.println(sites[index]);
        if (game.getNumOfPlayers() == 4) {
            startNewGame();
        }
    }
    public static void addGame(MahjongGame mahjongGame) {
        game = mahjongGame;
    }
    private static void startGame() {
        try {
            game.startGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Handles the action for the "Pause" button.
     * This method is triggered when a player wants to pause the game.
     */
    public static void handlePauseButtonAction(Player player) {
        Message message = new Message(MessageType.PAUSE);
        player.sendMessageObjectToHost(message);
    }
    /**
     * Handles the action for the "Continue" button.
     * This method is triggered when a player wants to resume the game after a pause.
     */
    public static void handleContinueButtonAction(Player player) {
        Message message = new Message(MessageType.UNPAUSE);
        player.sendMessageObjectToHost(message);
    }
    /**
     * This method controls the execution of the game.
     */
    public void run(Stage stage) throws Exception {
        loginScreen.loadWindow(stage);
    }
}
