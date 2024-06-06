package Module.Game;

import Module.ImageMap.TileImageMapper;
import Module.Tile.Tile;
import System.*;
import Message.Message;
import Message.MessageType;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;



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
    static long scheduledTime = 0;
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

    public int getNumOfPlayers() {
        return players.size();
    }
    public static int getTileCountInTheTileWall() {
        return gameBoard.getTileCountInTheWall();
    }

    @Override
    public void initializeGame() {
        gameBoard = new GameBoard();
        gameBoard.determineDealer();
        gameBoard.shuffleTiles();
        gameBoard.dealAllTiles();
        gameBoard.determineHunTile();
        setHunTileToPlayers();
        isGameStart = true;

        scheduledFuture = scheduler.schedule(() -> {
            scheduledTime=System.currentTimeMillis();
            scheduledFuture = scheduler.schedule(() -> {
                GameManager.handleDiscardButtonAction(13,gameBoard.getCurrentActivePlayer());
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);;

    }

    public static void setHunTileToPlayers() {
        for (Player player : players) {
            player.setHunTile(gameBoard.getHunTile());
        }
    }

    public static int getRemainingTime() {
        if (scheduledFuture == null || scheduledFuture.isDone() || scheduledFuture.isCancelled()) {
            return 0;
        }
        long elapsedTime = (System.currentTimeMillis() - scheduledTime) / 1000;
        return (int) (TASK_INTERVAL_SECONDS - elapsedTime);
    }

    public static List<Player> getPlayers() {
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

    public static void sendMessageToAll() {
        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            String message;
            while ((message = stdIn.readLine()) != null) {
                for (Socket socket : sockets) {
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println(message);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void sendOperationMessageToAll(Message message) throws IOException {
        // TODO: 向所有玩家发送操作信息
        for (Socket socket : sockets) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            oos.flush();
        }
    }

    public static void handleDiscardMessage(Message message) {
        // TODO: 发牌应在操作前
        List<Tile> tiles = gameBoard.getCurrentActivePlayer().getTile_hand();
        if (tiles.get(message.getIndex()).equals(GameBoard.getHunTile())) {
            System.out.println("You can't discard the hun tile.");
            return;
        }
        GameManager.updateScreen();
        gameBoard.setLeastDiscardedTile(gameBoard.getCurrentActivePlayer().discardTiles(message.getIndex()));

        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }
        scheduledTime=System.currentTimeMillis();
        if(playerToEat()!=null || playerToPung()!=null){
            //如果有玩家能吃或者能碰能杠
            scheduledFuture = scheduler.schedule(() -> {
                //延迟十秒等待，如果没人吃碰杠，那就切换下一个玩家并且抓牌
                gameBoard.swap();
                gameBoard.dealTiles();
                GameManager.updateScreen();
                scheduledTime=System.currentTimeMillis();
                scheduledFuture = scheduler.schedule(() -> {
                    //等待100秒，如果没打牌则自动打出
                    gameBoard.dealTiles();
                    GameManager.handleDiscardButtonAction(13,gameBoard.getCurrentActivePlayer());
                }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }else {
            //如果没人能吃碰杠
            scheduledFuture = scheduler.schedule(() -> {
                //下一个玩家立即抓牌
                gameBoard.swap();
                gameBoard.dealTiles();
                GameManager.updateScreen();
                scheduledTime=System.currentTimeMillis();
                scheduledFuture = scheduler.schedule(() -> {
                    //下一个玩家等待100秒，如果没有打牌则自动打出
                    GameManager.handleDiscardButtonAction(13, gameBoard.getCurrentActivePlayer());
                }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
            }, 0, TimeUnit.SECONDS);
        }
    }
    public static void handleChewMessage(Message message) {
        List<Tile> chewTiles = message.getTiles();
        gameBoard.getCurrentActivePlayer().addChew_Pong_Kung_Tiles((ArrayList<Tile>) chewTiles);
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduler.schedule(() -> {
            //如果玩家吃牌，则他立即打牌
            GameManager.updateScreen();
            scheduledTime=System.currentTimeMillis();
            scheduledFuture = scheduler.schedule(() -> {
                GameManager.handleDiscardButtonAction(13,gameBoard.getCurrentActivePlayer());
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }, 0, TimeUnit.SECONDS);
    }
    public static void handlePungMessage(Message message) {
        List<Tile> pungTiles = message.getTiles();
        gameBoard.getCurrentActivePlayer().addChew_Pong_Kung_Tiles((ArrayList<Tile>) pungTiles);
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduler.schedule(() -> {
            //玩家碰牌，则他立刻打牌
            GameManager.updateScreen();
            scheduledTime=System.currentTimeMillis();
            scheduledFuture = scheduler.schedule(() -> {
                GameManager.handleDiscardButtonAction(13,gameBoard.getCurrentActivePlayer());
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }, 0, TimeUnit.SECONDS);
    }
    public static void handleKongMessage(Message message) {
        List<Tile> kongTiles = message.getTiles();
        gameBoard.getCurrentActivePlayer().addChew_Pong_Kung_Tiles((ArrayList<Tile>) kongTiles);
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduler.schedule(() -> {
            //玩家杠牌，则他立刻抓牌打牌
            GameManager.updateScreen();
            scheduledTime=System.currentTimeMillis();
            scheduledFuture = scheduler.schedule(() -> {
                GameManager.handleDiscardButtonAction(13,gameBoard.getCurrentActivePlayer());
            }, TASK_INTERVAL_SECONDS, TimeUnit.SECONDS);
        }, 0, TimeUnit.SECONDS);
    }

    public static Player playerToEat(){
        Tile chowTile = gameBoard.getLeastDiscardedTile();
        int tempindex = 0;
        int index = 999;
        for (Player player : players) {
            if(player.equals(gameBoard.getCurrentActivePlayer())){
                index = tempindex;
            }
            tempindex++;
        }
        int preindex = (index-1+4)%4;
        if(index==999 || !players.get(preindex).canchi(chowTile)){
            return null;
        }
        return players.get(preindex);
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

    public void setCurrentPlayer(Player player){
        int index = gameBoard.getPlayerIndex(player);
        gameBoard.setCurrentActivePlayerIndex(index);
    }

}
