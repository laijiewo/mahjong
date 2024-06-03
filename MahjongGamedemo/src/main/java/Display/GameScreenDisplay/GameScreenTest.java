package Display.GameScreenDisplay;
import Module.*;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Display.GameScreenDisplay.LoginScreen;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import System.*;

import java.io.IOException;
import java.util.ArrayList;

public class GameScreenTest extends Application {



    @Override
    public void init() {
        Font.loadFont(getClass().getResourceAsStream("/fonts/Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Pixelmania.ttf"), 20);
        Font.loadFont(getClass().getResourceAsStream("/fonts/DePixelKlein.ttf"), 20);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage1, stage2, stage3, stage4;
        stage1 = new Stage();
        stage2 = new Stage();
        stage3 = new Stage();
        stage4 = new Stage();

        GameManager gameManager = new GameManager();
        MahjongGame game = new MahjongGame(8081);
        GameManager.addGame(game);


        Player eastplayer = new Player(stage1);
        Player southplayer = new Player(stage2);
        Player westplayer = new Player(stage3);
        Player northplayer = new Player(stage4);
        ArrayList<Tile> hand = new ArrayList<Tile>();
        hand.add(new NumberTile(9, Suit.WAN));
        hand.add(new NumberTile(3, Suit.WAN));
        hand.add(new NumberTile(4, Suit.WAN));
        hand.add(new NumberTile(5, Suit.WAN));
        hand.add(new NumberTile(6, Suit.WAN));
        hand.add(new NumberTile(7, Suit.WAN));
        hand.add(new NumberTile(8, Suit.WAN));
        hand.add(new NumberTile(6, Suit.TIAO));
        hand.add(new NumberTile(1, Suit.TIAO));
        hand.add(new NumberTile(2, Suit.TIAO));
        hand.add(new NumberTile(3, Suit.TIAO));
        hand.add(new NumberTile(4, Suit.TIAO));
        hand.add(new NumberTile(5, Suit.TIAO));
        eastplayer.setTile_hand(hand);


        try {
            GameManager.addPlayer(eastplayer);
        } catch (Exception e) {
            System.out.println("Cannot add player");
        }
        try {
            GameManager.addPlayer(southplayer);
        } catch (Exception e) {
            System.out.println("Cannot add player");
        }
        try {
            GameManager.addPlayer(westplayer);
        } catch (Exception e) {
            System.out.println("Cannot add player");
        }
        try {
            GameManager.addPlayer(northplayer);
        } catch (Exception e) {
            System.out.println("Cannot add player");
        }

    }


    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}



