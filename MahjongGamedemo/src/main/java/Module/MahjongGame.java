package Module;
import System.*;
import Display.*;

public class MahjongGame implements Game {
    private GameBoard gameBoard;
    private RuleImplementation ruleImplementation;
    private GameManager gameManager;
    @Override
    public Player determineDealer() {
        return gameBoard.Dealer;
    }

    @Override
    public void initializeGame() {
        GameBoard gameBoard1=new GameBoard();

    }

    @Override
    public Player checkVictory() {
        if(gameBoard.player1.canHu(this,gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size()-1))){
            return gameBoard.player1;
        } else if (gameBoard.player2.canHu(this,gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size()-1))) {
            return gameBoard.player2;
        } else if (gameBoard.player3.canHu(this,gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size()-1))) {
            return gameBoard.player3;
        } else if (gameBoard.player4.canHu(this,gameBoard.Tiles_discardedByPlayer.get(gameBoard.Tiles_discardedByPlayer.size()-1))) {
            return gameBoard.player4;
        }else {
            return null;
        }
    }

    @Override
    public void calculateScores() {


    }

    @Override
    public void shuffleTiles() {
        gameBoard.shuffleTiles();
    }

    @Override
    public void startNewGame() {

    }

    @Override
    public void swap() {

    }

    @Override
    public boolean isRoundOver() {
        if (checkVictory()!=null){
            return true;
        }else {
            return false;
        }

    }
}
