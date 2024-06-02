package System;

import Module.*;
import WebConnect.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class controls the game flow and the game state.
 *
 * @author Jingwang Li
 */
public class GameManager {
    private static MahjongGame game = null;

    /**
     * Constructor for the GameManager class.
     * This class should be created only once in the application start class.
     *
     */
    public GameManager() {
    }
    /**
     * This method is called when there are enough players to play a game.
     *
     */
    public static void startNewGame() {
            game.initializeGame();
            // TODO: game应进行定庄、发牌、定混等操作
    }

    public static void addPlayer(Player player) {
        game.addPlayer(player);
    }
    /**
     * Handles the action for the "Hu" button.
     * This method is triggered when a player wants to declare a win and end the current game round.
     */
    public static void handleHuButtonAction(Player player) {
        Tile discardedTile = game.getLeastDiscardedTile();
        if (player.canHu(discardedTile)) {
            Message message = new Message(MessageType.HU);
            player.sendMessageObjectToHost(message);
        }
    }
    public static void addGame(MahjongGame mahjongGame) {
        game = mahjongGame;
    }
    /**
     * Handles the action for the "Kong" button.
     * This method is triggered when a player wants to perform a Kong, which is claiming four of the same tile.
     */
    public static void handleKongButtonAction(Player player) {
        Tile discardedTile = game.getLeastDiscardedTile();
        List<Tile> tiles = player.kong(discardedTile);
        if (!tiles.isEmpty()) {
            Message message = new Message(MessageType.KONG, tiles);
            player.sendMessageObjectToHost(message);
        }
    }
    /**
     * Handles the action for the "Pung" button.
     * This method is triggered when a player wants to declare a Pung, which is claiming three of the same tile.
     */
    public static void handlePungButtonAction(Player player) {
        Player currentPlayer = game.getCurrentPlayer();
        if (!currentPlayer.equals(player)) {
            return;
        }

        Tile discardedTile = game.getLeastDiscardedTile();
        List<Tile> tiles = player.pung(discardedTile);
        if (!tiles.isEmpty()) {
            Message message = new Message(MessageType.PUNG, tiles);
            player.sendMessageObjectToHost(message);
        }
    }
    /**
     * Handles the action for the "Chow" button.
     * This method is triggered when a player wants to declare a Chow, which is claiming a sequence of three tiles in the same suit.
     */
    public static void handleChewButtonAction(Player player) {
        /*
         * TODO: Implement Chow action.
         *      1. 用户点击"Chow"按钮，应用该函数
         *      2. 首先判断是否能胡？
         *      3. 如果能胡，根据牌的信息建立Message Object
         *      4. 调用client中sentMessageObject 函数，向主机发送信息
         */
        Tile discardedTile = game.getLeastDiscardedTile();
        List<Tile> tiles = player.chi(discardedTile);
        if (!tiles.isEmpty()) {
            Message message = new Message(MessageType.CHEW, tiles);
            player.sendMessageObjectToHost(message);
        }
    }
    /**
     * Handles the action for the "Discard" button.
     * This method is triggered when a player chooses to discard a tile from their hand.
     */
    public static void handleDiscardButtonAction(int tileIndex, Player player) {
        Tile discardedTile = player.discardTiles(tileIndex);
        Message message = new Message(MessageType.DISCARD, discardedTile);
        player.sendMessageObjectToHost(message);
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

    }

}
