package Display.GameScreenDisplay;

import Display.ClientDisplay.ClientTest;
import Display.Screen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Module.*;
import System.*;

public class JoinScreen implements Screen {

    @FXML
    private TextField IpText;

    @FXML
    private Button JoinGameButton;

    @FXML
    private TextField PortText;
    private Stage stage;

    @FXML
    void JoinGame(ActionEvent event) {
        Player player = new Player(stage);
        String ip = IpText.getText();
        int port = Integer.parseInt(PortText.getText());
        if(ip!="" && port!=0){
            player.setServerHostname(ip);
            player.setServerPort(port);
            try {
                player.connect();
                if (player.getconnected()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Connected to Server");
                    alert.setHeaderText("Connected to Server");
                    alert.setContentText("You are connected to the server");
                    alert.showAndWait();
                    GameManager.addPlayer(player);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error");
                    alert.setContentText("You are not connected to the server");
                    alert.showAndWait();
                }
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("You are not connected to the server");
                alert.showAndWait();
                System.out.println(e);
            }
        }
    }
    public JoinScreen closeWindow() {
        Stage stage = (Stage) JoinGameButton.getScene().getWindow();
        stage.close();
        return this;
    }

    @Override
    public void loadWindow(Stage stage) throws Exception {
        this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/Display/JoinScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Join Screen");
        stage.show();
    }
}

