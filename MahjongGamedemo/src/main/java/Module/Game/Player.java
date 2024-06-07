package Module.Game;
import Display.GameScreen;
import Display.*;
import Module.Tile.Tile;
import Message.*;
import javafx.application.Platform;
import Module.Rule.*;
import Module.utils.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a player in a Mahjong game, managing their actions and hand.
 *
 * @author Jingwang Li, Lanyun Xiao
 */
public class Player implements Serializable {
    private int Score;
    private Site playerSite;
    private ArrayList<Tile> Tile_hand;
    private ArrayList<Tile> Chew_Pong_Kung_Tiles;
    private Screen gameScreen;
    private ArrayList<Tile> discard_Tiles;
    private RuleImplementation ruleImplementation;
    private static Socket echoSocket;
    private static boolean isRunning = true;
    private String serverHostname;
    private Tile hunTile;
    private int serverPort;
    private boolean connected = false;
    private boolean isScreenLaunched = false;
    private GameInformationMessage gameInformationMessage;


    public Player(){
        this.Score = 0;
        Tile_hand = new ArrayList<>();
        discard_Tiles = new ArrayList<>();
        gameScreen = new GameScreen();
        ((GameScreen) gameScreen).setPlayer(this);
        Chew_Pong_Kung_Tiles = new ArrayList<>();
    }

    public void sort_hand(){
        Collections.sort(Tile_hand);

        List<Tile> tilesToRemove = new ArrayList<>();
        List<Tile> tilesToAdd = new ArrayList<>();

        for (Tile tile : Tile_hand) {
            if (!tile.equals(hunTile)) {
                tilesToRemove.add(tile);
                tilesToAdd.add(tile);
            }
        }

        Tile_hand.removeAll(tilesToRemove);
        Tile_hand.addAll(tilesToAdd);
    }
    /**
     * Rolls the dice using a provided Dice object.
     * @param Dice The dice to roll.
     * @return The result of the dice toss.
     */
    public int rollDice(Dice Dice){
        return Dice.toss()+Dice.toss();
    }
    public void setHunTile(Tile tile){
        hunTile=tile;
        ruleImplementation = new RuleImplementation(hunTile);
        ((GameScreen) gameScreen).setHunTile(tile);
    }

    /**
     * Gets the current score of the player.
     * @return Current score.
     */
    public int getScore() {
        return Score;
    }

    /**
     * Draws a tile from the tile wall if available.
     */
    public void drawTiles(Tile tile) {
        Tile_hand.add(tile);
    }

    /**
     * Discards a tile from the player's hand based on the index provided.
     * @param Tile_number Index of the tile in the hand to discard.
     * @return The tile that was discarded.
     */
    public Tile discardTiles(int Tile_number){
        Tile Tile_discard = Tile_hand.get(Tile_number);
        Tile_hand.remove(Tile_discard);
        discard_Tiles.add(Tile_discard);
        return Tile_discard;
    }

    /**
     * Attempts to form a "Kong" by adding a provided tile if valid.
     * @param tile The tile to attempt to add for a Kong.
     */
    public List<Tile> kong( Tile tile){
        List<Tile> result;
        if(ruleImplementation.canGang(Tile_hand, tile)){
            result = ruleImplementation.getGangTiles();
        } else {
            return null;
        }
        return result;
    }

    /**
     * Attempts to add a tile to the player's hand to form a "Pung" if the rules allow.
     * @param tile The tile to attempt to add for a Pung.
     */
    public List<Tile> pung(Tile tile) {
        List<Tile> result;
        if(ruleImplementation.canPeng(Tile_hand, tile)){
            result = ruleImplementation.getPengTiles();
        } else {
            return null;
        }
        return result;
    }
    public List<Tile> chi(Tile tile){
        List<Tile> result;
        if(ruleImplementation.canChi(Tile_hand, tile)){
            result = ruleImplementation.getChiTiles();
        } else {
            return null;
        }
        return result;
    }

    public boolean canchi(Tile tile) {return ruleImplementation.canChi(new ArrayList<>(Tile_hand), tile);}
    public boolean canpeng(Tile tile){return (ruleImplementation.canPeng(new ArrayList<>(Tile_hand),tile));}
    public boolean cangang(Tile tile){return (ruleImplementation.canGang(new ArrayList<>(Tile_hand),tile));}

