package Module.Game;

import Message.*;
import Module.Game.MahjongGame;
import System.*;

import java.io.*;
import java.net.Socket;

public class ServerGameThread implements Runnable {
    Socket socket;
    Player player;
    public ServerGameThread(Socket socket) {
        this.socket = socket;
    }

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
            if (message != null) {
                System.out.println(1);
                MessageType type = message.getType();
                System.out.println(type);
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
                            MahjongGame.handleHuMessage(((HuMessage) message).getWinnerIndex());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

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

    public void sendToAll(String message) {
        synchronized (MahjongGame.sockets) {
            for (Socket socket : MahjongGame.sockets) {
                try {
                    PrintWriter out = new PrintWriter(
                            new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
                    out.println(message);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendToAllExcept(String message) {
        synchronized (MahjongGame.sockets) {
            for (Socket s : MahjongGame.sockets) {
                if (s != socket) {
                    try {
                        PrintWriter out = new PrintWriter(
                                new OutputStreamWriter(s.getOutputStream(), "UTF-8"), true);
                        out.println(message);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
