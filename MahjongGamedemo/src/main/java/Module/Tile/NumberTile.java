package Module.Tile;

import java.util.Objects;
/**
 * Represents a tile with a numerical rank and suit in Mahjong.
 * This class extends the {@link Tile} class, providing additional properties specific to tiles
 * that have numerical values, such as tiles in the suits of WAN, TONG, and TIAO.
 * @author Yixin Niu
 */
public class NumberTile extends Tile{
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
            System.out.println(rank);
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
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NumberTile that = (NumberTile) obj;
        return rank == that.rank && getSuit() == that.getSuit();
    }
    /**
     * Overrides the hashCode method to provide a hash code based on the rank and suit of the tile.
     *
     * @return The hash code for the tile.
     */
    @Override
    public int hashCode() {
        return Objects.hash(rank, getSuit());
    }

    /**
     * Compares this tile to another tile for order. If the other tile is a NumberTile,
     * it first compares based on suit, and if the suits are equal, it compares based on rank.
     * If the other tile is not a NumberTile, it falls back to the superclass's compareTo method.
     *
     * @param other The tile to be compared.
     * @return A negative integer, zero, or a positive integer as this tile is less than,
     *         equal to, or greater than the specified tile.
     */
    @Override
    public int compareTo(Tile other) {
        if (other instanceof NumberTile) {
            NumberTile otherNumberTile = (NumberTile) other;
            // First compare by suit
            int suitComparison = Integer.compare(this.getSuit().ordinal(), otherNumberTile.getSuit().ordinal());
            if (suitComparison != 0) {
                return suitComparison;
            }
            // Then compare by rank
            return Integer.compare(this.rank, otherNumberTile.rank);
        }
        // If not comparable by rank, fall back to suit comparison
        return super.compareTo(other);
    }

}
