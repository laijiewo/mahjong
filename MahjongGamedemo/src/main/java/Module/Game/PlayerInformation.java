package Module.Game;

import Module.Tile.Tile;

import java.io.Serializable;
import java.util.List;
/**
 * The PlayerInformation class stores information about a player's current state in the game.
 */
public class PlayerInformation implements Serializable {
    private List<Tile> hand_Tiles, Chew_Pung_Kong_Tiles, discardedTiles;
    private Site site;
    /**
     * Constructs a PlayerInformation object from a Player object.
     *
     * @param player The Player object from which to extract information.
     */
    public PlayerInformation(Player player) {
        hand_Tiles = player.getTile_hand();
        Chew_Pung_Kong_Tiles = player.getChew_Pong_Kung_Tiles();
        discardedTiles = player.getDiscard_Tiles();
        this.site = player.getPlayerSite();
    }

    /**
     * Gets the tiles in the player's hand.
     *
     * @return The list of tiles in the player's hand.
     */
    public List<Tile> getHand_Tiles() {
        return hand_Tiles;
    }

    /**
     * Gets the tiles that the player has Chew (Chow), Pung (Pong), or Kong (Kang).
     *
     * @return The list of Chew, Pung, and Kong tiles.
     */
    public List<Tile> getChew_Pung_Kong_Tiles() {
        return Chew_Pung_Kong_Tiles;
    }

    /**
     * Gets the tiles that the player has discarded.
     *
     * @return The list of discarded tiles.
     */
    public List<Tile> getDiscardedTiles() {
        return discardedTiles;
    }

    /**
     * Gets the site of the player.
     *
     * @return The site of the player.
     */
    public Site getSite() {
        return site;
    }
}
