package WebConnect;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class ServerThread implements Runnable {
    Socket socket;
    String name;
    LinkedList<Socket> sockets;
    ServerThread(Socket socket, String name, LinkedList<Socket> sockets) {
        this.socket = socket;
        this.name = name;
        this.sockets = sockets;
    }
    @Override
    public void run() {
        sendToAll(name + " has joined the chat.");
        new Thread(() -> {try {
            sendMessageToAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }}).start();
        new Thread(() -> {try {
            acceptMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }}).start();
    }

    private void acceptMessages() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(),
                                          true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(socket.getInetAddress().getHostAddress() + " " + name + " : " + inputLine);
            out.println(inputLine);
            if (inputLine.equals("Bye.")) {
                System.out.println(socket.getInetAddress().getHostAddress() + " has left the chat.");
                socket.close();
                sockets.remove(socket);
                break;
            }
        }
        in.close();
        out.close();
    }
    public void sendMessageToAll() throws IOException {
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            sendToAll(userInput);
        }
    }
    public void sendToAll(String message) {
        for (Socket socket : sockets) {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
