package Module;

import Module.Tile.NumberTile;
import Module.Tile.Suit;
import org.junit.Assert;
import org.junit.Test;

public class NumberTileTest {

    @Test
    public void testValidNumberTileCreation() {
        NumberTile tile = new NumberTile(5, Suit.WAN);
        Assert.assertNotNull("Tile should not be null", tile);
        Assert.assertEquals("Rank should be 5", 5, tile.getRank());
        Assert.assertEquals("Suit should be WAN", Suit.WAN, tile.getSuit());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRankCreation() {
        new NumberTile(0, Suit.WAN);
    }
    
}