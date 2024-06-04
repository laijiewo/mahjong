package Module;

import Module.Tile.NumberTile;
import Module.Tile.Tile;
import Module.Tile.Tile.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class TileTest {
    @Test
    public void testTileCreationAndSuit() {
        Tile tile = new Tile(Suit.TONG);
        Assert.assertEquals("Tile should have suit TONG", Suit.TONG, tile.getSuit());

        tile.setSuit(Suit.WAN);
        Assert.assertEquals("Tile suit should be changed to WAN", Suit.WAN, tile.getSuit());
    }


    @Test
    public void testCompareToDifferentSuits() {
        Tile tile1 = new Tile(Suit.TONG);
        Tile tile2 = new Tile(Suit.WAN);

        // TONG should come before WAN based on their ordinal values
        assertTrue("TONG should be less than TONG", tile2.compareTo(tile1) < 0);
        assertTrue("WAN should be greater than WAN", tile1.compareTo(tile2) > 0);
    }

    @Test
    public void testCompareToSameSuit() {
        Tile tile1 = new Tile(Suit.TONG);
        Tile tile2 = new Tile(Suit.TONG);

        // Tiles of the same suit are considered equal in comparison
        assertEquals("Tiles of the same suit should be equal", 0, tile1.compareTo(tile2));
    }

    @Test
    public void testNumberTileCompareTo() {
        // Assuming NumberTile properly overrides compareTo to consider rank
        NumberTile tile1 = new NumberTile(1, Suit.TONG);
        NumberTile tile2 = new NumberTile(3, Suit.TONG);
        NumberTile tile3 = new NumberTile(1, Suit.WAN);

        // Same suit, different ranks
        assertTrue("Tile with rank 1 should be less than tile with rank 3", tile1.compareTo(tile2) < 0);
        assertTrue("Tile with rank 3 should be greater than tile with rank 1", tile2.compareTo(tile1) > 0);

        // Different suits, same rank
        assertTrue("WAN should be less than TONG when rank is the same", tile3.compareTo(tile1) < 0);
        assertTrue("TONG should be greater than WAN when rank is the same", tile1.compareTo(tile3) > 0);
    }
    @Test
    public void testNumberTilesCompareTo() {
        // Assuming NumberTile properly overrides compareTo to consider rank
        NumberTile tile1 = new NumberTile(1, Suit.TONG);
        NumberTile tile2 = new NumberTile(3, Suit.TONG);
        NumberTile tile3 = new NumberTile(1, Suit.WAN);
        NumberTile tile4 = new NumberTile(2, Suit.WAN);
        NumberTile tile5 = new NumberTile(2, Suit.TONG);
        NumberTile tile6 = new NumberTile(6, Suit.WAN);
        NumberTile tile7 = new NumberTile(6, Suit.TIAO);
        LinkedList<Tile> tiles = new LinkedList<>();
        tiles.add(tile1);
        tiles.add(tile2);
        tiles.add(tile3);
        tiles.add(tile4);
        tiles.add(tile5);
        tiles.add(tile6);
        tiles.add(tile7);

        tiles.sort(Tile::compareTo);
        assertEquals(tiles.get(0), tile7);
        assertEquals(tiles.get(1), tile3);
        assertEquals(tiles.get(2), tile4);
        assertEquals(tiles.get(5), tile5);
    }
}