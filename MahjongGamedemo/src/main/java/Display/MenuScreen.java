package Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuScreen implements Screen {

    @FXML
    private Button RulesButton;

    @FXML
    private Button ExitButton;

    @FXML
    private Button ScoresButton;

    @FXML
    private Button JoinGameButton;

    @FXML
    private Label GameTitle;

    @FXML
    private Button CreateGameButton;

    @FXML
    void JoinGame(ActionEvent event) {

    }

    @FXML
    void LearnRules(ActionEvent event) {

    }

    @FXML
    void Scores(ActionEvent event) {

    }

    @FXML
    void Exit(ActionEvent event) {

    }

    @FXML
    void CreateGame(ActionEvent event) {

    }

    @Override
    public void loadWindow(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Display/MenuScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Mahjong Game");
        stage.setScene(scene);
        stage.show();
    }
}


