package WebConnect;

import Module.*;
import java.util.List;
public class GameThread implements Runnable {
    private final List<Player> players;

    public GameThread(List<Player> players) {
        this.players = players;
    }
    // TODO: Implement the game logic here
    //      1. 完成游戏轮转，即东南西北顺时针出牌
    //      2. 轮到玩家时，玩家通过点击向host发送Message信息，host发送向所有玩家完成游戏的更新

    @Override
    public void run() {
        // TODO: Implement the game logic here
        //      1. 轮转
    }
    private void sendOperationMessageToAll(Message message) {
        // TODO: 向所有玩家发送操作信息
    }
    private Message receiveOperationMessageFromPlayer(Player player) {
        //TODO：接收玩家操作信息
        return null;
    }
    private void runGame() {
        // TODO: 运行游戏
        //      1. 轮转
        //      2. 发送操作信息
        //      3. 接收玩家操作信息
        while (true) {
            for (Player player : players) {
                Message message = receiveOperationMessageFromPlayer(player);
                if (message.getType() == MessageType.DRAW) {
                    // TODO: 处理玩家操作信息
                    //      1. 出牌
                    //      2. 过牌
                    //      3. 吃牌
                    //      4. 碰牌
                    //      5. 杠牌
                    //      7. 其他操作
                }
            }
        }
    }
}
