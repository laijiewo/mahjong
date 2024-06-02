package Display.GameScreenDisplay;
import Module.*;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Display.GameScreenDisplay.LoginScreen;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import System.*;

public class GameScreenTest extends Application {

    Player player1 = new Player(0, Site.East);
    Player player2 = new Player(0, Site.South);
    Player player3 = new Player(0, Site.West);
    Player player4 = new Player(0, Site.North);

    @Override
    public void init() {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pixelmania.ttf"), 20);
        Font.loadFont(getClass().getResourceAsStream("/fonts/DePixelKlein.ttf"), 20);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = new MahjongGame();
        GameManager manager = new GameManager(game);
        GameScreen gameScreen = new GameScreen();
        gameScreen.loadWindow(primaryStage);
        manager.run(primaryStage);
    }


    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}



