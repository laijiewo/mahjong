package Module;

import java.util.*;

/**
 * Represents the game board for a Mahjong game, managing the state and interactions within a game session.
 */
public class GameBoard {
    public ArrayList<Tile> Hand_tilesOfPlayer;
    public ArrayList<Tile> Tiles_discardedByPlayer;
    public ArrayList<Tile> Tiles_inTheWall;
    public TileFactory tileFactory;

    public Tile hunTile;

    private int dealerIndex; // Index to track the dealer in the players list
    private List<Player> players; // List to hold all players
    public Dice dice;



    public GameBoard() {

        // Initialize the lists for tiles
        this.Hand_tilesOfPlayer = new ArrayList<>();
        this.Tiles_discardedByPlayer = new ArrayList<>();
        this.Tiles_inTheWall = new ArrayList<>();

        // Setup the tile factory and dice
        this.tileFactory = new TileFactory();
        this.dice = new Dice();
        this.hunTile = null;

        // Initialize the tile wall with tiles from the factory and shuffle them
        this.Tiles_inTheWall.addAll(tileFactory.createTiles());
        Collections.shuffle(this.Tiles_inTheWall);

        // Set the initial dealer
        this.dealerIndex = 0; // Start with the first player as the dealer

        // Set players from MahjongGame
        this.players = MahjongGame.getPlayers();
    }

    public Player getDealer() {
        return players.get(dealerIndex);
    }

    public Player getCurrentActivePlayer() {
        return players.get(dealerIndex); // Assuming the dealer is also the current active player
    }

    public int determineDealer() {
        int count = players.get(dealerIndex).rollDice(dice);
        dealerIndex = count % players.size();
        return dealerIndex;
    }

    public void dealAllTiles() {
        int numTiles = 13 * players.size();
        for (int i = 0; i < numTiles; i++) {
            Player currentPlayer = players.get((dealerIndex + i) % players.size());
            dealTiles(currentPlayer);
        }
    }

    public void dealTiles(Player player) {
        if (!Tiles_inTheWall.isEmpty()) {
            player.drawTiles(Tiles_inTheWall.remove(0));
        }
    }

    public void determineHunTile() {
        dealAllTiles();
        Tile hunTile = Tiles_inTheWall.get(0);
        if (hunTile.getSuit().equals(Suit.WAN) || hunTile.getSuit().equals(Suit.TIAO) || hunTile.getSuit().equals(Suit.TONG)) {
            NumberTile hun = (NumberTile) hunTile;
            hunTile = new NumberTile((hun.getRank() + 1) % 9, hunTile.getSuit());
        } else {
            WindAndDragonTile hun = (WindAndDragonTile) hunTile;
            hunTile = new WindAndDragonTile(hun.getType() + 1, hunTile.getSuit());
        }
        this.hunTile = hunTile;
    }
    public Tile getHunTile() {
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
