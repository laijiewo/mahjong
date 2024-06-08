package Module;

import Module.Game.GameBoard;
import Module.Game.Player;
import Module.Tile.NumberTile;
import Module.Tile.Suit;
import Module.Tile.Tile;
import Module.Tile.WindAndDragonTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class GameBoardTest {
    private GameBoard gameBoard;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        // Setup a mock list of players using the default constructor
        players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new Player());
        }
        gameBoard = new GameBoard(players);
    }


    @Test
    void testDealerAndActivePlayerInitiallyTheSame() {
        // Assuming the dealer is determined in the constructor or another initializing method
        gameBoard.determineDealer();
        assertEquals(gameBoard.getDealer(), gameBoard.getCurrentActivePlayer(), "Dealer should initially be the active player");
    }

    @Test
    void testShuffleTiles() {
        List<Tile> initialTiles = new ArrayList<>(gameBoard.getTilesInTheWall());
        gameBoard.shuffleTiles();
        List<Tile> shuffledTiles = gameBoard.getTilesInTheWall();
        assertNotEquals(initialTiles, shuffledTiles, "Tiles should be shuffled and not in the same order");
    }

    @Test
    void testDealTilesReducesWallCount() {
        int initialCount = gameBoard.getTileCountInTheWall();
        gameBoard.dealTiles();
        assertEquals(initialCount - 1, gameBoard.getTileCountInTheWall(), "Dealing a tile should reduce the count of tiles in the wall");
    }

    @Test
    void testDetermineHunTile() {
        gameBoard.determineHunTile();
        assertNotNull(gameBoard.getHunTile(), "Hun tile should be determined and not null");
    }

    @Test
    void testSetAndGetLeastDiscardedTile() {
        Tile testTile = new NumberTile(1, Suit.WAN);
        gameBoard.setLeastDiscardedTile(testTile);
        assertEquals(testTile, gameBoard.getLeastDiscardedTile(), "Set and get least discarded tile should be consistent");
    }

    @Test
    void testSwapCyclesActivePlayer() {
        Player initialPlayer = gameBoard.getCurrentActivePlayer();
        gameBoard.swap();
        assertNotEquals(initialPlayer, gameBoard.getCurrentActivePlayer(), "Swapping should cycle the active player index");
    }
}
