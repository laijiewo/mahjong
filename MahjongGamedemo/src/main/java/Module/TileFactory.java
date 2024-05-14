package Module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Generates a complete set of tiles for games such as Mahjong. This factory creates all the
 * necessary tiles including numerical tiles and special tiles such as winds and dragons.
 * After creation, the tiles are shuffled to ensure randomness.
 *
 * @author Yixin Niu
 */
public class TileFactory {
    private List<Tile> tiles;
    /**
     * Constructs a new TileFactory and initializes the tile set.
     */
    public TileFactory() {
        tiles = new ArrayList<>();
        createTile();
    }

    /**
     * Creates the full set of tiles, including numerical tiles for three suits (TONG, TIAO, WAN),
     * wind tiles (East, South, West, North), and dragon tiles (ZHONG, FA, BAI). Each type of tile is
     * created in multiples as per traditional Mahjong rules.
     */
    private void createTile() {
        for (Suit suit : new Suit[]{Suit.TONG, Suit.TIAO, Suit.WAN}) {
            for (int rank = 1; rank <= 9; rank++) {
                for (int i = 0; i < 4; i++) {
                    tiles.add(new NumberTile(rank, suit));
                }
            }
        }
        String[] windTypes = {"East", "South", "West", "North"};
        for (String type : windTypes) {
            for (int i = 0; i < 4; i++) {
                tiles.add(new WindAndDragonTile(type, Suit.WIND));
            }
        }
        String[] dragonTypes = {"ZHONG", "FA", "BAI"};
        for (String type : dragonTypes) {
            for (int i = 0; i < 4; i++) {
                tiles.add(new WindAndDragonTile(type, Suit.DRAGON));
            }
        }
        Collections.shuffle(tiles);
    }

    /**
     * Returns a list of all tiles currently in the factory's inventory.
     * The tiles are returned in a shuffled state.
     *
     * @return a shuffled list of all tiles created by the factory.
     */
    public List<Tile> getTiles(){
        return tiles;
    }

}
