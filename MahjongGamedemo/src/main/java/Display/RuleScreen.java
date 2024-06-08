package Display;

import Display.Screen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

/**
 * Controller class for the rule screen of the Mahjong game.
 * Provides the method to load the rule screen window.
 */
public class RuleScreen implements Screen {
    private Background Background;
    Stage stage;

    @FXML
    private TextArea textarea;

    /**
     * Loads the rule screen window.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if there is an error loading the FXML file.
     */
    @Override
    public void loadWindow(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Display/RuleScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Rule Screen");
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }
}
