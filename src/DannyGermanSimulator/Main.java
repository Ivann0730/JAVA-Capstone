package DannyGermanSimulator;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Create the window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true); // Allow the window to be resized
        window.setTitle("DannyGerman Simulator: Da hood but medieval");

        // Set the size of the window to match the screen size, minus the taskbar space
        window.setSize(screenWidth, screenHeight);
        window.setLocationRelativeTo(null); // Center the window on the screen

        // Create the GamePanel and add it to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // Make the window visible and request focus on the GamePanel for key events
        window.setVisible(true);
        gamePanel.requestFocus();  // Ensure focus is gained after window is visible

        // Start the game thread
        gamePanel.startGameThread();
    }
}
