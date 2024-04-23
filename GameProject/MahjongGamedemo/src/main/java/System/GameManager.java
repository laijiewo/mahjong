package System;
import Display.*;
import Module.*;

import java.util.ArrayList;

public class GameManager {
    enum Game_state {Waiting, InProgress, Paused, Ended}
    Player Dealer;
    ArrayList<Tile> Remaining_tiles = new ArrayList<Tile>();

    public void determineDealer(){}

    public void rotateDealer(){}

    public void initializeGame(){}

    public void startGame(){}

    public void endGame(){}

    public void checkVictory(){}

    public void calculateScores(){}

    public void pauseGame(){}

    public void resumeGame(){}
}
