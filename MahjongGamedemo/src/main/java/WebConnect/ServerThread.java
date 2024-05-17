package WebConnect;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class ServerThread implements Runnable {
    Socket socket;
    String name;
    ServerThread(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
    }
    @Override
    public void run() {
        sendToAll(name + " has joined the chat.");
        new Thread(this::acceptMessages).start();
    }

    private void acceptMessages() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),
                                              true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(socket.getInetAddress().getHostAddress() + " " + name + " : " + inputLine);
                out.println(inputLine);
                sendToAllExcept(name + " : " + inputLine);
                if (inputLine.equals("Bye.")) {
                    System.out.println(socket.getInetAddress().getHostAddress() + " has left the chat.");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(name + " has left the chat due to an error.");
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            SeverHost.sockets.remove(socket);
        }
    }
    public void sendToAll(String message) {
        synchronized (SeverHost.sockets) {
            for (Socket socket : SeverHost.sockets) {
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(message);
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void sendToAllExcept(String message) {
        synchronized (SeverHost.sockets) {
            for (Socket s : SeverHost.sockets) {
                if (s != socket) {
                    try {
                        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
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
