package Message;

import Module.Game.Player;


/**
 * Represents a message for a join action in the Mahjong game.
 * Extends the base Message class.
 */
public class joinMessage extends Message {

    /**
     * Constructs a new JoinMessage.
     */
    public joinMessage() {
        super(MessageType.JOIN);
    }
}

