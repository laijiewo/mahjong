package WebConnect;

import java.io.Serializable;
import Module.*;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private MessageType type;
    private String content;

    // Discard and DRAW card Message
    public Message(MessageType type, Tile tile) {
        this.type = type;
    }
    //CHEW Message
    public Message(MessageType type, String content) {
        this.type = type;
        this.content = content;
    }
    // KONG Message
    public Message(MessageType type, String content, int index) {
        this.type = type;
        this.content = content;
    }

    // PUNG Message
    public Message(MessageType type, String content, int index, int tileIndex) {
        this.type = type;
        this.content = content;
    }
    // HU Message
    public Message(MessageType type, String content, int index, int tileIndex, int tileValue, int huValue) {
        this.type = type;
    }
    // PAUSE and UNPAUSE Message
    public Message(MessageType type, String content, int index, int tileIndex, int tileValue, int huValue, int pauseTime) {

        this.type = type;
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
