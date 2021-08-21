package screen.client;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Client;
import model.RECEIVER_TYPE;
import model.Receiver;

public class ClientChatWindow extends JFrame implements KeyListener {

    // * global widgets
    final JTextArea chatPanel;
    final JTextField messageBox;
    final Client client;
    final Receiver receiver;

    ClientChatWindow() {

        // * setting up window title and icon
        this.setTitle("Client");
        final ImageIcon icon = new ImageIcon("assets/client.png");
        this.setIconImage(icon.getImage());

        // * chat panel
        chatPanel = new JTextArea();

        chatPanel.setFont(new Font("MV Boli", Font.BOLD, 20));
        chatPanel.setText("Connected to server!!");
        chatPanel.setEditable(false);
        chatPanel.setLineWrap(true);
        chatPanel.setWrapStyleWord(true);
        chatPanel.setBackground(Color.decode("#016937"));
        chatPanel.setForeground(Color.decode("#fffefe"));

        // * Client object
        client = Client.getInstance();
        receiver = new Receiver(RECEIVER_TYPE.CLIENT, chatPanel);

        // * start connection
        receiver.start();

        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // * message box
        messageBox = new JTextField();
        messageBox.addKeyListener(this);
        messageBox.setBorder(new CompoundBorder(new LineBorder(Color.decode("#016937"),12), new EmptyBorder(12, 12, 12, 12)));
        messageBox.setFont(new Font("MV Boli", Font.BOLD, 17));
        messageBox.setBackground(Color.lightGray);
        messageBox.setForeground(Color.decode("#15191a"));

        this.add(chatPanel, BorderLayout.CENTER);
        this.add(messageBox, BorderLayout.SOUTH);

        // * disposing the connection before window closes
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                receiver.dispose();
            }
        });

        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            if (messageBox.getText().length() != 0
                    && chatPanel.getLineCount() < ((int) (chatPanel.getHeight() * .03) - 1)) {
                client.sendMessage(messageBox.getText());
                chatPanel.append("\nYou : " + messageBox.getText());
                messageBox.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "CHAT SCREEN FULL PRESS \'C\' TO CLEAR ", "Client Chat",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getKeyCode() == 67) {
            chatPanel.setText("");
            chatPanel.append("Connected to server!!");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
