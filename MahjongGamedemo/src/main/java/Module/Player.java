package Module;
import System.*;
import Display.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a player in a Mahjong game, managing their actions and hand.
 */
public class Player {
    private int Score;
    private Site site;
    private ArrayList<Tile> Tile_hand;

    private Tile hunTile;
    private RuleImplementation ruleImplementation;

    /**
     * Constructs a Player with initial settings.
     * @param Score Initial score of the player.
     * @param site Initial seating position of the player.
     * @param hunTile The hun tile used for certain game rules.
     */
    public Player(int Score, Site site, Tile hunTile){
        this.Score = Score;
        this.site = site;
        this.hunTile = hunTile;
        Tile_hand = new ArrayList<Tile>();
        ruleImplementation = new RuleImplementation(hunTile);
    }


    /**
     * Rolls the dice using a provided Dice object.
     * @param Dice The dice to roll.
     * @return The result of the dice toss.
     */
    public int rollDice(Dice Dice){
        return Dice.toss();
    }

    /**
     * Gets the current score of the player.
     * @return Current score.
     */
    public int getScore() {
        return Score;
    }

    /**
     * Draws a tile from the tile wall if available.
     * @param tileWall The tile wall from which to draw a tile.
     * @return The drawn tile or null if no tiles are available.
     */
    public Tile drawTiles(TileWall tileWall) {
        if (!tileWall.StackOfTiles.isEmpty()) {
            Tile drawTile = tileWall.StackOfTiles.pop();
            Tile_hand.add(drawTile);
            return drawTile;
        }
        return null;
    }

    /**
     * Discards a tile from the player's hand based on the index provided.
     * @param Tile_number Index of the tile in the hand to discard.
     * @return The tile that was discarded.
     */
    public Tile discardTiles(int Tile_number){
        Tile Tile_discard = Tile_hand.get(Tile_number);
        Tile_hand.remove(Tile_discard);
        return Tile_discard;
    }

    /**
     * Attempts to form a "Kong" by adding a provided tile if valid.
     * @param gameBoard The game board, used for game state context.
     * @param tile The tile to attempt to add for a Kong.
     */
    public void kong(GameBoard gameBoard, Tile tile){
        if(ruleImplementation.canGang(Tile_hand, tile)){
            Tile_hand.add(tile);
        }
    }

    /**
     * Attempts to add a tile to the player's hand to form a "Pung" if the rules allow.
     * @param gameBoard The game board, providing game state context.
     * @param tile The tile to attempt to add for a Pung.
     */
    public void pung(GameBoard gameBoard, Tile tile) {
        if(ruleImplementation.canPeng(Tile_hand, tile)){
            Tile_hand.add(tile);
        }
    }

    /**
     * Determines if the player can declare a win ("Mahjong") based on the current hand and a given tile.
     * @param mahjongGame The game manager controlling game logic.
     * @param tile The tile to check if it completes a winning hand.
     * @return true if the player can declare a win, false otherwise.
     */
    public boolean canHu(MahjongGame mahjongGame, Tile tile) {
        if(ruleImplementation.canHu(Tile_hand, tile)){
            Tile_hand.add(tile);
            return true;
        } else {
            return false;
        }
    }
}
