package Message;

import Module.Tile.Tile;

/**
 * Represents a message containing the Hun tile in the Mahjong game.
 * Extends the base Message class and includes the Hun tile.
 */
public class HunTileMessage extends Message {
    private final Tile hunTile;

    /**
     * Constructs a new HunTileMessage with the specified Hun tile.
     *
     * @param tile The Hun tile.
     */
    public HunTileMessage(Tile tile) {
        super(MessageType.HUN_TILE);
        this.hunTile = tile;
    }

    /**
     * Gets the Hun tile from the message.
     *
     * @return The Hun tile.
     */
    public Tile getHunTile() {
        return hunTile;
    }
}
