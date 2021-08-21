package model;

import javax.swing.JTextArea;

public class Receiver extends Thread {

    // * enum for initialzing connection
    final RECEIVER_TYPE conType;

    // * chat panel
    final JTextArea chatPanel;

    // * connection manager enum
    private enum CONNECTION_MANAGER {
        CLOSE_CONNECTION, START_CONNECTION
    }

    // * connection manager var
    CONNECTION_MANAGER manager = CONNECTION_MANAGER.CLOSE_CONNECTION;

    // * chat string
    String chatString;

    public Receiver(RECEIVER_TYPE type, JTextArea chatPanel) {
        conType = type;
        this.chatPanel = chatPanel;
    }

    @Override
    public void run() {
        // * start connection
        manager = CONNECTION_MANAGER.START_CONNECTION;
        if (conType == RECEIVER_TYPE.CLIENT) {
            final Client client = Client.getInstance();
            
            while (manager != CONNECTION_MANAGER.CLOSE_CONNECTION) {
                chatPanel.append("\nServer : " + client.recieveMessage());
            }

            client.disposeServer();
        } else {
            final Server server = Server.getInstance();

            while (manager != CONNECTION_MANAGER.CLOSE_CONNECTION) {
                chatPanel.append("\nClient : " + server.recieveMessage());
            }

            server.disposeServer();
        }
    }

    public void dispose() {
        manager = CONNECTION_MANAGER.CLOSE_CONNECTION;

    }
}
