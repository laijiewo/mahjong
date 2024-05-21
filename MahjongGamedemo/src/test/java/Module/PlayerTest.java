package Module;

import Module.*;
import System.*;
import Display.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Player actions within a Mahjong game context.
 */
class PlayerTest {

    private Player player;
    private TileWall tileWall;
    private GameBoard gameBoard;
    private MahjongGame mahjongGame;

    @BeforeEach
    void setUp() {
        player = new Player();
        tileWall = new TileWall(); // Assuming TileWall manages a collection of tiles.
        gameBoard = new GameBoard(); // Assuming GameBoard holds game state.
        mahjongGame = new MahjongGame(); // Assuming MahjongGame checks for win conditions.

        // Fill tile wall with tiles for testing
        tileWall.StackOfTiles.push(new NumberTile(3, Suit.WAN));
        tileWall.StackOfTiles.push(new NumberTile(2, Suit.WAN));
        tileWall.StackOfTiles.push(new NumberTile(1, Suit.WAN));
        gameBoard.Tiles_discardedByPlayer = new ArrayList<>();
    }

    @Test
    void testDrawTile() {
        Tile tile = player.drawTiles(tileWall);
        assertNotNull(tile, "Tile should be drawn successfully.");
        assertEquals(2, tileWall.StackOfTiles.size(), "Tile wall should have one less tile after drawing.");
    }

    @Test
    void testDiscardTile() {
        player.Tile_hand.add(new NumberTile(3, Suit.WAN));
        Tile discarded = player.discardTiles(0);
        assertNotNull(discarded, "Discarded tile should not be null.");
        assertTrue(player.Tile_hand.isEmpty(), "Hand should be empty after discarding tile.");
    }

    @Test
    void testKong() {
        // Set up for kong
        Tile kongTile = new NumberTile(3, Suit.WAN);
        player.Tile_hand.add(new NumberTile(3, Suit.WAN));
        player.Tile_hand.add(new NumberTile(3, Suit.WAN));
        player.Tile_hand.add(new NumberTile(3, Suit.WAN));
        gameBoard.Tiles_discardedByPlayer.add(kongTile);

        player.kong(gameBoard);
        assertEquals(4, player.Tile_hand.size(), "Should have four tiles of the same type after kong.");
    }

    @Test
    void testChow() {
        // Assuming a valid chow scenario
        player.Tile_hand.add(new NumberTile(2, Suit.WAN));
        player.Tile_hand.add(new NumberTile(3, Suit.WAN));
        gameBoard.Tiles_discardedByPlayer.add(new NumberTile(1, Suit.WAN));

        player.chow(gameBoard);
        assertEquals(3, player.Tile_hand.size(), "Should have three consecutive tiles after chow.");
    }

    @Test
    void testPung() {
        // Set up for pung
        Tile pungTile = new NumberTile(2, Suit.WAN);
        player.Tile_hand.add(new NumberTile(2, Suit.WAN));
        player.Tile_hand.add(new NumberTile(2, Suit.WAN));
        gameBoard.Tiles_discardedByPlayer.add(pungTile);

        player.pung(gameBoard);
        assertEquals(3, player.Tile_hand.size(), "Should have three tiles of the same type after pung.");
    }

    @Test
    void testWinHand() {
        // Assume condition for winning is met
        assertTrue(player.winHand(mahjongGame), "Player should win the hand under correct conditions.");
    }

    @Test
    void testScoreChange() {
        int originalScore = player.getScore();
        int scoreChange = 50;
        player.Score_change(scoreChange);
        assertEquals(originalScore + scoreChange, player.getScore(), "Player's score should be updated correctly.");
    }
}
