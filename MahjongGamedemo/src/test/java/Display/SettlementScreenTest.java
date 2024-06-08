package Display;

import Message.HuMessage;
import Module.Game.Player;
import javafx.application.Application;
import javafx.stage.Stage;
import Module.Game.PlayerInformation;
import Module.Game.Site;

import java.util.Arrays;

public class SettlementScreenTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SettlementScreen settlementScreen = new SettlementScreen();

        // Mock data for testing
        Player player = new Player();
        player.setSite(Site.East);
        PlayerInformation winner = new PlayerInformation(player);
        HuMessage mes = new HuMessage(5, 2 ^ (5 - 1) * 100, winner, Arrays.asList("SelfDrawn"));

        // Set mock data
        settlementScreen.setWinner(mes.getWinner());
        settlementScreen.setFans(mes.getFans());
        settlementScreen.setPoint(mes.getPoint());
        settlementScreen.setWintypelist(mes.getWintype());

        // Load the window
        settlementScreen.loadWindow(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
