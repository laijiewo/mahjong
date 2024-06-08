package Module.Game;

import Message.*;
import Module.Game.MahjongGame;
import System.*;

import java.io.*;
import java.net.Socket;
/**
 * ServerGameThread handles communication between the server and a connected player.
 */
public class ServerGameThread implements Runnable {
    Socket socket;
    Player player;
    /**
     * Constructs a ServerGameThread with a specified socket.
     *
     * @param socket The socket connected to the player.
     */
    public ServerGameThread(Socket socket) {
        this.socket = socket;
    }
    /**
     * The main run method for the thread, handling incoming messages and delegating actions based on message type.
     */
    @Override
    public void run() {
        while (true) {
            Message message = null;
            try {
                message = acceptMessages();
                if (message == null) {
                    System.out.println("" + socket + " is closed");
                    GameManager.removePlayer(player);
                    Thread.currentThread().interrupt();
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            handleMessage(message);
        }
    }

    private void handleMessage(Message message) {
        if (message != null) {
            MessageType type = message.getType();
            switch (type) {
                case JOIN -> {
                    try {
                        player = new Player();
                        GameManager.addPlayer(player);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case DISCARD -> {
                    MahjongGame.handleDiscardMessage(message);
                }
                case CHEW -> {
                    MahjongGame.handleChewMessage(message);
                }
                case PUNG -> {
                    MahjongGame.handlePungMessage(message);
                }
                case KONG -> {
                    MahjongGame.handleKongMessage(message);
                }
                case HU -> {
                    try {
                        MahjongGame.handleHuMessage(message);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
    /**
     * Accepts incoming messages from the socket.
     *
     * @return The received message or null if the socket is closed or an error occurs.
     */
    private Message acceptMessages() {
        if (socket.isClosed()) {
            MahjongGame.sockets.remove(socket);
            return null;
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (Message) ois.readObject();
        }catch (Exception e){
            try {
                socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            MahjongGame.sockets.remove(socket);
            return null;
        }
    }
}
