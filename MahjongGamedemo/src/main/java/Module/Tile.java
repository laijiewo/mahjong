package Module;
import System.*;
import Display.*;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.stream.Collector;

/**
 * Represents a generic tile in games such as Mahjong. This class serves as a base class
 * for different types of tiles that might have specific attributes and behaviors.
 * @author Yixin Niu
 */
public class Tile implements Comparable<Tile>{
    private Suit suit;
    /**
     * Constructs a new tile with the specified suit.
     *
     * @param suit the suit of this tile, which determines its category in the game.
     */
    public Tile(Suit suit) {
        this.suit = suit;
    }
    /**
     * Returns the suit of this tile.
     *
     * @return the suit of the tile.
     */
    public Suit getSuit() {
        return suit;
    }
    /**
     * Sets the suit of this tile. This method is protected to allow access only within
     * the package or subclasses.
     *
     * @param suit the new suit of the tile.
     */
    protected void setSuit(Suit suit) {
        this.suit = suit;
    }


    @Override
    public int compareTo(@NotNull Tile other) {
        return Integer.compare(this.getSuit().ordinal(), other.getSuit().ordinal());
    }
}
