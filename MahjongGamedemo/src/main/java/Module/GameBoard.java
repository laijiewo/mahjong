package Module;

import java.util.*;

/**
 * Represents the game board for a Mahjong game, managing the state and interactions within a game session.
 */
public class GameBoard {
    public ArrayList<Tile> Hand_tilesOfPlayer;
    public ArrayList<Tile> Tiles_discardedByPlayer;
    public ArrayList<Tile> Tiles_inTheWall;
    public Player Current_activePlayer;
    public MahjongGame mahjongGame;
    public TileFactory tileFactory;

    public Player Dealer;
    public Player currentPlayer;


    public Player player1;
    public Player player2;
    public Player player3;
    public Player player4;
    public Dice dice;

    public GameBoard() {
        // Initialize the lists for tiles
        this.Hand_tilesOfPlayer = new ArrayList<>();
        this.Tiles_discardedByPlayer = new ArrayList<>();
        this.Tiles_inTheWall = new ArrayList<>();

        // Setup the tile factory and dice
        this.tileFactory = new TileFactory();
        this.dice = new Dice();

        // Create players
        this.player1 = new Player(0,Site.East);
        this.player2 = new Player(0,Site.South);
        this.player3 = new Player(0,Site.West);
        this.player4 = new Player(0,Site.North);

        // Set the current and dealer players initially to player1 for simplicity
        this.currentPlayer = this.player1;
        this.Dealer = this.player1;

        // Initialize the MahjongGame controller
        this.mahjongGame = new MahjongGame();

        // Populate the tile wall with tiles from the factory
        this.Tiles_inTheWall.addAll(tileFactory.createTiles());

        // Shuffle the tiles to prepare for game start
        Collections.shuffle(this.Tiles_inTheWall);

        // Optionally set the initial active player
        this.Current_activePlayer = this.player1; // Assuming the game starts with player1
    }



    /**
     * Determines the dealer for the game using dice rolls to select from among the players.
     */
    public ArrayList<Tile> Tiles_discardedByPlayer(){
        return Tiles_discardedByPlayer;
    }
    public void determineDealer(){
        int count = player1.rollDice(dice);
        int Croupier = count % 4;
        if(Croupier == 1){
            Dealer = player1;
        } else if (Croupier == 2) {
            Dealer = player2;
        } else if (Croupier == 3) {
            Dealer = player3;
        } else if (Croupier == 0) {
            Dealer = player4;
        }
    }

    public Tile determineHunTile(){
        dealAllTiles();
        Tile hunTile=Tiles_inTheWall.get(0);
        return hunTile;
        
    }

    /**
     * Changes the dealer based on the results of the game, typically to the winning player.
     */
    public void changeDealer(){
        Dealer = mahjongGame.checkVictory();
    }

    /**
     * Shuffles the tiles before the start of the game using a TileFactory to create and shuffle a new set of tiles.
     */
    public void shuffleTiles(){
        List<Tile> newList = tileFactory.createTiles();
        Collections.shuffle(newList);
    }

    /**
     * Deals tiles to a player from the tile wall during the game.
     * @param currentPlayer The player to whom the tiles are to be dealt.
     */
    public void dealTiles(Player currentPlayer){
        currentPlayer.drawTiles(tileFactory);
    }

    public void dealAllTiles(){
        for(int i=0;i<13;i++){
            dealTiles(player1);
            dealTiles(player2);
            dealTiles(player3);
            dealTiles(player4);
        }

    }

    /**
     * Discards a tile from a player's hand.
     * @param player The player discarding a tile.
     * @param num The index of the tile to be discarded in the player's hand.
     * @return The tile that was discarded.
     */
    public Tile discardTile(Player player, int num){
        Tile discardTile = player.discardTiles(num);
        return discardTile;
    }

    /**
     * Retrieves the currently active player.
     * @return The currently active player in the game.
     */
    public Player getCurrent_activePlayer(){
        return currentPlayer;
    }
}
