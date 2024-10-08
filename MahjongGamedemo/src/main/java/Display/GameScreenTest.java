package Display;
import Module.Game.MahjongGame;
import Module.Game.Player;
import Module.Tile.NumberTile;
import Module.Tile.Tile;
import Module.Tile.Suit;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import System.*;

import java.util.ArrayList;

public class GameScreenTest extends Application {



    @Override
    public void init() {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pixelmania.ttf"), 20);
        Font.loadFont(getClass().getResourceAsStream("/fonts/DePixelKlein.ttf"), 20);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pixel Bug.otf"), 20);
        Font.loadFont(getClass().getResourceAsStream("/fonts/PixelGameFont.ttf"), 20);


    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage1, stage2, stage3, stage4;
        stage1 = new Stage();
        stage2 = new Stage();
        stage3 = new Stage();
        stage4 = new Stage();

        int port = 8081;
        String ip = "127.0.0.1";

        GameManager gameManager = new GameManager();
        MahjongGame game = new MahjongGame(port);
        GameManager.addGame(game);


        Player eastplayer = new Player();
        eastplayer.setServerPort(port);
        eastplayer.setServerHostname(ip);
        eastplayer.connect();
        Player southplayer = new Player();
        southplayer.setServerPort(port);
        southplayer.setServerHostname(ip);
        southplayer.connect();
        Player westplayer = new Player();
        westplayer.setServerPort(port);
        westplayer.setServerHostname(ip);
        westplayer.connect();
        Player northplayer = new Player();
        northplayer.setServerPort(port);
        northplayer.setServerHostname(ip);
        northplayer.connect();

//        ArrayList<Tile> hand = new ArrayList<Tile>();
//        ArrayList<Tile> pungTiles = new ArrayList<Tile>();
//        hand.add(new NumberTile(9, Suit.WAN));
//        hand.add(new NumberTile(3, Suit.WAN));
//        hand.add(new NumberTile(4, Suit.WAN));
//        hand.add(new NumberTile(5, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.WAN));
//        hand.add(new NumberTile(7, Suit.WAN));
//        hand.add(new NumberTile(8, Suit.WAN));
//        hand.add(new NumberTile(6, Suit.TIAO));
//        hand.add(new NumberTile(1, Suit.TIAO));
//        hand.add(new NumberTile(2, Suit.TIAO));
//        pungTiles.add(new NumberTile(4, Suit.TIAO));
//        pungTiles.add(new NumberTile(4, Suit.TIAO));
//        pungTiles.add(new NumberTile(4, Suit.TIAO));
//
//        eastplayer.setDiscard_Tiles(hand);
//        eastplayer.setChew_Pong_Kung_Tiles(pungTiles);
//
//        ArrayList<Tile> hand1 = new ArrayList<Tile>();
//        hand1.add(new NumberTile(9, Suit.WAN));
//        hand1.add(new NumberTile(3, Suit.WAN));
//        hand1.add(new NumberTile(4, Suit.WAN));
//        hand1.add(new NumberTile(5, Suit.WAN));
//        southplayer.setDiscard_Tiles(hand1);
//
//        ArrayList<Tile> hand2 = new ArrayList<Tile>();
//        hand2.add(new NumberTile(7, Suit.WAN));
//        hand2.add(new NumberTile(8, Suit.WAN));
//        hand2.add(new NumberTile(6, Suit.TIAO));
//        westplayer.setDiscard_Tiles(hand2);
//
//        ArrayList<Tile> hand3 = new ArrayList<Tile>();
//        hand3.add(new NumberTile(1, Suit.TIAO));
//        hand3.add(new NumberTile(2, Suit.TIAO));
//        northplayer.setDiscard_Tiles(hand3);

        try {
            GameManager.addPlayer(eastplayer);
        } catch (Exception e) {
            System.out.println(1);
            e.printStackTrace();
            System.out.println("Cannot add player");
        }
        try {
            GameManager.addPlayer(southplayer);
        } catch (Exception e) {
            System.out.println(2);
            e.printStackTrace();
            System.out.println("Cannot add player");
        }
        try {
            GameManager.addPlayer(westplayer);
        } catch (Exception e) {
            System.out.println(3);
            e.printStackTrace();
            System.out.println("Cannot add player");
        }
        try {
            GameManager.addPlayer(northplayer);
        } catch (Exception e) {
            System.out.println(4);
            e.printStackTrace();
            System.out.println("Cannot add player");
        }

    }


    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}