    /**
     * Determines if the player can declare a win ("Mahjong") based on the current hand and a given tile.
     * @return true if the player can declare a win, false otherwise.
     */
    public boolean canHu() {
        if(ruleImplementation.canHu(Tile_hand, Chew_Pong_Kung_Tiles)){
            return true;
        } else {
            return false;
        }
    }

    public void setSite(Site site){
        playerSite=site;
    }
    public Site getPlayerSite(){return playerSite;}
    public boolean getconnected() {
        return connected;
    }
    public List<Tile> getTile_hand() {
        return Tile_hand;
    }
    public List<Tile> getDiscard_Tiles() {
        return discard_Tiles;
    }
    public List<Tile> getChew_Pong_Kung_Tiles() {
        return Chew_Pong_Kung_Tiles;
    }
    public void addChew_Pong_Kung_Tiles(ArrayList<Tile> tiles) {
        Chew_Pong_Kung_Tiles.addAll(tiles);
    }
    public void launchGameScreen() {
        Platform.runLater(() -> {
            try {
                ((GameScreen) gameScreen).updateGameInformation(gameInformationMessage);
                isScreenLaunched = true;
                gameScreen.loadWindow(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public void setServerHostname(String serverHostname) {
        this.serverHostname = serverHostname;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    public int getCurrentPlayerIndex() {
        return gameInformationMessage.getCurrentPlayerIndexFromMessage();
    }
    public List<Tile> getTileInTheWall() {
        return gameInformationMessage.getTilesInTheWallFromMessage();
    }


    /**
     * Establishes a connection to the server.
     * Prompts the user for the hostname and port number,
     * and then attempts to connect to the server.
     */
    public void connect() throws IOException {
        //String serverHostname = scanner.next();
        int port = serverPort;
        // Attempt to connect to the server
        echoSocket = new Socket(serverHostname, port);
        connected = true;
        new Thread(this::startReceiveGameMessages).start();
    }

    public Socket getEchoSocket() {
        return echoSocket;
    }
    public void joinToHost() {
        Message message = new joinMessage();
        sendMessageObjectToHost(message);
    }
    /**
     * Starts a new thread to receive messages from the server.
     */
    private void startReceiveGameMessages() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(echoSocket.getInputStream());
                Message mes = (Message) ois.readObject();
                if (mes.getType() == MessageType.GAME_INFORMATION) {
                    gameInformationMessage = (GameInformationMessage) mes;
                    if (isScreenLaunched) {
                        ((GameScreen) gameScreen).updateScreen(gameInformationMessage);
                    }
                } else if (mes.getType() == MessageType.HUN_TILE) {
                    setHunTile(((HunTileMessage) mes).getHunTile());
                } else if (mes.getType() == MessageType.LAUNCH_GAME) {
                    launchGameScreen();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void sendMessageObjectToHost(Message message) {
        System.out.println("dis " + discard_Tiles.size());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(echoSocket.getOutputStream());;
            oos.writeObject(message);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    /**
//     * Sends messages to the server.
//     * Reads user input from the console and sends it to the server.
//     * Handles the "Bye." command to exit the application.
//     */
//    private static void sendMessage() {
//        try {
//            PrintWriter out = new PrintWriter(
//                    new OutputStreamWriter(echoSocket.getOutputStream(), "UTF-8"), true);
//            BufferedReader stdIn = new BufferedReader(
//                    new InputStreamReader(System.in));
//            String userInput;
//
//            while ((userInput = stdIn.readLine()) != null) {
//                if (userInput.equals("Bye.")) {
//                    isRunning = false;
//                    System.out.println("See you again!");
//                    out.println(userInput);
//                    out.close();
//                    stdIn.close();
//                    return;
//                }
//                out.println(userInput);
//            }
//            out.close();
//            stdIn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    /**
//     * Receives messages from the server.
//     * Continuously reads messages from the server and prints them to the console.
//     * If an error occurs, it attempts to reconnect to the server after a delay.
//     *
//     * @throws InterruptedException If the thread is interrupted while sleeping
//     */
//    private static void receiveMessage() throws InterruptedException {
//        String message;
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
//            while (isRunning && (message = reader.readLine()) != null) {
//                if (message.contains("player")) {
//                    System.out.println(message);
//                } else {
//                    System.out.println("Server: " + message);
//                }
//            }
//        } catch (IOException e) {
//            if (isRunning) {
//                System.out.println("An error occurred while receiving message: " + e.getMessage());
//                System.out.println("Reconnecting...");
//                Thread.sleep(3000);
//            }
//        }
//    }

}
