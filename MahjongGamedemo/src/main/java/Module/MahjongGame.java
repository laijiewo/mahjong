package Module;
import System.*;
import WebConnect.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


public class MahjongGame implements Game {
    private GameBoard gameBoard;
    private RuleImplementation ruleImplementation;
    protected static LinkedList<Socket> sockets;
    private static ServerSocket serverSocket;
    private int port;
    static int i = 0;
    public MahjongGame(int port) throws IOException {
        this.port = port;
        startServer(port);
    }
    @Override
    public Player determineDealer() {
        return gameBoard.Dealer;
    }

    @Override
    public void initializeGame() {
        GameBoard gameBoard1=new GameBoard();

    }

    @Override
    public Player checkVictory() {
        if(gameBoard.player1.canHu(this,gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size()-1))){
            return gameBoard.player1;
        } else if (gameBoard.player2.canHu(this,gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size()-1))) {
            return gameBoard.player2;
        } else if (gameBoard.player3.canHu(this,gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size()-1))) {
            return gameBoard.player3;
        } else if (gameBoard.player4.canHu(this,gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size()-1))) {
            return gameBoard.player4;
        }else {
            return null;
        }
    }

    @Override
    public void calculateScores() {


    }

    @Override
    public void shuffleTiles() {
        gameBoard.shuffleTiles();
    }

    @Override
    public void startNewGame() {

    }

    public Tile getLeastDiscardedTile() {
        return gameBoard.Tiles_discardedByPlayer().get(gameBoard.Tiles_discardedByPlayer().size() - 1);
    }

    @Override
    public void swap() {

    }

    @Override
    public boolean isRoundOver() {
        if (checkVictory()!=null){
            return true;
        }else {
            return false;
        }

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
        new Thread(MahjongGame::sendMessageToAll).start();
        while (true) {
            System.out.println(sockets.size() + " clients connected.");
            listenForConnections();
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

    public static void listenForConnections() {
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            synchronized (clientSocket) {
                sockets.add(clientSocket);
            }
            new Thread(new ServerChatThread(clientSocket, "player " + i)).start();
            i++;
        } catch (IOException e) {
            System.out.println("Accept failed.");
            System.exit(1);
        }
        System.out.println("Got a connection from " + clientSocket.getInetAddress().getHostAddress());
        System.out.println("Waiting for input.....");
    }
    private void sendOperationMessageToAll(Message message) throws IOException {
        // TODO: 向所有玩家发送操作信息
        for (Socket socket : sockets) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            oos.flush();
        }
    }
    private Message receiveOperationMessageFromPlayer(Socket player) throws IOException, ClassNotFoundException {
        //TODO：接收玩家操作信息
        ObjectInputStream ois = new ObjectInputStream(player.getInputStream());
        return (Message) ois.readObject();
    }
    private void runGame() throws IOException, ClassNotFoundException {
        // TODO: 运行游戏
        //      1. 轮转
        //      2. 发送操作信息
        //      3. 接收玩家操作信息
        while (true) {
            for (Socket socket : sockets) {
                Message message = receiveOperationMessageFromPlayer(socket);
                if (checkOperationMessage(message)) {
                    sendOperationMessageToAll(message);
                } else {
                    // TODO: 处理非法操作信息
                }
            }
        }
    }
    private boolean checkOperationMessage(Message message) {
        // TODO: 检查操作信息是否合法
        return true;
    }
}
