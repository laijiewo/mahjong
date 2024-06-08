package Message;

import Module.Tile.Tile;

import java.util.List;

/**
 * Represents a message for Chew, Pung, or Kong actions in the Mahjong game.
 * Extends the base Message class and includes the player index who performed the action.
 */
public class Chew_Pung_KongMessage extends Message {
    int playerIndex;

    /**
     * Constructs a new Chew_Pung_KongMessage with the specified message type and player index.
     *
     * @param type The type of the message (Chew, Pung, or Kong).
     * @param playerIndex The index of the player performing the action.
     */
    public Chew_Pung_KongMessage(MessageType type, int playerIndex) {
        super(type);
        this.playerIndex = playerIndex;
    }

    /**
     * Gets the index of the player who performed the action.
     *
     * @return The player index.
     */
    public int getPlayerIndex() {
        return playerIndex;
    }
}

