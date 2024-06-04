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

    @Override
    public int hashCode() {
        return Objects.hash(type, getSuit());
    }

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


