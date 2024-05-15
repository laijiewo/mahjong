package Display;
import System.*;
import Module.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.stage.Stage;


public class GameScreen implements Screen {
    MahjongGame Mahjong_game;
    Background Background;

    static Button Pause_gameButton = ButtonDisplay.Pause_gameButton;
    static Button Continue_gameButton = ButtonDisplay.Continue_gameButton;
    static Button Chow_button = ButtonDisplay.Chow_button;
    static Button Pung_button = ButtonDisplay.Pung_button;
    static Button Kong_button = ButtonDisplay.Kong_button;
    static Button Hu_button = ButtonDisplay.Win_button;
    static Button Discard_Button = ButtonDisplay.Hand_tilesButton;
    public GameScreen(MahjongGame Mahjong_game) {
        this.Mahjong_game = Mahjong_game;
    }

    public static void loadGameWindow(Stage primaryStage){
        Chow_button.setOnAction(e -> {
            GameManager.handleChowButtonAction();
        });
        Pung_button.setOnAction(e -> {
            GameManager.handlePungButtonAction();
        });
        Kong_button.setOnAction(e -> {
            GameManager.handleKongButtonAction();
        });
        Hu_button.setOnAction(e -> {
            GameManager.handleHuButtonAction();
        });
        Discard_Button.setOnAction(e -> {
            GameManager.handleDiscardButtonAction();
        });
        Pause_gameButton.setOnAction(e -> {
            GameManager.handlePauseButtonAction();
        });
        Continue_gameButton.setOnAction(e -> {
            GameManager.handleContinueButtonAction();
        });
    }

    @Override
    public void loadWindow(Stage primaryStage) {
        loadGameWindow(primaryStage);
    }
}
