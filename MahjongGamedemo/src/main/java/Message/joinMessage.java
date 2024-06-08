package Message;

import Module.Game.Player;

public class joinMessage extends Message {
    public joinMessage() {
        super(MessageType.JOIN);
    }
}
