package Module;
import System.*;
import Display.*;


public class MahjongGame {
    private Player[] players;
    private GameBoard gameBoard;
    private int currentPlayerIndex;

    public MahjongGame() {
        this.players = new Player[4];
        this.gameBoard = new GameBoard();
        this.currentPlayerIndex = 0;
    }
}
