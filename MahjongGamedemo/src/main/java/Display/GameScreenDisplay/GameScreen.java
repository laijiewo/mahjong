package Display.GameScreenDisplay;
import Display.Screen;
import Message.*;
import Module.Game.*;
import Module.ImageMap.FallenTileImageMapper;
import Module.ImageMap.TileImageMapper;
import Module.Tile.Tile;
import System.*;
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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Jingwang Li, Lanyun Xiao
 */
public class GameScreen implements Screen {

    Player MainPlayer;
    Player NextPlayer;
    Player OppositePlayer;
    Player PreviousPlayer;
    List<PlayerInformation> players = new ArrayList<>();
    List<Label> playerDirections = new ArrayList<>();
    List<Label> playerPhotos = new ArrayList<>();
    Player mainPlayer;
    int currentActivePlayer, playerIndex;
    Tile huntile, leastDiscardedTile;
    List<Tile> tilesInTheWall = new ArrayList<>();

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
        discard(0);
    }

    @FXML
    void DiscardTile11(ActionEvent event) {
        discard(10);
    }

    @FXML
    void DiscardTile7(ActionEvent event) {
        discard(6);
    }

    @FXML
    void DiscardTile6(ActionEvent event) {
        discard(5);
    }

    @FXML
    void DiscardTile3(ActionEvent event) {
        discard(2);
    }

    @FXML
    void DiscardTile14(ActionEvent event) {
        discard(13);
    }

    @FXML
    void DiscardTile9(ActionEvent event) {
        discard(8);
    }

    @FXML
    void DiscardTile12(ActionEvent event) {
        discard(11);
    }

    @FXML
    void DiscardTile8(ActionEvent event) {
        discard(7);
    }

    @FXML
    void DiscardTile13(ActionEvent event) {
        discard(12);
    }

    @FXML
    void DiscardTile5(ActionEvent event) {
        discard(4);
    }

    @FXML
    void DiscardTile10(ActionEvent event) {
        discard(9);
    }

    @FXML
    void DiscardTile4(ActionEvent event) {
        discard(3);
    }

    @FXML
    void DiscardTile2(ActionEvent event) {
        discard(1);
    }

    public void discard(int index) {
        System.out.println(playerIndex + "      " + currentActivePlayer);
        if (playerIndex == currentActivePlayer) {
            Message message = new DiscardMessage(index);
            mainPlayer.sendMessageObjectToHost(message);
        } else {
            System.out.println("Can not discard!");
        }
    }
    @FXML
    void Pung(ActionEvent event) {
        List<Tile> tiles = mainPlayer.pung(leastDiscardedTile);
        if (tiles != null) {
            Message message = new Chew_Pung_KongMessage(MessageType.PUNG, tiles);
            mainPlayer.sendMessageObjectToHost(message);
        } else {
            System.out.println("Can not pung!");
        }
    }

    @FXML
    void Chow(ActionEvent event) {
        List<Tile> tiles = mainPlayer.chi(leastDiscardedTile);
        if (tiles != null) {
            Message message = new Chew_Pung_KongMessage(MessageType.CHEW, tiles);
            mainPlayer.sendMessageObjectToHost(message);
        } else {
            System.out.println("Can not chew!");
        }
    }

    @FXML
    void Kong(ActionEvent event) {
        List<Tile> tiles = mainPlayer.kong(leastDiscardedTile);
        if (tiles != null) {
            Message message = new Chew_Pung_KongMessage(MessageType.KONG, tiles);
            mainPlayer.sendMessageObjectToHost(message);
        } else {
            System.out.println("Can not kong!");
        }
    }

    @FXML
    void Win(ActionEvent event) {
        if (mainPlayer.canHu()) {
            Message message = new Message(MessageType.HU);
            mainPlayer.sendMessageObjectToHost(message);
        } else {
            System.out.println("Can not hu!");
        }
    }

    @FXML
    void Pause(ActionEvent event) {
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
            PlayerInformation player = players.get(indexofPlayer);

            List<Tile> tiles = player.getDiscardedTiles();
            System.out.println("Discarded tiles of player " + tiles.size());
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
            int remainingtiles = tilesInTheWall.size();
            RemainingTiles.setText(remainingtiles+"/136");
            RemainingTiles.setFont(new Font("PixelGameFont",12));
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
            PlayerInformation player = players.get(indexofPlayer);
            List<Tile> tiles = player.getChew_Pung_Kong_Tiles();
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

        PlayerInformation player = players.get(0);

        List<Button> buttons = new ArrayList<>();
        buttons.addAll(Arrays.asList(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14));

        List<Tile> tiles = player.getHand_Tiles();
        List<Tile> chewpongkongtiles = player.getChew_Pung_Kong_Tiles();
        int indexOfButton = 0;

        for (Tile tile : tiles) {
            Button button = buttons.get(indexOfButton);
            button.setVisible(true);
            button.setDisable(false);
            Image image = imageMap.get(tile);
            String imageUrl = image.getUrl();

            button.setStyle("-fx-text-fill: #308C4C;" +
                                    " -fx-background-color: transparent;" +
                                    " -fx-background-image: url('" + imageUrl + "');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: 175%;");
            indexOfButton++;
            if(tile.equals(huntile)){
                button.setDisable(true);
            }
        }

        for (Tile tile : chewpongkongtiles) {
            FallenTileImageMapper fallenMapper = new FallenTileImageMapper();
            Map<Tile, Image> imageMap1 = fallenMapper.getImageMap();
            Button button = buttons.get(indexOfButton);
            Image image = imageMap1.get(tile);
            String imageUrl = image.getUrl();

            button.setStyle("-fx-text-fill: #308C4C;" +
                                    " -fx-background-color: transparent;" +
                                    " -fx-background-image: url('" + imageUrl + "');" +
                                    " -fx-background-repeat: no-repeat;" +
                                    " -fx-background-position: center center;" +
                                    " -fx-background-size: 175%;");
            indexOfButton++;
            button.setDisable(true);
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
        Button button = buttons.get(13);
        button.setDisable(true);
        button.setVisible(false);
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

    public void updateGameInformation(Message message) {
        List<PlayerInformation> playerInformationList = ((GameInformationMessage) message).getPlayersFromMessage();
        players.clear();
        players.add(playerInformationList.get(0));

        players.add(playerInformationList.get(1));

        players.add(playerInformationList.get(2));

        players.add(playerInformationList.get(3));
        currentActivePlayer = ((GameInformationMessage) message).getCurrentPlayerIndexFromMessage();
        tilesInTheWall = ((GameInformationMessage) message).getTilesInTheWallFromMessage();
        leastDiscardedTile = ((GameInformationMessage) message).getLeastDiscardedTileFromMessage();
        playerIndex = ((GameInformationMessage) message).getPlayerIndexFromMessage();
    }
    public void setPlayer(Player player) {
        this.mainPlayer = player;
    }
    public void setHunTile(Tile huntile) {
        this.huntile = huntile;
    }
    private void setPlayerPhotoStyle(){
        for(int i = 0; i != 4; i++) {
            PlayerInformation player = players.get(i);
            Label playerPhoto = playerPhotos.get(i);
            Site site = player.getSite();
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

        for(int i = 0; i != 4; i++) {
            PlayerInformation player = players.get(i);
            Label playerDirection = playerDirections.get(i);
            Site site = player.getSite();
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
        HunTile.setImage(imageMap.get(huntile));
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

    public void updateScreen(Message message) {
        updateGameInformation(message);
        Platform.runLater(() -> {
            clearButtons();
            paintHandTiles();
            paintDiscardPiles();
            paintOtherHandTiles();
            paintPlayerSite();
        });
    }
}

