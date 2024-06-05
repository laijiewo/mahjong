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


public class MahjongGame implements Game {
    private static List<Player> players;
    private static GameBoard gameBoard;
    protected static LinkedList<Socket> sockets;
    TileImageMapper tileImageMapper;
    private static ServerSocket serverSocket;
    private int port;
    static int i = 0;

    public MahjongGame(int port) throws IOException {
        this.port = port;
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
    }

    public void setHunTileToPlayers() {
        for (Player player : players) {
            player.setHunTile(gameBoard.getHunTile());
        }
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


    public Tile getLeastDiscardedTile() {
        return gameBoard.getLeastDiscardedTile();
    }

    @Override
    public void swap() {
        gameBoard.swap();
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
        List<Tile> discards = gameBoard.getCurrentActivePlayer().getDiscard_Tiles();
        List<Tile> tiles = gameBoard.getCurrentActivePlayer().getTile_hand();
        if (!(discards.isEmpty() && gameBoard.getCurrentActivePlayer() == gameBoard.getDealer())) {
            gameBoard.dealTiles();
            gameBoard.getCurrentActivePlayer().updateScreen();
        }
        if (tiles.get(message.getIndex()).equals(GameBoard.getHunTile())) {
            System.out.println("You can't discard the hun tile.");
            return;
        }
        gameBoard.getCurrentActivePlayer().discardTiles(message.getIndex());
        gameBoard.swap();
    }
    public static void handleChewMessage(Message message) {
        // 添加玩家的chew_pong_kong_Tiles
        List<Tile> chewTiles = message.getTiles();
        gameBoard.getCurrentActivePlayer().addChew_Pong_Kung_Tiles((ArrayList<Tile>) chewTiles);
    }
    public static void handlePungMessage(Message message) {

    }
    public static void handleKongMessage(Message message) {

    }
}