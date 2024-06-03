package Display.GameScreenDisplay;
import Display.Screen;
import System.*;
import Module.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


public class GameScreen implements Screen {

    Player MainPlayer;
    Player NextPlayer;
    Player OppositePlayer;
    Player PreviousPlayer;
    List<Player> Players = new ArrayList<>();
    List<Label> playerDirections = new ArrayList<>();
    List<Label> playerPhotos = new ArrayList<>();

    @FXML
    private ImageView tile11_111;

    @FXML
    private ImageView tile19_11;

    @FXML
    private Button Chow;

    @FXML
    private ImageView tile7_111;

    @FXML
    private ImageView tile11_11;

    @FXML
    private ImageView tile3_1;

    @FXML
    private ImageView tile7_1;

    @FXML
    private ImageView tile8_1111;

    @FXML
    private ImageView tile12_1111;

    @FXML
    private ImageView tile6_11;

    @FXML
    private Button Kong;

    @FXML
    private ImageView optile10;

    @FXML
    private ImageView optile11;

    @FXML
    private ImageView tile16_11;

    @FXML
    private ImageView tile12_1;

    @FXML
    private Label playerDirection1;

    @FXML
    private Label playerDirection3;

    @FXML
    private ImageView tile18_111;

    @FXML
    private Label playerDirection2;

    @FXML
    private ImageView tile16_1;

    @FXML
    private Label playerDirection4;

    @FXML
    private ImageView tile7_1111;

    @FXML
    private ImageView tile13_1111;

    @FXML
    private ImageView tile3_11;

    @FXML
    private ImageView tile10_1111;

    @FXML
    private ImageView optile12;

    @FXML
    private ImageView optile13;

    @FXML
    private ImageView tile9_111;

    @FXML
    private Button Pause;

    @FXML
    private ImageView tile15_1111;

    @FXML
    private GridPane OPTiles;

    @FXML
    private ImageView tile15_111;

    @FXML
    private ImageView pretile10;

    @FXML
    private ImageView pretile11;

    @FXML
    private ImageView pretile12;

    @FXML
    private ImageView pretile13;

    @FXML
    private ImageView tile17_11;

    @FXML
    private Label player1Photo;

    @FXML
    private ImageView tile2_111;

    @FXML
    private ImageView tile16_1111;

    @FXML
    private ImageView tile2_1;

    @FXML
    private ImageView nexttile12;

    @FXML
    private ImageView nexttile13;

    @FXML
    private ImageView tile8_11;

    @FXML
    private ImageView nexttile10;

    @FXML
    private ImageView tile6_1;

    @FXML
    private ImageView nexttile11;

    @FXML
    private ImageView tile17_111;

    @FXML
    private Button T1;

    @FXML
    private Button T2;

    @FXML
    private Button T3;

    @FXML
    private Button T4;

    @FXML
    private Button T5;

    @FXML
    private Button T6;

    @FXML
    private ImageView tile21_111;

    @FXML
    private Button T7;

    @FXML
    private Button T8;

    @FXML
    private Button T9;

    @FXML
    private ImageView tile6_111;

    @FXML
    private ImageView tile13_111;

    @FXML
    private ImageView tile13_1;

    @FXML
    private ImageView tile14_11;

    @FXML
    private ImageView tile17_1;

    @FXML
    private Label player4Photo;

    @FXML
    private ImageView pretile8;

    @FXML
    private ImageView pretile7;

    @FXML
    private Button T10;

    @FXML
    private ImageView pretile6;

    @FXML
    private ImageView tile5_11;

    @FXML
    private ImageView pretile5;

    @FXML
    private ImageView tile6_1111;

    @FXML
    private Button T12;

    @FXML
    private ImageView pretile4;

    @FXML
    private Button T11;

    @FXML
    private ImageView pretile3;

    @FXML
    private Button T14;

    @FXML
    private ImageView pretile2;

    @FXML
    private ImageView tile20_1;

    @FXML
    private Button T13;

    @FXML
    private ImageView pretile1;

    @FXML
    private ImageView tile9_1111;

    @FXML
    private ImageView tile4_111;

    @FXML
    private ImageView tile20_11;

    @FXML
    private ImageView pretile9;

    @FXML
    private ImageView tile3_1111;

    @FXML
    private ImageView optile5;

    @FXML
    private ImageView nexttile1;

    @FXML
    private ImageView optile6;

