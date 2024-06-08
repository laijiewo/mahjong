package Display;

import Module.Game.MahjongGame;
import System.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LaunchScreen implements Screen {

    @FXML
    private Button LaunchGameButton;

    @FXML
    private TextField PortText;

    @FXML
    private Button StartGameButton;

    @FXML
    private Label Waiting;

    /**
     * Handles the action of starting a new game. This method is triggered when the user clicks the "Start Game" button.
     * It opens the GameScreen window and closes the current window.
     *
     * @param event The action event triggered by clicking the "Start Game" button.
     * @throws Exception if there is an error loading the GameScreen window.
     */
    @FXML
    void StartGame(ActionEvent event) throws Exception {
        GameScreen gameScreen = new GameScreen();
        gameScreen.loadWindow(new Stage());
        Stage stage = (Stage) StartGameButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles the action of launching a game server. This method is triggered when the user clicks the "Launch Game" button.
     * It creates a new game server at the specified port and displays a success alert.
     *
     * @param event The action event triggered by clicking the "Launch Game" button.
     * @throws IOException if there is an error creating the game server.
     */
    @FXML
    void LaunchGame(ActionEvent event) throws IOException {
        int port = Integer.parseInt(PortText.getText());
        if (port != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server Host");
            alert.setHeaderText("Create Server Host");
            alert.setContentText("Successfully created game at server Port: " + port);
            alert.showAndWait();

            MahjongGame game = new MahjongGame(port);
            GameManager.addGame(game);
        }
    }

    /**
     * Closes the current window.
     *
     * @return The current instance of LaunchScreen.
     */
    public LaunchScreen closeWindow() {
        Stage stage = (Stage) LaunchGameButton.getScene().getWindow();
        stage.close();
        return this;
    }

    /**
     * Loads the launch screen window.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if there is an error loading the FXML file.
     */
    public void loadWindow(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Display/LaunchScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Launch Screen");
        stage.show();
    }
}


