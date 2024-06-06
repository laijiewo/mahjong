package com.example.mahjonggamedemo;

import System.GameManager;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ApplicationStart1 extends Application {

    @Override
    public void init() {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pixelmania.ttf"), 20);
        Font.loadFont(getClass().getResourceAsStream("/fonts/DePixelKlein.ttf"), 20);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pixel Bug.otf"), 20);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        GameManager gameManager = new GameManager();
        gameManager.run(primaryStage);
    }


    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}

