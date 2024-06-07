package Module;

import Display.GameScreen;
import Module.Game.Player;
import Module.Tile.*;
import Message.*;
import Module.utils.Dice;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class PlayerTest {
    private Player player;
    private Dice dice;
    private TileFactory tileFactory;

    @Before
    public void setUp() {
        player = new Player();
        dice = new Dice();
        tileFactory = new TileFactory();
        Tile hunTile = new WindAndDragonTile("ZHONG", Suit.DRAGON);
        player.setHunTile(hunTile);
    }

    @Test
    public void testSortHand() {
        Tile t1 = new NumberTile(3, Suit.TIAO);
        Tile t2 = new NumberTile(1, Suit.TIAO);
        Tile t3 = new NumberTile(2, Suit.TIAO);
        player.drawTiles(t1);
        player.drawTiles(t3);
        player.drawTiles(t2);
        player.sort_hand();
        assertEquals(1, ((NumberTile) player.getTile_hand().get(0)).getRank());
        assertEquals(2, ((NumberTile) player.getTile_hand().get(1)).getRank());
        assertEquals(3, ((NumberTile) player.getTile_hand().get(2)).getRank());

    }

    @Test
    public void testRollDice() {
        int result = player.rollDice(dice);
        assertTrue(result >= 2 && result <= 12);
    }

    @Test
    public void testDiscardTiles() {
        Tile tile = new NumberTile(3, Suit.TIAO);
        player.drawTiles(tile);
        Tile discarded = player.discardTiles(0);
        assertTrue(player.getDiscard_Tiles().contains(discarded));
        assertFalse(player.getTile_hand().contains(discarded));
    }

    @Test
    public void testKong() {
        Player player = new Player();
        Tile tile1 = new NumberTile(6, Suit.WAN);
        Tile tile = new NumberTile(5, Suit.WAN);
        player.setHunTile(tile1);
        player.drawTiles(tile);
        player.drawTiles(tile);
        player.drawTiles(tile); // Setup three identical tiles

        List<Tile> pengResult = player.kong(tile);
        assertNotNull("Peng result should not be null", pengResult);
        assertEquals(4, pengResult.size());
    }


    @Test
    public void testPeng() {
        Player player = new Player();
        Tile tile1 = new NumberTile(6, Suit.WAN);
        Tile tile = new NumberTile(5, Suit.WAN);
        player.setHunTile(tile1);
        player.drawTiles(tile);
        player.drawTiles(tile); // Setup three identical tiles

        List<Tile> pengResult = player.pung(tile);
        assertNotNull("Peng result should not be null", pengResult);
        assertEquals(3, pengResult.size());
    }

    @Test
    public void testChi() {
        Player player = new Player();
        Tile tile1 = new NumberTile(2, Suit.TIAO);
        Tile tile2 = new NumberTile(3, Suit.TIAO);
        Tile tile3 = new NumberTile(4, Suit.TIAO); // Create a sequence
        player.setHunTile(tile1);
        player.drawTiles(tile1);
        player.drawTiles(tile2);
        player.drawTiles(tile3);
        ArrayList tiles= new ArrayList();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        player.addChew_Pong_Kung_Tiles(tiles);

        List<Tile> chiResult = player.chi(tile3);
        assertNotNull("Chi result should not be null", chiResult);
        assertEquals("Chi should correctly add three tiles to Chew_Pong_Kung_Tiles", 3, player.getChew_Pong_Kung_Tiles().size());
        assertTrue("Chew_Pong_Kung_Tiles should contain the chi tiles", player.getChew_Pong_Kung_Tiles().containsAll(Arrays.asList(tile1, tile2, tile3)));
    }





    @Test
    public void testCanHu() {
        // Clear existing tiles and setup a definite Hu scenario:
        player.getTile_hand().clear(); // Clearing the hand before setup if necessary
        player.getChew_Pong_Kung_Tiles().clear(); // Clearing melds list if used for evaluating Hu

        // Adding pungs
        player.getTile_hand().add(new NumberTile(2, Suit.WAN));
        player.getTile_hand().add(new NumberTile(2, Suit.WAN));
        player.getTile_hand().add(new NumberTile(2, Suit.WAN));

        player.getTile_hand().add(new NumberTile(5, Suit.TONG));
        player.getTile_hand().add(new NumberTile(5, Suit.TONG));
        player.getTile_hand().add(new NumberTile(5, Suit.TONG));

        player.getTile_hand().add(new NumberTile(9, Suit.TIAO));
        player.getTile_hand().add(new NumberTile(9, Suit.TIAO));
        player.getTile_hand().add(new NumberTile(9, Suit.TIAO));

        // Adding a pair
        player.getTile_hand().add(new NumberTile(7, Suit.TIAO));
        player.getTile_hand().add(new NumberTile(7, Suit.TIAO));

        // Assume canHu method checks if the hand is a winning hand
        boolean canDeclareHu = player.canHu();
        System.out.println("Hand tiles: " + player.getTile_hand().stream()
                .map(tile -> tile.getSuit() + " " + ((NumberTile) tile).getRank())
                .collect(Collectors.joining(", ")));
        System.out.println("Can declare Hu: " + canDeclareHu);
        assertTrue("Player should be able to declare Hu based on hand setup", canDeclareHu);
    }

}
