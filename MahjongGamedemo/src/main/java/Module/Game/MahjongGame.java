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
                throw new RuntimeException(e);
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
    public static int getTileCountInTheTileWall() {
        return gameBoard.getTileCountInTheWall();
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

    public List<Player> getPlayers() {
        return players;
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
//      System.out.println("Before rotate: " + getCurrentPlayerIndex() + " " + gameBoard.getCurrentActivePlayer().getPlayerSite() + " " + gameBoard.getCurrentActivePlayer().getTile_hand().size());
        gameBoard.swap();
        gameBoard.dealTiles();
        GameManager.updateScreen();
//      System.out.println("After rotate: " + getCurrentPlayerIndex() + " " + gameBoard.getCurrentActivePlayer().getPlayerSite() + " " + gameBoard.getCurrentActivePlayer().getTile_hand().size());
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

    public String getHostIPAddress() {
        return serverSocket.getInetAddress().getHostAddress();
    }

    public int getPort() {
        return port;
    }

//    public static void sendMessageToAll() {
//        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
//            String message;
//            while ((message = stdIn.readLine()) != null) {
//                for (Socket socket : sockets) {
//                    try {
//                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//                        out.println(message);
//                        out.flush();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void startGame() throws IOException {
        sortPlayersTiles();
        sendGameMessageToAll();
        Message message = new launchGameMessage();
        sendMessageToAll(message);
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
                Message message = new GameInformationMessage(playerInformation1, gameBoard.getTilesInTheWall(), getCurrentPlayerIndex(), getLeastDiscardedTile(), i);
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

    public static void handleDiscardMessage(Message mes) {
        GameManager.updateScreen();

        DiscardMessage message = (DiscardMessage) mes;
        List<Tile> tiles = gameBoard.getCurrentActivePlayer().getTile_hand();
        System.out.println("Player           1231234 " + gameBoard.getCurrentActivePlayer().getPlayerSite() + " discarded tile " + tiles.get(message.getIndex()));
        if (tiles.get(message.getIndex()).equals(gameBoard.getHunTile())) {
            System.out.println("You can't discard the hun tile.");
            return;
        }
        System.out.println("Player " + gameBoard.getCurrentActivePlayer().getPlayerSite() + " discarded tile " + tiles.get(message.getIndex()));
        gameBoard.setLeastDiscardedTile(gameBoard.getCurrentActivePlayer().discardTiles(message.getIndex()));

        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }

        GameManager.updateScreen();
        if (playerToKong() != null || playerToPung() != null) {
//            scheduledFuture = scheduler.schedule(() -> {
//            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
            System.out.println("能碰杠");
            scheduledFuture = scheduler.schedule(() -> {
                if (playerToEat() != null) {
                    System.out.println("没碰杠但能吃");
                    scheduledFuture = scheduler.schedule(() -> {
                        System.out.println("没碰杠也没吃");
                        //摸牌
                        MahjongGame.rotate();
                        scheduledFuture = scheduler.schedule(() -> {
                            Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                            handleDiscardMessage(discardMessage);
                        }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
                    }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
                } else {
                    System.out.println("没碰杠也不能吃");
                    //摸牌
                    MahjongGame.rotate();
                    scheduledFuture = scheduler.schedule(() -> {
                        //下一个玩家等待20秒，如果没有打牌则自动打出
                        Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                        handleDiscardMessage(discardMessage);
                    }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
                }
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);

        } else{
            System.out.println("不能碰杠");
            if (playerToEat() != null) {
                System.out.println("不能碰杠但能吃");
                scheduledFuture = scheduler.schedule(() -> {
                    System.out.println("不能碰杠但能吃没吃");
                    //摸牌
                    MahjongGame.rotate();
                    scheduledFuture = scheduler.schedule(() -> {
                        Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                        handleDiscardMessage(discardMessage);
                    }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
                }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
            } else {
                System.out.println("不能碰杠也不能吃");
                //摸牌
                MahjongGame.rotate();
                scheduledFuture = scheduler.schedule(() -> {
                    //下一个玩家等待20秒，如果没有打牌则自动打出
                    Message discardMessage = new DiscardMessage(gameBoard.getCurrentActivePlayer().getTile_hand().size() - 1);
                    handleDiscardMessage(discardMessage);
                }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
            }
        }
    }

    public static void handleChewMessage(Message mes) {
        Chew_Pung_KongMessage message = (Chew_Pung_KongMessage) mes;
        ArrayList<Tile> tiles = (ArrayList<Tile>) players.get(message.getPlayerIndex()).chi(gameBoard.getLeastDiscardedTile());

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

    public static Player playerToEat(){
        Tile chowTile = gameBoard.getLeastDiscardedTile();
        int index = (players.indexOf(gameBoard.getCurrentActivePlayer()) + 1) % 4;
        if (players.get(index).canchi(chowTile)) {
            return gameBoard.getCurrentActivePlayer();
        }
        return null;
    }

    public static Player playerToPung(){
        Tile pungTile = gameBoard.getLeastDiscardedTile();
        for (Player player : players) {
            if(player.canpeng(pungTile)){
                return player;
            }
        }
        return null;
    }

    public static Player playerToKong(){
        Tile pungTile = gameBoard.getLeastDiscardedTile();
        for (Player player : players) {
            if(player.cangang(pungTile)){
                return player;
            }
        }
        return null;
    }
}
