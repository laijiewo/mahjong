package Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class JoinScreen implements Screen{

    @FXML
    private TextField IpText;

    @FXML
    private Button JoinGameButton;

    @FXML
    private TextField PortText;

    @FXML
    void JoinGame(ActionEvent event) {
        ClientTest client = new ClientTest();
        String ip = IpText.getText();
        int port = Integer.parseInt(PortText.getText());
        if(ip!="" && port!=0){
            System.out.println(12);
            client.setSeverHostname(ip);
            client.setSeverPort(port);
            client.connect();
        }
    }
    public JoinScreen closeWindow() {
        Stage stage = (Stage) JoinGameButton.getScene().getWindow();
        stage.close();
        return this;
    }

    @Override
    public void loadWindow(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("JoinScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Join Screen");
        stage.show();
    }
}

