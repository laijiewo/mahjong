package Message;

/**
 * Represents a message for discarding a tile in the Mahjong game.
 * Extends the base Message class and includes the index of the discarded tile.
 */
public class DiscardMessage extends Message {
    private final int index;

    /**
     * Constructs a new DiscardMessage with the specified index.
     *
     * @param index The index of the discarded tile.
     */
    public DiscardMessage(int index) {
        super(MessageType.DISCARD);
        this.index = index;
    }

    /**
     * Gets the index of the discarded tile.
     *
     * @return The tile index.
     */
    public int getIndex() {
        return index;
    }
}
