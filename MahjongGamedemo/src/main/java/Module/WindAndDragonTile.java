package Module;

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

    /**
     * Returns a string representation of this tile, using the type as its descriptor.
     * This method overrides the {@link Tile#toString()} method to provide a more specific output.
     *
     * @return a string that represents this specific tile, e.g., "East" or "ZHONG".
     */
    @Override
    public String toString() {
        return type;
    }
}
