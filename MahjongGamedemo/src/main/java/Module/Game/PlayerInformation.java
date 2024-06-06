package Module.Game;

import Module.Tile.Tile;

import java.io.Serializable;
import java.util.List;

public class PlayerInformation implements Serializable {
    private List<Tile> hand_Tiles, Chew_Pung_Kong_Tiles, discardedTiles;
    private Site site;
    public PlayerInformation(Player player) {
        hand_Tiles = player.getTile_hand();
        Chew_Pung_Kong_Tiles = player.getChew_Pong_Kung_Tiles();
        discardedTiles = player.getDiscard_Tiles();
        this.site = player.getPlayerSite();
    }
    public List<Tile> getHand_Tiles() {
        return hand_Tiles;
    }
    public List<Tile> getChew_Pung_Kong_Tiles() {
        return Chew_Pung_Kong_Tiles;
    }
    public List<Tile> getDiscardedTiles() {
        return discardedTiles;
    }
    public Site getSite() {
        return site;
    }
}
