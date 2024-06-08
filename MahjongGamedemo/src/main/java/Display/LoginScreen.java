package Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginScreen implements Screen{

    Stage stage;

    @FXML
    private TextField AccountText;

    @FXML
    private TextField PasswordText;

    @FXML
    private Label AccountLabel;

    @FXML
    private Button LoginButton;

    @FXML
    private Label GameTitle;

    @FXML
    private Label PasswordLabel;

    @FXML
    private ImageView BackgroundImage;

    /**
     * Handles the login action. This method is triggered when the user clicks the "Login" button.
     * It retrieves the username and password from the text fields, then loads the MenuScreen window and closes the current window.
     *
     * @param event The action event triggered by clicking the "Login" button.
     * @throws Exception if there is an error loading the MenuScreen window.
     */
    @FXML
    void Login(ActionEvent event) throws Exception {
        String username = AccountText.getText();
        String password = PasswordText.getText();
        MenuScreen menuScreen = new MenuScreen();
        menuScreen.loadWindow(new Stage());
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Loads the login screen window.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if there is an error loading the FXML file.
     */
    @Override
    public void loadWindow(Stage stage)throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Display/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Mahjong Game");
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }
}
