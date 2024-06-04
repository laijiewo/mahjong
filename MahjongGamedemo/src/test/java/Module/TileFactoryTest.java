package Module;

import Module.Tile.Tile;
import Module.Tile.TileFactory;
import Module.Tile.WindAndDragonTile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TileFactoryTest {

    private TileFactory factory;
    private List<Tile> tiles;

    @Before
    public void setUp() {
        factory = new TileFactory();
        tiles = factory.createTiles(); // Assuming createTiles is called and shuffled list is returned
    }

    @Test
    public void testTileCount() {
        // Ensure the total number of tiles is as expected
        int expectedNumberOfTiles = (3 * 9 * 4) + (4 * 4) + (3 * 4); // 3 suits x 9 ranks x 4 each, 4 winds x 4 each, 3 dragons x 4 each
        Assert.assertEquals("Total number of tiles should be correct", expectedNumberOfTiles, tiles.size());
    }

    @Test
    public void testSpecificTileCount() {
        // Check that there are exactly four of each dragon and wind type
        Map<String, Long> countMap = tiles.stream()
                .filter(tile -> tile instanceof WindAndDragonTile)
                .map(tile -> (WindAndDragonTile) tile)
                .collect(Collectors.groupingBy(WindAndDragonTile::getType, Collectors.counting()));

        Assert.assertEquals("There should be four East wind tiles", 4, (long) countMap.get("East"));
        Assert.assertEquals("There should be four South wind tiles", 4, (long) countMap.get("South"));
        Assert.assertEquals("There should be four West wind tiles", 4, (long) countMap.get("West"));
        Assert.assertEquals("There should be four North wind tiles", 4, (long) countMap.get("North"));
        Assert.assertEquals("There should be four ZHONG tiles", 4, (long) countMap.get("ZHONG"));
        Assert.assertEquals("There should be four FA tiles", 4, (long) countMap.get("FA"));
        Assert.assertEquals("There should be four BAI tiles", 4, (long) countMap.get("BAI"));
    }

}