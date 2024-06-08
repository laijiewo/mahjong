package Display;

import Module.Game.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class JoinScreen implements Screen {

    @FXML
    private TextField IpText;

    @FXML
    private Button JoinGameButton;

    @FXML
    private TextField PortText;

    /**
     * Handles the action of joining a game. This method is triggered when the user clicks the "Join Game" button.
     * It attempts to connect the player to the server using the IP address and port specified in the text fields.
     *
     * @param event The action event triggered by clicking the "Join Game" button.
     */
    @FXML
    void JoinGame(ActionEvent event) {
        Player player = new Player();
        player.joinToHost();
        String ip = IpText.getText();
        int port = Integer.parseInt(PortText.getText());

        if (!ip.isEmpty() && port != 0) {
            player.setServerHostname(ip);
            player.setServerPort(port);

            try {
                player.connect();
                if (player.getconnected()) {
                    showAlert(Alert.AlertType.INFORMATION, "Connected to Server", "You are connected to the server");
                    player.joinToHost();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "You are not connected to the server");
                }
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Error", "You are not connected to the server");
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes the current window.
     *
     * @return The current instance of JoinScreen.
     */
    public JoinScreen closeWindow() {
        Stage stage = (Stage) JoinGameButton.getScene().getWindow();
        stage.close();
        return this;
    }

    /**
     * Loads the join screen window.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if there is an error loading the FXML file.
     */
    @Override
    public void loadWindow(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Display/JoinScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Join Screen");
        stage.show();
    }

    /**
     * Shows an alert dialog with the specified type, title, and content.
     *
     * @param alertType The type of alert (e.g., INFORMATION, ERROR).
     * @param title The title of the alert dialog.
     * @param content The content text of the alert dialog.
     */
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


