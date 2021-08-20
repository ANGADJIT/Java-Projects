package screen.client;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ClientChatWindow extends JFrame implements KeyListener {

    // * global widgets
    final JTextArea chatPanel;
    final JTextField messageBox;
    String chatString = "";

    ClientChatWindow() {
        this.setSize(900, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // * chat panel
        chatPanel = new JTextArea();
        chatPanel.setFont(new Font("MV Boli", Font.BOLD, 20));
        chatPanel.setText(chatString);
        chatPanel.setEditable(false);
        chatPanel.setForeground(Color.magenta);
        
        // * message box
        messageBox = new JTextField();
        messageBox.addKeyListener(this);
        messageBox.setBorder(new CompoundBorder(new LineBorder(Color.blue), new EmptyBorder(12, 12, 12, 12)));
        messageBox.setFont(new Font("MV Boli", Font.BOLD, 17));
        messageBox.setForeground(Color.gray);

        this.add(chatPanel, BorderLayout.CENTER);
        this.add(messageBox, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            if (messageBox.getText().length() == 0) {
                chatString += messageBox.getText();
            }
            else{
                chatString += ("\n" + messageBox.getText());
            }
            chatPanel.setText(chatString);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
