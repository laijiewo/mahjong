package Display;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Display.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML
    void Login(ActionEvent event) throws Exception {
        String username = AccountText.getText();
        String password = PasswordText.getText();
        MenuScreen menuScreen = new MenuScreen();
        menuScreen.loadWindow(new Stage());
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.close();
    }


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

