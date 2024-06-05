package Display.GameScreenDisplay;
import Display.Screen;
import Module.Game.GameBoard;
import Module.Game.MahjongGame;
import Module.Game.Player;
import Module.Game.Site;
import Module.ImageMap.FallenTileImageMapper;
import Module.ImageMap.TileImageMapper;
import Module.Tile.Tile;
import System.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class GameScreen implements Screen {

    Player MainPlayer;
    Player NextPlayer;
    Player OppositePlayer;
    Player PreviousPlayer;
    List<Player> players = new ArrayList<>();
    List<Label> playerDirections = new ArrayList<>();
    List<Label> playerPhotos = new ArrayList<>();

    @FXML
    private Button Chow;

    @FXML
    private GridPane OppositeTiles;

    @FXML
    private Button Kong;

    @FXML
    private Label playerDirection1;

    @FXML
    private Label playerDirection3;

    @FXML
    private Label playerDirection2;

    @FXML
    private Label playerDirection4;


    @FXML
    private GridPane PreviousDiscardPile;


    @FXML
    private Button Pause;


    @FXML
    private Label player1Photo;


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
    private Button T7;

    @FXML
    private Button T8;

    @FXML
    private Button T9;


    @FXML
    private GridPane NextTiles;


    @FXML
    private Label player4Photo;


    @FXML
    private Button T10;


    @FXML
    private Button T12;


    @FXML
    private Button T11;


    @FXML
    private Button T14;


    @FXML
    private Button T13;


    @FXML
    private GridPane OppositeDiscardPile;


    @FXML
    private GridPane PlayerDiscardPile;


    @FXML
    private GridPane PreviousTiles;


    @FXML
    private Button Pung;

    @FXML
    private Label player3Photo;

    @FXML
    private Button Win;


    @FXML
    private GridPane NextDiscardPile;


    @FXML
    private Label player2Photo;

    @FXML
    private GridPane PlayerTiles;

    @FXML
    ImageView HunTile;

    @FXML
    Label RemainingTiles;


    @FXML
    void DiscardTile1(ActionEvent event) {
        GameManager.handleDiscardButtonAction(0, players.get(0));
    }

    @FXML
    void DiscardTile11(ActionEvent event) {
        GameManager.handleDiscardButtonAction(10, players.get(0));
    }

    @FXML
    void DiscardTile7(ActionEvent event) {
        GameManager.handleDiscardButtonAction(6, players.get(0));
    }

    @FXML
    void DiscardTile6(ActionEvent event) {
        GameManager.handleDiscardButtonAction(5, players.get(0));
    }

    @FXML
    void DiscardTile3(ActionEvent event) {
        GameManager.handleDiscardButtonAction(2, players.get(0));
    }

    @FXML
    void DiscardTile14(ActionEvent event) {
        GameManager.handleDiscardButtonAction(13, players.get(0));
    }

    @FXML
    void DiscardTile9(ActionEvent event) {
        GameManager.handleDiscardButtonAction(8, players.get(0));
    }

    @FXML
    void DiscardTile12(ActionEvent event) {
        GameManager.handleDiscardButtonAction(11, players.get(0));
    }

    @FXML
    void DiscardTile8(ActionEvent event) {
        GameManager.handleDiscardButtonAction(7, players.get(0));
    }

    @FXML
    void DiscardTile13(ActionEvent event) {
        GameManager.handleDiscardButtonAction(12, players.get(0));
    }

    @FXML
    void DiscardTile5(ActionEvent event) {
        GameManager.handleDiscardButtonAction(4, players.get(0));
    }

    @FXML
    void DiscardTile10(ActionEvent event) {
        GameManager.handleDiscardButtonAction(9, players.get(0));
    }

    @FXML
    void DiscardTile4(ActionEvent event) {
        GameManager.handleDiscardButtonAction(3, players.get(0));
    }

    @FXML
    void DiscardTile2(ActionEvent event) {
        GameManager.handleDiscardButtonAction(1, players.get(0));
    }

    @FXML
    void Pung(ActionEvent event) {
        GameManager.handlePungButtonAction(players.get(0));
    }

    @FXML
    void Chow(ActionEvent event) {
        GameManager.handleChewButtonAction(players.get(0));
    }

    @FXML
    void Kong(ActionEvent event) {
        GameManager.handleKongButtonAction(players.get(0));
    }

    @FXML
    void Win(ActionEvent event) {
        GameManager.handleHuButtonAction(players.get(0));
    }

    @FXML
    void Pause(ActionEvent event) {
        GameManager.handlePauseButtonAction(players.get(0));
    }

    public void paintDiscardPiles(){
        List<GridPane> gridPaneList = new ArrayList<>();
        gridPaneList.add(PlayerDiscardPile);
        gridPaneList.add(NextDiscardPile);
        gridPaneList.add(OppositeDiscardPile);
        gridPaneList.add(PreviousDiscardPile);

        FallenTileImageMapper mapper = new FallenTileImageMapper();
        Map<Tile, Image> imageMap = mapper.getImageMap();

        int indexofPlayer = 0;
        for (GridPane gridPane : gridPaneList) {
            Player player = players.get(indexofPlayer);

            List<Tile> tiles = player.getDiscard_Tiles();
            List<ImageView> imageViewList = getAllImageViews(gridPane);

            int indexOfImage = 0;
            for (Tile tile : tiles) {
                ImageView imageView = imageViewList.get(indexOfImage);
                imageView.setVisible(true);
                imageView.setImage(imageMap.get(tile));
                indexOfImage++;
            }
            indexofPlayer++;
        }
        Platform.runLater(() -> {
            int remainingtiles = MahjongGame.getTileCountInTheTileWall();
            RemainingTiles.setText(remainingtiles+"/136");
            RemainingTiles.setFont(new Font("Pixel Bug",12));
        });
    }

    public void paintOtherHandTiles(){
        List<GridPane> gridPaneList = new ArrayList<>();
        gridPaneList.add(NextTiles);
        gridPaneList.add(OppositeTiles);
        gridPaneList.add(PreviousTiles);

        double rotationDegree = 360;

        FallenTileImageMapper mapper = new FallenTileImageMapper();
        Map<Tile, Image> imageMap = mapper.getImageMap();

        int indexofPlayer = 1;
        for (GridPane gridPane : gridPaneList) {
            Player player = players.get(indexofPlayer);
            List<Tile> tiles = player.getChew_Pong_Kung_Tiles();
            List<ImageView> imageViewList = getAllImageViews(gridPane);
            rotationDegree-=90;

            int indexOfImage = 0;
            for (Tile tile : tiles) {
                ImageView imageView = imageViewList.get(indexOfImage);
                imageView.setImage(imageMap.get(tile));
                imageView.setRotate(rotationDegree);
                indexOfImage++;
            }
            indexofPlayer++;
        }

    }

    public void paintHandTiles(){
        TileImageMapper mapper = new TileImageMapper();
        Map<Tile, Image> imageMap = mapper.getImageMap();

        Player player = players.get(0);

        List<Button> buttons = new ArrayList<>();
        buttons.addAll(Arrays.asList(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14));

        List<Tile> tiles = player.getTile_hand();
        int indexOfButton = 0;

        for (Tile tile : tiles) {
            Button button = buttons.get(indexOfButton);
            Image image = imageMap.get(tile);
            String imageUrl = image.getUrl();

            button.setStyle("-fx-text-fill: #308C4C;" +
                                    " -fx-background-color: transparent;" +
                                    " -fx-background-image: url('" + imageUrl + "');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: 175%;");
            indexOfButton++;
        }

        if (indexOfButton == 14) {
            return;
        }
        Button button = buttons.get(indexOfButton);
        button.setDisable(indexOfButton == 13);
        button.setVisible(false);
    }

    public void clearButtons() {
        List<Button> buttons = new ArrayList<>();
        buttons.addAll(Arrays.asList(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14));

        for(Button button : buttons){
            button.setStyle("-fx-text-fill: #308C4C;" +
                                    " -fx-background-color: transparent;" +
                                    " -fx-background-image: url('/mj/mj_24.png');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: 175%;");
        }
    }

    public static List<Label> getAllLabels(GridPane gridPane) {
        List<Label> labels = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Label) {
                labels.add((Label) node);
            }
        }

        return labels;
    }

    public static List<ImageView> getAllImageViews(GridPane gridPane) {
        List<ImageView> imageViews = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                imageViews.add((ImageView) node);
            }
        }


        return imageViews;
    }

    public void setPlayers(Player player1,Player player2,Player player3, Player player4){
        players.add(player1);

        players.add(player2);

        players.add(player3);

        players.add(player4);

    }

    private void setPlayerPhotoStyle(){
        for(int i = 0 ; i !=4; i++) {
            Player player = players.get(i);
            Label playerPhoto = playerPhotos.get(i);
            Site site = player.getPlayerSite();
            if (playerPhoto != null) {
                if (site == Site.East) {
                    playerPhoto.setStyle(
                            "-fx-background-image: url('/UI/EastPlayer.png');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: stretch;"
                    );
                } else if (site == Site.North) {
                    playerPhoto.setStyle(
                            "-fx-background-image: url('/UI/NorthPlayer.png');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: stretch;"
                    );
                } else if (site == Site.South) {
                    playerPhoto.setStyle(
                            "-fx-background-image: url('/UI/SouthPlayer.png');"
                                    + " -fx-background-repeat: no-repeat;"
                                    + " -fx-background-position: center center;"
                                    + " -fx-background-size: stretch;"
                    );
                } else {
                    playerPhoto.setStyle(
                            "-fx-background-image: url('/UI/WestPlayer.png');"
                                    + " -fx-background-repeat: no-repeat;"
                                    + " -fx-background-position: center center;"
                                    + " -fx-background-size: stretch;"
                    );
                }
            }
        }
    }

    private void paintPlayerSite(){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.YELLOW);
        dropShadow.setRadius(10);
        dropShadow.setSpread(0.5);

        for(int i = 0; i !=4; i++) {
            Player player = players.get(i);
            Label playerDirection = playerDirections.get(i);
            Site site = player.getPlayerSite();
            int currentActivePlayer = MahjongGame.getCurrentPlayerIndex();

            if (playerDirection != null) {
                if (site == Site.East) {
                    playerDirection.setEffect(null);
                    playerDirection.setStyle(
                            "-fx-background-image: url('/UI/East.png');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: stretch;"
                    );
                    if(currentActivePlayer == 0){
                        playerDirection.setEffect(dropShadow);
                    }
                } else if (site == Site.South) {
                    playerDirection.setEffect(null);
                    playerDirection.setStyle(
                            "-fx-background-image: url('/UI/South.png');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: stretch;"
                    );
                    if(currentActivePlayer == 1){
                        playerDirection.setEffect(dropShadow);
                    }
                } else if (site == Site.West) {
                    playerDirection.setEffect(null);
                    playerDirection.setStyle(
                            "-fx-background-image: url('/UI/West.png');"
                                    + " -fx-background-repeat: no-repeat;"
                                    + " -fx-background-position: center center;"
                                    + " -fx-background-size: stretch;"
                    );
                    if(currentActivePlayer == 2){
                        playerDirection.setEffect(dropShadow);
                    }
                } else {
                    playerDirection.setEffect(null);
                    playerDirection.setStyle(
                            "-fx-background-image: url('/UI/North.png');"
                                    + " -fx-background-repeat: no-repeat;"
                                    + " -fx-background-position: center center;"
                                    + " -fx-background-size: stretch;"
                    );
                    if(currentActivePlayer == 3){
                        playerDirection.setEffect(dropShadow);
                    }
                }
            } else {
                System.out.println("failed to change site");
            }
        }
    }

    @FXML
    private void initialize() {
        playerPhotos.add(player1Photo);
        playerPhotos.add(player2Photo);
        playerPhotos.add(player3Photo);
        playerPhotos.add(player4Photo);

        playerDirections.add(playerDirection1);
        playerDirections.add(playerDirection2);
        playerDirections.add(playerDirection3);
        playerDirections.add(playerDirection4);

        FallenTileImageMapper mapper = new FallenTileImageMapper();
        Map<Tile, Image> imageMap = mapper.getImageMap();
        Tile huntile = GameBoard.getHunTile();
        HunTile.setImage(imageMap.get(huntile));
        System.out.println(huntile.getSuit());
    }

    @Override
    public void loadWindow(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Display/GameScreen.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Game Screen");
        stage.show();


        setPlayerPhotoStyle();
        paintPlayerSite();
        paintHandTiles();
    }

    public void updateScreen() {
        Platform.runLater(() -> {
            clearButtons();
            paintHandTiles();
            paintDiscardPiles();
            paintOtherHandTiles();
            paintPlayerSite();
        });
    }
}

