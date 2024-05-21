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


    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        this.Score = score;
    }

    public Tile drawTiles(TileWall tileWall) {
        if (!tileWall.StackOfTiles.isEmpty()) {
            Tile drawTile = tileWall.StackOfTiles.pop();
            Tile_hand.add(drawTile);
            return drawTile;
        }
        return null;
    }

    public Tile discardTiles(int Tile_number){
        Tile Tile_discard = Tile_hand.get(Tile_number);
        Tile_hand.remove(Tile_discard);
        return Tile_discard;
    }

    public void kong(GameBoard gameBoard){
        Tile kongTile = gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size() - 1);
        int count = 0;
        for(int number=0;number<Tile_hand.size();number++){
            if(Tile_hand.get(number).equals(kongTile)){
                count++;
            }
        }
        if(count==3 && kongTile.equals(kongTile)){
            Tile_hand.add(kongTile);
        }
        //暗杠没实现

    }

    /**
     * Attempts to form a "Chow" (a sequential set of three tiles of the same suit) with the most recently discarded tile.
     * This method checks if the player's hand contains the necessary tiles to form a Chow sequence with the discarded tile.
     * @param gameBoard The game board containing the discarded tiles.
     */
    public void chow(GameBoard gameBoard) {
        // Retrieve the list of tiles discarded by players from the game board
        ArrayList<Tile> discardedTiles = gameBoard.Tiles_discardedByPlayer;
        // Get the most recently discarded tile
        Tile chowTile = discardedTiles.get(discardedTiles.size() - 1);
        // Check if the discarded tile is suitable for a Chow (must be a numeric tile from suits WAN, TONG, or TIAO)
        if(chowTile.getSuit().equals(Suit.WAN) || chowTile.getSuit().equals(Suit.TONG) || chowTile.getSuit().equals(Suit.TIAO)){
            NumberTile chow = (NumberTile) chowTile;
            // List to hold numeric tiles of the same suit as the chowTile from the player's hand
            ArrayList<NumberTile> handNumberCard = new ArrayList<>();
            // Populate the list with numeric tiles of the matching suit
            for(Tile tile : Tile_hand){
                if(tile.getSuit().equals(chow.getSuit())){
                    handNumberCard.add((NumberTile) tile);
                }
            }
            // Check for sequential combinations around the chowTile's rank, avoiding the first and last ranks
            if(chow.getRank() != 1 && chow.getRank() != 9){
                NumberTile lowerTile1 = new NumberTile(chow.getRank() - 1, chow.getSuit());
                NumberTile lowerTile2 = new NumberTile(chow.getRank() - 2, chow.getSuit());
                NumberTile higherTile1 = new NumberTile(chow.getRank() + 1, chow.getSuit());
                NumberTile higherTile2 = new NumberTile(chow.getRank() + 2, chow.getSuit());

                // Check for the possibility of a Chow with the tile immediately before or after the chowTile
                if(handNumberCard.contains(lowerTile1)){
                    if(handNumberCard.contains(lowerTile2) || handNumberCard.contains(higherTile1)){
                        Tile_hand.add(chowTile);
                    }
                } else if (handNumberCard.contains(higherTile1)) {
                    if(handNumberCard.contains(higherTile2)){
                        Tile_hand.add(chowTile);
                    }
                }
            } else if (chow.getRank() == 1) {
                // Special case for the lowest rank: only check tiles immediately higher
                NumberTile higherTile1 = new NumberTile(chow.getRank() + 1, chow.getSuit());
                NumberTile higherTile2 = new NumberTile(chow.getRank() + 2, chow.getSuit());
                if(handNumberCard.contains(higherTile1) && handNumberCard.contains(higherTile2)){
                    Tile_hand.add(chowTile);
                }
            } else if (chow.getRank() == 9) {
                // Special case for the highest rank: only check tiles immediately lower
                NumberTile lowerTile1 = new NumberTile(chow.getRank() - 1, chow.getSuit());
                NumberTile lowerTile2 = new NumberTile(chow.getRank() - 2, chow.getSuit());
                if(handNumberCard.contains(lowerTile1) && handNumberCard.contains(lowerTile2)){
                    Tile_hand.add(chowTile);
                }
            }
        }
    }



    public void pung(GameBoard gameBoard){
        Tile pungTile = gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size() - 1);
        int count = 0;
        for(int number=0;number<Tile_hand.size();number++){
            if(Tile_hand.get(number).equals(pungTile)){
                count++;
            }
        }
        if(count==2 && pungTile.equals(pungTile)){
            Tile_hand.add(pungTile);
        }}

    /**
     * Determines if the player has won the game based on their hand.
     * @param mahjongGame The manager controlling the game logic.
     */
    public boolean winHand(MahjongGame mahjongGame) {
        // Check conditions for winning the game
        if (mahjongGame.checkVictory()==this) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Changes the player's score based on game events.
     * @param change Amount to change the score.
     */
    public void Score_change(int change) {
        this.Score += change;
    }
}
