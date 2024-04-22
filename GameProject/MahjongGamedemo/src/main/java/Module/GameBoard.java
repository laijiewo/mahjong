package Module;
import System.*;
import Display.*;


import java.util.ArrayList;

public class GameBoard {
    public ArrayList<Tile> Hand_tilesOfPlayer;
    public Tile Tiles_discardedByPlayer;
    public ArrayList<Tile> Tiles_inTheWall;
    public Player Current_activePlayer;

    public void shuffleTiles(){}
    public void dealTiles(TileWall Tile_wall){}

}
