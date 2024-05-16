package com.example.mahjonggamedemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Display.*;
import javafx.scene.image.ImageView;

public class Sample {

    @FXML
    private TextField AccountText;

    @FXML
    private TextField PasswordText;

    @FXML
    private Label AccountLabel;

    @FXML
    private Button LogininButton;

    @FXML
    private Label PasswordLabel;
    @FXML
    private ImageView BackgroundImage;

    @FXML
    void Login(ActionEvent event) {
        String username = AccountText.getText();
        String password = PasswordText.getText();
    }

}


