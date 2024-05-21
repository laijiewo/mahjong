package Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LaunchScreen implements Screen{

    @FXML
    private Button LaunchGameButton;

    @FXML
    private TextField PortText;

    @FXML
    void LaunchGame(ActionEvent event) {

    }
    public void closeWindow() {
        Stage stage = (Stage) LaunchGameButton.getScene().getWindow();
        stage.close();
    }

    public void loadWindow(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LaunchScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Launch Screen");
        stage.show();
    }
}

