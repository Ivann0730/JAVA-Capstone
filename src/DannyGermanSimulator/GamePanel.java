package DannyGermanSimulator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Entity.Entity;
import Entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings (dynamic scaling based on screen resolution)
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
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

    //SYSTEM
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound(); // music
    Sound SE = new Sound(); //sound effect
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;
    public CollisionChecker colCheck = new CollisionChecker(this);
    public UI ui = new UI(this);

    // Player, Entity, Objects
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10]; // 10 being 10 slots for objects (can have many objects but for now 10)5
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[10];
    public ArrayList<Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    //more objects can slow down the game

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setGameObjects(){
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        gameState = titleState;
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
        if(gameState == playState){
            player.update();
            //NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            //MONSTER
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    if(monster[i].alive && !monster[i].dying){
                        monster[i].update();
                    }
                    if(!monster[i].alive){
                        monster[i] = null;
                    }
                }
            }
            //PROJECTILE
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null){
                    if(projectileList.get(i).alive){
                        projectileList.get(i).update();
                    }
                    if(!projectileList.get(i).alive){
                        projectileList.remove(i);
                    }
                }
            }
        }
        if(gameState == pauseState){
            //nothing yet
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //DEBUG
        long drawStart = 0;
        if(keyH.showDebugText){
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }
        //OTHERS
        else {
            // Draw tiles
            tileM.draw(g2);

            //ADD ENTITIES
            entityList.add(player);
            //NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            //OBJECT
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            //MONSTER
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            //PROJECTILE
            for(int i = 0; i < projectileList.size(); i++){
                if(projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    return Integer.compare(e1.worldY, e2.worldY);
                }
            });

            //DRAW ENTITY
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }

            //EMPTY ENTITY LIST
            entityList.clear();

            //UI
            ui.draw(g2);
        }

        //DEBUG
        if(keyH.showDebugText){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN,20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX " + player.worldX,x,y); y+=lineHeight;
            g2.drawString("WorldY " + player.worldY,x,y); y+=lineHeight;
            g2.drawString("Col " + (player.worldX + player.solidArea.x)/tileSize,x,y); y+=lineHeight;
            g2.drawString("Row " +(player.worldY + player.solidArea.y)/tileSize,x,y); y+=lineHeight;
            g2.drawString("Draw Time: " + passed, x, y);
            System.out.println("Draw Time: " + passed);
        }
        g2.dispose();
    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    //SE =  sound effect
    public void playSE(int i){
        SE.setFile(i);
        SE.play();
    }
}
