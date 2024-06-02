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

    @FXML
    private ImageView tile5111;

    @FXML
    private ImageView tile111;

    @FXML
    private ImageView tile61;

    @FXML
    private ImageView tile10111;

    @FXML
    private ImageView tile511;

    @FXML
    private ImageView tile911;

    @FXML
    private Button Chow;

    @FXML
    private ImageView tile1311;

    @FXML
    private ImageView tile91111;

    @FXML
    private ImageView tile111111;

    @FXML
    private ImageView tile1711;

    @FXML
    private Button Kong;

    @FXML
    private ImageView tile131111;

    @FXML
    private ImageView tile101;

    @FXML
    private ImageView tile2111;

    @FXML
    private ImageView optile10;

    @FXML
    private ImageView tile51;

    @FXML
    private ImageView optile11;

    @FXML
    private Label playerDirection1;

    @FXML
    private ImageView tile21111;

    @FXML
    private Label playerDirection3;

    @FXML
    private Label playerDirection2;

    @FXML
    private Label playerDirection4;

    @FXML
    private ImageView optile12;

    @FXML
    private ImageView tile18111;

    @FXML
    private ImageView optile13;

    @FXML
    private ImageView tile191;

    @FXML
    private ImageView tile131;

    @FXML
    private Button Pause;

    @FXML
    private ImageView tile7111;

    @FXML
    private GridPane OPTiles;

    @FXML
    private ImageView tile1011;

    @FXML
    private ImageView tile411;

    @FXML
    private ImageView tile41;

    @FXML
    private ImageView pretile10;

    @FXML
    private ImageView pretile11;

    @FXML
    private ImageView pretile12;

    @FXML
    private ImageView tile811;

    @FXML
    private ImageView pretile13;

    @FXML
    private ImageView tile41111;

    @FXML
    private Label player1Photo;

    @FXML
    private ImageView nexttile12;

    @FXML
    private ImageView nexttile13;

    @FXML
    private ImageView tile1411;

    @FXML
    private ImageView nexttile10;

    @FXML
    private ImageView nexttile11;

    @FXML
    private Button T1;

    @FXML
    private ImageView tile1811;

    @FXML
    private Button T2;

    @FXML
    private ImageView tile19111;

    @FXML
    private ImageView tile151111;

    @FXML
    private Button T3;

    @FXML
    private Button T4;

    @FXML
    private ImageView tile31;

    @FXML
    private ImageView tile61111;

    @FXML
    private Button T5;

    @FXML
    private Button T6;

    @FXML
    private ImageView tile121;

    @FXML
    private ImageView tile4111;

    @FXML
    private Button T7;

    @FXML
    private ImageView tile13111;

    @FXML
    private Button T8;

    @FXML
    private Button T9;

    @FXML
    private ImageView tile211111;

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
    private ImageView pretile5;

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
    private Button T13;

    @FXML
    private ImageView pretile1;

    @FXML
    private ImageView tile171111;

    @FXML
    private ImageView tile16111;

    @FXML
    private ImageView pretile9;

    @FXML
    private ImageView optile5;

    @FXML
    private ImageView nexttile1;

    @FXML
    private ImageView optile6;

    @FXML
    private ImageView nexttile2;

    @FXML
    private ImageView tile21;

    @FXML
    private ImageView optile3;

    @FXML
    private ImageView nexttile3;

    @FXML
    private ImageView optile4;

    @FXML
    private ImageView nexttile4;

    @FXML
    private ImageView tile14111;

    @FXML
    private ImageView optile1;

    @FXML
    private ImageView nexttile5;

    @FXML
    private ImageView optile2;

    @FXML
    private ImageView nexttile6;

    @FXML
    private ImageView tile311;

    @FXML
    private ImageView nexttile7;

    @FXML
    private ImageView tile1111;

    @FXML
    private ImageView nexttile8;

    @FXML
    private ImageView nexttile9;

    @FXML
    private ImageView tile101111;

    @FXML
    private ImageView tile711;

    @FXML
    private ImageView tile20111;

    @FXML
    private ImageView optile9;

    @FXML
    private ImageView tile9111;

    @FXML
    private ImageView optile7;

    @FXML
    private ImageView tile81111;

    @FXML
    private ImageView optile8;

    @FXML
    private ImageView tile191111;

    @FXML
    private ImageView tile201111;

    @FXML
    private ImageView tile17111;

    @FXML
    private ImageView tile1511;

    @FXML
    private ImageView tile161;

    @FXML
    private ImageView tile1911;

    @FXML
    private ImageView tile141;

    @FXML
    private ImageView tile11;

    @FXML
    private ImageView tile11111;

    @FXML
    private ImageView tile6111;

    @FXML
    private ImageView tile91;

    @FXML
    private ImageView tile121111;

    @FXML
    private ImageView tile141111;

    @FXML
    private GridPane Tiles;

    @FXML
    private Button Pung;

    @FXML
    private ImageView tile31111;

    @FXML
    private Label player3Photo;

    @FXML
    private Button Win;

    @FXML
    private ImageView tile151;

    @FXML
    private ImageView tile12111;

    @FXML
    private ImageView tile211;

    @FXML
    private ImageView tile3111;

    @FXML
    private ImageView tile1211;

    @FXML
    private ImageView tile611;

    @FXML
    private ImageView tile161111;

    @FXML
    private ImageView tile81;

    @FXML
    private ImageView tile1611;

    @FXML
    private ImageView tile181;

    @FXML
    private ImageView tile15111;

    @FXML
    private ImageView tile51111;

    @FXML
    private ImageView tile181111;

    @FXML
    private ImageView tile71;

    @FXML
    private ImageView tile2011;

    @FXML
    private ImageView tile201;

    @FXML
    private Label player2Photo;

    @FXML
    private ImageView tile8111;

    @FXML
    private ImageView tile171;

    @FXML
    private ImageView tile71111;

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
        MainPlayer=player1;
        NextPlayer=player2;
        OppositePlayer=player3;
        PreviousPlayer=player4;

        Players.add(player1);
        Players.add(player2);
        Players.add(player3);
        Players.add(player4);



        player1Photo.setStyle(
                "-fx-background-image: url('/UI/East.png');" +
                        " -fx-background-repeat: no-repeat;" +
                        " -fx-background-position: center center;" +
                        " -fx-background-size: stretch;"
        );

    }

    private void setPlayerPhotoStyle(Label playerPhoto,Player player){
        Site site = player.getPlayerSite();
        if(site==Site.East) {
            playerPhoto.setStyle(
                    "-fx-background-image: url('/UI/East.png');" +
                            " -fx-background-repeat: no-repeat;" +
                            " -fx-background-position: center center;" +
                            " -fx-background-size: stretch;"
            );
        }else if(site==Site.North){
            playerPhoto.setStyle(
                    "-fx-background-image: url('/UI/North.png');" +
                            " -fx-background-repeat: no-repeat;" +
                            " -fx-background-position: center center;" +
                            " -fx-background-size: stretch;"
            );
        } else if (site==Site.South) {
            playerPhoto.setStyle(
                    "-fx-background-image: url('/UI/South.png');"
                    + " -fx-background-repeat: no-repeat;"
                    + " -fx-background-position: center center;"
                    + " -fx-background-size: stretch;"
            );
        }else{
            playerPhoto.setStyle(
                    "-fx-background-image: url('/UI/West.png');"
                    + " -fx-background-repeat: no-repeat;"
                    + " -fx-background-position: center center;"
                    + " -fx-background-size: stretch;"
            );
        }
    }
    private void setPlayerSiteImage(Label playerDirection,Player player){
        Site site = player.getPlayerSite();
        if(site==Site.East) {
            playerDirection.setStyle(
                    "-fx-background-image: url('/UI/East.png');" +
                            " -fx-background-repeat: no-repeat;" +
                            " -fx-background-position: center center;" +
                            " -fx-background-size: stretch;"
            );
        }else if(site==Site.North){
            playerDirection.setStyle(
                    "-fx-background-image: url('/UI/North.png');" +
                            " -fx-background-repeat: no-repeat;" +
                            " -fx-background-position: center center;" +
                            " -fx-background-size: stretch;"
            );
        } else if (site==Site.South) {
            playerDirection.setStyle(
                    "-fx-background-image: url('/UI/South.png');"
                            + " -fx-background-repeat: no-repeat;"
                            + " -fx-background-position: center center;"
                            + " -fx-background-size: stretch;"
            );
        }else{
            playerDirection.setStyle(
                    "-fx-background-image: url('/UI/West.png');"
                            + " -fx-background-repeat: no-repeat;"
                            + " -fx-background-position: center center;"
                            + " -fx-background-size: stretch;"
            );
        }
    }

    @Override
    public void loadWindow(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Display/GameScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Game Screen");
        stage.show();
    }
}

