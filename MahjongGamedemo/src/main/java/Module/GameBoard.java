package Module;
import System.*;
import Display.*;

import java.util.ArrayList;
import java.util.Arrays;

public class GameBoard {
    public ArrayList<Tile> Hand_tilesOfPlayer;
    public Tile Tiles_discardedByPlayer;
    public ArrayList<Tile> Tiles_inTheWall;
    public Player Current_activePlayer;

    public Player player1;
    public Player player2;
    public Player player3;
    public Player player4;

    ArrayList<Player> playerList = new ArrayList<>(Arrays.asList(player1,player2,player3,player4 e));

    public void rotate(){
        Dice dice = new Dice();
        int totalNumber = 0;
        player1.rollDice(dice);
    }

    public void shuffleTiles(){}
    public void dealTiles(TileWall Tile_wall){
    }


}
