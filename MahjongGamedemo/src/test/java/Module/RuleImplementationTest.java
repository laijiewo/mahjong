//package Module;
//
//import Module.Rule.RuleImplementation;
//import Module.Tile.NumberTile;
//import Module.Tile.Tile;
//import Module.Tile.Suit;
//import Module.Tile.WindAndDragonTile;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class RuleImplementationTest {
//
//    private Tile hunTile;
//    private RuleImplementation ruleImplementation;
//    private List<Tile> hand;
//
//    @BeforeEach
//    void setUp() {
//        //hunTile = new NumberTile(1, Suit.WAN); // Let's assume the hun tile is 1 WAN
//        hunTile=new WindAndDragonTile("West", Suit.WIND);
//        ruleImplementation = new RuleImplementation(hunTile);
//        hand = new ArrayList<>();
//    }
//
//    /*
//    WAN之间的吃
//     */
//    @Test
//    void testCanChi1() {
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//
//        assertTrue(ruleImplementation.canChi(hand, new NumberTile(1, Suit.WAN))); // 123 WAN
//        assertFalse(ruleImplementation.canChi(hand, new NumberTile(5, Suit.WAN))); // No 456 WAN
//    }
//
//    /*
//    TIAO之间的吃
//     */
//    @Test
//    void testCanChi2() {
//        hand.add(new NumberTile(3, Suit.TIAO));
//        hand.add(new NumberTile(4, Suit.TIAO));
//
//        assertFalse(ruleImplementation.canChi(hand, new NumberTile(1, Suit.TIAO))); // no 134 WAN
//        assertTrue(ruleImplementation.canChi(hand, new NumberTile(5, Suit.TIAO))); // 456 WAN
//    }
//
//    /*
//    TONG之间的吃
//     */
//    @Test
//    void testCanChi3() {
//        hand.add(new NumberTile(7, Suit.TONG));
//        hand.add(new NumberTile(8, Suit.TONG));
//
//        assertTrue(ruleImplementation.canChi(hand, new NumberTile(6, Suit.TONG))); // 123 TONG
//        assertFalse(ruleImplementation.canChi(hand, new NumberTile(5, Suit.TONG))); // No 456 WAN
//    }
//
//    /*
//    TIAO和其他牌的吃
//     */
//    @Test
//    void testCanChi4() {
//        hand.add(new NumberTile(2, Suit.TIAO));
//        hand.add(new NumberTile(3, Suit.TIAO));
//
//        assertTrue(ruleImplementation.canChi(hand, new NumberTile(1, Suit.TIAO))); // 123 TIAO
//        assertFalse(ruleImplementation.canChi(hand, new NumberTile(4, Suit.WAN))); // No 456 TIAO
//    }
//
//    /*
//    混牌不可以吃
//     */
//    @Test
//    void testCanChi5() {
//        hand.add(new NumberTile(2, Suit.TIAO));
//        hand.add(new NumberTile(3, Suit.TIAO));
//
//        assertTrue(ruleImplementation.canChi(hand, new NumberTile(1, Suit.TIAO))); // 123 TIAO
//        assertFalse(ruleImplementation.canChi(hand, new NumberTile(1, Suit.WAN))); // No 456 TIAO
//    }
//    /*
//    WIND不能吃
//    */
//    @Test
//    void testCanChi6() {
//        hand.add(new WindAndDragonTile("East",Suit.WIND));
//        hand.add(new WindAndDragonTile("East",Suit.WIND));
//
//        assertFalse(ruleImplementation.canChi( hand,new WindAndDragonTile("East",Suit.WIND)));
//        assertFalse(ruleImplementation.canChi( hand,new WindAndDragonTile("West",Suit.WIND)));
//    }
//
//    /*
//    DRAGON不能吃
//     */
//    @Test
//    void testCanChi7() {
//        hand.add(new WindAndDragonTile("ZHONG",Suit.DRAGON));
//        hand.add(new WindAndDragonTile("ZHONG",Suit.DRAGON));
//
//        assertFalse(ruleImplementation.canChi( hand,new WindAndDragonTile("ZHONG",Suit.DRAGON)));
//        assertFalse(ruleImplementation.canChi( hand,new WindAndDragonTile("BAI",Suit.DRAGON)));
//    }
//
//    /*
//    WAN之间碰测试
//     */
//    @Test
//    void testCanPeng1() {
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        assertTrue(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.WAN))); // 222 WAN
//        assertFalse(ruleImplementation.canPeng(hand, new NumberTile(3, Suit.WAN))); // No 33 WAN
//    }
//
//    /*
//    TIAO之间碰测试
//     */
//    @Test
//    void testCanPeng2() {
//        hand.add(new NumberTile(2, Suit.TIAO));
//        hand.add(new NumberTile(2, Suit.TIAO));
//        assertFalse(ruleImplementation.canPeng(hand, new NumberTile(1, Suit.TIAO))); // 222 WAN
//        assertTrue(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.TIAO))); // No 33 WAN
//    }
//
//    /*
//    TONG之间碰测试
//     */
//    @Test
//    void testCanPeng3() {
//        hand.add(new NumberTile(2, Suit.TONG));
//        hand.add(new NumberTile(2, Suit.TONG));
//        assertFalse(ruleImplementation.canPeng(hand, new NumberTile(1, Suit.TONG))); // 222 WAN
//        assertTrue(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.TONG))); // No 33 WAN
//    }
//
//    /*
//    WIND之间的碰
//     */
//    @Test
//    void testCanPeng4() {
//        hand.add(new WindAndDragonTile("East", Suit.WIND));
//        hand.add(new WindAndDragonTile("East", Suit.WIND));
//        assertTrue(ruleImplementation.canPeng(hand, new WindAndDragonTile("East", Suit.WIND))); // 222 WAN
//        assertFalse(ruleImplementation.canPeng(hand, new WindAndDragonTile("West", Suit.WIND))); // 222 WAN
//    }
//
//    /*
//    DRAGON之间的碰
//     */
//    @Test
//    void testCanPeng5() {
//        hand.add(new WindAndDragonTile("FA", Suit.DRAGON));
//        hand.add(new WindAndDragonTile("FA", Suit.DRAGON));
//        assertTrue(ruleImplementation.canPeng(hand, new WindAndDragonTile("FA", Suit.DRAGON))); // 222 WAN
//        assertFalse(ruleImplementation.canPeng(hand, new WindAndDragonTile("BAI", Suit.DRAGON))); // 222 WAN
//    }
//
//    /*
//    混不能碰
//     */
//    @Test
//    void testCanPeng6() {
//        hand.add(new NumberTile(2, Suit.TONG));
//        hand.add(new NumberTile(2, Suit.TONG));
//        assertFalse(ruleImplementation.canPeng(hand, new NumberTile(1, Suit.WAN))); // 222 WAN
//        assertTrue(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.TONG))); // No 33 WAN
//    }
//    /*
//    不同字牌之间的碰
//    */
//    @Test
//    void testCanPeng7() {
//        hand.add(new NumberTile(2, Suit.TONG));
//        hand.add(new NumberTile(2, Suit.TONG));
//        assertFalse(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.WAN))); // 222 WAN
//        assertTrue(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.TONG))); // No 33 WAN
//    }
//
//    /*
//    WIND 和其他牌碰
//     */
//    @Test
//    void testCanPeng8() {
//        hand.add(new WindAndDragonTile("East", Suit.WIND));
//        hand.add(new WindAndDragonTile("East", Suit.WIND));
//        assertTrue(ruleImplementation.canPeng(hand, new WindAndDragonTile("East", Suit.WIND))); // 222 WAN
//        assertFalse(ruleImplementation.canPeng(hand, new WindAndDragonTile("West", Suit.WIND))); // 222 WAN
//    }
//
//    /*
//    DRAGON 和其他牌碰
//     */
//    @Test
//    void testCanPeng9() {
//        hand.add(new WindAndDragonTile("BAI", Suit.DRAGON));
//        hand.add(new WindAndDragonTile("BAI", Suit.DRAGON));
//        assertTrue(ruleImplementation.canPeng(hand, new WindAndDragonTile("BAI", Suit.DRAGON))); // 222 WAN
//        assertFalse(ruleImplementation.canPeng(hand, new WindAndDragonTile("FA", Suit.DRAGON))); // 222 WAN
//    }
//
//    /*
//    WAN之间的杠
//     */
//    @Test
//    void testCanGang1() {
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//
//        assertTrue(ruleImplementation.canGang(hand, new NumberTile(2, Suit.WAN))); // 2222 WAN
//        assertFalse(ruleImplementation.canGang(hand, new NumberTile(3, Suit.WAN))); // No 333 WAN
//    }
//
//    /*
//    TONG之间的杠
//     */
//    @Test
//    void testCanGang2() {
//        hand.add(new NumberTile(2, Suit.TONG));
//        hand.add(new NumberTile(2, Suit.TONG));
//        hand.add(new NumberTile(2, Suit.TONG));
//
//        assertTrue(ruleImplementation.canGang(hand, new NumberTile(2, Suit.TONG))); // 2222 WAN
//        assertFalse(ruleImplementation.canGang(hand, new NumberTile(3, Suit.TONG))); // No 333 WAN
//    }
//
//    /*
//    TIAO之间的杠
//     */
//    @Test
//    void testCanGang3() {
//        hand.add(new NumberTile(2, Suit.TIAO));
//        hand.add(new NumberTile(2, Suit.TIAO));
//        hand.add(new NumberTile(2, Suit.TIAO));
//
//        assertTrue(ruleImplementation.canGang(hand, new NumberTile(2, Suit.TIAO))); // 2222 WAN
//        assertFalse(ruleImplementation.canGang(hand, new NumberTile(3, Suit.TIAO))); // No 333 WAN
//    }
//
//    /*
//    不同字牌之间的杠
//     */
//    @Test
//    void testCanGang4() {
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//
//        assertTrue(ruleImplementation.canGang(hand, new NumberTile(2, Suit.WAN))); // 2222 WAN
//        assertFalse(ruleImplementation.canGang(hand, new NumberTile(2, Suit.TONG))); // No 333 WAN
//    }
//
//    /*
//    混牌不能杠
//     */
//    @Test
//    void testCanGang5() {
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//
//        assertTrue(ruleImplementation.canGang(hand, new NumberTile(2, Suit.WAN))); // 2222 WAN
//        assertFalse(ruleImplementation.canGang(hand, new NumberTile(1, Suit.WAN))); // No 333 WAN
//    }
//
//    /*
//    DRAGON 之间的杠
//     */
//    @Test
//    void testCanGang6() {
//        hand.add(new WindAndDragonTile("BAI", Suit.DRAGON));
//        hand.add(new WindAndDragonTile("BAI", Suit.DRAGON));
//        hand.add(new WindAndDragonTile("BAI", Suit.DRAGON));
//
//        assertTrue(ruleImplementation.canGang(hand,new WindAndDragonTile("BAI", Suit.DRAGON))); // 2222 WAN
//        assertFalse(ruleImplementation.canGang(hand,new WindAndDragonTile("FA", Suit.DRAGON))); // 2222 WAN
//    }
//
//    /*
//    WIND之间的杠
//     */
//    @Test
//    void testCanGang7() {
//        hand.add(new WindAndDragonTile("East", Suit.WIND));
//        hand.add(new WindAndDragonTile("East", Suit.WIND));
//        hand.add(new WindAndDragonTile("East", Suit.WIND));
//
//        assertTrue(ruleImplementation.canGang(hand,new WindAndDragonTile("East", Suit.WIND))); // 2222 WAN
//        assertFalse(ruleImplementation.canGang(hand,new WindAndDragonTile("West", Suit.WIND))); // 2222 WAN
//    }
//    /*
//    DRAGON和其他牌杠
//     */
//
//    @Test
//    void testCanGang8() {
//        hand.add(new WindAndDragonTile("BAI", Suit.DRAGON));
//        hand.add(new WindAndDragonTile("BAI", Suit.DRAGON));
//        hand.add(new WindAndDragonTile("BAI", Suit.DRAGON));
//
//        assertTrue(ruleImplementation.canGang(hand,new WindAndDragonTile("BAI", Suit.DRAGON))); // 2222 WAN
//        assertFalse(ruleImplementation.canGang(hand, new NumberTile(3, Suit.WAN))); // No 333 WAN
//    }
//
//    /*
//    WIND和其他牌杠
//     */
//    @Test
//    void testCanGang9() {
//        hand.add(new WindAndDragonTile("East", Suit.WIND));
//        hand.add(new WindAndDragonTile("East", Suit.WIND));
//        hand.add(new WindAndDragonTile("East", Suit.WIND));
//
//        assertTrue(ruleImplementation.canGang(hand,new WindAndDragonTile("East", Suit.WIND))); // 2222 WAN
//        assertFalse(ruleImplementation.canGang(hand, new NumberTile(3, Suit.WAN))); // No 333 WAN
//    }
//
//    @Test
//    void testChiTilesRemovedFromHand() {
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        assertTrue(ruleImplementation.canChi(hand, new NumberTile(1, Suit.WAN))); // 123 WAN
//
//        List<Tile> expectedHand = new ArrayList<>();
//        assertEquals(expectedHand, hand);
//    }
//
//    /*
//    测试碰操作后手牌的变化
//    */
//    @Test
//    void testPengTilesRemovedFromHand() {
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        assertTrue(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.WAN))); // 222 WAN
//
//        List<Tile> expectedHand = new ArrayList<>();
//        // No tiles should be left in hand after Peng
//        assertEquals(expectedHand, hand);
//    }
//
//    /*
//    测试杠操作后手牌的变化
//    */
//    @Test
//    void testGangTilesRemovedFromHand() {
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        hand.add(new NumberTile(2, Suit.WAN));
//        assertTrue(ruleImplementation.canGang(hand, new NumberTile(2, Suit.WAN))); // 2222 WAN
//
//        List<Tile> expectedHand = new ArrayList<>();
//        // No tiles should be left in hand after Gang
//        assertEquals(expectedHand, hand);
//    }
//    /*
//    有混牌，
//     */
//
//        /**
//         * Test for a winning hand with a simple sequence and pair.
//         */
//        @Test
//        void testCanHu1() {
//            hand.add(new NumberTile(9, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.WAN));
//            hand.add(new NumberTile(6, Suit.WAN));
//            hand.add(new NumberTile(7, Suit.WAN));
//            hand.add(new NumberTile(8, Suit.WAN));
//            hand.add(new NumberTile(6, Suit.TIAO));
//            hand.add(new NumberTile(1, Suit.TIAO));
//            hand.add(new NumberTile(2, Suit.TIAO));
//            hand.add(new NumberTile(3, Suit.TIAO));
//            hand.add(new NumberTile(4, Suit.TIAO));
//            hand.add(new NumberTile(5, Suit.TIAO));
//
////            assertTrue(ruleImplementation.canHu(hand, new NumberTile(9, Suit.WAN))); // Winning hand with 23456789 WAN and 123456 TIAO
////            assertFalse(ruleImplementation.canHu(hand, new NumberTile(7, Suit.TIAO))); // Not a winning hand
//        }
//
//        /**
//         * Test for a winning hand with a meld and pair.
//         */
//        @Test
//        void testCanHu2() {
//            hand.add(new NumberTile(1, Suit.WAN));
//            hand.add(new NumberTile(1, Suit.WAN));
//            hand.add(new NumberTile(1, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.WAN));
//
////            assertTrue(ruleImplementation.canHu(hand, new NumberTile(4, Suit.WAN))); // Winning hand with melds and a pair
//        }
//
//        /**
//         * Test for a winning hand with mixed sequences and triplets.
//         */
//        @Test
//        void testCanHu3() {
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.WAN));
//            hand.add(new NumberTile(6, Suit.WAN));
//            hand.add(new NumberTile(7, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.TIAO));
//
////            assertTrue(ruleImplementation.canHu(hand, new NumberTile(5, Suit.TIAO))); // Winning hand with mixed sequences and triplets
//        }
//
//        /**
//         * Test for a hand that is not a winning hand.
//         */
//        @Test
//        void testCannotHu() {
//            hand.add(new NumberTile(7, Suit.WAN));
//            hand.add(new NumberTile(7, Suit.WAN));
//            hand.add(new NumberTile(7, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.WAN));
//            hand.add(new NumberTile(6, Suit.TONG));
//
////            assertFalse(ruleImplementation.canHu(hand, new NumberTile(5, Suit.WAN))); // Not a winning hand
//        }
//
//        /**
//         * Test for a winning hand with mixed sequences and pairs.
//         */
//        @Test
//        void testCanHu4() {
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//
//            hand.add(new NumberTile(5, Suit.WAN));
//            hand.add(new NumberTile(6, Suit.WAN));
//            hand.add(new NumberTile(7, Suit.WAN));
//
//            hand.add(new NumberTile(8, Suit.WAN));
//            hand.add(new NumberTile(8, Suit.WAN));
//
//            hand.add(new NumberTile(1, Suit.TIAO));
//            hand.add(new NumberTile(1, Suit.TIAO));
//            hand.add(new NumberTile(2, Suit.TIAO));
//            hand.add(new NumberTile(2, Suit.TIAO));
//            hand.add(new NumberTile(3, Suit.TIAO));
//
////            assertTrue(ruleImplementation.canHu(hand, new NumberTile(3, Suit.TIAO))); // Winning hand with mixed sequences and pairs
//        }
//
//
//        /**
//         * Test for a winning hand with a simple sequence and pair, including hun tile.
//         */
//        @Test
//        void testCanHuWithHun1() {
//            hand.add(new NumberTile(9, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.WAN));
//            hand.add(new NumberTile(6, Suit.WAN));
//            hand.add(new NumberTile(7, Suit.WAN));
//            hand.add(new NumberTile(8, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.TIAO));
//            hand.add(new NumberTile(1, Suit.TIAO));
//            hand.add(new NumberTile(2, Suit.TIAO));
//            hand.add(new NumberTile(3, Suit.TIAO));
//            hand.add(new NumberTile(4, Suit.TIAO));
//            hand.add(hunTile); // Add a hun tile to the hand
//
////            assertTrue(ruleImplementation.canHu(hand, new NumberTile(9, Suit.WAN))); // Winning hand with 23456789 WAN, 12345 TIAO and hun tile as pair
////            assertFalse(ruleImplementation.canHu(hand, new NumberTile(7, Suit.TIAO))); // Not a winning hand
//        }
//
//        /**
//         * Test for a winning hand with a meld and pair, including hun tile.
//         */
//        @Test
//        void testCanHuWithHun2() {
//            hand.add(new NumberTile(7, Suit.WAN));
//            hand.add(new NumberTile(7, Suit.WAN));
//            hand.add(hunTile); // Add a hun tile to the hand
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.WAN));
//
////            assertTrue(ruleImplementation.canHu(hand, new NumberTile(4, Suit.WAN))); // Winning hand with melds and a pair using hun tile
//        }
//
//        /**
//         * Test for a winning hand with mixed sequences and triplets, including hun tile.
//         */
//        @Test
//        void testCanHuWithHun3() {
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(hunTile); // Add a hun tile to the hand
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.WAN));
//            hand.add(new NumberTile(6, Suit.WAN));
//            hand.add(new NumberTile(7, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.TIAO));
//
////            assertTrue(ruleImplementation.canHu(hand, new NumberTile(6, Suit.TIAO))); // Winning hand with mixed sequences and triplets using hun tile
//        }
//
//        /**
//         * Test for a hand that is not a winning hand, including hun tile.
//         */
//        @Test
//        void testCannotHuWithHun() {
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.WAN));
//
//            hand.add(hunTile); // Add a hun tile to the hand
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//
//            hand.add(new NumberTile(5, Suit.WAN));
//            hand.add(new NumberTile(6, Suit.WAN));
//
//            hand.add(new NumberTile(7, Suit.TONG));
//            hand.add(new NumberTile(7, Suit.TIAO));
//
////            assertFalse(ruleImplementation.canHu(hand, new NumberTile(3, Suit.WAN))); // Not a winning hand even with hun tile
//        }
//
//        /**
//         * Test for a winning hand with mixed sequences and pairs, including hun tile.
//         */
//        @Test
//        void testCanHuWithHun4() {
//            hand.add(new NumberTile(2, Suit.WAN));
//            hand.add(new NumberTile(3, Suit.WAN));
//            hand.add(new NumberTile(4, Suit.WAN));
//            hand.add(new NumberTile(5, Suit.WAN));
//            hand.add(new NumberTile(6, Suit.WAN));
//            hand.add(new NumberTile(7, Suit.WAN));
//            hand.add(new NumberTile(8, Suit.WAN));
//            hand.add(new NumberTile(9, Suit.WAN));
//            hand.add(new NumberTile(2, Suit.TIAO));
//            hand.add(new NumberTile(2, Suit.TIAO));
//            hand.add(new NumberTile(3, Suit.TIAO));
//            hand.add(new NumberTile(4, Suit.TIAO));
//            hand.add(hunTile); // Add a hun tile to the hand
//
////            assertTrue(ruleImplementation.canHu(hand, new NumberTile(5, Suit.TIAO))); // Winning hand with mixed sequences and pairs using hun tile
//        }
//
//
//
//
//}
