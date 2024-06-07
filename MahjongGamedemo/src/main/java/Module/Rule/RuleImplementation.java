package Module.Rule;
import Module.Game.Player;
import Module.Rule.MahjongRule;
import Module.Tile.NumberTile;
import Module.Tile.Tile;
import Module.Tile.Suit;
import Module.utils.*;

import java.util.*;

/**
 * Implementation of Beijing Mahjong rules.
 *
 * @author Yixin Niu
 */
public class RuleImplementation implements MahjongRule {
    private Tile hunTile; // The wildcard tile (混儿牌)
//    private FanCalculator fanCalculator;
    private List<Tile> chiTiles = new ArrayList<>();
    private List<Tile> pengTiles = new ArrayList<>();
    private List<Tile> gangTiles = new ArrayList<>();

    /**
     * Constructs the RuleImplementation with the specified wildcard tile.
     *
     * @param hunTile the wildcard tile
     */
    public RuleImplementation(Tile hunTile) {
        this.hunTile = hunTile;
//        this.fanCalculator=new FanCalculator(hunTile, player);
    }

    /**
     * Checks if the player can perform a "Chi" (吃) with the given tile.
     * Chi is only allowed with number tiles (WAN, TIAO, TONG).
     *
     * @param hand the player's hand
     * @param tile the tile to be used for Chi
     * @return true if the player can Chi, false otherwise
     */
    @Override
    public boolean canChi(List<Tile> hand, Tile tile) {
        if (tile == null) {
            return false;
        }
        chiTiles.clear(); // Clear previous chi tiles
        if (tile.getSuit() != Suit.WAN && tile.getSuit() != Suit.TIAO && tile.getSuit() != Suit.TONG) {
            return false; // Only number tiles can be used to Chi
        }

        List<Tile> sortedHand = new ArrayList<>(hand);
        sortedHand.add(tile); // Add the tile to the hand for checking
        Collections.sort(sortedHand);

        // Array to count the occurrences of each rank (1-9) in the hand
        int[] ranks = new int[10]; // ranks[0] is unused, ranks[1-9] store the count of each rank
        Suit suit = tile.getSuit();
        for (Tile t : sortedHand) {
            if (t instanceof NumberTile &&t.getSuit()==suit) {
                ranks[((NumberTile) t).getRank()]++;
            }
        }

        int rank = ((NumberTile) tile).getRank();

        // Check if there are two consecutive tiles that can form a sequence with the given tile
        if (rank >= 3 && ranks[rank - 1] > 0 && ranks[rank - 2] > 0) {
            chiTiles.add(new NumberTile(rank - 2, suit));
            chiTiles.add(new NumberTile(rank - 1, suit));
            chiTiles.add(tile);
            removeChiTilesFromHand(hand);
            return true; // e.g., 123
        } else if (rank <= 7 && ranks[rank + 1] > 0 && ranks[rank + 2] > 0) {
            chiTiles.add(tile);
            chiTiles.add(new NumberTile(rank + 1, suit));
            chiTiles.add(new NumberTile(rank + 2, suit));
            removeChiTilesFromHand(hand);
            return true; // e.g., 789
        } else if (rank >= 2 && rank <= 8 && ranks[rank - 1] > 0 && ranks[rank + 1] > 0) {
            chiTiles.add(new NumberTile(rank - 1, suit));
            chiTiles.add(tile);
            chiTiles.add(new NumberTile(rank + 1, suit));
            removeChiTilesFromHand(hand);
            return true; // e.g., 234
        }

        return false;
    }

    /**
     * Checks if the player can perform a "Peng" (碰) with the given tile.
     *
     * @param hand the player's hand
     * @param tile the tile to be used for Peng
     * @return true if the player can Peng, false otherwise
     */
    @Override
    public boolean canPeng(List<Tile> hand, Tile tile) {
        pengTiles.clear(); // Clear previous peng tiles
        int count = 0;
        for (Tile t : hand) {
            if (t.equals(tile)) {
                pengTiles.add(t);
                count++;
            }
        }
        pengTiles.add(tile);
        count++;
        if (count == 3) {
            removePengTilesFromHand(hand);
            return true; // Need at least two tiles to Peng
        }
        return false;
    }

