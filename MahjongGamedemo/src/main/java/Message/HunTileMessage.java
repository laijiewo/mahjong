package Message;

import Module.Tile.Tile;

public class HunTileMessage extends Message {
    private final Tile hunTile;
    public HunTileMessage(Tile tile) {
        super(MessageType.HUN_TILE);
        this.hunTile = tile;
    }
    public Tile getHunTile() {
        return hunTile;
    }
}
