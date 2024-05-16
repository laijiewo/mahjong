package com.example.mahjonggamedemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class test extends Application{

    @Override
    public void init() {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pixelmania.ttf"), 20);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Regular.ttf"), 20);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Display/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Mahjong Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
