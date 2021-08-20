package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    // * making it singleton
    private static final Client instance = new Client();
    Socket socket;

    private Client() {
        try {
            socket = new Socket("localhost", 4999);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Client getInstance() {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
