package Module.Game;

import Module.ImageMap.TileImageMapper;
import Module.Tile.Tile;
import System.*;
import Message.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Represents a Mahjong game.
 *
 * Handles game initialization, player management, and communication between clients and server.
 * Also manages the game board, tile shuffling, and tile dealing.
 * Implements the Game interface.
 *
 * Authors: Jingwang Li, Lanyun Xiao
 */
public class MahjongGame implements Game {
    private static List<Player> players;
    private static GameBoard gameBoard;
    protected static LinkedList<Socket> sockets;
    TileImageMapper tileImageMapper;
    private static ServerSocket serverSocket;
    private int port;
    private boolean isGameStart;
    private static final Object lock = new Object();
    private static boolean isRotating = false;
    private static ScheduledExecutorService scheduler;
    private static ScheduledFuture<?> scheduledFuture;
    static int i = 0;
    static long TASK_INTERVAL_SECONDS = 20;

    /**
     * Constructs a MahjongGame and starts the server.
     *
     * @param port The port number for the server.
     * @throws IOException If an I/O error occurs.
     */
    public MahjongGame(int port) throws IOException {
        this.port = port;
        scheduler = Executors.newScheduledThreadPool(4);
        new Thread(() -> {
            try {
                startServer(port);
            } catch (IOException e) {
                System.out.println("Game Over");
            }
        }).start();
        tileImageMapper = new TileImageMapper();
        players = new LinkedList<>();
    }

    /**
     * Adds a player to the game.
     *
     * @param player The player to be added.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Removes a player from the game.
     *
     * @param player The player to be removed.
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Gets the number of players in the game.
     *
     * @return The number of players.
     */
    public int getNumOfPlayers() {
        return players.size();
    }

    /**
     * Initializes the game, sets player sites, shuffles and deals tiles,
     * determines the dealer, and sets the Hun tile for players.
     */
    @Override
    public void initializeGame() {
        setPlayersSites();
        gameBoard = new GameBoard(players);
        gameBoard.determineDealer();
        gameBoard.shuffleTiles();
        gameBoard.dealAllTiles();
        gameBoard.determineHunTile();
        setHunTileToPlayers();
        isGameStart = true;

        scheduledFuture = scheduler.schedule(() -> {
            scheduledFuture = scheduler.schedule(() -> {
                Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                handleDiscardMessage(discardMessage);
            }, 0, TimeUnit.SECONDS);
        }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);;

    }

    /**
     * Sets the sites for each player.
     */
    public void setPlayersSites() {
        Site[] sites = {Site.East, Site.South, Site.West, Site.North};
        int i = 0;
        for (Player player : players) {
            player.setSite(sites[i]);
            i++;
        }
    }

