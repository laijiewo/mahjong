package Message;

import Module.Tile.Tile;

import java.util.List;

public class Chew_Pung_KongMessage extends Message {
    int playerIndex;
    public Chew_Pung_KongMessage(MessageType type, int playerIndex) {
        super(type);
        this.playerIndex = playerIndex;
    }
    public int getPlayerIndex() {
        return playerIndex;
    }
}
