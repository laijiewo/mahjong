package Message;

import java.io.Serializable;
import java.util.List;

import Module.Tile.Tile;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private MessageType type;
    private Tile tile = null;
    private int index;
    private List<Tile> tiles = null;

    // Discard
    public Message(int index, MessageType type, Tile tile) {
        this.type = type;
        this.tile = tile;
        this.index = index;
    }
    // CHEW, PUNG, and KONG Message
    public Message(MessageType type, List<Tile> tiles) {
        this.type = type;
        this.tiles = tiles;
    }

    // HU, PAUSE and UNPAUSE Message
    public Message(MessageType type) {
        this.type = type;
    }
    public int getIndex() {
        return index;
    }
    public MessageType getType() {
        return type;
    }
    public Tile getTile() {
        return tile;
    }
    public List<Tile> getTiles() {
        return tiles;
    }
}
