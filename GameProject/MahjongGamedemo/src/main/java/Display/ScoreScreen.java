package Display;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ScoreScreen implements Screen {
    Background Background;
    static Button Return_toMenuButton = ButtonDisplay.Return_toMenuButton;

    public static void loadScoreWindow(Stage primarystage){
        VBox layout = new VBox(10);
        layout.getChildren().addAll(new Label("This is ScoreScreen"),Return_toMenuButton);
        Return_toMenuButton.setOnAction(e -> {MenuScreen.loadMenuWindow(primarystage);});

        layout.setStyle("-fx-background-color: #fff; -fx-padding: 10; -fx-alignment: bottom");

        Scene scene = new Scene(layout,300,200);
        primarystage.setScene(scene);
        primarystage.setTitle("Score Screen");
        primarystage.show();

    }

    @Override
    public void loadWindow(Stage stage) {
        loadScoreWindow(stage);
    }
}
