package Module;

import Module.Game.MahjongGame;
import Module.Game.Player;
import Module.Game.Site;
import Module.Tile.Tile;
import Module.Tile.NumberTile;
import Module.Tile.Suit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

public class MahjongGameTest {
    private MahjongGame game;

    @BeforeEach
    public void setUp() throws IOException {
        game = new MahjongGame(12345); // Assume port 12345 for testing
    }

    @Test
    public void testGameInitialization() {
        assertNotNull(game, "Game should be successfully created");
    }

    @Test
    public void testAddAndRemovePlayer() {
        Player player = new Player();
        game.addPlayer(player);
        assertEquals(1, game.getNumOfPlayers(), "Game should have one player after adding");

        game.removePlayer(player);
        assertEquals(0, game.getNumOfPlayers(), "Game should have no players after removal");
    }

    @Test
    public void testPlayerSitesSetup() {
        // Setup game with 4 players
        for (int i = 0; i < 4; i++) {
            game.addPlayer(new Player());
        }
        game.setPlayersSites();
        List<Player> players = game.getPlayers();
        assertEquals(Site.East, players.get(0).getPlayerSite(), "First player should be at East site");
        assertEquals(Site.South, players.get(1).getPlayerSite(), "Second player should be at South site");
        assertEquals(Site.West, players.get(2).getPlayerSite(), "Third player should be at West site");
        assertEquals(Site.North, players.get(3).getPlayerSite(), "Fourth player should be at North site");
    }

    @Test
    public void testInitialTileCount() {
        // Assuming each player starts with 13 tiles and there is 1 additional tile
        game.initializeGame();
        int expectedTiles = (144 - (13 * game.getNumOfPlayers() + 1)); // Total tiles - initial deal
        assertEquals(expectedTiles, MahjongGame.getTileCountInTheTileWall(), "Tile wall should have the correct number of tiles after dealing");
    }

    @Test
    public void testGameStartStatus() {
        assertFalse(game.isGameStart(), "Game should not be started initially");
        game.initializeGame();
        assertTrue(game.isGameStart(), "Game should be started after initialization");
    }

    @Test
    public void testRotationAndDeal() {
        for (int i = 0; i < 4; i++) {
            game.addPlayer(new Player());
        }
        game.initializeGame();
        int initialIndex = MahjongGame.getCurrentPlayerIndex();
        game.rotate();
        assertNotEquals(initialIndex, MahjongGame.getCurrentPlayerIndex(), "Active player should change after rotation");
    }
}
