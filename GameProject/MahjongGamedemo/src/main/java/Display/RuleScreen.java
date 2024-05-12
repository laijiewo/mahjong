package Display;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;


import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class RuleScreen implements Screen {
    private Background Background;
    private static Button Return_toMenuButton = ButtonDisplay.Return_toMenuButton;


    public static void loadRuleWindow(Stage primaryStage){
        VBox layout = new VBox(10);
        layout.getChildren().addAll(new Label("This is ruleScreen"),Return_toMenuButton);
        Return_toMenuButton.setOnAction(event -> {
            // Assuming successful login, load the Menu Screen
            MenuScreen.loadMenuWindow(primaryStage);
        });

        layout.setStyle("-fx-background-color: #fff; -fx-padding: 10; -fx-alignment: bottom");

        Scene scene = new Scene(layout,300,200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rule Screen");
        primaryStage.show();
    }

    @Override
    public void loadWindow(Stage stage) {
        loadRuleWindow(stage);
    }
}
