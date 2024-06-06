package Message;

import Module.Tile.Tile;

import java.util.List;

public class Chew_Pung_KongMessage extends Message {
    List<Tile> tiles;
    public Chew_Pung_KongMessage(MessageType type, List<Tile> tiles) {
        super(type);
        this.tiles = tiles;
    }
    public List<Tile> getTiles() {
        return tiles;
    }
}
