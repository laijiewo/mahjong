package Display;

import WebConnect.ServerHost;
import com.almasb.fxgl.net.Server;
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

public class LaunchScreen implements Screen{

    @FXML
    private Button LaunchGameButton;

    @FXML
    private TextField PortText;

    @FXML
    void LaunchGame(ActionEvent event) throws IOException {
        int port = Integer.parseInt(PortText.getText());
        if(port!=0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Server Host");
            alert.setHeaderText("Create Server Host");
            alert.setContentText("Successfully create game at server Port: "+port);
            ServerHost serverHost = new ServerHost(port);
        }
    }

    public LaunchScreen closeWindow() {
        Stage stage = (Stage) LaunchGameButton.getScene().getWindow();
        stage.close();
        return this;
    }

    public void loadWindow(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LaunchScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Launch Screen");
        stage.show();
    }
}

