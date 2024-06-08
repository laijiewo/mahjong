package Message;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import Module.Game.MahjongGame;
import Module.Tile.Tile;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private MessageType type;

    // HU, PAUSE and UNPAUSE Message
    public Message(MessageType type) {
        this.type = type;
    }
    public MessageType getType() {
        return type;
    }
}
