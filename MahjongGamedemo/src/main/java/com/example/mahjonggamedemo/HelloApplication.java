package com.example.mahjonggamedemo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();

        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10));
        gp.setHgap(4);
        gp.setVgap(8);
        VBox.setVgrow(gp, Priority.ALWAYS);

        Label lblTitle = new Label("欢迎来到售后支持页面。");

        Label lblEmail = new Label("邮箱");
        TextField tfEmail = new TextField();

        Label lblPriority = new Label("优先级");
        ObservableList<String> priorities =
                FXCollections.observableArrayList("高", "中", "低");
        ComboBox<String> cbPriority = new ComboBox<>(priorities);

        Label lblProblem = new Label("问题");
        TextField tfProblem = new TextField();

        Label lblDescription = new Label("描述");
        TextArea taDescription = new TextArea();

        gp.add(lblTitle, 1, 1);
        gp.add(lblEmail, 0, 2);
        gp.add(tfEmail, 1, 2);
        gp.add(lblPriority, 0, 3);
        gp.add(cbPriority, 1, 3);
        gp.add(lblProblem, 0, 4);
        gp.add(tfProblem, 1, 4);
        gp.add(lblDescription, 0, 5);
        gp.add(taDescription, 1, 5);

        Separator sep = new Separator(); // 水平分割线

        ButtonBar buttonBar = new ButtonBar();
        buttonBar.setPadding(new Insets(10));

        Button saveButton = new Button("保存");
        Button cancelButton = new Button("取消");

        buttonBar.setButtonData(saveButton, ButtonBar.ButtonData.OK_DONE);
        buttonBar.setButtonData(cancelButton, ButtonBar.ButtonData.CANCEL_CLOSE);

        buttonBar.getButtons().addAll(saveButton, cancelButton);

        vbox.getChildren().addAll(gp, sep, buttonBar);

        Scene scene = new Scene(vbox);

        stage.setTitle("布局面板测试");
        stage.setScene(scene);
        stage.setWidth(736);
        stage.setHeight(414);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
