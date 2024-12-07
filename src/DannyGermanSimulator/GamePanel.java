package DannyGermanSimulator;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Entity.Entity;
import Entity.Player;
import ai.PathFinder;
//import tile.Map;
import environment.EnvironmentManager;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 40; //tile size
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    //    screenWidth / maxScreenCol
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //World Settings
    public final int maxWorldCol = 250;
    public final int maxWorldRow = 250;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public boolean fullScreenOn = false;
    public final int maxMap = 10;
    public int currentMap = 3;

    // FPS
    int FPS = 60;

    //FOR FULL SCREEN
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;

    //SYSTEM
    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound(); // music
    Sound SE = new Sound(); //sound effect
    public CollisionChecker colCheck = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
//    Map map = new Map(this);
    public PathFinder pFinder = new PathFinder(this);
    EnvironmentManager eManager = new EnvironmentManager(this);
    Thread gameThread;

    // Player, Entity, Objects
    public Player player = new Player(this, keyH);
    public Entity obj[][] = new Entity[maxMap][50]; // 10 being 10 slots for objects (can have many objects but for now 10)5
    public Entity npc[][] = new Entity[maxMap][50];
    public Entity monster[][] = new Entity[maxMap][50];
    public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
    public Entity projectile[][] = new Entity[maxMap][20];
    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();
    //more objects can slow down the game

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int tradeState = 8;
    public final int sleepsState = 9;
    public final int mapState = 10;
    public final int transitionStateExpansion = 11;


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
        aSetter.setInteractiveTile();
        eManager.setup();

        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth,screenHeight,BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();
        setFullScreen();
    }
    public void retry(){
        player.setDefaultPosition();
        player.restoreLifeAndMan();
        aSetter.setNPC();
        aSetter.setMonster();
    }
    public void restart(){
        player.setDefaultValues();
        player.setItems();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setInteractiveTile();
    }
    public void setFullScreen(){
        //GET MONITOR ENVIRONMENT
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice gd = ge.getDefaultScreenDevice();
//        gd.setFullScreenWindow(Main.window);
        //GET FULLSCREEN DIMENSIONS
//        screenWidth2 = Main.window.getWidth();
//        screenHeight2 = Main.window.getHeight();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenWidth2 = (int) width;
        screenHeight2 = (int) height;
        //offset factor to be used by mouse listener or mouse motion listener if you are using cursor in your game. Multiply your e.getX()e.getY() by this.
//        fullScreenOffsetFactor = (float) screenWidth / (float) screenWidth2;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run() {
        double drawInterval = 1000000000 / FPS; //0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                drawToTempScreen(); //draws everything to the buffered Image
                drawToScreen(); //draws the buffered image to the screen
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        if(gameState == playState){
            player.update();
            //NPC
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            //MONSTER
            for(int i = 0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null){
                    if(monster[currentMap][i].alive && !monster[currentMap][i].dying){
                        monster[currentMap][i].update();
                    }
                    if(!monster[currentMap][i].alive){
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            //PROJECTILE
            for(int i = 0; i < projectile[1].length; i++){
                if(projectile[currentMap][i] != null){
                    if(projectile[currentMap][i].alive){
                        projectile[currentMap][i].update();
                    }
                    if(!projectile[currentMap][i].alive){
                        projectile[currentMap][i] = null;
                    }
                }
            }
            //PARTICLE
            for(int i = 0; i < particleList.size(); i++){
                if(particleList.get(i) != null){
                    if(particleList.get(i).alive){
                        particleList.get(i).update();
                    }
                    if(!particleList.get(i).alive){
                        particleList.remove(i);
                    }
                }
            }
            for(int i = 0; i < iTile[1].length; i++){
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].update();
                }
            }

            eManager.update();
        }
        if(gameState == pauseState){
            //nothing yet
        }
    }
    public void drawToTempScreen(){
        //DEBUG
        long drawStart = 0;
        if(keyH.showDebugText){
            drawStart = System.nanoTime();
        }

        //TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }
//        Map Screen
//        else if(gameState == mapState){
//            map.drawFullMapScreen(g2);
//        }
        //OTHERS
        else {
            // Draw tiles
            tileM.draw(g2);

            //INTERACTIVE TILE
            for(int i = 0; i < iTile[1].length; i++){
                if(iTile[currentMap][i] != null){
                    iTile[currentMap][i].draw(g2);
                }
            }

            //ADD ENTITIES
            entityList.add(player);
            //NPC
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            //OBJECT
            for(int i = 0; i < obj[1].length; i++){
                if(obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            //MONSTER
            for(int i = 0; i < monster[1].length; i++){
                if(monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            //PROJECTILE
            for(int i = 0; i < projectile[1].length; i++){
                if(projectile[currentMap][i] != null) {
                    entityList.add(projectile[currentMap][i]);
                }
            }

            //PARTICLE
            for(int i = 0; i < particleList.size(); i++){
                if(particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
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

            //MINIMAP
//            map.drawMiniMap(g2);

            //ENVIRONMENT
            eManager.draw(g2);

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
    }
    public void drawToScreen(){
        Graphics g = getGraphics();
        g.drawImage(tempScreen,0,0, screenWidth2, screenHeight2,null);
        g.dispose();
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