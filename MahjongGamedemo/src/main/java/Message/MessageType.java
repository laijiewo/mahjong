package Message;

import java.io.Serializable;

public enum MessageType implements Serializable {
    JOIN, HUN_TILE, GAME_INFORMATION, LAUNCH_GAME,
    DISCARD, DRAW, CHEW, PUNG, KONG, HU, GAME_OVER;
}
