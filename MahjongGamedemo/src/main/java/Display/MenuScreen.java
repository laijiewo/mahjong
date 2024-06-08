package Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller class for the menu screen of the Mahjong game.
 * Provides methods to navigate to different screens and perform actions such as creating or joining a game, learning rules, viewing scores, and exiting the game.
 */
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

    /**
     * Opens the launch game screen when the "Create Game" button is clicked.
     *
     * @param event The action event triggered by clicking the "Create Game" button.
     * @throws Exception if there is an error loading the LaunchScreen window.
     */
    @FXML
    void CreateGame(ActionEvent event) throws Exception {
        LaunchScreen launchScreen = new LaunchScreen();
        launchScreen.loadWindow(new Stage());
        Stage stage = (Stage) CreateGameButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Opens the join game screen when the "Join Game" button is clicked.
     *
     * @param event The action event triggered by clicking the "Join Game" button.
     * @throws Exception if there is an error loading the JoinScreen window.
     */
    @FXML
    void JoinGame(ActionEvent event) throws Exception {
        JoinScreen joinScreen = new JoinScreen();
        joinScreen.loadWindow(new Stage());
        Stage stage = (Stage) JoinGameButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Opens the rule screen when the "Rules" button is clicked.
     *
     * @param event The action event triggered by clicking the "Rules" button.
     * @throws Exception if there is an error loading the RuleScreen window.
     */
    @FXML
    void LearnRules(ActionEvent event) throws Exception {
        RuleScreen ruleScreen = new RuleScreen();
        ruleScreen.loadWindow(new Stage());
        Stage stage = (Stage) RulesButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the action of viewing scores when the "Scores" button is clicked.
     * Currently not implemented.
     *
     * @param event The action event triggered by clicking the "Scores" button.
     */
    @FXML
    void Scores(ActionEvent event) {
        // Implementation for viewing scores can be added here
    }

    /**
     * Exits the application when the "Exit" button is clicked.
     *
     * @param event The action event triggered by clicking the "Exit" button.
     */
    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Loads the menu screen window.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if there is an error loading the FXML file.
     */
    @Override
    public void loadWindow(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Display/MenuScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Mahjong Game");
        stage.setScene(scene);
        stage.show();
    }
}
