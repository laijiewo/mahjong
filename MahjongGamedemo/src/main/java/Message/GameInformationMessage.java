package Message;

import Module.Game.PlayerInformation;
import Module.Tile.Tile;

import java.util.List;

/**
 * Represents a message containing game information in the Mahjong game.
 * Extends the base Message class and includes information about players, tiles, and the current game state.
 */
public class GameInformationMessage extends Message {
    private List<PlayerInformation> players;
    List<Tile> tilesInTheWall;
    int currentPlayerIndex, playerIndex, dealerIndex;
    Tile leastDiscardedTile;

    /**
     * Constructs a new GameInformationMessage with the specified game details.
     *
     * @param players The list of player information.
     * @param tilesInTheWall The list of tiles in the wall.
     * @param currentPlayerIndex The index of the current player.
     * @param leastDiscardedTile The tile that was most recently discarded.
     * @param playerIndex The index of the player receiving this message.
     * @param dealerIndex The index of the dealer.
     */
    public GameInformationMessage(List<PlayerInformation> players, List<Tile> tilesInTheWall, int currentPlayerIndex, Tile leastDiscardedTile, int playerIndex, int dealerIndex) {
        super(MessageType.GAME_INFORMATION);
        this.players = players;
        this.tilesInTheWall = tilesInTheWall;
        this.currentPlayerIndex = currentPlayerIndex;
        this.leastDiscardedTile = leastDiscardedTile;
        this.playerIndex = playerIndex;
        this.dealerIndex = dealerIndex;
    }

    /**
     * Gets the list of players from the message.
     *
     * @return The list of PlayerInformation objects.
     */
    public List<PlayerInformation> getPlayersFromMessage() {
        return players;
    }

    /**
     * Gets the list of tiles in the wall from the message.
     *
     * @return The list of Tile objects.
     */
    public List<Tile> getTilesInTheWallFromMessage() {
        return tilesInTheWall;
    }

    /**
     * Gets the current player index from the message.
     *
     * @return The index of the current player.
     */
    public int getCurrentPlayerIndexFromMessage() {
        return currentPlayerIndex;
    }

    /**
     * Gets the least discarded tile from the message.
     *
     * @return The most recently discarded Tile.
     */
    public Tile getLeastDiscardedTileFromMessage() {
        return leastDiscardedTile;
    }

    /**
     * Gets the player index from the message.
     *
     * @return The index of the player receiving this message.
     */
    public int getPlayerIndexFromMessage() {
        return playerIndex;
    }

    /**
     * Gets the dealer index from the message.
     *
     * @return The index of the dealer.
     */
    public int getDealerIndexFromMessage() {
        return dealerIndex;
    }
}
