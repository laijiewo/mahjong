package Message;

import Module.Game.PlayerInformation;

import java.util.List;

/**
 * Represents a message for a Hu (winning) action in the Mahjong game.
 * Extends the base Message class and includes information about the fans, points, winner, and winning types.
 */
public class HuMessage extends Message {
    private final int fans, point;
    private final PlayerInformation winner;
    private final List<String> wintype;

    /**
     * Constructs a new HuMessage with the specified details.
     *
     * @param fans The number of fans (points multiplier).
     * @param point The total points won.
     * @param winner The player information of the winner.
     * @param wintype The list of winning types.
     */
    public HuMessage(int fans, int point, PlayerInformation winner, List<String> wintype) {
        super(MessageType.HU);
        this.fans = fans;
        this.point = point;
        this.winner = winner;
        this.wintype = wintype;
    }

    /**
     * Gets the number of fans (points multiplier).
     *
     * @return The number of fans.
     */
    public int getFans() {
        return fans;
    }

    /**
     * Gets the total points won.
     *
     * @return The total points.
     */
    public int getPoint() {
        return point;
    }

    /**
     * Gets the player information of the winner.
     *
     * @return The winner's PlayerInformation.
     */
    public PlayerInformation getWinner() {
        return winner;
    }

    /**
     * Gets the list of winning types.
     *
     * @return The list of winning types.
     */
    public List<String> getWintype() {
        return wintype;
    }
}
