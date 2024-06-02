package WebConnect;

import Module.*;

import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.util.List;
public class GameThread implements Runnable {
    private final List<Socket> players;
    private Socket gameSocket;

    public GameThread(List<Socket> players) {
        this.players = players;
    }
    // TODO: Implement the game logic here
    //      1. 完成游戏轮转，即东南西北顺时针出牌
    //      2. 轮到玩家时，玩家通过点击向host发送Message信息，host发送向所有玩家完成游戏的更新

    @Override
    public void run() {
        // TODO: Implement the game logic here
        //      1. 轮转
        try {
            runGame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void sendOperationMessageToAll(Message message) throws IOException {
        // TODO: 向所有玩家发送操作信息
        for (Socket player : players) {
            ObjectOutputStream oos = new ObjectOutputStream(player.getOutputStream());
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
            for (Socket player : players) {
                Message message = receiveOperationMessageFromPlayer(player);
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
