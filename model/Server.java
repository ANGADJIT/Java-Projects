package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    // * making it singleton
    private static final Server instance = new Server();
    Socket socket;
    ServerSocket serverSocket;

    private Server() {
        try {
            serverSocket = new ServerSocket(4999);
            socket = serverSocket.accept();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Server getInstance() {
        return instance;
    }

    // * method to send message
    public void sendMessage(String message) {
        try {
            PrintWriter writer = new PrintWriter(this.socket.getOutputStream());
            writer.println(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // * method for reciveing message
    public String recieveMessage() {
        String recievedMessage = "";

        try {
            InputStreamReader reader = new InputStreamReader(this.socket.getInputStream());
            BufferedReader buffer = new BufferedReader(reader);
            recievedMessage = buffer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recievedMessage;
    }

    public void disposeServer() {
        try {
            this.socket.close();
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
