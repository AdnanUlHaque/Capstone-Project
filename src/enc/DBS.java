package enc;

import java.io.*;
import javax.swing.*;
import java.awt.*;

class filedialog extends JFrame {
    String name;

    filedialog() {
        FileDialog fd = new FileDialog(filedialog.this, "Save as", FileDialog.SAVE);
        fd.setVisible(true);
        if (fd.getFile() != null) {
            name = fd.getDirectory() + fd.getFile();
            setTitle(name);
        }
    }

    public String getfile() {
        return name;
    }
}

public class DBS {

    static String name;

    public boolean DBST(int op, String x, javax.swing.JTextArea jLabel9) {
        boolean flag = false;
        String toBeSaved = "";
        name = x;

        try {
            byte[] theFile = getFile(name);

            String key = JOptionPane.showInputDialog("Enter your key (the longer the better):");
            if (key == null || key.isEmpty()) {
                throw new IllegalArgumentException("Key cannot be empty!");
            }

            Encryption encryption = new Encryption(theFile, key);

            if (op == 1) { // Encrypt
                encryption.encrypt();
                toBeSaved = saveFile(encryption.getFileBytes());
                JOptionPane.showMessageDialog(null, "File encrypted and saved successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                flag = true;
            } else if (op == 2) { // Decrypt
                encryption.decrypt();
                toBeSaved = saveFile(encryption.getFileBytes());
                JOptionPane.showMessageDialog(null, "File decrypted and saved successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                flag = true;
            }
            jLabel9.setText(toBeSaved);
        } catch (Exception e) {
            jLabel9.setText("");
            e.printStackTrace();
        }
        return flag;
    }

    /****************************/
    /* Load file into byte array */
    /****************************/
    public static byte[] getFile(String name) {
        byte[] readFromFile = null;
        try (FileInputStream in = new FileInputStream(name)) {
            readFromFile = in.readAllBytes();
        } catch (IOException e) {
            System.out.println("File not found or cannot be read!");
        }
        return readFromFile;
    }

    /**************************/
    /* Save byte array to file */
    /**************************/
    public static String saveFile(byte[] toSave) {
        String txt;
        filedialog fd = new filedialog();
        txt = fd.getfile();
        try (FileOutputStream out = new FileOutputStream(txt)) {
            out.write(toSave);
        } catch (IOException e) {
            System.out.println("Error saving the file! Check permissions or disk space.");
        }
        return txt;
    }

    /************************************************************/
    /* Class handling encryption and decryption                */
    /************************************************************/
    class Encryption {
        private String key;
        private char[] keys;
        private byte[] fileBytes;

        public Encryption(byte[] fileBytes, String key) {
            this.fileBytes = fileBytes;
            this.key = key;
            keys = key.toCharArray();
        }

        public void encrypt() {
            System.out.println("Encrypting...");
            for (int i = 0; i < fileBytes.length; i++) {
                fileBytes[i] = (byte) (fileBytes[i] ^ keys[i % keys.length]);
            }
        }

        public void decrypt() {
            System.out.println("Decrypting...");
            for (int i = 0; i < fileBytes.length; i++) {
                fileBytes[i] = (byte) (fileBytes[i] ^ keys[i % keys.length]);
            }
        }

        public byte[] getFileBytes() {
            return fileBytes;
        }
    }
}
