package DannyGermanSimulator;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    public static JFrame window;

    public static void main(String[] args) throws FileNotFoundException {

        // Create the window
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // Allow the window to be resized
        window.setTitle("DannyGerman Simulator: Da hood but medieval");

        // Create the GamePanel and add it to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();
        if(gamePanel.fullScreenOn){
            window.setUndecorated(true);
        }

        window.pack();

        window.setLocationRelativeTo(null); // Center the window on the screen
        window.setVisible(true);

        gamePanel.setGameObjects();
        gamePanel.startGameThread();
    }
}