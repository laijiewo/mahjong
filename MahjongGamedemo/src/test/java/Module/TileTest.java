package Module;

import org.junit.Assert;
import org.junit.Test;

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
}