package System;
import Display.*;
import Module.*;

import java.util.ArrayList;

public class GameManager{
    enum Game_state {Waiting, InProgress, Paused, Ended}
    private final ArrayList<Player> players;
    private Player dealer;
    private final MahjongGame mahjongGame;
    private final Screen gameScreen;
    private final Screen loginScreen;
    private final Screen menuScreen;
    private final Screen scoreScreen;
    private Screen rulesScreen;
    public GameManager(MahjongGame mahjongGame, Screen gameScreen, Screen loginScreen, Screen menuScreen, Screen scoreScreen, Screen rulesScreen) {
        players = new ArrayList<>();
        this.mahjongGame = mahjongGame;
        this.gameScreen = gameScreen;
        this.loginScreen = loginScreen;
        this.menuScreen = menuScreen;
        this.scoreScreen = scoreScreen;
        this.rulesScreen = rulesScreen;
    }


    public void checkVictory(){}

    public void calculateScores(){}

    public void pauseGame(){}

    public void resumeGame(){}
}
