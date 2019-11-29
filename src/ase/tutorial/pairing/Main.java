package ase.tutorial.pairing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Main implements KeyListener {
    static KeyListener listener;
    static ArrayList<File> imgList = new ArrayList<>();
    static int i = 0;

    public static void main(String[] args) throws IOException {
	// write your code here
        newListener();
        final File folder = new File("C:\\Users\\Konstantin\\Documents\\GitHub\\ase-pairing-image-viewer-mob-123nochmal\\data");
        listFilesForFolder(folder);
        DisplayImage(imgList.get(i));
    }

    public static void listFilesForFolder(final File folder){
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                    imgList.add(fileEntry);
            }
        }
    }

    public static void newListener() {
        listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT && i > 0) {
                    try {
                        DisplayImage(imgList.get(i--));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                if (key == KeyEvent.VK_RIGHT && i < 24) {
                    try {
                        DisplayImage(imgList.get(i++));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
    }

    public static void DisplayImage(File file) throws IOException {
        BufferedImage img = ImageIO.read(file);
        ImageIcon icon = new ImageIcon(img);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 500);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(listener);
    }
}
