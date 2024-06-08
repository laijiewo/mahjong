package Module.Tile;
import Module.Tile.Suit;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Represents a generic tile in games such as Mahjong. This class serves as a base class
 * for different types of tiles that might have specific attributes and behaviors.
 * @author Yixin Niu
 */
public class Tile implements Comparable<Tile>, Serializable {
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
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    /**
     * Compares this tile to another tile for order based on the suit of the tiles.
     *
     * @param other The tile to be compared.
     * @return A negative integer, zero, or a positive integer as this tile's suit is less than,
     *         equal to, or greater than the suit of the specified tile.
     */
    @Override
    public int compareTo( Tile other) {
        return Integer.compare(this.getSuit().ordinal(), other.getSuit().ordinal());
    }
}
