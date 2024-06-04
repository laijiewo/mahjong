package Module;

import Module.Tile.NumberTile;
import Module.Tile.Tile;
import Module.Tile.Tile.Suit;
import Module.utils.FanCalculator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FanCalculatorTest {
    private final Tile hunTile = new NumberTile(1, Suit.WAN); // 假设混儿牌是1万
    private final FanCalculator fanCalculator = new FanCalculator(hunTile);
    @Test
    void testCalculateSelfDrawFan() {
        List<Tile> hand = new ArrayList<>();
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));

        Tile finalTile = new NumberTile(6, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, true, false, finalTile);
        assertEquals(1, fan); // 自摸
    }

    @Test
    void testCalculateMenQingFan() {
        List<Tile> hand = new ArrayList<>();
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));
        hand.add(new NumberTile(8, Suit.WAN));
        hand.add(new NumberTile(9, Suit.WAN));
        hand.add(new NumberTile(1, Suit.TIAO));
        hand.add(new NumberTile(2, Suit.TIAO));
        hand.add(new NumberTile(3, Suit.TIAO));
        hand.add(new NumberTile(7, Suit.TONG));
        hand.add(new NumberTile(7, Suit.TONG));

        Tile finalTile = new NumberTile(8, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
        assertEquals(1, fan); // 门清
    }

    @Test
    void testCalculateDuiDuiHuFan() {
        List<Tile> hand = new ArrayList<>();
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));

        Tile finalTile = new NumberTile(6, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
        assertEquals(1, fan); // 对对胡
    }

    @Test
    void testCalculateQuanQiuRenFan() {
        List<Tile> hand = new ArrayList<>();
        hand.add(new NumberTile(1, Suit.WAN));

        Tile finalTile = new NumberTile(1, Suit.WAN);
        int fan =fanCalculator.calculateFan(hand, false, false, finalTile);
        assertEquals(1, fan); // 全求人
    }

    @Test
    void testCalculateZhuaWuKuiFan() {
        List<Tile> hand = new ArrayList<>();
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));

        Tile finalTile = new NumberTile(5, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
        assertEquals(1, fan); // 捉五魁
    }

    @Test
    void testCalculateHaiDiLaoYueFan() {
        List<Tile> hand = new ArrayList<>();
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));

        Tile finalTile = new NumberTile(8, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, true, false, finalTile);
        assertEquals(1, fan); // 海底捞月
    }

    @Test
    void testCalculateYiTiaoLongFan() {
        List<Tile> hand = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            hand.add(new NumberTile(i, Suit.WAN));
        }
        hand.add(new NumberTile(1, Suit.TIAO));
        hand.add(new NumberTile(1, Suit.TIAO));
        hand.add(new NumberTile(1, Suit.TIAO));
        hand.add(new NumberTile(2, Suit.TIAO));
        hand.add(new NumberTile(2, Suit.TIAO));

        Tile finalTile = new NumberTile(2, Suit.TIAO);
        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
        assertEquals(2, fan); // 一条龙
    }

    @Test
    void testCalculateQiXiaoDuiFan() {
        List<Tile> hand = new ArrayList<>();
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));

        Tile finalTile = new NumberTile(8, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
        assertEquals(2, fan); // 七小对
    }

    @Test
    void testCalculateHaoHuaQiDuiFan() {
        List<Tile> hand = new ArrayList<>();
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(1, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));

        Tile finalTile = new NumberTile(8, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
        assertEquals(3, fan); // 豪华七对
    }

    @Test
    void testCalculateQingYiSeFan() {
        List<Tile> hand = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            hand.add(new NumberTile(i, Suit.WAN));
            hand.add(new NumberTile(i, Suit.WAN));
        }
        Tile finalTile = new NumberTile(9, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
        assertEquals(3, fan); // 清一色
    }

    @Test
    void testCalculateTianHuFan() {
        List<Tile> hand = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            hand.add(new NumberTile(i, Suit.WAN));
            hand.add(new NumberTile(i, Suit.WAN));
        }
        Tile finalTile = new NumberTile(9, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, false, true, finalTile);
        assertEquals(6, fan); // 天和
    }

    @Test
    void testCalculateDiHuFan() {
        List<Tile> hand = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            hand.add(new NumberTile(i, Suit.WAN));
            hand.add(new NumberTile(i, Suit.WAN));
        }
        Tile finalTile = new NumberTile(9, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, false, false, finalTile);
        assertEquals(5, fan); // 地和
    }

    @Test
    void testCalculateRenHuFan() {
        List<Tile> hand = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            hand.add(new NumberTile(i, Suit.WAN));
            hand.add(new NumberTile(i, Suit.WAN));
        }
        Tile finalTile = new NumberTile(9, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, true, false, finalTile);
        assertEquals(5, fan); // 人和
    }

    @Test
    void testCalculateHunGangFan() {
        List<Tile> hand = new ArrayList<>();
        hand.add(hunTile);
        hand.add(hunTile);
        hand.add(hunTile);
        hand.add(hunTile);
        for (int i = 1; i <= 9; i++) {
            hand.add(new NumberTile(i, Suit.WAN));
        }
        Tile finalTile = new NumberTile(9, Suit.WAN);
        int fan = fanCalculator.calculateFan(hand, true, false, finalTile);
        assertEquals(9, fan); // 混杠
    }

}