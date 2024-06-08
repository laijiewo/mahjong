package Module.ImageMap;

import Module.Tile.NumberTile;
import Module.Tile.Tile;
import Module.Tile.Suit;
import Module.Tile.WindAndDragonTile;
import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;
/**
 * The TileImageMapper class maps Mahjong tiles to their corresponding images.
 */
public class TileImageMapper {
    private Map<Tile, Image> imageMap = new HashMap<>();
    /**
     * Constructs a TileImageMapper and initializes the mappings for all tile types.
     */
    public TileImageMapper() {
        addWanMapping();
        addTiaoMapping();
        addTongMapping();
        addWindAndDragonMapping();
    }

    /**
     * Adds a mapping between a tile and its image.
     *
     * @param object The tile to be mapped.
     * @param imagePath The path to the image of the tile.
     */
    public void addMapping(Tile object, String imagePath) {
        imageMap.put(object, new Image(imagePath));
    }

    /**
     * Retrieves the image corresponding to a tile.
     *
     * @param object The tile whose image is to be retrieved.
     * @return The image corresponding to the tile.
     */
    public Image getImage(Tile object) {
        return imageMap.get(object);
    }

    /**
     * Gets the map of tiles and their corresponding images.
     *
     * @return The map of tiles and their corresponding images.
     */
    public Map<Tile, Image> getImageMap() {
        return imageMap;

    }

    /**
     * Adds mappings for Wan (Character) tiles.
     */
    private void addWanMapping() {
        Tile oneWan = new NumberTile(1, Suit.WAN);
        addMapping(oneWan, "/mj/mj_28.png");
        Tile twoWan = new NumberTile(2, Suit.WAN);
        addMapping(twoWan, "/mj/mj_29.png");
        Tile threeWan = new NumberTile(3, Suit.WAN);
        addMapping(threeWan, "/mj/mj_30.png");
        Tile fourWan = new NumberTile(4, Suit.WAN);
        addMapping(fourWan, "/mj/mj_31.png");
        Tile fiveWan = new NumberTile(5, Suit.WAN);
        addMapping(fiveWan, "/mj/mj_32.png");
        Tile sixWan = new NumberTile(6, Suit.WAN);
        addMapping(sixWan, "/mj/mj_33.png");
        Tile sevenWan = new NumberTile(7, Suit.WAN);
        addMapping(sevenWan, "/mj/mj_34.png");
        Tile eightWan = new NumberTile(8, Suit.WAN);
        addMapping(eightWan, "/mj/mj_35.png");
        Tile nineWan = new NumberTile(9, Suit.WAN);
        addMapping(nineWan, "/mj/mj_36.png");
    }

    /**
     * Adds mappings for Tiao (Bamboo) tiles.
     */
    private void addTiaoMapping() {
        Tile oneTiao = new NumberTile(1, Suit.TIAO);
        addMapping(oneTiao, "/mj/mj_37.png");
        Tile twoTiao = new NumberTile(2, Suit.TIAO);
        addMapping(twoTiao, "/mj/mj_38.png");
        Tile threeTiao = new NumberTile(3, Suit.TIAO);
        addMapping(threeTiao, "/mj/mj_39.png");
        Tile fourTiao = new NumberTile(4, Suit.TIAO);
        addMapping(fourTiao, "/mj/mj_40.png");
        Tile fiveTiao = new NumberTile(5, Suit.TIAO);
        addMapping(fiveTiao, "/mj/mj_41.png");
        Tile sixTiao = new NumberTile(6, Suit.TIAO);
        addMapping(sixTiao, "/mj/mj_42.png");
        Tile sevenTiao = new NumberTile(7, Suit.TIAO);
        addMapping(sevenTiao, "/mj/mj_43.png");
        Tile eightTiao = new NumberTile(8, Suit.TIAO);
        addMapping(eightTiao, "/mj/mj_44.png");
        Tile nineTiao = new NumberTile(9, Suit.TIAO);
        addMapping(nineTiao, "/mj/mj_45.png");
    }

    /**
     * Adds mappings for Tong (Circle) tiles.
     */
    private void addTongMapping() {
        Tile oneTong = new NumberTile(1, Suit.TONG);
        addMapping(oneTong, "/mj/mj_46.png");
        Tile twoTong = new NumberTile(2, Suit.TONG);
        addMapping(twoTong, "/mj/mj_47.png");
        Tile threeTong = new NumberTile(3, Suit.TONG);
        addMapping(threeTong, "/mj/mj_48.png");
        Tile fourTong = new NumberTile(4, Suit.TONG);
        addMapping(fourTong, "/mj/mj_49.png");
        Tile fiveTong =  new NumberTile(5, Suit.TONG);
        addMapping(fiveTong, "/mj/mj_50.png");
        Tile sixTong = new NumberTile(6, Suit.TONG);
        addMapping(sixTong, "/mj/mj_51.png");
        Tile sevenTong = new NumberTile(7, Suit.TONG);
        addMapping(sevenTong, "/mj/mj_52.png");
        Tile eightTong = new NumberTile(8, Suit.TONG);
        addMapping(eightTong, "/mj/mj_53.png");
        Tile nineTong = new NumberTile(9, Suit.TONG);
        addMapping(nineTong, "/mj/mj_54.png");
    }

    /**
     * Adds mappings for Wind and Dragon tiles.
     */
    private void addWindAndDragonMapping() {
        Tile EastWind = new WindAndDragonTile("East", Suit.WIND);
        addMapping(EastWind, "/mj/mj_55.png");
        Tile SouthWind = new WindAndDragonTile("South", Suit.WIND);
        addMapping(SouthWind, "/mj/mj_56.png");
        Tile WestWind = new WindAndDragonTile("West", Suit.WIND);
        addMapping(WestWind, "/mj/mj_57.png");
        Tile NorthWind = new WindAndDragonTile("North", Suit.WIND);
        addMapping(NorthWind, "/mj/mj_58.png");
        Tile Zhong = new WindAndDragonTile("ZHONG", Suit.DRAGON);
        addMapping(Zhong, "/mj/mj_61.png");
        Tile Fa = new WindAndDragonTile("FA", Suit.DRAGON);
        addMapping(Fa, "/mj/mj_60.png");
        Tile Bai = new WindAndDragonTile("BAI", Suit.DRAGON);
        addMapping(Bai, "/mj/mj_59.png");
    }
}