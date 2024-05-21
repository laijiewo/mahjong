package Module;
import System.*;
import Display.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a player in the game.
 */
public class Player {
    public int Score;
    public int location;

    public Site site;
    public ArrayList<Tile> Tile_hand = new ArrayList<>();

    /**
     * Default constructor for Player.
     */
    public Player() {}

    /**
     * Rolls a dice and returns the result.
     * @param dice The dice to roll.
     * @return The result of the dice toss.
     */
    public int rollDice(Dice dice) {
        return dice.toss();
    }

    /**
     * Retrieves the current score of the player.
     * @return The current score.
     */
    public int getScore() {
        return Score;
    }

    /**
     * Sets the player's score.
     * @param score The new score.
     */
    public void setScore(int score) {
        this.Score = score;
    }

    /**
     * Draws a tile from the tile wall if available.
     * @param tileWall The wall of tiles from which to draw.
     * @return The drawn tile, or null if the wall is empty.
     */
    public Tile drawTiles(TileWall tileWall) {
        if (!tileWall.StackOfTiles.isEmpty()) {
            Tile drawTile = tileWall.StackOfTiles.pop();
            Tile_hand.add(drawTile);
            return drawTile;
        }
        return null;
    }

    /**
     * Discards a tile from the player's hand.
     * @param tileNumber The index of the tile to discard.
     * @return The discarded tile.
     */
    public Tile discardTiles(int tileNumber) {
        Tile tileDiscard = Tile_hand.get(tileNumber);
        Tile_hand.remove(tileDiscard);
        return tileDiscard;
    }

    /**
     * Attempts to form a "Kong" with the latest discarded tile.
     * @param gameBoard The current state of the game board.
     */
    public void kong(GameBoard gameBoard) {
        Tile kongTile = gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size() - 1);
        int count = 0;
        for (Tile tile : Tile_hand) {
            if (tile.equals(kongTile)) {
                count++;
            }
        }
        if (count == 3) {
            Tile_hand.add(kongTile);
        }
        // Note: Hidden Kong logic needs to be implemented.
    }

    /**
     * Attempts to form a "Chow" with the latest discarded tile, which is a sequence of three consecutive number tiles of the same suit.
     * @param gameBoard The current state of the game board.
     */
    public void chow(GameBoard gameBoard) {
        ArrayList<Tile> discardedTiles = gameBoard.Tiles_discardedByPlayer;
        Tile chowTile = discardedTiles.get(discardedTiles.size() - 1);
        if (chowTile.getSuit().equals(Suit.WAN) || chowTile.getSuit().equals(Suit.TONG) || chowTile.getSuit().equals(Suit.TIAO)) {
            NumberTile numberTile = (NumberTile) chowTile;
            int targetRank = numberTile.getRank();
            ArrayList<NumberTile> sequenceCandidates = new ArrayList<>();
            for (Tile tile : Tile_hand) {
                if (tile instanceof NumberTile && tile.getSuit() == chowTile.getSuit()) {
                    sequenceCandidates.add((NumberTile) tile);
                }
            }
            boolean canChow = checkChowPossibility(sequenceCandidates, targetRank);
            if (canChow) {
                Tile_hand.add(chowTile);
                discardedTiles.remove(chowTile);
            }
        }
    }

    /**
     * Attempts to form a "Pung" with the latest discarded tile, which is a set of three identical tiles.
     * @param gameBoard The current state of the game board.
     */
    public void pung(GameBoard gameBoard) {
        Tile pungTile = gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size() - 1);
        int count = 0;
        for (Tile tile : Tile_hand) {
            if (tile.equals(pungTile)) {
                count++;
            }
        }
        if (count == 2) {
            Tile_hand.add(pungTile);
        }
    }

    /**
     * Determines if the player has won the game.
     * @param mahjongGame The game in which to check for a win.
     * @return True if the player has won, false otherwise.
     */
    public boolean winHand(MahjongGame mahjongGame) {
        return mahjongGame.checkVictory() == this;
    }

    /**
     * Adjusts the player's score by a specified amount.
     * @param change The amount to adjust the score.
     */
    public void Score_change(int change) {
        this.Score += change;
    }

    /**
     * Checks the possibility of forming a Chow from a set of candidate tiles.
     * @param candidates Tiles available to form a Chow.
     * @param targetRank The rank of the chow tile.
     * @return True if forming a Chow is possible.
     */
    private boolean checkChowPossibility(ArrayList<NumberTile> candidates, int targetRank) {
        // Logic to check for valid Chow combinations.
        return true; // Placeholder for actual implementation.
    }
}
