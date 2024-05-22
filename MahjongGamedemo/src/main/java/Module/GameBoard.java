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
    public Dice dice1;
    public Dice dice2;
    ArrayList<Player> playerList = new ArrayList<>(Arrays.asList(player1,player2,player3,player4));

    /**
     * Determines the dealer for the game using dice rolls to select from among the players.
     */
    public void determineDealer(){
        int count = player1.rollDice(dice1) + player1.rollDice(dice2);
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
     * @param Tile_wall The tile wall from which tiles are drawn.
     * @param currentPlayer The player to whom the tiles are to be dealt.
     */
    public void dealTiles(TileWall Tile_wall, Player currentPlayer){
        currentPlayer.drawTiles(Tile_wall);
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
