package Module;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WindAndDragonTileTest {
    @Test
    public void testWindAndDragonTileCreation() {
        WindAndDragonTile tile = new WindAndDragonTile("East", Suit.WIND);
        Assert.assertEquals("Tile should have suit WIND", Suit.WIND, tile.getSuit());
        Assert.assertEquals("Tile type should be East", "East", tile.getType());
    }

}