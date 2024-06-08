package System;

import Display.*;
import Display.LoginScreen;
import Module.Game.MahjongGame;
import Module.Game.Player;
import Module.Game.Site;
import Message.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class controls the game flow and the game state.
 *
 * @author Jingwang Li
 */
public class GameManager {
    private static MahjongGame game = null;
    private final Screen loginScreen;

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

    }
    public static void updateScreen() {
        game.update();
    }
    public static void addPlayer(Player player) throws Exception{
        game.addPlayer(player);
        if (game.getNumOfPlayers() == 4) {
            startNewGame();
        }
    }
    public static void removePlayer(Player player) {
        game.removePlayer(player);
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
     * This method controls the execution of the game.
     */
    public void run(Stage stage) throws Exception {
        loginScreen.loadWindow(stage);
    }
}