    @FXML
    private ImageView nexttile2;

    @FXML
    private ImageView optile3;

    @FXML
    private ImageView nexttile3;

    @FXML
    private ImageView tile21_11;

    @FXML
    private ImageView tile18_1111;

    @FXML
    private ImageView optile4;

    @FXML
    private ImageView nexttile4;

    @FXML
    private ImageView optile1;

    @FXML
    private ImageView nexttile5;

    @FXML
    private ImageView optile2;

    @FXML
    private ImageView nexttile6;

    @FXML
    private ImageView tile8_111;

    @FXML
    private ImageView tile10_111;

    @FXML
    private ImageView nexttile7;

    @FXML
    private ImageView nexttile8;

    @FXML
    private ImageView nexttile9;

    @FXML
    private ImageView tile15_11;

    @FXML
    private ImageView tile2_1111;

    @FXML
    private ImageView optile9;

    @FXML
    private ImageView optile7;

    @FXML
    private ImageView tile21_1111;

    @FXML
    private ImageView optile8;

    @FXML
    private ImageView tile2_11;

    @FXML
    private ImageView tile19_1111;

    @FXML
    private ImageView tile20_1111;

    @FXML
    private ImageView tile1_1;

    @FXML
    private ImageView tile12_111;

    @FXML
    private ImageView tile5_1;

    @FXML
    private ImageView tile9_1;

    @FXML
    private ImageView tile1_1111;

    @FXML
    private ImageView tile1_111;

    @FXML
    private ImageView tile4_1111;

    @FXML
    private ImageView tile14_1;

    @FXML
    private ImageView tile18_1;

    @FXML
    private ImageView tile19_111;

    @FXML
    private ImageView tile12_11;

    @FXML
    private GridPane Tiles;

    @FXML
    private ImageView tile21_1;

    @FXML
    private Button Pung;

    @FXML
    private Label player3Photo;

    @FXML
    private Button Win;

    @FXML
    private ImageView tile7_11;

    @FXML
    private ImageView tile3_111;

    @FXML
    private ImageView tile16_111;

    @FXML
    private ImageView tile13_11;

    @FXML
    private ImageView tile4_11;

    @FXML
    private ImageView tile10_1;

    @FXML
    private ImageView tile5_1111;

    @FXML
    private ImageView tile4_1;

    @FXML
    private ImageView tile8_1;

    @FXML
    private ImageView tile19_1;

    @FXML
    private Label player2Photo;

    @FXML
    private ImageView tile11_1111;

    @FXML
    private ImageView tile11_1;

    @FXML
    private ImageView tile15_1;

    @FXML
    private ImageView tile18_11;

    @FXML
    private ImageView tile14_111;

    @FXML
    private ImageView tile20_111;

    @FXML
    private ImageView tile10_11;

    @FXML
    private ImageView tile1_11;

    @FXML
    private ImageView tile5_111;

    @FXML
    private ImageView tile9_11;

    @FXML
    private ImageView tile17_1111;

    @FXML
    private ImageView tile14_1111;

    @FXML
    void DiscardTile1(ActionEvent event) {

    }

    @FXML
    void DiscardTile11(ActionEvent event) {

    }

    @FXML
    void DiscardTile7(ActionEvent event) {

    }

    @FXML
    void DiscardTile6(ActionEvent event) {

    }

    @FXML
    void DiscardTile3(ActionEvent event) {

    }

    @FXML
    void DiscardTile14(ActionEvent event) {

    }

    @FXML
    void DiscardTile9(ActionEvent event) {

    }

    @FXML
    void DiscardTile12(ActionEvent event) {

    }

    @FXML
    void DiscardTile8(ActionEvent event) {

    }

    @FXML
    void DiscardTile13(ActionEvent event) {

    }

    @FXML
    void DiscardTile5(ActionEvent event) {

    }

    @FXML
    void DiscardTile10(ActionEvent event) {

    }

    @FXML
    void DiscardTile4(ActionEvent event) {

    }

    @FXML
    void DiscardTile2(ActionEvent event) {

    }

    @FXML
    void Pung(ActionEvent event) {

    }

    @FXML
    void Chow(ActionEvent event) {

    }

    @FXML
    void Kong(ActionEvent event) {

    }

    @FXML
    void Win(ActionEvent event) {

    }

    @FXML
    void Pause(ActionEvent event) {

    }

