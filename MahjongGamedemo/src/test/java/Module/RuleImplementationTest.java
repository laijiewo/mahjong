package Module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RuleImplementationTest {

    private Tile hunTile;
    private RuleImplementation ruleImplementation;
    private List<Tile> hand;

    @BeforeEach
    void setUp() {
        hunTile = new NumberTile(1, Suit.WAN); // Let's assume the hun tile is 1 WAN
        ruleImplementation = new RuleImplementation(hunTile);
        hand = new ArrayList<>();
    }

    @Test
    void testCanChi1() {
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));

        assertTrue(ruleImplementation.canChi(hand, new NumberTile(1, Suit.WAN))); // 123 WAN
        assertFalse(ruleImplementation.canChi(hand, new NumberTile(5, Suit.WAN))); // No 456 WAN
    }

    @Test
    void testCanChi2() {
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));

        assertFalse(ruleImplementation.canChi(hand, new NumberTile(1, Suit.WAN))); // no 134 WAN
        assertTrue(ruleImplementation.canChi(hand, new NumberTile(5, Suit.WAN))); // 456 WAN
    }

    @Test
    void testCanChi3() {
        hand.add(new NumberTile(2, Suit.TONG));
        hand.add(new NumberTile(3, Suit.TONG));

        assertTrue(ruleImplementation.canChi(hand, new NumberTile(1, Suit.WAN))); // 123 TONG
        assertFalse(ruleImplementation.canChi(hand, new NumberTile(5, Suit.TONG))); // No 456 WAN
    }
    @Test
    void testCanChi4() {
        hand.add(new NumberTile(2, Suit.TIAO));
        hand.add(new NumberTile(3, Suit.TIAO));

        assertTrue(ruleImplementation.canChi(hand, new NumberTile(1, Suit.WAN))); // 123 TIAO
        assertFalse(ruleImplementation.canChi(hand, new NumberTile(5, Suit.TIAO))); // No 456 TIAO
    }

    @Test
    void testCanPeng1() {
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        assertTrue(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.WAN))); // 222 WAN
        assertFalse(ruleImplementation.canPeng(hand, new NumberTile(3, Suit.WAN))); // No 33 WAN
    }

    @Test
    void testCanPeng2() {
        hand.add(new NumberTile(2, Suit.TIAO));
        hand.add(new NumberTile(2, Suit.TIAO));
        assertFalse(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.WAN))); // 222 WAN
        assertTrue(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.TIAO))); // No 33 WAN
    }

    @Test
    void testCanPeng3() {
        hand.add(new NumberTile(2, Suit.TONG));
        hand.add(new NumberTile(2, Suit.TONG));
        assertFalse(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.WAN))); // 222 WAN
        assertTrue(ruleImplementation.canPeng(hand, new NumberTile(2, Suit.TONG))); // No 33 WAN
    }

    @Test
    void testCanPeng4() {
        hand.add(new WindAndDragonTile("East", Suit.WIND));
        hand.add(new WindAndDragonTile("East", Suit.WIND));
        assertTrue(ruleImplementation.canPeng(hand, new WindAndDragonTile("East", Suit.WIND))); // 222 WAN
        assertFalse(ruleImplementation.canPeng(hand, new NumberTile(3, Suit.WAN))); // No 33 WAN
    }

    @Test
    void testCanGang() {
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));
        hand.add(new NumberTile(2, Suit.WAN));

        assertTrue(ruleImplementation.canGang(hand, new NumberTile(2, Suit.WAN))); // 2222 WAN
        assertFalse(ruleImplementation.canGang(hand, new NumberTile(3, Suit.WAN))); // No 333 WAN
    }
    @Test
    void testCanGang2() {
        hand.add(new WindAndDragonTile("East", Suit.WIND));
        hand.add(new WindAndDragonTile("East", Suit.WIND));
        hand.add(new WindAndDragonTile("East", Suit.WIND));

        assertTrue(ruleImplementation.canGang(hand,new WindAndDragonTile("East", Suit.WIND))); // 2222 WAN
        assertFalse(ruleImplementation.canGang(hand, new NumberTile(3, Suit.WAN))); // No 333 WAN
    }

    @Test
    void testCanHu() {
        hand.add(new NumberTile(9, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));
        hand.add(new NumberTile(8, Suit.WAN));
        hand.add(new NumberTile(6, Suit.TIAO));
        hand.add(new NumberTile(1, Suit.TIAO));
        hand.add(new NumberTile(2, Suit.TIAO));
        hand.add(new NumberTile(3, Suit.TIAO));
        hand.add(new NumberTile(4, Suit.TIAO));
        hand.add(new NumberTile(5, Suit.TIAO));

        assertTrue(ruleImplementation.canHu(hand, new NumberTile(1, Suit.WAN))); // Winning hand with 23456789 WAN and 123456 TIAO
        assertFalse(ruleImplementation.canHu(hand, new NumberTile(7, Suit.TIAO))); // Not a winning hand
    }
}
