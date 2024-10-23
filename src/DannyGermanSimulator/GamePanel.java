package DannyGermanSimulator;

import javax.swing.*;
import java.awt.*;
import Entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings (dynamic scaling based on screen resolution)
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = (int) screenSize.getWidth();
    public final int screenHeight = (int) screenSize.getHeight();
    public final int tileSize = screenWidth / maxScreenCol; // Dynamically set the tile size based on the screen width

    //World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Player
    public Player player = new Player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Draw tiles
        tileM.draw(g2);

        // Draw player
        player.draw(g2);

        g2.dispose();
    }
}
