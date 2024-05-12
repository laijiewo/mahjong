package Module;

import java.util.Random;

public class Dice {
    private int pips;
    private Random rand = new Random();
    public Dice(){
    }
    public int randomPip(){
        return rand.nextInt(6);
    }
    public int getPips() {
        return pips;
    }
}
