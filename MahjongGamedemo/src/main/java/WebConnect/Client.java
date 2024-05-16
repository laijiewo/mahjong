package WebConnect;

import java.io.*;
import java.net.*;

public class Client {
    static String serverHostname = new String ("127.0.0.1");
    static Socket echoSocket = null;

    public static void main(String[] args) throws IOException {
        if (args.length > 0)
            serverHostname = args[0];
        System.out.println ("Attemping to connect to host " +
                                    serverHostname + " on port 8080.");
        connect();
        echoSocket.close();
    }
    public static void connect() throws IOException {
        echoSocket = null;
        try {
            echoSocket = new Socket(serverHostname, 8080);
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

        System.out.print ("input: ");
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
            System.out.print ("input: ");
        }

        out.close();
        in.close();
        stdIn.close();
    }
}
