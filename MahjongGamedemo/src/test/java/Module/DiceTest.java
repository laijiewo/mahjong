package Module;

import Module.utils.Dice;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    @Test
    public void toss() {
        Dice dice=new Dice();
        int pips=dice.toss();
        assertTrue("Pips should be in the range of 1 to 6", pips >= 1 && pips <= 6);
    }

    @Test
    public void randomPip() {
        Dice dice= new Dice();
        int pips=dice.randomPip();
        assertTrue("Pips should be in the range of 1 to 6", pips >= 1 && pips <= 6);
    }
    @Test
    public void randomPip_shouldGenerateRandomPips() {
        Dice dice = new Dice();
        int pips1 = dice.randomPip();
        int pips2 = dice.randomPip();
        assertNotEquals("Random pips should not be the same", pips1, pips2);
    }
}