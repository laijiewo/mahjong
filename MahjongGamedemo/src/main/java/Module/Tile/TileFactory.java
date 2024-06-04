package Module.Tile;

import Module.Tile.NumberTile;
import Module.Tile.Tile;
import Module.Tile.Suit;
import Module.Tile.WindAndDragonTile;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates a complete set of tiles for games such as Mahjong. This factory creates all the
 * necessary tiles including numerical tiles and special tiles such as winds and dragons.
 *
 * @author Yixin Niu
 */
public class TileFactory {
    private List<Tile> tiles;

    /**
     * Constructs a new TileFactory and initializes the tile set.
     */
    public TileFactory() {

    }

    /**
     * Creates the full set of tiles, including numerical tiles for three suits (TONG, TIAO, WAN),
     * wind tiles (East, South, West, North), and dragon tiles (ZHONG, FA, BAI). Each type of tile is
     * created in multiples as per traditional Mahjong rules. The tiles are then shuffled to randomize the order.
     *
     * @return a list of initialized tiles ready for game use.
     */
    public List<Tile> createTiles() {
        tiles = new ArrayList<>();
        createDragonTiles();
        createNumberTiles();
        createWindTiles();
        return tiles;
    }

    /**
     * Creates dragon tiles with each type occurring four times as per Mahjong rules.
     * Types include ZHONG, FA, and BAI.
     */
    private void createDragonTiles() {
        String[] dragonTypes = {"ZHONG", "FA", "BAI"};
        for (String type : dragonTypes) {
            for (int i = 0; i < 4; i++) {
                tiles.add(new WindAndDragonTile(type, Suit.DRAGON));
            }
        }
    }

    /**
     * Creates number tiles for three suits: TONG, TIAO, and WAN. Each suit contains tiles
     * ranked from 1 to 9, with four tiles of each rank as per Mahjong rules.
     */
    private void createNumberTiles() {
        for (Suit suit : new Suit[]{Suit.TONG, Suit.TIAO, Suit.WAN}) {
            for (int rank = 1; rank <= 9; rank++) {
                for (int i = 0; i < 4; i++) {
                    tiles.add(new NumberTile(rank, suit));
                }
            }
        }
    }

    /**
     * Creates wind tiles with each wind direction (East, South, West, North) occurring four times.
     */
    private void createWindTiles() {
        String[] windTypes = {"East", "South", "West", "North"};
        for (String type : windTypes) {
            for (int i = 0; i < 4; i++) {
                tiles.add(new WindAndDragonTile(type, Suit.WIND));
            }
        }
    }

    /**
     * Returns a list of all tiles currently in the factory's inventory, already shuffled.
     *
     * @return a shuffled list of all tiles created by the factory.
     */
    public List<Tile> getTiles() {
        return tiles;
    }

}
