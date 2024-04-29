package Display;
import System.*;
import Module.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen {
    private static Button loginButton = new Button("Login");
    private static TextField usernameField = new TextField();
    private static PasswordField passwordField = new PasswordField();

    public static void loadLoginWindow(Stage primaryStage) {
        // Setup the user interface elements
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        // Event handling for login button
        loginButton.setOnAction(event -> {
            // Assuming successful login, load the Menu Screen
            MenuScreen.loadMenuWindow(primaryStage);
        });

        // Create layout and add elements
        VBox layout = new VBox(10); // 10 is the spacing between elements
        layout.getChildren().addAll(new Label("Login"), usernameField, passwordField, loginButton);

        // Optionally set a background, adjust layout, etc.
        layout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        // Set the scene and stage
        Scene scene = new Scene(layout, 300, 200); // Set appropriate size for your window
        primaryStage.setTitle("Login Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

