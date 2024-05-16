package WebConnect;

import java.io.*;
import java.net.*;

public class Client2 {
    static String serverHostname = "10.26.51.204";

    static Socket echoSocket;
    static boolean isRunning = true;

    public static void main(String[] args) throws IOException {
        if (args.length > 0)
            serverHostname = args[0];
        System.out.println ("Attemping to connect to host " +
                                    serverHostname + " on port 8080.");
        connect();
    }
    public static void connect(){
        try {
            System.out.println("Connecting to server...");
            echoSocket = new Socket(serverHostname, 8080);
            System.out.println("Connection successful.");
            Thread receiveThread = new Thread(() -> {
                try {
                    receiveMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();
            System.out.println("Type 'Bye.' to exit.");
            new Thread(() -> {
                try {
                    sendMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }}).start();
            receiveThread.join();
            echoSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                                       + "the connection to: " + serverHostname);
            System.exit(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void sendMessage() {
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
                    Thread.currentThread().interrupt();
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
    public static void receiveMessage() {
        String message;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            while (isRunning && (message = reader.readLine()) != null) {
                System.out.println("Server: " + message);
            }
        } catch (IOException e) {
            if (isRunning) {
                System.out.println("An error occurred while receiving message: " + e.getMessage());
            }
        }
    }
}
