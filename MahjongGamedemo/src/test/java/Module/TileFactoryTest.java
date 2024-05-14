package Module;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TileFactoryTest {

    @Test
    public void testTileCreation() {
        TileFactory factory = new TileFactory();
        List<Tile> tiles = factory.getTiles();

        // Expect 3 suits * 9 ranks * 4 copies + 2 types of special tiles * 3 kinds * 4 copies
        int expectedNumberOfTiles = (3 * 9 * 4) + (7* 4);
        Assert.assertEquals("Should create correct number of tiles", expectedNumberOfTiles, tiles.size());

    }
    @Test
    public void testSpecificTileCount() {
        TileFactory factory = new TileFactory();
        List<Tile> tiles = factory.getTiles();

        // 测试东风牌数量
        long eastWindCount = tiles.stream()
                .filter(tile -> tile instanceof WindAndDragonTile)
                .map(tile -> (WindAndDragonTile) tile)
                .filter(tile -> tile.getType().equals("East") && tile.getSuit() == Suit.WIND)
                .count();
        Assert.assertEquals("Should create four eastWind", 4, eastWindCount);

        // 测试一万牌数量
        long oneWanCount = tiles.stream()
                .filter(tile -> tile instanceof NumberTile)
                .map(tile -> (NumberTile) tile)
                .filter(tile -> tile.getRank() == 1 && tile.getSuit() == Suit.WAN)
                .count();
        Assert.assertEquals("Should create four oneWan", 4, oneWanCount);

        // 测试八条牌数量
        long eightTiaoCount = tiles.stream()
                .filter(tile -> tile instanceof NumberTile)
                .map(tile -> (NumberTile) tile)
                .filter(tile -> tile.getRank() == 8 && tile.getSuit() == Suit.TIAO)
                .count();
        Assert.assertEquals("Should create four eightTiao", 4, eightTiaoCount);
    }
}