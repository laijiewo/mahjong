package Display.GameScreenDisplay;
import Display.Screen;
import Module.Game.Player;
import Module.Game.Site;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


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
    private GridPane OppositeTiles;

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
    private GridPane PreviousDiscardPile;

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
    private GridPane NextTiles;

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
    private GridPane OppositeDiscardPile;

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
    private GridPane PlayerDiscardPile;

    @FXML
    private ImageView tile18_1;

    @FXML
    private ImageView tile19_111;

    @FXML
    private ImageView tile12_11;

    @FXML
    private GridPane PreviousTiles;

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
    private GridPane NextDiscardPile;

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
    private GridPane PlayerTiles;

    @FXML
    private ImageView tile14_1111;

    @FXML
    void DiscardTile1(ActionEvent event) {
        GameManager.handleDiscardButtonAction(1, players.get(0));
    }

    @FXML
    void DiscardTile11(ActionEvent event) {
        GameManager.handleDiscardButtonAction(11, players.get(0));
    }

    @FXML
    void DiscardTile7(ActionEvent event) {
        GameManager.handleDiscardButtonAction(7, players.get(0));
    }

    @FXML
    void DiscardTile6(ActionEvent event) {
        GameManager.handleDiscardButtonAction(6, players.get(0));
    }

    @FXML
    void DiscardTile3(ActionEvent event) {
        GameManager.handleDiscardButtonAction(3, players.get(0));
    }

    @FXML
    void DiscardTile14(ActionEvent event) {
        GameManager.handleDiscardButtonAction(14, players.get(0));
    }

    @FXML
    void DiscardTile9(ActionEvent event) {
        GameManager.handleDiscardButtonAction(9, players.get(0));
    }

    @FXML
    void DiscardTile12(ActionEvent event) {
        GameManager.handleDiscardButtonAction(12, players.get(0));
    }

    @FXML
    void DiscardTile8(ActionEvent event) {
        GameManager.handleDiscardButtonAction(8, players.get(0));
    }

    @FXML
    void DiscardTile13(ActionEvent event) {
        GameManager.handleDiscardButtonAction(13, players.get(0));
    }

    @FXML
    void DiscardTile5(ActionEvent event) {
        GameManager.handleDiscardButtonAction(5, players.get(0));
    }

    @FXML
    void DiscardTile10(ActionEvent event) {
        GameManager.handleDiscardButtonAction(10, players.get(0));
    }

    @FXML
    void DiscardTile4(ActionEvent event) {
        GameManager.handleDiscardButtonAction(4, players.get(0));
    }

    @FXML
    void DiscardTile2(ActionEvent event) {
        GameManager.handleDiscardButtonAction(2, players.get(0));
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
        int indexofTile = 0;

        List<Button> buttons = new ArrayList<>();
        buttons.addAll(Arrays.asList(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14));

        for (Button button :buttons) {
            Tile tile = player.getTile_hand().get(indexofTile);
            if (tile == null) {
                return;
            }
            Image image = imageMap.get(tile);
            String imageUrl = image.getUrl();

            button.setStyle("-fx-text-fill: #308C4C;" +
                    " -fx-background-color: transparent;" +
                    " -fx-background-image: url('" + imageUrl + "');" +
                    " -fx-background-repeat: no-repeat;" +
                    " -fx-background-position: center center;" +
                    " -fx-background-size: 175%;");
            indexofTile++;
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

    private void setPlayerSiteImage(){
        for(int i = 0; i !=4; i++) {
            Player player = players.get(i);
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
        playerPhotos.add(player1Photo);
        playerPhotos.add(player2Photo);
        playerPhotos.add(player3Photo);
        playerPhotos.add(player4Photo);

        playerDirections.add(playerDirection1);
        playerDirections.add(playerDirection2);
        playerDirections.add(playerDirection3);
        playerDirections.add(playerDirection4);

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
        paintHandTiles();
        paintDiscardPiles();
        paintOtherHandTiles();
    }
}

