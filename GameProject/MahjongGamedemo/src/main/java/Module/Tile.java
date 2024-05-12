package Module;
import System.*;
import Display.*;


public class Tile {
    private int suit;
    public Tile(int suit) {
        this.suit = suit;
    }
    public int getSuit() {
        return suit;
    }
    protected void setSuit(int suit) {
        this.suit = suit;
    }
}
