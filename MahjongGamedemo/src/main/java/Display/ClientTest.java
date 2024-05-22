package Display;

import java.io.*;
import java.net.*;

import java.util.Scanner;

/**
 * The Client class represents a client application that connects to a server
 * and allows the user to send and receive messages.
 * TODO: 1. 测试Client与JoinGameWindow的联动效果，没问题则可以以此替代Client
 *
 * @author Jingwang Li, Jie Mao
 */
public class ClientTest {
    private static final Scanner scanner = new Scanner(System.in);
    private static Socket echoSocket;
    private static boolean isRunning = true;
    private String serverHostname;
    private int serverPort;
    private boolean connected = false;

    public boolean getconnected(){ return connected; }
    public void setSeverHostname(String serverHostname) {
        this.serverHostname = serverHostname;
    }
    public void setSeverPort(int severPort) {
        this.serverPort = serverPort;
    }

    public static void main(String[] args) {

    }

    /**
     * Establishes a connection to the server.
     * Prompts the user for the hostname and port number,
     * and then attempts to connect to the server.
     */
    public  void connect(){
        // Prompt the user for the hostname and port number
        System.out.println("Please enter the hostname and the port of the server:");
        //String serverHostname = scanner.next();
        int port = serverPort;
        System.out.println ("Attempting to connect to host " +
                serverHostname + " on port 8080.");
        // Attempt to connect to the server
        try {
            System.out.println("Connecting to server...");
            echoSocket = new Socket(serverHostname, port);
            System.out.println("Connection successful.");
            // Start the threads to receive and send messages
            startReceiveMessages();
            // Start the thread to send messages
            // TODO: 1. 当用户进入游戏后再触发startSendMessages()
            //       2. 为sendMessage添加Button，实现点击发送消息
            startSendMessages();

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + serverHostname);
            System.exit(1);
        }
    }
    /**
     * Starts a new thread to receive messages from the server.
     */
    private  void startReceiveMessages() {
        new Thread(() -> {
            try {
                receiveMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("Type 'Bye.' to exit.");
        connected=true;
    }
    /**
     * Starts a new thread to send messages to the server.
     */
    private static void startSendMessages() {
        new Thread(() -> {
            try {
                sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }}).start();
    }
    /**
     * Sends messages to the server.
     * Reads user input from the console and sends it to the server.
     * Handles the "Bye." command to exit the application.
     */
    private static void sendMessage() {
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
    /**
     * Receives messages from the server.
     * Continuously reads messages from the server and prints them to the console.
     * If an error occurs, it attempts to reconnect to the server after a delay.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping
     */
    private  void receiveMessage() throws InterruptedException {
        String message;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            while (isRunning && (message = reader.readLine()) != null) {
                if (message.contains("player")) {
                    System.out.println(message);
                } else {
                    System.out.println("Server: " + message);
                }
            }
        } catch (IOException e) {
            if (isRunning) {
                System.out.println("An error occurred while receiving message: " + e.getMessage());
                System.out.println("Reconnecting...");
                Thread.sleep(3000);
                connect();
            }
        }
    }
}