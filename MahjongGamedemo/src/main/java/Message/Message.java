package Message;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a general message in the Mahjong game.
 * Implements Serializable for message serialization.
 */
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private MessageType type;

    /**
     * Constructs a new Message with the specified type.
     *
     * @param type The type of the message.
     */
    public Message(MessageType type) {
        this.type = type;
    }

    /**
     * Gets the type of the message.
     *
     * @return The type of the message.
     */
    public MessageType getType() {
        return type;
    }
}

