package System;

public interface Game {
    public void determineDealer();
    public void rotateDealer();
    public void initializeGame();
    public void startNewGame();
    public boolean isGameOver();
    public void isPaused();
    public void checkForPause();
    public void swap();
    public boolean isRoundOver();
}
