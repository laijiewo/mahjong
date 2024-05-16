package Display;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import System.*;
import Module.*;

public class ApplicationStart extends Application {

    @Override
    public void init() {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pixelmania.ttf"), 20);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Game game = new MahjongGame();
        GameManager manager = new GameManager(game);
        manager.run(primaryStage);
    }

    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}

