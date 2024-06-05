package Module.Game;

import Module.Game.MahjongGame;
import Module.Tile.NumberTile;
import Module.Tile.Tile;
import Module.Rule.*;
import Module.utils.*;
import Module.Tile.*;
import Module.Tile.TileFactory;
import Module.Tile.WindAndDragonTile;

import java.util.*;

/**
 * Represents the game board for a Mahjong game, managing the state and interactions within a game session.
 */
public class GameBoard {
    private ArrayList<Tile> Tiles_discardedByPlayer;
    private static ArrayList<Tile> Tiles_inTheWall;
    private TileFactory tileFactory;

    private static Tile hunTile;

    private int dealerIndex, currentActivePlayerIndex; // Index to track the dealer in the players list
    private List<Player> players; // List to hold all players
    private Dice dice;

    public GameBoard() {

        // Initialize the lists for tiles
        this.Tiles_discardedByPlayer = new ArrayList<>();
        this.Tiles_inTheWall = new ArrayList<>();

        // Setup the tile factory and dice
        this.tileFactory = new TileFactory();
        this.dice = new Dice();
        this.hunTile = null;

        // Initialize the tile wall with tiles from the factory and shuffle them
        this.Tiles_inTheWall.addAll(tileFactory.createTiles());
        Collections.shuffle(this.Tiles_inTheWall);

        // Set players from MahjongGame
        this.players = MahjongGame.getPlayers();
    }

    public Player getDealer() {
        return players.get(dealerIndex);
    }

    public Player getCurrentActivePlayer() {
        System.out.println(currentActivePlayerIndex + " " + players.get(currentActivePlayerIndex).getPlayerSite());
        return players.get(currentActivePlayerIndex); // Assuming the dealer is also the current active player
    }

    public int determineDealer() {
        int count = players.get(dealerIndex).rollDice(dice);
        dealerIndex = count % players.size();
        currentActivePlayerIndex = dealerIndex;
        return dealerIndex;
    }

    public void dealAllTiles() {
        int numTiles = 13 * players.size() + 1;
        for (int i = 0; i < numTiles; i++) {
            dealTiles();
            swap();
        }
        currentActivePlayerIndex = dealerIndex;
        System.out.println(dealerIndex + "  " + currentActivePlayerIndex);
    }

    public void dealTiles() {
        if (!Tiles_inTheWall.isEmpty()) {
            players.get(currentActivePlayerIndex).drawTiles(Tiles_inTheWall.remove(0));
        }
    }
    public void swap() {
        currentActivePlayerIndex = (currentActivePlayerIndex + 1) % 4;
    }
    public void determineHunTile() {
        Tile hunTile = Tiles_inTheWall.get(0);
        if (hunTile.getSuit().equals(Suit.WAN) || hunTile.getSuit().equals(Suit.TIAO) || hunTile.getSuit().equals(Suit.TONG)) {
            NumberTile hun = (NumberTile) hunTile;
            hunTile = new NumberTile((hun.getRank() % 9) + 1, hunTile.getSuit());
        } else {
            WindAndDragonTile hun = (WindAndDragonTile) hunTile;
            hunTile = new WindAndDragonTile(hun.getType() + 1, hunTile.getSuit());
        }
        this.hunTile = hunTile;
    }
    public static Tile getHunTile() {
        return hunTile;
    }
    public Tile discardTile(Player player, int num) {
        return player.discardTiles(num);
    }

    public void shuffleTiles() {
        List<Tile> newList = tileFactory.createTiles();
        Collections.shuffle(newList);
        this.Tiles_inTheWall = new ArrayList<>(newList);
    }
    public Tile getLeastDiscardedTile() {
        return Tiles_discardedByPlayer.get(Tiles_discardedByPlayer.size()-1);
    }
    public int getTileCountInTheWall() {
        return Tiles_inTheWall.size();
    }
}
