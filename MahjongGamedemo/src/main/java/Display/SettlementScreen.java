package Display;

import Display.Screen;
import Module.Game.PlayerInformation;
import Module.Game.Site;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.List;

/**
 * Controller class for the settlement screen of the Mahjong game.
 * Displays the game results including the winner, score, fan count, and winning types.
 */
public class SettlementScreen implements Screen {

    @FXML
    private Label wintype8;

    @FXML
    private Label wintype9;

    @FXML
    private Label wintype6;

    @FXML
    private Label wintype7;

    @FXML
    private Label WinnerLabel;

    @FXML
    private Label wintype13;

    @FXML
    private Label score;

    @FXML
    private Label wintype1;

    @FXML
    private Label fan;

    @FXML
    private Label wintype4;

    @FXML
    private Label wintype5;

    @FXML
    private Label wintype2;

    @FXML
    private Label wintype3;

    @FXML
    private Label wintype10;

    @FXML
    private ImageView winnerPhoto;

    @FXML
    private Label wintype11;

    @FXML
    private Label wintype12;

    int FANS;
    PlayerInformation winner;
    int point;
    List<String> wintypeList;
    List<Label> wintypelabels;

    /**
     * Returns the image corresponding to the winner's site.
     *
     * @return The Image object representing the winner's site.
     */
    private Image getPlayerImage() {
        Site site = winner.getSite();
        switch (site) {
            case East:
                return new Image("/UI/Player_E.png");
            case West:
                return new Image("/UI/Player_W.png");
            case North:
                return new Image("/UI/Player_N.png");
            case South:
                return new Image("/UI/Player_S.png");
        }
        return null;
    }

    /**
     * Sets the winner information.
     *
     * @param winner The PlayerInformation object representing the winner.
     */
    public void setWinner(PlayerInformation winner) {
        this.winner = winner;
    }

    /**
     * Sets the points scored by the winner.
     *
     * @param point The points scored by the winner.
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * Sets the number of fans (multipliers) for the winner.
     *
     * @param FANS The number of fans.
     */
    public void setFans(int FANS) {
        this.FANS = FANS;
    }

    /**
     * Sets the list of winning types.
     *
     * @param wintypeList The list of winning types as strings.
     */
    public void setWintypelist(List<String> wintypeList) {
        this.wintypeList = wintypeList;
    }

    /**
     * Prints the winning types in the labels on the screen.
     *
     * @param wintypelist The list of winning types as strings.
     */
    public void printWintypeList(List<String> wintypelist) {
        int i = 0;
        for (String wintype : wintypelist) {
            wintypelabels.get(i).setText(wintype);
            i++;
        }
        while (i != wintypelist.size()) {
            wintypelabels.get(i).setText("   ");
            i++;
        }
    }

    /**
     * Initializes the settlement screen with the winner's information.
     * This method is called automatically after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        wintypelabels = Arrays.asList(wintype1, wintype2, wintype3, wintype4, wintype5, wintype6,
                wintype7, wintype8, wintype9, wintype10, wintype11, wintype12, wintype13);

        // Update winner information
        winnerPhoto.setImage(getPlayerImage());
        printWintypeList(wintypeList);
        score.setText(point + "  pt");
        fan.setText(FANS + "  fan");
    }

    /**
     * Loads the settlement screen window.
     *
     * @param stage The primary stage for this application.
     * @throws Exception if there is an error loading the FXML file.
     */
    @Override
    public void loadWindow(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Display/SettlementScreen.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("SettlementScreen Screen");
        stage.show();
    }
}
