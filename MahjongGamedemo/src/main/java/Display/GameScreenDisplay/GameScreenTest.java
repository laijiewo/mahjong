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



    @Override
    public void init() {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pixelmania.ttf"), 20);
        Font.loadFont(getClass().getResourceAsStream("/fonts/DePixelKlein.ttf"), 20);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Player eastplayer = new Player();
        eastplayer.setSite(Site.East);

        Player southplayer = new Player();
        southplayer.setSite(Site.South);

        Player westplayer = new Player();
        westplayer.setSite(Site.West);

        Player northplayer = new Player();
        northplayer.setSite(Site.North);

        GameScreen gameScreen = new GameScreen();

        gameScreen.setPlayers(southplayer,westplayer,northplayer,eastplayer);
        gameScreen.loadWindow(primaryStage);

    }


    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}



