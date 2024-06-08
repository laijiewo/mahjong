package Module.Tile;

import Module.Tile.Suit;

import java.util.Objects;

/**
 * Represents specific types of tiles used in games like Mahjong, specifically wind and dragon tiles.
 * This class extends {@link Tile} to include a specific type, which can either be one of the four
 * wind directions (East, South, West, North) or one of the three traditional dragons (ZHONG, FA, BAI).
 *
 * @author Yixin Niu
 */
public class WindAndDragonTile extends Tile {
    private String type;


    /**
     * Constructs a new WindAndDragonTile with the specified type and suit.
     * This allows the creation of both wind and dragon tiles based on the parameters passed.
     *
     * @param type the specific type of the tile, such as "East" for East Wind or "ZHONG" for Red Dragon.
     * @param suit the suit of the tile, which should be either {@link Suit#WIND} or {@link Suit#DRAGON}
     *             depending on the type specified.
     */

    public WindAndDragonTile(String type, Suit suit) {
        super(suit);
        this.type = type;
    }

    /**
     * Returns the type of the tile, providing more specific information about the tile's identity,
     * such as whether it is a wind direction or a dragon type.
     *
     * @return the specific type of this tile.
     */
    public String getType() {
        return type;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WindAndDragonTile that = (WindAndDragonTile) obj;
        return type.equals(that.type) && getSuit() == that.getSuit();
    }

    /**
     * Overrides the hashCode method to provide a hash code based on the type and suit of the tile.
     *
     * @return The hash code for the tile.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, getSuit());
    }

    /**
     * Compares this tile to another tile for order. If the other tile is a WindAndDragonTile,
     * it first compares based on suit, and if the suits are equal, it compares based on type.
     * If the other tile is not a WindAndDragonTile, it falls back to the superclass's compareTo method.
     *
     * @param other The tile to be compared.
     * @return A negative integer, zero, or a positive integer as this tile is less than, equal to, or greater than the specified tile.
     */
    @Override
    public int compareTo(Tile other) {
        if (other instanceof WindAndDragonTile) {
            WindAndDragonTile otherTile = (WindAndDragonTile) other;
            int suitComparison = Integer.compare(this.getSuit().ordinal(), otherTile.getSuit().ordinal());
            if (suitComparison != 0) {
                return suitComparison;
            }
            return this.type.compareTo(otherTile.type);
        }
        return super.compareTo(other);
    }
}


