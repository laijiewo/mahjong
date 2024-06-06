package Message;

import Module.Game.Player;
import Module.Game.PlayerInformation;
import Module.Tile.Tile;

import java.util.List;

public class GameInformationMessage extends Message {
    private List<PlayerInformation> players;
    List<Tile> tilesInTheWall;
    int currentPlayerIndex, playerIndex;
    Tile leastDiscardedTile;
    public GameInformationMessage(List<PlayerInformation> players, List<Tile> tilesInTheWall, int currentPlayerIndex, Tile leastDiscardedTile, int playerIndex) {
        super(MessageType.GAME_INFORMATION);
        this.players = players;
        this.tilesInTheWall = tilesInTheWall;
        this.currentPlayerIndex = currentPlayerIndex;
        this.leastDiscardedTile = leastDiscardedTile;
        this.playerIndex = playerIndex;
    }
    public List<PlayerInformation> getPlayersFromMessage() {
        return players;
    }
    public List<Tile> getTilesInTheWallFromMessage() {
        return tilesInTheWall;
    }
    public int getCurrentPlayerIndexFromMessage() {
        return currentPlayerIndex;
    }
    public Tile getLeastDiscardedTileFromMessage() {
        return leastDiscardedTile;
    }
    public int getPlayerIndexFromMessage() {
        return playerIndex;
    }
}
