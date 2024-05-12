package Display;
import System.*;
import Module.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.stage.Stage;


public class GameScreen implements Screen {
    MahjongGame Mahjong_game;
    GameManager Game_manager;
    Background Background;

    Button Pause_gameButton = ButtonDisplay.Pause_gameButton;
    Button Continue_gameButton = ButtonDisplay.Continue_gameButton;
    Button Chow_button = ButtonDisplay.Chow_button;
    Button Pung_button = ButtonDisplay.Pung_button;
    Button Kong_button = ButtonDisplay.Kong_button;
    Button Win_button = ButtonDisplay.Win_button;
    Button Hand_tilesButton = ButtonDisplay.Hand_tilesButton;
    public GameScreen(MahjongGame Mahjong_game) {
        this.Mahjong_game = Mahjong_game;
    }

    public static void loadGameWindow(Stage primaryStage){}

    @Override
    public void loadWindow(Stage primaryStage) {
        loadGameWindow(primaryStage);
    }
}
