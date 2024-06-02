package Module;
import System.*;
import Display.*;
import WebConnect.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a player in a Mahjong game, managing their actions and hand.
 */
public class Player {
    private int Score;
    private Site playerSite;
    private ArrayList<Tile> Tile_hand;

    private Tile hunTile;
    private RuleImplementation ruleImplementation;
    private static Socket echoSocket;
    private static boolean isRunning = true;
    private String serverHostname;
    private int serverPort;
    private boolean connected = false;


    public Player(){
        this.Score = 0;
    }

    /**
     * Rolls the dice using a provided Dice object.
     * @param Dice The dice to roll.
     * @return The result of the dice toss.
     */
    public int rollDice(Dice Dice){
        return Dice.toss()+Dice.toss();
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
     * @param tileFactory The tile wall from which to draw a tile.
     * @return The drawn tile or null if no tiles are available.
     */
    public Tile drawTiles(TileFactory tileFactory) {
        if (!tileFactory.createTiles().isEmpty()) {
            Tile drawTile = tileFactory.createTiles().get(0);
            Tile_hand.add(drawTile);
            return drawTile;
        }
        return null;
    }

    /**
     * Discards a tile from the player's hand based on the index provided.
     * @param Tile_number Index of the tile in the hand to discard.
     * @return The tile that was discarded.
     */
    public Tile discardTiles(int Tile_number){
        Tile Tile_discard = Tile_hand.get(Tile_number);
        Tile_hand.remove(Tile_discard);
        return Tile_discard;
    }

    /**
     * Attempts to form a "Kong" by adding a provided tile if valid.
     * @param tile The tile to attempt to add for a Kong.
     */
    public List<Tile> kong( Tile tile){
        if(ruleImplementation.canGang(Tile_hand, tile)){
            Tile_hand.add(tile);
        }
        return Tile_hand;
    }

    /**
     * Attempts to add a tile to the player's hand to form a "Pung" if the rules allow.
     * @param tile The tile to attempt to add for a Pung.
     */
    public List<Tile> pung(Tile tile) {
        if(ruleImplementation.canPeng(Tile_hand, tile)){
            Tile_hand.add(tile);
        }
        return Tile_hand;
    }

    public List<Tile> chi(Tile tile){
        if(ruleImplementation.canChi(Tile_hand,tile)){
            Tile_hand.add(tile);
        }
        return Tile_hand;
    }

    /**
     * Determines if the player can declare a win ("Mahjong") based on the current hand and a given tile.
     * @param tile The tile to check if it completes a winning hand.
     * @return true if the player can declare a win, false otherwise.
     */
    public boolean canHu(MahjongGame mahjongGame, Tile tile) {
        if(ruleImplementation.canHu(Tile_hand, tile)){
            Tile_hand.add(tile);
            return true;
        } else {
            return false;
        }
    }

    public void setSite(Site site){
        playerSite=site;
    }
    public Site getPlayerSite(){return playerSite;}

    public void setHunTile(Tile tile){
        hunTile=tile;
    }
    public boolean getconnected() {
        return connected;
    }

    public void setServerHostname(String serverHostname) {
        this.serverHostname = serverHostname;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
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
        // Start the threads to receive and send messages
        startReceiveMessages();
        // Start the thread to send messages
        // TODO: 1. 当用户进入游戏后再触发startSendMessages()
        //       2. 为sendMessage添加Button，实现点击发送消息
        startSendMessages();
    }

    /**
     * Starts a new thread to receive messages from the server.
     */
    private static void startReceiveMessages() {
        new Thread(() -> {
            try {
                receiveMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("Type 'Bye.' to exit.");
        //connected = true;
    }

    /**
     * Starts a new thread to send messages to the server.
     */
    private static void startSendMessages() {
        new Thread(() -> {
            try {
                sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Sends messages to the server.
     * Reads user input from the console and sends it to the server.
     * Handles the "Bye." command to exit the application.
     */
    private static void sendMessage() {
        try {
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(
                    new InputStreamReader(System.in));
            String userInput;

            while ((userInput = stdIn.readLine()) != null) {
                if (userInput.equals("Bye.")) {
                    isRunning = false;
                    System.out.println("See you again!");
                    out.println(userInput);
                    out.close();
                    in.close();
                    stdIn.close();
                    return;
                }
                out.println(userInput);
            }
            out.close();
            in.close();
            stdIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Receives messages from the server.
     * Continuously reads messages from the server and prints them to the console.
     * If an error occurs, it attempts to reconnect to the server after a delay.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping
     */
    private static void receiveMessage() throws InterruptedException {
        String message;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            while (isRunning && (message = reader.readLine()) != null) {
                if (message.contains("player")) {
                    System.out.println(message);
                } else {
                    System.out.println("Server: " + message);
                }
            }
        } catch (IOException e) {
            if (isRunning) {
                System.out.println("An error occurred while receiving message: " + e.getMessage());
                System.out.println("Reconnecting...");
                Thread.sleep(3000);
            }
        }
    }
    public void sendMessageObjectToHost(Message message) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(echoSocket.getOutputStream());
            oos.writeObject(message);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void receiveMessageObjectFromHost() {
        try {
            ObjectInputStream ois = new ObjectInputStream(echoSocket.getInputStream());
            Message message = (Message) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
