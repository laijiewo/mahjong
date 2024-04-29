package Display;
import System.*;
import Module.*;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuScreen {

    private static Background background; // Assuming Background is a custom class or JavaFX Background
    private static Button startGameButton = ButtonDisplay.Start_gameButton;
    private static Button exitGameButton = ButtonDisplay.Exit_gameButton;
    private static Button rulesExplanationButton = ButtonDisplay.Rules_explanationButton;
    private static Button scoreInquiryButton = ButtonDisplay.Score_inquiryButton;

    public static void loadMenuWindow(Stage primaryStage) {
        // Create layout and add elements
        VBox layout = new VBox(10); // 10 is the spacing between elements
        layout.getChildren().addAll(startGameButton, exitGameButton, rulesExplanationButton, scoreInquiryButton);
        startGameButton.setOnAction(event -> {GameScreen.loadGameWindow(primaryStage);});
        exitGameButton.setOnAction(event -> {Platform.exit();});
        rulesExplanationButton.setOnAction(event -> {RuleScreen.loadRuleWindow(primaryStage);});
        scoreInquiryButton.setOnAction(event -> {ScoreScreen.loadScoreWindow(primaryStage);});

        // Optionally set a background, adjust layout, etc.
        layout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // Set the scene and stage
        Scene scene = new Scene(layout, 300, 200); // Set appropriate size for your window
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu Screen");
        primaryStage.show();
    }
}

