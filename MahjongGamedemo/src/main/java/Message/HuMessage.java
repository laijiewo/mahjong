package Message;

import Module.Game.PlayerInformation;

import java.util.List;

public class HuMessage extends Message {
    private final int fans, point;
    private final PlayerInformation winner;
    private final List<String> wintype;
    public HuMessage(int fans, int point, PlayerInformation winner, List<String> wintype) {
        super(MessageType.HU);
        this.fans = fans;
        this.point = point;
        this.winner = winner;
        this.wintype = wintype;
    }
    public int getFans() {
        return fans;
    }
    public int getPoint() {
        return point;
    }
    public PlayerInformation getWinner() {
        return winner;
    }
    public List<String> getWintype() {
        return wintype;
    }
}
