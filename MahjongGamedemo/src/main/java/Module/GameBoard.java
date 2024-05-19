package Module;
import System.*;
import Display.*;

import java.util.*;

public class GameBoard {
    public ArrayList<Tile> Hand_tilesOfPlayer;
    public Tile Tiles_discardedByPlayer;
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




    public void determineDealer(){
        player1.site=Site.East;
        player2.site=Site.South;
        player3.site=Site.West;
        player4.site=Site.North;
        int count=player1.rollDice(dice1)+player1.rollDice(dice2);
        int Croupier = count%4;
        if(Croupier==1){
            Dealer=player1;
        } else if (Croupier==2) {
            Dealer=player2;
        } else if (Croupier==3) {
            Dealer=player3;
        } else if (Croupier==4) {
            Dealer=player4;
        }
    }

    public void changeDealer(){
        Dealer=mahjongGame.checkVictory();
    }

    public List shuffleTiles(){
        List newList=tileFactory.createTiles();
        Collections.shuffle(newList);
        return newList;

    }
    public void dealTiles(TileWall Tile_wall, Player currentPlayer){
        currentPlayer.Tile_hand.add(Tile_wall.StackOfTiles.get(0));
        Tile_wall.StackOfTiles.remove(0);
    }

    public Tile discardTile(Player player, int num){
        Tile discardTile = player.discardTiles(num);
        player.Tile_hand.remove(num);
        return discardTile;
    }

    public Player getCurrent_activePlayer(){
        return currentPlayer;
    }



}
