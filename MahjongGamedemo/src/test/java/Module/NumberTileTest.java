package Module;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void testToString() {
        NumberTile tile = new NumberTile(9, Suit.TIAO);
        Assert.assertEquals("toString should return '9TIAO'", "9TIAO", tile.toString());

        tile = new NumberTile(1, Suit.TONG);
        Assert.assertEquals("toString should return '1TONG'", "1TONG", tile.toString());

        tile = new NumberTile(3, Suit.WAN);
        Assert.assertEquals("toString should return '3WAN'", "3WAN", tile.toString());
    }
}