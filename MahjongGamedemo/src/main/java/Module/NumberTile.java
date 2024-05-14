package Module;
/**
 * Represents a tile with a numerical rank and suit in Mahjong.
 * This class extends the {@link Tile} class, providing additional properties specific to tiles
 * that have numerical values, such as tiles in the suits of WAN, TONG, and TIAO.
 * @author Yixin Niu
 */
public class NumberTile extends  Tile{
    private int rank;
    /**
     * Constructs a new NumberTile with the specified rank and suit.
     *
     * @param rank the rank of the tile, which must be between 1 and 9 inclusive.
     * @param suit the suit of the tile, one of the suits defined in {@link Suit}.
     * @throws IllegalArgumentException if the rank is not between 1 and 9.
     */
    public NumberTile(int rank,Suit suit) {
        super(suit);
        if (rank < 1 || rank > 9) {
            throw new IllegalArgumentException("Rank must be between 1 and 9.");
        }
        this.rank = rank;
    }

    /**
     * Returns the rank of the tile.
     *
     * @return the rank of this tile.
     */
    public int getRank() {
        return rank;
    }

}
