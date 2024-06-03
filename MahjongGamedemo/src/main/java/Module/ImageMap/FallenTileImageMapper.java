package Module.ImageMap;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

import Module.*;

public class FallenTileImageMapper {
    private Map<Tile, Image> imageMap = new HashMap<>();
    public FallenTileImageMapper() {
        addFallenWanMapping();
        addFallenTiaoMapping();
        addFallenTongMapping();
        addFallenWindAndDragonMapping();
    }

    public void addMapping(Tile object, String imagePath) {
        imageMap.put(object, new Image(imagePath));
    }

    public Image getImage(Tile object) {
        return imageMap.get(object);
    }

    public Map<Tile, Image> getImageMap() {
        return imageMap;

    }

    private void addFallenWanMapping() {
        Tile oneWan = new NumberTile(1, Suit.WAN);
        addMapping(oneWan, "/mj/mj_73.png");
        Tile twoWan = new NumberTile(2, Suit.WAN);
        addMapping(twoWan, "/mj/mj_74.png");
        Tile threeWan = new NumberTile(3, Suit.WAN);
        addMapping(threeWan, "/mj/mj_75.png");
        Tile fourWan = new NumberTile(4, Suit.WAN);
        addMapping(fourWan, "/mj/mj_76.png");
        Tile fiveWan = new NumberTile(5, Suit.WAN);
        addMapping(fiveWan, "/mj/mj_77.png");
        Tile sixWan = new NumberTile(6, Suit.WAN);
        addMapping(sixWan, "/mj/mj_78.png");
        Tile sevenWan = new NumberTile(7, Suit.WAN);
        addMapping(sevenWan, "/mj/mj_79.png");
        Tile eightWan = new NumberTile(8, Suit.WAN);
        addMapping(eightWan, "/mj/mj_80.png");
        Tile nineWan = new NumberTile(9, Suit.WAN);
        addMapping(nineWan, "/mj/mj_81.png");
    }

    private void addFallenTiaoMapping() {
        Tile oneTiao = new NumberTile(1, Suit.TIAO);
        addMapping(oneTiao, "/mj/mj_82.png");
        Tile twoTiao = new NumberTile(2, Suit.TIAO);
        addMapping(twoTiao, "/mj/mj_83.png");
        Tile threeTiao = new NumberTile(3, Suit.TIAO);
        addMapping(threeTiao, "/mj/mj_84.png");
        Tile fourTiao = new NumberTile(4, Suit.TIAO);
        addMapping(fourTiao, "/mj/mj_85.png");
        Tile fiveTiao = new NumberTile(5, Suit.TIAO);
        addMapping(fiveTiao, "/mj/mj_86.png");
        Tile sixTiao = new NumberTile(6, Suit.TIAO);
        addMapping(sixTiao, "/mj/mj_87.png");
        Tile sevenTiao = new NumberTile(7, Suit.TIAO);
        addMapping(sevenTiao, "/mj/mj_88.png");
        Tile eightTiao = new NumberTile(8, Suit.TIAO);
        addMapping(eightTiao, "/mj/mj_89.png");
        Tile nineTiao = new NumberTile(9, Suit.TIAO);
        addMapping(nineTiao, "/mj/mj_90.png");
    }
    private void addFallenTongMapping() {
        Tile oneTong = new NumberTile(1, Suit.TONG);
        addMapping(oneTong, "/mj/mj_91.png");
        Tile twoTong = new NumberTile(2, Suit.TONG);
        addMapping(twoTong, "/mj/mj_92.png");
        Tile threeTong = new NumberTile(3, Suit.TONG);
        addMapping(threeTong, "/mj/mj_93.png");
        Tile fourTong = new NumberTile(4, Suit.TONG);
        addMapping(fourTong, "/mj/mj_94.png");
        Tile fiveTong =  new NumberTile(5, Suit.TONG);
        addMapping(fiveTong, "/mj/mj_95.png");
        Tile sixTong = new NumberTile(6, Suit.TONG);
        addMapping(sixTong, "/mj/mj_96.png");
        Tile sevenTong = new NumberTile(7, Suit.TONG);
        addMapping(sevenTong, "/mj/mj_97.png");
        Tile eightTong = new NumberTile(8, Suit.TONG);
        addMapping(eightTong, "/mj/mj_98.png");
        Tile nineTong = new NumberTile(9, Suit.TONG);
        addMapping(nineTong, "/mj/mj_99.png");
    }
    private void addFallenWindAndDragonMapping() {
        Tile EastWind = new WindAndDragonTile("East", Suit.WIND);
        addMapping(EastWind, "/mj/mj_100.png");
        Tile SouthWind = new WindAndDragonTile("South", Suit.WIND);
        addMapping(SouthWind, "/mj/mj_101.png");
        Tile WestWind = new WindAndDragonTile("West", Suit.WIND);
        addMapping(WestWind, "/mj/mj_102.png");
        Tile NorthWind = new WindAndDragonTile("North", Suit.WIND);
        addMapping(NorthWind, "/mj/mj_103.png");
        Tile Zhong = new WindAndDragonTile("ZHONG", Suit.DRAGON);
        addMapping(Zhong, "/mj/mj_106.png");
        Tile Fa = new WindAndDragonTile("FA", Suit.DRAGON);
        addMapping(Fa, "/mj/mj_105.png");
        Tile Bai = new WindAndDragonTile("BAI", Suit.DRAGON);
        addMapping(Bai, "/mj/mj_104.png");
    }
}
