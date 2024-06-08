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
 *@author Jingwang Li,Lanyun Xiao
 */
public class GameBoard {
    private ArrayList<Tile> Tiles_inTheWall;
    private final TileFactory tileFactory;
    private Tile leastDiscardedTile;
    private Tile hunTile;

    private int dealerIndex, currentActivePlayerIndex; // Index to track the dealer in the players list
    private final List<Player> players; // List to hold all players
    private final Dice dice;

    public GameBoard(List<Player> players) {

        // Initialize the lists for tiles
        this.Tiles_inTheWall = new ArrayList<>();

        // Setup the tile factory and dice
        this.tileFactory = new TileFactory();
        this.dice = new Dice();
        this.hunTile = null;

        // Initialize the tile wall with tiles from the factory and shuffle them
        this.Tiles_inTheWall.addAll(tileFactory.createTiles());
        Collections.shuffle(this.Tiles_inTheWall);

        // Set players from MahjongGame
        this.players = players;
    }

    public int getDealerIndex() {
        return dealerIndex;
    }

    public Player getCurrentActivePlayer() {
        return players.get(currentActivePlayerIndex); // Assuming the dealer is also the current active player
    }

    public void determineDealer() {
        int count = players.get(dealerIndex).rollDice(dice);
        dealerIndex = count % players.size();
        currentActivePlayerIndex = dealerIndex;
    }

    public void setCurrentActivePlayerIndex(int index){
        currentActivePlayerIndex = index;
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
    public Tile getHunTile() {
        return hunTile;
    }

    public void shuffleTiles() {
        List<Tile> newList = tileFactory.createTiles();
        Collections.shuffle(newList);
        this.Tiles_inTheWall = new ArrayList<>(newList);
    }
    public Tile getLeastDiscardedTile() {
        return leastDiscardedTile;
    }
    public void setLeastDiscardedTile(Tile leastDiscardedTile) {
        this.leastDiscardedTile = leastDiscardedTile;
    }
    public List<Tile> getTilesInTheWall() {
        return Tiles_inTheWall;
    }
    public int getTileCountInTheWall() {
        return Tiles_inTheWall.size();
    }
}
