package Display;
import Display.Screen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;


import javafx.stage.Stage;


public class RuleScreen implements Screen {
    private Background Background;
    Stage stage;

    @FXML
    private TextArea textarea;



    @Override
    public void loadWindow(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Display/RuleScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Rule Screen");
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }
}
