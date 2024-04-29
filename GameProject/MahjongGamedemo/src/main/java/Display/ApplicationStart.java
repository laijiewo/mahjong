package Display;
import System.*;
import Module.*;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.stage.Stage;

public class ApplicationStart extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginScreen.loadLoginWindow(primaryStage); // Call the static method to load the login window
    }

    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}

