//package Module;
//
//import Module.Game.GameBoard;
//import Module.Game.MahjongGame;
//import Module.Game.Player;
//import Module.Tile.*;
//import Module.Tile.Tile;
//import Module.Tile.Suit;
//import Module.utils.FanCalculator;
//import javafx.stage.Stage;
//import org.junit.Before;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//public class FanCalculatorTest {
//    private final Tile hunTile = new NumberTile(1, Suit.WAN); // 假设混儿牌是1万
//    private final FanCalculator fanCalculator = new FanCalculator(hunTile);
//    private GameBoard gameBoard;
//    private Stage stage;
//    Player west;
//    Player north;
//    Player south;
//    Player east;
//    @BeforeEach
//    void setUp() {
//        gameBoard = new GameBoard();
//        gameBoard.shuffleTiles();
//        west=new Player(stage);
//        north=new Player(stage);
//        south=new Player(stage);
//        east=new Player(stage);
//        List<Player> players = new ArrayList<>();
//        players.add(west);
//        players.add(north);
//        players.add(south);
//        players.add(east);
//
//        gameBoard.dealAllTiles();
//
//    }
//    @Test
//    void testCalculateSelfDrawFan() {
//        //zimo, menqing,duiduihu,qingyise
//        List<Tile> hand = new ArrayList<>();
//        System.out.println("Tile count1 " +  MahjongGame.getTileCountInTheTileWall());
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        System.out.println("Tile count2 " +  MahjongGame.getTileCountInTheTileWall());
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//
//        Tile finalTile = new NumberTile(6, Suit.WAN);
//        int fan = fanCalculator.calculateFan(hand, true, false, finalTile);
//        assertEquals(6, fan); // 自摸
//    }
//
//    @Test
//    void testCalculateMenQingFan() {
//        List<Tile> hand = new ArrayList<>();
//        //menqing and yitiaolong
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(7, Suit.WAN));
//        hand.add(new NumberTile(8, Suit.WAN));
//        hand.add(new NumberTile(9, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.TIAO));
//        hand.add(new NumberTile(2, Suit.TIAO));
//        hand.add(new NumberTile(7, Suit.TONG));
//        hand.add(new NumberTile(7, Suit.TONG));
//
//        Tile finalTile = new NumberTile(3, Suit.TIAO);
//        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
//        assertEquals(3, fan); // 门清
//    }
//
//    @Test
//    void testCalculateDuiDuiHuFan() {
//        //menqing,duiduihu,zhuawukui,qingyise
//        List<Tile> hand = new ArrayList<>();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//
//        Tile finalTile = new NumberTile(5, Suit.WAN);
//        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
//        assertEquals(6, fan); // 对对胡
//    }
//
//    @Test
//    void testCalculateZhuaWuKuiFan() {
//        //menqing duiduihu zhuawukui qingyise
//        List<Tile> hand = new ArrayList<>();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//
//        Tile finalTile = new NumberTile(5, Suit.WAN);
//        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
//        assertEquals(6, fan); // 捉五魁
//    }
//
//    @Test
//    void testCalculateHaiDiLaoYueFan() {
//        //zimo,qixiaodui,qingyise
//        List<Tile> hand = new ArrayList<>();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(7, Suit.WAN));
//
//        Tile finalTile = new NumberTile(7, Suit.WAN);
//        int fan = fanCalculator.calculateFan(hand, true, false, finalTile);
//        assertEquals(6, fan); // 海底捞月
//    }
//
//    @Test
//    void testCalculateYiTiaoLongFan() {
//        //yitiaolong menqing
//        List<Tile> hand = new ArrayList<>();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        for (int i = 1; i <= 9; i++) {
//            hand.add(new NumberTile(i, Suit.WAN));
//        }
//        hand.add(new NumberTile(1, Suit.TIAO));
//        hand.add(new NumberTile(1, Suit.TIAO));
//        hand.add(new NumberTile(1, Suit.TIAO));
//        hand.add(new NumberTile(2, Suit.TIAO));
//
//        Tile finalTile = new NumberTile(2, Suit.TIAO);
//        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
//        assertEquals(3, fan); // 一条龙
//    }
//
//    @Test
//    void testCalculateQiXiaoDuiFan() {
//        //qixiaodui qingyise
//        List<Tile> hand = new ArrayList<>();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(7, Suit.WAN));
//
//        Tile finalTile = new NumberTile(7, Suit.WAN);
//        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
//        assertEquals(5, fan); // 七小对
//    }
//
//    @Test
//    void testCalculateHaoHuaQiDuiFan() {
//        List<Tile> hand = new ArrayList<>();
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(7, Suit.WAN));
//        hand.add(new NumberTile(7, Suit.WAN));
//        hand.add(new NumberTile(7, Suit.WAN));
//
//        Tile finalTile = new NumberTile(7, Suit.WAN);
//        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
//        assertEquals(3, fan); // 豪华七对
//    }
//
//    @Test
//    void testCalculateQingYiSeFan() {
//        //menqing,qingyise
//        List<Tile> hand = new ArrayList<>();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(9, Suit.WAN));
//
//        Tile finalTile = new NumberTile(9, Suit.WAN);
//        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
//        assertEquals(4, fan);
//    }
//
//    @Test
//    void testCalculateTianHuFan() {
//        //qingyise tianhu
//        List<Tile> hand = new ArrayList<>();
//        gameBoard.dealTiles();
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(9, Suit.WAN));
//
//        Tile finalTile = new NumberTile(9, Suit.WAN);
//        int fan = fanCalculator.calculateFan(hand, false, true, finalTile);
//        assertEquals(9, fan); // 天和
//    }
//
//    @Test
//    void testCalculateDiHuFan() {
//        List<Tile> hand = new ArrayList<>();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.TIAO));
//        hand.add(new NumberTile(5, Suit.TIAO));
//        hand.add(new NumberTile(5, Suit.TIAO));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(9, Suit.WAN));
//
//        Tile finalTile = new NumberTile(9, Suit.WAN);
//        int fan = fanCalculator.calculateFan(hand, true, false, finalTile);
//        assertEquals(5, fan); // 地和
//    }
//
//    @Test
//    void testCalculateRenHuFan() {
//        //menqing renhu
//        List<Tile> hand = new ArrayList<>();
//        gameBoard.dealTiles();
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(1, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.TIAO));
//        hand.add(new NumberTile(5, Suit.TIAO));
//        hand.add(new NumberTile(5, Suit.TIAO));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(9, Suit.TIAO));
//
//        Tile finalTile = new NumberTile(9, Suit.TIAO);
//        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
//        assertEquals(5, fan); // 人和
//    }
//
//    @Test
//    void testCalculateHunGangFan() {
//        List<Tile> hand = new ArrayList<>();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        gameBoard.dealTiles();
//        hand.add(hunTile);
//        hand.add(hunTile);
//        hand.add(hunTile);
//        hand.add(hunTile);
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.TIAO));
//        hand.add(new NumberTile(5, Suit.TIAO));
//        hand.add(new NumberTile(5, Suit.TIAO));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        Tile finalTile = new NumberTile(9, Suit.WAN);
//        int fan = fanCalculator.calculateFan(hand, true, false, finalTile);
//        assertEquals(9, fan); // 混杠
//    }
//
//}