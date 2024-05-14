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
}