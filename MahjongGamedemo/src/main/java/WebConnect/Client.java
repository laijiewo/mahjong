package WebConnect;

import java.io.*;
import java.net.*;

public class Client {
    static String serverHostname = "10.19.44.61";

    static Socket echoSocket;

    public static void main(String[] args) throws IOException {
        if (args.length > 0)
            serverHostname = args[0];
        System.out.println ("Attemping to connect to host " +
                                    serverHostname + " on port 8080.");
        connect();
        echoSocket.close();
    }
    public static void connect(){
        try {
            System.out.println("Connecting to server...");
            echoSocket = new Socket(serverHostname, 8080);
            System.out.println("Connection successful.");
            new Thread(() -> {
                try {
                    receiveMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            sendMessage();
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                                       + "the connection to: " + serverHostname);
            System.exit(1);
        }
    }
    public static void sendMessage() throws IOException {
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                echoSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            if (userInput.equals("Bye.")) {
                System.out.println("See you again!");
                break;
            }
            System.out.print ("input: ");
            out.println(userInput);
        }

        out.close();
        in.close();
        stdIn.close();
    }
    public static void receiveMessage() {
        String message;
        try {
            InputStream read = echoSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(read));
            while ((message = reader.readLine()) != null) {
                System.out.println("Server: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
