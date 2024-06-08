package Display;

import Display.Screen;
import Message.*;
import Module.Game.*;
import Module.ImageMap.FallenTileImageMapper;
import Module.ImageMap.TileImageMapper;
import Module.Rule.RuleImplementation;
import Module.Tile.Tile;
import Module.utils.FanCalculator;
import System.*;
import javafx.animation.KeyFrame;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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

/**
 * This class represents the game screen for the Mahjong game.
 * It handles the UI elements and game logic for displaying and interacting with the game screen.
 *
 * @authoр Jingwang Li, Jie Mao
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
    List<Button> buttons = new ArrayList<>();
    Map<Tile, Image> imageMap;
    Map<Tile, Image> fallenImageMap;
    RuleImplementation rule;
    int time = 20;
    boolean isDealer;

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
    Label timer;

    private MediaPlayer mediaPlayer;

    /**
     * Handles the action when a tile is discarded.
     * This method is used for all discard actions.
     *
     * @param event The ActionEvent triggered by the discard button.
     */
    @FXML
    void DiscardTile(ActionEvent event) {
        shutDownButtons();
        Button sourceButton = (Button) event.getSource();
        int index = buttons.indexOf(sourceButton);
        discard(index);
    }

    /**
     * Sends a discard message to the host.
     *
     * @param index The index of the tile to be discarded.
     */
    public void discard(int index) {
        if (playerIndex == currentActivePlayer) {
            Message message = new DiscardMessage(index);
            mainPlayer.sendMessageObjectToHost(message);
        } else {
            System.out.println("Cannot discard!");
        }
    }

    /**
     * Handles the action when the "Pung" button is pressed.
     *
     * @param event The ActionEvent triggered by the button.
     */
    @FXML
    void Pung(ActionEvent event) {
        Pung.setDisable(true);
        Pung.setVisible(false);
        Message message = new Chew_Pung_KongMessage(MessageType.PUNG, playerIndex);
        mainPlayer.sendMessageObjectToHost(message);
    }

    /**
     * Handles the action when the "Chow" button is pressed.
     *
     * @param event The ActionEvent triggered by the button.
     */
    @FXML
    void Chow(ActionEvent event) {
        Chow.setDisable(true);
        Chow.setVisible(false);
        Message message = new Chew_Pung_KongMessage(MessageType.CHEW, playerIndex);
        mainPlayer.sendMessageObjectToHost(message);
    }

    /**
     * Handles the action when the "Kong" button is pressed.
     *
     * @param event The ActionEvent triggered by the button.
     */
    @FXML
    void Kong(ActionEvent event) {
        Kong.setDisable(true);
        Kong.setVisible(false);
        Message message = new Chew_Pung_KongMessage(MessageType.KONG, playerIndex);
        mainPlayer.sendMessageObjectToHost(message);
    }

    /**
     * Handles the action when the "Win" button is pressed.
     *
     * @param event The ActionEvent triggered by the button.
     */
    @FXML
    void Win(ActionEvent event) throws Exception {
        Win.setDisable(true);
        Win.setVisible(false);
        FanCalculator fanCalculator = new FanCalculator(huntile, tilesInTheWall.size());
        int fans = fanCalculator.calculateFan(players.get(0).getHand_Tiles(), isDealer);
        PlayerInformation winner = players.get(0);
        int point = 2 ^ (fans - 1) * 100;
        List<String> wintype = fanCalculator.getHuTypes();
        Message message = new HuMessage(fans, point, winner, wintype);
        mainPlayer.sendMessageObjectToHost(message);
    }

    /**
     * Handles the action when the "Pause" button is pressed.
     *
     * @param event The ActionEvent triggered by the button.
     */
    @FXML
    void Pause(ActionEvent event) {
        // Implementation for pause functionality
    }

    /**
     * Clears all discard piles on the game screen.
     */
    public void clearDiscardPiles() {
        List<GridPane> gridPaneList = new ArrayList<>();
        gridPaneList.add(PlayerDiscardPile);
        gridPaneList.add(NextDiscardPile);
        gridPaneList.add(OppositeDiscardPile);
        gridPaneList.add(PreviousDiscardPile);
        int indexofPlayer = 0;

        for (GridPane gridPane : gridPaneList) {
            PlayerInformation player = players.get(indexofPlayer);

            List<Tile> tiles = player.getDiscardedTiles();
            List<ImageView> imageViewList = getAllImageViews(gridPane);
            for (ImageView imageView : imageViewList) {
                imageView.setVisible(false);
            }
            indexofPlayer++;
        }
    }

    /**
     * Paints the discard piles on the game screen.
     */
    public void paintDiscardPiles() {
        List<GridPane> gridPaneList = new ArrayList<>();
        gridPaneList.add(PlayerDiscardPile);
        gridPaneList.add(NextDiscardPile);
        gridPaneList.add(OppositeDiscardPile);
        gridPaneList.add(PreviousDiscardPile);

        int indexofPlayer = 0;
        for (GridPane gridPane : gridPaneList) {
            PlayerInformation player = players.get(indexofPlayer);

            List<Tile> tiles = player.getDiscardedTiles();
            List<ImageView> imageViewList = getAllImageViews(gridPane);

            int indexOfImage = 0;
            for (Tile tile : tiles) {
                ImageView imageView = imageViewList.get(indexOfImage);
                imageView.setVisible(true);
                imageView.setImage(fallenImageMap.get(tile));
                indexOfImage++;
            }
            indexofPlayer++;
        }
        Platform.runLater(() -> {
            int remainingtiles = tilesInTheWall.size();
            RemainingTiles.setText(remainingtiles + "/136");
            RemainingTiles.setFont(new Font("PixelGameFont", 12));
        });
    }

    /**
     * Paints the hand tiles of other players on the game screen.
     */
    public void paintOtherHandTiles() {
        List<GridPane> gridPaneList = new ArrayList<>();
        gridPaneList.add(NextTiles);
        gridPaneList.add(OppositeTiles);
        gridPaneList.add(PreviousTiles);

        double rotationDegree = 360;

        int indexofPlayer = 1;
        for (GridPane gridPane : gridPaneList) {
            PlayerInformation player = players.get(indexofPlayer);
            List<Tile> tiles = player.getChew_Pung_Kong_Tiles();
            List<ImageView> imageViewList = getAllImageViews(gridPane);
            rotationDegree -= 90;

            int indexOfImage = 0;
            for (Tile tile : tiles) {
                ImageView imageView = imageViewList.get(indexOfImage);
                imageView.setImage(fallenImageMap.get(tile));
                imageView.setRotate(rotationDegree);
                indexOfImage++;
            }
            indexofPlayer++;
        }
    }

    /**
     * Paints the hand tiles of the main player on the game screen.
     */
    public void paintHandTiles() {
        PlayerInformation player = players.get(0);

        List<Tile> tiles = player.getHand_Tiles();
        List<Tile> chewpongkongtiles = player.getChew_Pung_Kong_Tiles();
        int indexOfButton = 0;
        if (playerIndex == currentActivePlayer) {
            releaseButtons();
        } else {
            shutDownButtons();
        }

        for (Tile tile : tiles) {
            Button button = buttons.get(indexOfButton);
            button.setVisible(true);
            Image image = imageMap.get(tile);
            String imageUrl = image.getUrl();

            button.setStyle("-fx-text-fill: #308C4C;" +
                    " -fx-background-color: transparent;" +
                    " -fx-background-image: url('" + imageUrl + "');" +
                    " -fx-background-repeat: no-repeat;" +
                    " -fx-background-position: center center;" +
                    " -fx-background-size: 175%;");
            indexOfButton++;
            if (tile.equals(huntile)) {
                button.setDisable(true);
            }
        }
        paintChew_Pung_Kong_Tiles(chewpongkongtiles, indexOfButton);
    }

    /**
     * Paints the Chew, Pung, and Kong tiles of the main player.
     *
     * @param chewpongkongtiles The list of Chew, Pung, and Kong tiles.
     * @param indexOfButton The starting index of the button.
     */
    private void paintChew_Pung_Kong_Tiles(List<Tile> chewpongkongtiles, int indexOfButton) {
        for (Tile tile : chewpongkongtiles) {
            Button button = buttons.get(indexOfButton);
            button.setVisible(true);
            Image image = fallenImageMap.get(tile);
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

    /**
     * Clears all buttons on the game screen.
     */
    public void clearButtons() {
        for (Button button : buttons) {
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

    /**
     * Gets all ImageView nodes in a given GridPane.
     *
     * @param gridPane The GridPane to search.
     * @return A list of ImageView nodes.
     */
    public static List<ImageView> getAllImageViews(GridPane gridPane) {
        List<ImageView> imageViews = new ArrayList<>();
        for (Node node : gridPane.getChildren()) {
            if (node instanceof ImageView) {
                imageViews.add((ImageView) node);
            }
        }
        return imageViews;
    }

    /**
     * Sets the game timer.
     */
    public void setTimer() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            time -= 1;
            timer.setText(time + " s");
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
        timeline.play(); // Start the timeline
    }

    /**
     * Updates the game information based on a received message.
     *
     * @param message The message containing game information.
     */
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
        isDealer = ((GameInformationMessage) message).getDealerIndexFromMessage() == playerIndex;
    }

    /**
     * Sets the main player.
     *
     * @param player The main player.
     */
    public void setPlayer(Player player) {
        this.mainPlayer = player;
    }

    /**
     * Sets the Hun tile.
     *
     * @param huntile The Hun tile.
     */
    public void setHunTile(Tile huntile) {
        this.huntile = huntile;
    }

    /**
     * Disables all buttons on the game screen.
     */
    public void shutDownButtons() {
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    /**
     * Enables all buttons on the game screen.
     */
    public void releaseButtons() {
        for (Button button : buttons) {
            button.setDisable(false);
        }
    }

    /**
     * Shows the "Chow" button.
     */
    public void showChewButton() {
        Chow.setDisable(false);
        Chow.setVisible(true);
    }

    /**
     * Shows the "Pung" button if the player can perform a Pung action.
     */
    public void showPungButton() {
        boolean canPung = rule.canPeng(new ArrayList<>(players.get(0).getHand_Tiles()), leastDiscardedTile);
        if (canPung) {
            Pung.setDisable(false);
            Pung.setVisible(true);
        }
    }

    /**
     * Shows the "Kong" button if the player can perform a Kong action.
     */
    public void showKongButton() {
        boolean canKong = rule.canGang(new ArrayList<>(players.get(0).getHand_Tiles()), leastDiscardedTile);
        if (canKong) {
            Kong.setDisable(false);
            Kong.setVisible(true);
        }
    }

    /**
     * Shows the "Win" button if the player can win.
     */
    private void showHuButton() {
        boolean canHu = rule.canHu(new ArrayList<>(players.get(0).getHand_Tiles()), new ArrayList<>(players.get(0).getChew_Pung_Kong_Tiles()));
        if (canHu) {
            Win.setDisable(false);
            Win.setVisible(true);
        }
    }

    /**
     * Handles the end of the game.
     */
    public void gameOver() {
        Platform.runLater(() -> {
            System.out.println("Game Over!");
            Stage stage = (Stage) Chow.getScene().getWindow();
            stage.close();
        });
    }

    /**
     * Sets the styles for player photos based on their positions.
     */
    private void setPlayerPhotoStyle() {
        for (int i = 0; i != 4; i++) {
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

    /**
     * Paints the direction of the players based on their positions.
     */
    private void paintPlayerSite() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.YELLOW);
        dropShadow.setRadius(10);
        dropShadow.setSpread(0.5);

        for (int i = 0; i != 4; i++) {
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
                    if (currentActivePlayer == 0) {
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
                    if (currentActivePlayer == 1) {
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
                    if (currentActivePlayer == 2) {
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
                    if (currentActivePlayer == 3) {
                        playerDirection.setEffect(dropShadow);
                    }
                }
            } else {
                System.out.println("Failed to change site");
            }
        }
    }

    /**
     * Initializes the game screen.
     * This method is called automatically after the FXML file has been loaded.
     */
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

        shutDownFunctionButtons();

        buttons.addAll(Arrays.asList(T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14));
        TileImageMapper mapper = new TileImageMapper();
        FallenTileImageMapper fallenMapper = new FallenTileImageMapper();
        imageMap = mapper.getImageMap();
        fallenImageMap = fallenMapper.getImageMap();
        HunTile.setImage(fallenImageMap.get(huntile));
        rule = new RuleImplementation(huntile);
        new Thread(this::setTimer).start();

        if (playerIndex == currentActivePlayer) {
            releaseButtons();
        } else {
            shutDownButtons();
        }
    }

    /**
     * Disables and hides all function buttons (Chow, Pung, Kong, Win).
     */
    public void shutDownFunctionButtons() {
        Kong.setDisable(true);
        Kong.setVisible(false);
        Chow.setDisable(true);
        Chow.setVisible(false);
        Pung.setDisable(true);
        Pung.setVisible(false);
        Win.setDisable(true);
        Win.setVisible(false);
    }

    /**
     * Loads the game screen window.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if there is an error loading the FXML file.
     */
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
        showHuButton();
    }

    /**
     * Launches the result screen when the game is over.
     *
     * @param message The message containing the result information.
     */
    public void launchResultScreen(Message message) {
        HuMessage mes = (HuMessage) message;
        Platform.runLater(() -> {
            SettlementScreen settlementScreen = new SettlementScreen();
            settlementScreen.setWinner(mes.getWinner());
            settlementScreen.setFans(mes.getFans());
            settlementScreen.setPoint(mes.getPoint());
            settlementScreen.setWintypelist(mes.getWintype());

            try {
                settlementScreen.loadWindow(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Updates the game screen based on the received message.
     *
     * @param message The message containing the updated game information.
     */
    public void updateScreen(Message message) {
        shutDownFunctionButtons();
        updateGameInformation(message);
        Platform.runLater(() -> {
            clearButtons();
            shutDownButtons();
            paintHandTiles();
            showHuButton();
            clearDiscardPiles();
            paintDiscardPiles();
            paintOtherHandTiles();
            paintPlayerSite();
            time = 20;
        });
    }
}