    /**
     * Sets the Hun tile for each player and sends the Hun tile message to all players.
     */
    public void setHunTileToPlayers() {
        Message message = new HunTileMessage(gameBoard.getHunTile());
        try {
            sendMessageToAll(message);
            for (Player player : players) {
                player.setHunTile(gameBoard.getHunTile());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if any player has won the game.
     *
     * @return The winning player, or null if no player has won.
     */
    @Override
    public Player checkVictory() {
        return null;
    }

    /**
     * Calculates the scores for the players.
     */
    @Override
    public void calculateScores() {
    }

    /**
     * Starts a new game.
     */
    @Override
    public void startNewGame() {

    }

    /**
     * Gets the index of the current player.
     *
     * @return The index of the current player.
     */
    public static int getCurrentPlayerIndex() {
        return players.indexOf(gameBoard.getCurrentActivePlayer());
    }

    /**
     * Checks if the game has started.
     *
     * @return True if the game has started, false otherwise.
     */
    public boolean isGameStart() {
        return isGameStart;
    }

    /**
     * Gets the tile that was least recently discarded.
     *
     * @return The least recently discarded tile.
     */
    public Tile getLeastDiscardedTile() {
        return gameBoard.getLeastDiscardedTile();
    }

    /**
     * Swaps the positions of players and re-deals the tiles.
     */
    @Override
    public void swap() {
        rotate();
    }

    /**
     * Rotates the players and re-deals the tiles.
     */
    public static void rotate() {
        gameBoard.swap();
        gameBoard.dealTiles();
        GameManager.updateScreen();
    }

    /**
     * Checks if the round is over.
     *
     * @return True if the round is over, false otherwise.
     */
    @Override
    public boolean isRoundOver() {
        if (checkVictory() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the current player can discard a tile.
     *
     * @param player The player to check.
     * @return True if the player can discard a tile, false otherwise.
     */
    public boolean playerCanDiscard(Player player) {
        return gameBoard.getCurrentActivePlayer() == player;
    }

    /**
     * Starts the server on the specified port.
     *
     * @param port The port number.
     * @throws IOException If an I/O error occurs.
     */
    private void startServer(int port) throws IOException {
        this.port = port;
        sockets = new LinkedList<>();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for connections.....");
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + port);
            System.exit(1);
        }
        while (true) {
            System.out.println(sockets.size() + " clients connected.");
            Socket clientSocket = serverSocket.accept();
            sockets.add(clientSocket);
            new Thread(new ServerGameThread(clientSocket)).start();
            if (sockets.isEmpty()) {
                System.out.println("No clients connected.");
                break;
            }
        }
        serverSocket.close();
    }

    /**
     * Starts the game by sorting player tiles and sending a game launch message to all players.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void startGame() throws IOException {
        sortPlayersTiles();
        sendGameMessageToAll();
        Message message = new launchGameMessage();
        sendMessageToAll(message);
        for (Player player : players) {
            player.setIsLaunched(true);
        }
    }

    /**
     * Updates the game state.
     */
    public void update() {
        try {
            sortPlayersTiles();
            sendGameMessageToAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sorts the tiles in each player's hand.
     */
    private void sortPlayersTiles() {
        for (Player player : players) {
            player.sort_hand();
        }
    }

    /**
     * Sends the game state to all players.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void sendGameMessageToAll() throws IOException {
        List<PlayerInformation> playerInformation = new ArrayList<>();
        for (Player player : players) {
            playerInformation.add(new PlayerInformation(player));
        }
        int i = 0;
        for (Socket socket : sockets) {
            synchronized (socket) {
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                List<PlayerInformation> playerInformation1 = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    playerInformation1.add(playerInformation.get((i + j) % 4));
                }
                Message message = new GameInformationMessage(playerInformation1, gameBoard.getTilesInTheWall(), getCurrentPlayerIndex(), getLeastDiscardedTile(), i, gameBoard.getDealerIndex());
                oos.writeObject(message);
                oos.flush();
                i++;
            }
        }
    }

    /**
     * Sends a message to all players.
     *
     * @param message The message to send.
     * @throws IOException If an I/O error occurs.
     */
    private void sendMessageToAll(Message message) throws IOException {
        for (Socket socket : sockets) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            oos.flush();
        }
    }

    /**
     * Sends a message to a specific player.
     *
     * @param message The message to send.
     * @param player The player to send the message to.
     */
    private static void sendMessageToSomeOne(Message message, Player player) {
        int index = players.indexOf(player);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(sockets.get(index).getOutputStream());
            oos.writeObject(message);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends a shutdown message to all players.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void sendShutDownMessageToAll() throws IOException {
        Message message = new Message(MessageType.SHUT_DOWN_BUTTONS);
        for (Socket socket : sockets) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            oos.flush();
        }
    }

    /**
     * Handles a discard message.
     *
     * @param mes The discard message.
     */
    public static void handleDiscardMessage(Message mes) {
        GameManager.updateScreen();
        DiscardMessage message = (DiscardMessage) mes;
        List<Tile> tiles = gameBoard.getCurrentActivePlayer().getTile_hand();
        if (tiles.get(message.getIndex()).equals(gameBoard.getHunTile())) {
            return;
        }
        Player player = gameBoard.getCurrentActivePlayer();
        gameBoard.setLeastDiscardedTile(player.discardTiles(message.getIndex()), getCurrentPlayerIndex());

        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }

        GameManager.updateScreen();

        Player playerToKong = playerToKong();
        Player playerToPung = playerToPung();
        Player playerToEat = playerToEat();

        if (playerToKong != null || playerToPung != null) {
            discardWithSomeOneCanPungOrKong(playerToKong, playerToPung, playerToEat);
        } else{
            discardWithSomeOneCanChew(playerToEat);
        }
    }

    /**
     * Handles the scenario when a discarded tile can be used for Pung or Kong.
     *
     * @param playerToKong The player who can perform a Kong.
     * @param playerToPung The player who can perform a Pung.
     * @param playerToEat The player who can perform a Chew.
     */
    private static void discardWithSomeOneCanPungOrKong(Player playerToKong, Player playerToPung, Player playerToEat) {
        if (playerToKong != null) {
            Message message1 = new Message(MessageType.KONG);
            sendMessageToSomeOne(message1, playerToKong);
            try {
                sendShutDownMessageToAll();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (playerToPung != null) {
            Message message2 = new Message(MessageType.PUNG);
            sendMessageToSomeOne(message2, playerToPung);
            try {
                sendShutDownMessageToAll();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        scheduledFuture = scheduler.schedule(() -> {
            discardWithSomeOneCanChew(playerToEat);
        }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * Handles the scenario when a discarded tile can be used for Chew.
     *
     * @param playerToEat The player who can perform a Chew.
     */
    private static void discardWithSomeOneCanChew(Player playerToEat) {
        if (playerToEat() != null) {
            Message message1 = new Message(MessageType.CHEW);
            sendMessageToSomeOne(message1, playerToEat);
            try {
                sendShutDownMessageToAll();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            scheduledFuture = scheduler.schedule(() -> {
                MahjongGame.rotate();
                scheduledFuture = scheduler.schedule(() -> {
                    Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                    handleDiscardMessage(discardMessage);
                }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        } else {
            MahjongGame.rotate();
            scheduledFuture = scheduler.schedule(() -> {
                Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                handleDiscardMessage(discardMessage);
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }
    }

    /**
     * Handles a Chew message.
     *
     * @param mes The Chew message.
     */
    public static void handleChewMessage(Message mes) {
        Chew_Pung_KongMessage message = (Chew_Pung_KongMessage) mes;
        ArrayList<Tile> tiles = (ArrayList<Tile>) players.get(message.getPlayerIndex()).chi(gameBoard.getLeastDiscardedTile());
        players.get(gameBoard.getLeastDiscardedPlayerIndex()).withdrawDiscardTile();

        // 添加玩家的chew_pong_kong_Tiles
        gameBoard.setCurrentActivePlayerIndex(message.getPlayerIndex());
        players.get(message.getPlayerIndex()).addChew_Pong_Kung_Tiles(tiles);
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduler.schedule(() -> {
            //如果玩家吃牌，则他立即打牌
            GameManager.updateScreen();
            scheduledFuture = scheduler.schedule(() -> {
                Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                handleDiscardMessage(discardMessage);
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }, 0, TimeUnit.SECONDS);
    }

    /**
     * Handles a Pung message.
     *
     * @param mes The Pung message.
     */
    public static void handlePungMessage(Message mes) {
        Chew_Pung_KongMessage message = (Chew_Pung_KongMessage) mes;
        ArrayList<Tile> tiles = (ArrayList<Tile>) players.get(message.getPlayerIndex()).pung(gameBoard.getLeastDiscardedTile());
        players.get(gameBoard.getLeastDiscardedPlayerIndex()).withdrawDiscardTile();

        // Add Pung tiles to player's hand
        gameBoard.setCurrentActivePlayerIndex(message.getPlayerIndex());
        players.get(message.getPlayerIndex()).addChew_Pong_Kung_Tiles(tiles);
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduler.schedule(() -> {
            // If a player performs Pung, they immediately discard a tile
            GameManager.updateScreen();
            scheduledFuture = scheduler.schedule(() -> {
                Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                handleDiscardMessage(discardMessage);
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }, 0, TimeUnit.SECONDS);
    }

    /**
     * Handles a Kong message.
     *
     * @param mes The Kong message.
     */
    public static void handleKongMessage(Message mes) {
        Chew_Pung_KongMessage message = (Chew_Pung_KongMessage) mes;
        ArrayList<Tile> tiles = (ArrayList<Tile>) players.get(message.getPlayerIndex()).kong(gameBoard.getLeastDiscardedTile());
        players.get(gameBoard.getLeastDiscardedPlayerIndex()).withdrawDiscardTile();

        //Add Kong tiles to player's hand
        gameBoard.setCurrentActivePlayerIndex(message.getPlayerIndex());
        gameBoard.getCurrentActivePlayer().addChew_Pong_Kung_Tiles(tiles);
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduler.schedule(() -> {
            // If a player performs Kong, they immediately draw and discard a tile
            GameManager.updateScreen();
            scheduledFuture = scheduler.schedule(() -> {
                Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                handleDiscardMessage(discardMessage);
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }, 0, TimeUnit.SECONDS);
    }

    /**
     * Handles a Hu message.
     *
     * @param mes The Hu message.
     * @throws IOException If an I/O error occurs.
     */
    public static void handleHuMessage(Message mes) throws IOException {
        for (Socket socket : sockets) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(mes);
            oos.flush();
        }
        Message message = new Message(MessageType.GAME_OVER);
        for (Socket socket : sockets) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            oos.flush();
        }
        serverSocket.close();
        System.exit(0);
    }

    /**
     * Determines which player can perform Chew.
     *
     * @return The player who can perform Chew, or null if no player can perform Chew.
     */
    public static Player playerToEat(){
        Tile chowTile = gameBoard.getLeastDiscardedTile();
        int index = (players.indexOf(gameBoard.getCurrentActivePlayer()) + 1) % 4;
        if (players.get(index).canchi(chowTile)) {
            return players.get((getCurrentPlayerIndex() + 1) % 4);
        }
        return null;
    }

    /**
     * Determines which player can perform Pung.
     *
     * @return The player who can perform Pung, or null if no player can perform Pung.
     */
    public static Player playerToPung(){
        Tile pungTile = gameBoard.getLeastDiscardedTile();
        for (Player player : players) {
            if(player.canpeng(pungTile) && players.indexOf(player) != getCurrentPlayerIndex()){
                return player;
            }
        }
        return null;
    }

    /**
     * Determines which player can perform Kong.
     *
     * @return The player who can perform Kong, or null if no player can perform Kong.
     */
    public static Player playerToKong(){
        Tile pungTile = gameBoard.getLeastDiscardedTile();
        for (Player player : players) {
            if(player.cangang(pungTile) && players.indexOf(player) != getCurrentPlayerIndex()){
                return player;
            }
        }
        return null;
    }
}
