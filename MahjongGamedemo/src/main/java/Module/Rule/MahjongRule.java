package Module.Rule;
import Module.Tile.Tile;

import java.util.List;

/**
 * Interface for defining Mahjong rules.
 */
public interface MahjongRule {

    /**
     * Checks if the player can perform a "Chi" (吃) with the given tile.
     *
     * @param hand the player's hand
     * @param tile the tile to be used for Chi
     * @return true if the player can Chi, false otherwise
     */
    boolean canChi(List<Tile> hand, Tile tile);

    /**
     * Checks if the player can perform a "Peng" (碰) with the given tile.
     *
     * @param hand the player's hand
     * @param tile the tile to be used for Peng
     * @return true if the player can Peng, false otherwise
     */
    boolean canPeng(List<Tile> hand, Tile tile);

    /**
     * Checks if the player can perform a "Gang" (杠) with the given tile.
     * This includes both Ming Gang (明杠) and An Gang (暗杠).
     *
     * @param hand the player's hand
     * @param tile the tile to be used for Gang
     * @return true if the player can Gang, false otherwise
     */
    boolean canGang(List<Tile> hand, Tile tile);

    /**
     * Checks if the player can perform a "Hu" (胡) with the given tile.
     *
     * @param hand the player's hand
     * @param tile the tile to be used for Hu
     * @return true if the player can Hu, false otherwise
     */
    public boolean canHu(List<Tile> hand, Tile tile,List<Tile>hunTile,List<Tile>meldsTile);
}
