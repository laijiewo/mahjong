package Display;

import javafx.application.Application;
import javafx.stage.Stage;
import System.*;
import Module.*;

public class ApplicationStart extends Application {

    @Override
    public void start(Stage primaryStage) {
        Game game = new MahjongGame();
        GameManager manager = new GameManager(game);
        manager.run(primaryStage);
    }

    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}