    public void setPlayers(Player player1,Player player2,Player player3, Player player4){
        Players.add(player1);
        playerPhotos.add(player1Photo);
        playerDirections.add(playerDirection1);

        Players.add(player2);
        playerPhotos.add(player2Photo);
        playerDirections.add(playerDirection2);

        Players.add(player3);
        playerPhotos.add(player3Photo);
        playerDirections.add(playerDirection3);

        Players.add(player4);
        playerPhotos.add(player4Photo);
        playerDirections.add(playerDirection4);

    }

    private void setPlayerPhotoStyle(){
        for(int i = 0 ; i !=4; i++) {
            Player player = Players.get(i);
            Label playerPhoto = playerPhotos.get(i);
            Site site = player.getPlayerSite();
            if (playerPhoto != null) {
                if (site == Site.East) {
                    playerPhoto.setStyle(
                            "-fx-background-image: url('/UI/East.png');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: stretch;"
                    );
                } else if (site == Site.North) {
                    playerPhoto.setStyle(
                            "-fx-background-image: url('/UI/North.png');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: stretch;"
                    );
                } else if (site == Site.South) {
                    playerPhoto.setStyle(
                            "-fx-background-image: url('/UI/South.png');"
                                    + " -fx-background-repeat: no-repeat;"
                                    + " -fx-background-position: center center;"
                                    + " -fx-background-size: stretch;"
                    );
                } else {
                    playerPhoto.setStyle(
                            "-fx-background-image: url('/UI/West.png');"
                                    + " -fx-background-repeat: no-repeat;"
                                    + " -fx-background-position: center center;"
                                    + " -fx-background-size: stretch;"
                    );
                }
            }
        }
    }

    private void setPlayerSiteImage(){
        for(int i = 0; i !=4; i++) {
            Player player = Players.get(i);
            Label playerDirection = playerDirections.get(i);
            Site site = player.getPlayerSite();
            if (playerDirection != null) {
                if (site == Site.East) {
                    playerDirection.setStyle(
                            "-fx-background-image: url('/UI/East.png');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: stretch;"
                    );
                } else if (site == Site.North) {
                    playerDirection.setStyle(
                            "-fx-background-image: url('/UI/North.png');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: stretch;"
                    );
                } else if (site == Site.South) {
                    playerDirection.setStyle(
                            "-fx-background-image: url('/UI/South.png');"
                                    + " -fx-background-repeat: no-repeat;"
                                    + " -fx-background-position: center center;"
                                    + " -fx-background-size: stretch;"
                    );
                } else {
                    playerDirection.setStyle(
                            "-fx-background-image: url('/UI/West.png');"
                                    + " -fx-background-repeat: no-repeat;"
                                    + " -fx-background-position: center center;"
                                    + " -fx-background-size: stretch;"
                    );
                }
            } else {
                System.out.println("failed to change site");
            }
        }
    }

    @FXML
    private void initialize() {
        // 确保控件已经初始化
        assert player1Photo != null : "fx:id=\"player1Photo\" was not injected: check your FXML file 'GameScreen.fxml'.";
        assert player2Photo != null : "fx:id=\"player2Photo\" was not injected: check your FXML file 'GameScreen.fxml'.";
        assert player3Photo != null : "fx:id=\"player3Photo\" was not injected: check your FXML file 'GameScreen.fxml'.";
        assert player4Photo != null : "fx:id=\"player4Photo\" was not injected: check your FXML file 'GameScreen.fxml'.";
        assert playerDirection1 != null : "fx:id=\"playerDirection1\" was not injected: check your FXML file 'GameScreen.fxml'.";
        assert playerDirection2 != null : "fx:id=\"playerDirection2\" was not injected: check your FXML file 'GameScreen.fxml'.";
        assert playerDirection3 != null : "fx:id=\"playerDirection3\" was not injected: check your FXML file 'GameScreen.fxml'.";
        assert playerDirection4 != null : "fx:id=\"playerDirection4\" was not injected: check your FXML file 'GameScreen.fxml'.";
    }

    @Override
    public void loadWindow(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Display/GameScreen.fxml"));
        loader.setController(this); // 设置当前类为控制器
        Parent root = loader.load(); // 加载 FXML 文件

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Game Screen");
        stage.show();

        // 调用设置方法
        setPlayerPhotoStyle();
        setPlayerSiteImage();
    }
}

