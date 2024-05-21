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
    Screen subscreen;

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
    void CreateGame(ActionEvent event) throws Exception {
        LaunchScreen launchScreen = new LaunchScreen();
        subscreen = (LaunchScreen) launchScreen;
        subscreen.loadWindow(new Stage());
    }

    @FXML
    void JoinGame(ActionEvent event) throws Exception {
        JoinScreen  joinScreen = new JoinScreen();
        subscreen = (JoinScreen) joinScreen;
        subscreen.loadWindow(new Stage());
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



    @Override
    public void loadWindow(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Display/MenuScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Mahjong Game");
        stage.setScene(scene);
        stage.show();
    }
}