    /**
     * Checks if the player can perform a "Gang" (杠) with the given tile.
     *
     * @param hand the player's hand
     * @param tile the tile to be used for Gang
     * @return true if the player can Gang, false otherwise
     */
    @Override
    public boolean canGang(List<Tile> hand, Tile tile) {
        gangTiles.clear(); // Clear previous gang tiles
        int count = 0;
        for (Tile t : hand) {
            if (t.equals(tile)) {
                gangTiles.add(t);
                count++;
            }
        }
        gangTiles.add(tile);
        count++;
        if (count == 4) {
            removeGangTilesFromHand(hand);
            return true; // Need at least three tiles to Gang
        }
        return false;    }

    /**
     * Checks if the player can perform a "Hu" (胡) with the given tile.
     *
     * @param hand the player's hand
     * @return true if the player can Hu, false otherwise
     */
    @Override
    public boolean canHu(List<Tile> hand, List<Tile>meldsTile) {
        List<Tile> newHand = new ArrayList<>(hand);
        newHand.addAll(meldsTile);
        Collections.sort(newHand); // Sort the hand to ensure tiles are in order
        return checkHu(newHand);
    }

    /**
     * Checks if the given hand can form a winning hand.
     * This includes checking for 4 melds (sequences or triplets) and a pair, considering the wildcard tile.
     *
     * @param hand the player's hand including the tile to be used for Hu
     * @return true if the hand can form a winning hand, false otherwise
     */
    private boolean checkHu(List<Tile> hand) {
        List<Tile> withoutHun = new ArrayList<>(); // Hand without the wildcard tiles
        int hunCount = 0; // Count of wildcard tiles

        for (Tile t : hand) {
            if (t.equals(hunTile)) {
                hunCount++;
            } else {
                withoutHun.add(t);
            }
        }

        return isWinningHand(withoutHun, hunCount); // Check if the hand is a winning hand
    }

    /**
     * Checks if the given hand can form a winning hand with the specified number of wildcard tiles.
     *
     * @param hand the player's hand without the wildcard tiles
     * @param hunCount the number of wildcard tiles in the hand
     * @return true if the hand can form a winning hand, false otherwise
     */
    private boolean isWinningHand(List<Tile> hand, int hunCount) {
        // Base cases: if there are no tiles left to check, we have a winning hand
        if (hand.isEmpty()) {
            return true;
        }

        // Try to find a pair (将牌)
        for (int i = 0; i < hand.size() - 1; i++) {
            Tile first = hand.get(i);
            Tile second = hand.get(i + 1);

            if (first.equals(second)) {
                // Remove the pair from the hand and check if the remaining hand can form melds
                List<Tile> remainingHand = new ArrayList<>(hand);
                remainingHand.remove(i);
                remainingHand.remove(i);

                if (canFormMelds(remainingHand, hunCount)) {
                    return true;
                }
            }
        }

        // Check if we can use a hun tile to form a pair
        if (hunCount > 0) {
            for (int i = 0; i < hand.size(); i++) {
                Tile first = hand.get(i);

                // Remove the pair (one tile from hand, one hun tile) and check if the remaining hand can form melds
                List<Tile> remainingHand = new ArrayList<>(hand);
                remainingHand.remove(i);

                if (canFormMelds(remainingHand, hunCount - 1)) {
                    return true;
                }
            }
        }

        return false;
    }

