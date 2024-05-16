package WebConnect;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class SeverHost {
    static LinkedList<Socket> sockets;
    static ServerSocket serverSocket;
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
             serverForClient();
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
         } catch (IOException e) {
             System.out.println("Accept failed.");
             System.exit(1);
         }

         System.out.println("Got a connection from " + clientSocket.getInetAddress().getHostAddress());
         System.out.println ("Waiting for input.....");
         sockets.add(clientSocket);
     }
     public static void serverForClient() throws IOException {
         for (Socket socket : sockets) {
             PrintWriter out = new PrintWriter(socket.getOutputStream(),
                                               true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader( socket.getInputStream()));

             String inputLine = in.readLine();
             if (inputLine != null) {
                 System.out.println(socket.getInetAddress().getHostAddress() + " : " + inputLine);
                 out.println(inputLine);
                 if (inputLine.equals("Bye.")) {
                     System.out.println(socket.getInetAddress().getHostAddress() + " has left the chat.");
                     socket.close();
                     sockets.remove(socket);
                 }
             }
             in.close();
             out.close();
         }
     }
}
