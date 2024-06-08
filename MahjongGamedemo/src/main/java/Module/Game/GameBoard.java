package Module.Game;

import Module.Tile.NumberTile;
import Module.Tile.Tile;
import Module.utils.*;
import Module.Tile.*;
import Module.Tile.TileFactory;
import Module.Tile.WindAndDragonTile;

import java.util.*;

/**
 * Represents the game board for a Mahjong game, managing the state and interactions within a game session.
 * Handles tile management, player interactions, and game state transitions.
 *
 * Authors: Jingwang Li, Lanyun Xiao
 */
public class GameBoard {
    private ArrayList<Tile> Tiles_inTheWall;
    private final TileFactory tileFactory;
    private Tile leastDiscardedTile;
    private int leastDiscardedPlayerIndex;
    private Tile hunTile;
    private int dealerIndex, currentActivePlayerIndex; // Index to track the dealer in the players list
    private final List<Player> players; // List to hold all players
    private final Dice dice;

    /**
     * Constructs a GameBoard object and initializes the game board.
     *
     * @param players The list of players participating in the game.
     */
    public GameBoard(List<Player> players) {

        // Initialize the lists for tiles
        this.Tiles_inTheWall = new ArrayList<>();

        // Set up the tile factory and dice
        this.tileFactory = new TileFactory();
        this.dice = new Dice();
        this.hunTile = null;

        // Initialize the tile wall with tiles from the factory and shuffle them
        this.Tiles_inTheWall.addAll(tileFactory.createTiles());
        Collections.shuffle(this.Tiles_inTheWall);

        // Set players from MahjongGame
        this.players = players;
    }

    /**
     * Gets the index of the dealer.
     *
     * @return The index of the dealer.
     */
    public int getDealerIndex() {
        return dealerIndex;
    }

    /**
     * Gets the current active player.
     *
     * @return The current active player.
     */
    public Player getCurrentActivePlayer() {
        return players.get(currentActivePlayerIndex); // Assuming the dealer is also the current active player
    }

    /**
     * Determines the dealer for the game by rolling the dice.
     */
    public void determineDealer() {
        int count = players.get(dealerIndex).rollDice(dice);
        dealerIndex = count % players.size();
        currentActivePlayerIndex = dealerIndex;
    }

    /**
     * Sets the index of the current active player.
     *
     * @param index The index to set as the current active player.
     */
    public void setCurrentActivePlayerIndex(int index){
        currentActivePlayerIndex = index;
    }

    /**
     * Deals all tiles to the players, giving each player their initial hand.
     */
    public void dealAllTiles() {
        int numTiles = 13 * players.size() + 1;
        for (int i = 0; i < numTiles; i++) {
            dealTiles();
            swap();
        }
        currentActivePlayerIndex = dealerIndex;
        System.out.println(dealerIndex + "  " + currentActivePlayerIndex);
    }

    /**
     * Deals one tile to the current active player.
     */
    public void dealTiles() {
        if (!Tiles_inTheWall.isEmpty()) {
            players.get(currentActivePlayerIndex).drawTiles(Tiles_inTheWall.remove(0));
        }
    }

    /**
     * Swaps the current active player to the next player.
     */
    public void swap() {
        currentActivePlayerIndex = (currentActivePlayerIndex + 1) % 4;
    }

    /**
     * Determines the Hun tile based on the tile at the top of the wall.
     */
    public void determineHunTile() {
        Tile hunTile = Tiles_inTheWall.get(0);
        if (hunTile.getSuit().equals(Suit.WAN) || hunTile.getSuit().equals(Suit.TIAO) || hunTile.getSuit().equals(Suit.TONG)) {
            NumberTile hun = (NumberTile) hunTile;
            hunTile = new NumberTile((hun.getRank() % 9) + 1, hunTile.getSuit());
        } else if (hunTile.getSuit().equals(Suit.DRAGON)){
            String[] dragonTypes = {"ZHONG", "FA", "BAI"};
            WindAndDragonTile hun = (WindAndDragonTile) hunTile;
            String type = hun.getType();
            int indexOfNewType = (Arrays.asList(dragonTypes).indexOf(type) + 1) % 3;
            hunTile = new WindAndDragonTile(dragonTypes[indexOfNewType], hunTile.getSuit());
        } else {
            String[] windTypes = {"East", "South", "West", "North"};
            WindAndDragonTile hun = (WindAndDragonTile) hunTile;
            String type = hun.getType();
            int indexOfNewType = (Arrays.asList(windTypes).indexOf(type) + 1) % 4;
            hunTile = new WindAndDragonTile(windTypes[indexOfNewType], hunTile.getSuit());
        }
        this.hunTile = hunTile;
    }

    /**
     * Gets the Hun tile.
     *
     * @return The Hun tile.
     */
    public Tile getHunTile() {
        return hunTile;
    }

    /**
     * Shuffles the tiles in the wall.
     */
    public void shuffleTiles() {
        List<Tile> newList = tileFactory.createTiles();
        Collections.shuffle(newList);
        this.Tiles_inTheWall = new ArrayList<>(newList);
    }

    /**
     * Gets the tile that was least recently discarded.
     *
     * @return The least recently discarded tile.
     */
    public Tile getLeastDiscardedTile() {
        return leastDiscardedTile;
    }

    /**
     * Sets the least recently discarded tile and the index of the player who discarded it.
     *
     * @param leastDiscardedTile The least recently discarded tile.
     * @param discardPlayerIndex The index of the player who discarded the tile.
     */
    public void setLeastDiscardedTile(Tile leastDiscardedTile, int discardPlayerIndex) {
        this.leastDiscardedTile = leastDiscardedTile;
        leastDiscardedPlayerIndex = discardPlayerIndex;
    }

    /**
     * Gets the index of the player who least recently discarded a tile.
     *
     * @return The index of the player who least recently discarded a tile.
     */
    public int getLeastDiscardedPlayerIndex() {
        return leastDiscardedPlayerIndex;
    }

    /**
     * Gets the tiles in the wall.
     *
     * @return The list of tiles in the wall.
     */
    public List<Tile> getTilesInTheWall() {
        return Tiles_inTheWall;
    }

    /**
     * Gets the number of tiles left in the wall.
     *
     * @return The number of tiles left in the wall.
     */
    public int getTileCountInTheWall() {
        return Tiles_inTheWall.size();
    }
}
