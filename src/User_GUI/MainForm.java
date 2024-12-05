package User_GUI;

import javax.swing.*;
import java.awt.*;
import Security.*;
import Server.*;
import Stegnography.DembedForm;
import Stegnography.EmbedForm;

public class MainForm extends javax.swing.JFrame {
    ServerThread st;
    EncryptionForm ef;
    DecryptionForm df;
    DembedForm debf;
    EmbedForm ebf;
    ReceiverClient rc;

    /** Creates new form MainForm */
    public MainForm() {
        initComponents();
        st = new ServerThread();
        Thread t = new Thread(st);
        t.start();
    }

    /** 
     * This method initializes the form. 
     * The UI is customized with a unique black and green color scheme. 
     */
    private void initComponents() {
        // Set the layout for the main content pane
        setLayout(new BorderLayout());

        // Title label settings
        JLabel titleLabel = new JLabel("Welcome to Video Steganography", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 40));
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);  // Place title at the top

        // Create the button panel using GridBagLayout to align buttons properly
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);  // Spacing between buttons
        gbc.anchor = GridBagConstraints.CENTER;

        // Button design with a green foreground and black background
        JButton encryptButton = createCustomButton("Encrypt");
        encryptButton.addActionListener(evt -> jMenuItem3ActionPerformed(evt));

        JButton decryptButton = createCustomButton("Decrypt");
        decryptButton.addActionListener(evt -> jMenuItem4ActionPerformed(evt));

        JButton embedButton = createCustomButton("Embed");
        embedButton.addActionListener(evt -> jMenuItem5ActionPerformed(evt));

        JButton dembedButton = createCustomButton("Dembed");
        dembedButton.addActionListener(evt -> jMenuItem6ActionPerformed(evt));

        JButton sendFileButton = createCustomButton("Send File");
        sendFileButton.addActionListener(evt -> jMenuItem10ActionPerformed(evt));

        JButton exitButton = createCustomButton("Exit");
        exitButton.addActionListener(evt -> jMenuItem9ActionPerformed(evt));

        // Add buttons to the panel with positioning
        gbc.gridx = 0;
        gbc.gridy = 0;
        buttonPanel.add(encryptButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(decryptButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        buttonPanel.add(embedButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(dembedButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        buttonPanel.add(sendFileButton, gbc);

        gbc.gridx = 1;
        buttonPanel.add(exitButton, gbc);

        // Add the button panel to the center of the layout
        add(buttonPanel, BorderLayout.CENTER);

        // Final window settings
        setTitle("Video Steganography");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
    }

    /** 
     * Helper method to create a custom button with green text and black background 
     */
    private JButton createCustomButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.BOLD, 18));
        button.setForeground(Color.GREEN);  // Green text
        button.setBackground(Color.BLACK);  // Black background
        button.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));  // Green border
        button.setPreferredSize(new Dimension(200, 50));  // Size of the button
        button.setFocusPainted(false);  // Remove focus border
        return button;
    }

    // Action listener methods for the buttons
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
        ef = new EncryptionForm();
        ef.setBounds(120, 120, 472, 477);
        ef.setVisible(true);
    }

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {
        df = new DecryptionForm();
        df.setBounds(120, 120, 472, 477);
        df.setVisible(true);
    }

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
        ebf = new EmbedForm();
        ebf.setBounds(120, 120, 472, 384);
        ebf.setVisible(true);
    }

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
        debf = new DembedForm();
        debf.setBounds(120, 120, 472, 299);
        debf.setVisible(true);
    }

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {
        rc = new ReceiverClient();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            MainForm mf = new MainForm();
            mf.setVisible(true);
        });
    }

    // Variables declaration - do not modify
    private JLabel jLabel1;
    private JMenu jMenu1;
    private JMenu jMenu2;
    private JMenu jMenu3;
    private JMenu jMenu4;
    private JMenu jMenu5;
    private JMenuBar jMenuBar1;
    private JMenuItem jMenuItem10;
    private JMenuItem jMenuItem3;
    private JMenuItem jMenuItem4;
    private JMenuItem jMenuItem5;
    private JMenuItem jMenuItem6;
    private JMenuItem jMenuItem8;
    private JMenuItem jMenuItem9;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JSeparator jSeparator4;
}
