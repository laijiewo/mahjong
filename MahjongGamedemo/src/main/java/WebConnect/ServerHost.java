package WebConnect;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerHost {
    protected static LinkedList<Socket> sockets;
    private static ServerSocket serverSocket;
    private int port;
    static int i = 0;

    public ServerHost(int port) throws IOException {
        this.port = port;
        startServer(port);
    }

    private void startServer(int port) throws IOException {
        this.port = port;
        sockets = new LinkedList<>();
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for connections.....");
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + port);
            System.exit(1);
        }
        new Thread(ServerHost::sendMessageToAll).start();
        while (true) {
            System.out.println(sockets.size() + " clients connected.");
            listenForConnections();
            if (sockets.isEmpty()) {
                System.out.println("No clients connected.");
                break;
            }
        }
        serverSocket.close();
    }
    public String getHostIPAddress() {
        return serverSocket.getInetAddress().getHostAddress();
    }
    public int getPort() {
        return port;
    }

    public static void sendMessageToAll() {
        try (BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
            String message;
            while ((message = stdIn.readLine()) != null) {
                for (Socket socket : ServerHost.sockets) {
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println(message);
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listenForConnections() {
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            synchronized (clientSocket) {
                sockets.add(clientSocket);
            }
            new Thread(new ServerThread(clientSocket, "player " + i)).start();
            i++;
        } catch (IOException e) {
            System.out.println("Accept failed.");
            System.exit(1);
        }
        System.out.println("Got a connection from " + clientSocket.getInetAddress().getHostAddress());
        System.out.println("Waiting for input.....");
    }
}