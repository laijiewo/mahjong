package Module;
import System.*;
import Display.*;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    public int Score;
    public int location;

    public Site site;
    public ArrayList<Tile> Tile_hand = new ArrayList<Tile>() ;

    public Player(){}

    public int rollDice(Dice Dice){
        return Dice.toss();
    }


    public Tile drawTiles(TileWall Tile_wall){
        Random r = new Random();
        int number = r.nextInt(1,Tile_wall.StackOfTiles.size());
        Tile drawTile = Tile_wall.StackOfTiles.get(number);
        Tile_wall.StackOfTiles.remove(drawTile);
        Tile_hand.add(drawTile);
        return drawTile;
    }

    public Tile discardTiles(int Tile_number){
        Tile Tile_discard = Tile_hand.get(Tile_number);
        Tile_hand.remove(Tile_discard);
        return Tile_discard;
    }

    public void kong(GameBoard gameBoard){
        ArrayList<Tile> Tiles_discardedByPlayer=gameBoard.Tiles_discardedByPlayer;
        Tile kongTile = Tiles_discardedByPlayer.get(Tiles_discardedByPlayer.size()-1);
        Tile tile = new Tile(kongTile.getSuit());
        int count = 0;
        for(int number=0;number<Tile_hand.size();number++){
            if(Tile_hand.get(number)==tile){
                count++;
            }
        }
        if(count==3 && kongTile.equals(tile)){
            Tile_hand.add(kongTile);
        }
        //如果玩家已经有四张牌了需要进行什么操作？

    }

    public void chow(GameBoard gameBoard){
        ArrayList<Tile> Tiles_discardedByPlayer=gameBoard.Tiles_discardedByPlayer;
        Tile chowTile = Tiles_discardedByPlayer.get(Tiles_discardedByPlayer.size()-1);
        Tile tile = new Tile(chowTile.getSuit());
        int count = 0;
        for(int number=0;number<Tile_hand.size();number++){
            if(Tile_hand.get(number)==tile){
                count++;
            }
        }
        if(count==2 && chowTile.equals(tile)){
            Tile_hand.add(chowTile);
        }

    }

    public void pung(GameBoard gameBoard){
        ArrayList<Tile> Tiles_discardedByPlayer=gameBoard.Tiles_discardedByPlayer;
        Tile pungTile = Tiles_discardedByPlayer.get(Tiles_discardedByPlayer.size()-1);
        Tile tile = new Tile(pungTile.getSuit());
        int count = 0;
        for(int number=0;number<Tile_hand.size();number++){
            if(Tile_hand.get(number)==tile){
                count++;
            }
        }
        if(count==2 && pungTile.equals(tile)){
            Tile_hand.add(pungTile);
        }}

    public void winHand(GameManager Game_manager){}

    public void Score_change(){}
}
