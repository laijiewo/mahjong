package Module;
import System.*;

import java.util.ArrayList;

public class Player {
    public int Score;
    public ArrayList<Tile> Tile_hand = new ArrayList<Tile>() ;

    public Player(){}

    public int rollDice(Dice Dose){return 0;}

    public Tile drawTiles(TileWall Tile_wall){return null;}

    public Tile discardTiles(TileWall Tile_wall){return null;}

    public void kong(GameManager Game_manager){}

    public void chow(GameManager Game_manager){}

    public void pung(GameManager Game_manager){}

    public void winHand(GameManager Game_manager){}

    public void Score_change(){}
}
