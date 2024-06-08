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
 * @author Jingwang Li, Lanyun Xiao
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

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void removePlayer(Player player) {
        players.remove(player);
    }
    public int getNumOfPlayers() {
        return players.size();
    }

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
    public void setPlayersSites() {
        Site[] sites = {Site.East, Site.South, Site.West, Site.North};
        int i = 0;
        for (Player player : players) {
            player.setSite(sites[i]);
            i++;
        }
    }
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

    @Override
    public Player checkVictory() {
        return null;
    }

    @Override
    public void calculateScores() {
    }

    @Override
    public void startNewGame() {

    }
    public static int getCurrentPlayerIndex() {
        return players.indexOf(gameBoard.getCurrentActivePlayer());
    }

    public boolean isGameStart() {
        return isGameStart;
    }

    public Tile getLeastDiscardedTile() {
        return gameBoard.getLeastDiscardedTile();
    }

    @Override
    public void swap() {
        rotate();
    }

    public static void rotate() {
        gameBoard.swap();
        gameBoard.dealTiles();
        GameManager.updateScreen();
    }

    @Override
    public boolean isRoundOver() {
        if (checkVictory() != null) {
            return true;
        } else {
            return false;
        }
    }
    public boolean playerCanDiscard(Player player) {
        return gameBoard.getCurrentActivePlayer() == player;
    }
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

    public void startGame() throws IOException {
        sortPlayersTiles();
        sendGameMessageToAll();
        Message message = new launchGameMessage();
        sendMessageToAll(message);
        for (Player player : players) {
            player.setIsLaunched(true);
        }
    }
    public void update() {
        try {
            sortPlayersTiles();
            sendGameMessageToAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void sortPlayersTiles() {
        for (Player player : players) {
            player.sort_hand();
        }
    }
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
    private void sendMessageToAll(Message message) throws IOException {
        for (Socket socket : sockets) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            oos.flush();
        }
    }
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
    private static void sendShutDownMessageToAll() throws IOException {
        Message message = new Message(MessageType.SHUT_DOWN_BUTTONS);
        for (Socket socket : sockets) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            oos.flush();
        }
    }
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

    private static void discardWithSomeOneCanPungOrKong(Player playerToKong, Player playerToPung, Player playerToEat) {
        System.out.println("能碰杠");
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

    public static void handlePungMessage(Message mes) {
        Chew_Pung_KongMessage message = (Chew_Pung_KongMessage) mes;
        ArrayList<Tile> tiles = (ArrayList<Tile>) players.get(message.getPlayerIndex()).pung(gameBoard.getLeastDiscardedTile());
        System.out.println(gameBoard.getLeastDiscardedTile().getSuit() + "    ");
        players.get(gameBoard.getLeastDiscardedPlayerIndex()).withdrawDiscardTile();

        // 添加玩家的pung_Tiles
        gameBoard.setCurrentActivePlayerIndex(message.getPlayerIndex());
        players.get(message.getPlayerIndex()).addChew_Pong_Kung_Tiles(tiles);
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduler.schedule(() -> {
            //玩家碰牌，则他立刻打牌
            GameManager.updateScreen();
            scheduledFuture = scheduler.schedule(() -> {
                Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                handleDiscardMessage(discardMessage);
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }, 0, TimeUnit.SECONDS);
    }

    public static void handleKongMessage(Message mes) {
        Chew_Pung_KongMessage message = (Chew_Pung_KongMessage) mes;
        ArrayList<Tile> tiles = (ArrayList<Tile>) players.get(message.getPlayerIndex()).kong(gameBoard.getLeastDiscardedTile());
        players.get(gameBoard.getLeastDiscardedPlayerIndex()).withdrawDiscardTile();

        // 添加玩家的kong_Tiles
        gameBoard.setCurrentActivePlayerIndex(message.getPlayerIndex());
        gameBoard.getCurrentActivePlayer().addChew_Pong_Kung_Tiles(tiles);
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduler.schedule(() -> {
            //玩家杠牌，则他立刻抓牌打牌
            GameManager.updateScreen();
            scheduledFuture = scheduler.schedule(() -> {
                Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                handleDiscardMessage(discardMessage);
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }, 0, TimeUnit.SECONDS);
    }

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

    public static Player playerToEat(){
        Tile chowTile = gameBoard.getLeastDiscardedTile();
        int index = (players.indexOf(gameBoard.getCurrentActivePlayer()) + 1) % 4;
        if (players.get(index).canchi(chowTile)) {
            return players.get((getCurrentPlayerIndex() + 1) % 4);
        }
        return null;
    }

    public static Player playerToPung(){
        Tile pungTile = gameBoard.getLeastDiscardedTile();
        for (Player player : players) {
            if(player.canpeng(pungTile) && players.indexOf(player) != getCurrentPlayerIndex()){
                return player;
            }
        }
        return null;
    }

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
