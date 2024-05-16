package WebConnect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
        try {
            sendMessages();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMessages() throws IOException {
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

}