   private boolean canFormMelds(List<Tile> hand, int hunCount) {
       // Base cases: if there are no tiles left to check, we have a winning hand
       if (hand.isEmpty()) {
           return true;
       }

       // Try to form a triplet (刻子)
       if (tryFormTriplet(hand, hunCount)) {
           return true;
       }

       // Check for sequence
       if (tryFormSequence(hand, hunCount)) {
           return true;
       }

       // Try to use hun tiles to form a triplet or sequence
       if (hunCount > 0) {
           if (tryFormTripletWithHun(hand, hunCount)) {
               return true;
           }
           if (tryFormSequenceWithHun(hand, hunCount, 1)) {
               return true;
           }
           if (tryFormSequenceWithHun(hand, hunCount, 2)) {
               return true;
           }
       }

       return false;
   }
    private boolean tryFormTriplet(List<Tile> hand, int hunCount) {
        for (int i = 0; i < hand.size() - 2; i++) {
            Tile first = hand.get(i);
            Tile second = hand.get(i + 1);
            Tile third = hand.get(i + 2);

            if (first.equals(second) && second.equals(third)) {
                List<Tile> remainingHand = new ArrayList<>(hand);
                remainingHand.remove(i);
                remainingHand.remove(i);
                remainingHand.remove(i);

                if (canFormMelds(remainingHand, hunCount)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean tryFormSequence(List<Tile> hand, int hunCount) {
        for (int i = 0; i < hand.size() - 2; i++) {
            if (hand.get(i) instanceof NumberTile) {
                NumberTile first = (NumberTile) hand.get(i);
                int rank = first.getRank();
                Suit suit = first.getSuit();

                if (rank <= 7) { // Ensure the rank is within valid range to form a sequence
                    NumberTile second = new NumberTile(rank + 1, suit);
                    NumberTile third = new NumberTile(rank + 2, suit);

                    if (containsTile(hand, second) && containsTile(hand, third)) {
                        List<Tile> remainingHand = new ArrayList<>(hand);
                        remainingHand.remove(first);
                        removeTile(remainingHand, second);
                        removeTile(remainingHand, third);

                        if (canFormMelds(remainingHand, hunCount)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean tryFormTripletWithHun(List<Tile> hand, int hunCount) {
        // Check for using hun tiles as part of a triplet
        for (int i = 0; i < hand.size() - 1; i++) {
            Tile first = hand.get(i);
            Tile second = hand.get(i + 1);

            if (first.equals(second)) {
                List<Tile> remainingHand = new ArrayList<>(hand);
                remainingHand.remove(first);
                remainingHand.remove(second);

                if (canFormMelds(remainingHand, hunCount - 1)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean tryFormSequenceWithHun(List<Tile> hand, int hunCount, int offset) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) instanceof NumberTile) {
                NumberTile first = (NumberTile) hand.get(i);
                int rank = first.getRank();
                Suit suit = first.getSuit();

                if (rank + offset <= 9) {
                    NumberTile next = new NumberTile(rank + offset, suit);
                    if (containsTile(hand, next)) {
                        List<Tile> remainingHand = new ArrayList<>(hand);
                        remainingHand.remove(first);
                        removeTile(remainingHand, next);

                        if (canFormMelds(remainingHand, hunCount - 1)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void removeTile(List<Tile> hand, Tile tile) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).equals(tile)) {
                hand.remove(i);
                return;
            }
        }
    }
    private void removeChiTilesFromHand(List<Tile> hand) {
        for (Tile tile : chiTiles) {
            removeTile(hand, tile);
        }
    }

    private void removePengTilesFromHand(List<Tile> hand) {
        for (Tile tile : pengTiles) {
            removeTile(hand, tile);
        }
    }

    private void removeGangTilesFromHand(List<Tile> hand) {
        for (Tile tile : gangTiles) {
            removeTile(hand, tile);
        }
    }

    /**
     * Gets the number tiles from the given hand.
     *
     * @param hand the player's hand
     * @return a list of number tiles in the hand
     */
    private List<NumberTile> getNumberTiles(List<Tile> hand) {
        List<NumberTile> numberTiles = new ArrayList<>();
        for (Tile t : hand) {
            if (t instanceof NumberTile) {
                numberTiles.add((NumberTile) t);
            }
        }
        return numberTiles;
    }

    private boolean containsTile(List<Tile> hand, Tile tile) {
        for (Tile t : hand) {
            if (t.equals(tile)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns the list of tiles used for the last Chi.
     *
     * @return the list of tiles used for Chi
     */
    public List<Tile> getChiTiles() {
        return new ArrayList<>(chiTiles);
    }

    /**
     * Returns the list of tiles used for the last Peng.
     *
     * @return the list of tiles used for Peng
     */
    public List<Tile> getPengTiles() {
        return new ArrayList<>(pengTiles);
    }

    /**
     * Returns the list of tiles used for the last Gang.
     *
     * @return the list of tiles used for Gang
     */
    public List<Tile> getGangTiles() {
        return new ArrayList<>(gangTiles);
    }


}