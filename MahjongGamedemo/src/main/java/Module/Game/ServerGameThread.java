package Module.Game;

import Message.*;
import Module.Game.MahjongGame;
import System.*;

import java.io.*;
import java.net.Socket;

public class ServerGameThread implements Runnable {
    Socket socket;

    public ServerGameThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            Message message = null;
            try {
                message = acceptMessages();
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
                            GameManager.addPlayer(new Player());
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
                }
            }
        }
    }

    private Message acceptMessages() {
        if (socket.isClosed()) {
            System.out.println("" + socket + " is closed");
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            return (Message) ois.readObject();
        }catch (Exception e){
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
