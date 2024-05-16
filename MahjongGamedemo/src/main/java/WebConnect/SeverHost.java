package WebConnect;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class SeverHost {
    static LinkedList<Socket> sockets;
    static ServerSocket serverSocket;
    static int i = 0;

    public static void main(String[] args) throws IOException {
        startServer();
    }

    public static void startServer() throws IOException {
        sockets = new LinkedList<>();
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Waiting for connections.....");
        } catch (IOException e) {
            System.out.println("Could not listen on port: 8080");
            System.exit(1);
        }
        while (true) {
            listenForConnections();
            if (sockets.isEmpty()) {
                System.out.println("No clients connected.");
                break;
            }
        }
        serverSocket.close();
    }

    public static void listenForConnections() {
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            synchronized (clientSocket){
                sockets.add(clientSocket);
            }
            System.out.println(clientSocket);
            new Thread(new ServerThread(clientSocket, "player " + i, sockets)).start();
            i++;
        } catch (IOException e) {
            System.out.println("Accept failed.");
            System.exit(1);
        }

        System.out.println("Got a connection from " + clientSocket.getInetAddress().getHostAddress());
        System.out.println("Waiting for input.....");
        sockets.add(clientSocket);
    }
}