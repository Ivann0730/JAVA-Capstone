package Entity;

import DannyGermanSimulator.GamePanel;
import DannyGermanSimulator.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp; // Initialize GamePanel
        this.keyH = keyH;
        this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);
        solidArea = new Rectangle();
        //TO FIX values
        solidArea.x=42;
        solidArea.y=42;
        solidArea.width=32;
        solidArea.height=42;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 10;
        direction = "down";
    }
    public void getPlayerImage() {
        try { //Diri ibutang nato atoang mga pictures for characters
            idleleft = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            idleright = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            idledown = ImageIO.read(getClass().getResourceAsStream("/player/back1.png"));
            idleup = ImageIO.read(getClass().getResourceAsStream("/player/front3.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/front2.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/front3.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/front4.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/front3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/back1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/back2.png"));  // Fixed typo
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/back3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/back4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/left4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/right4.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }

            //check tile collision
            collisionOn = false;
            gp.colCheck.checkTile(this);

            //if collision is false, player can move
            if(!collisionOn){
                if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
                    if (keyH.upPressed) {
                        worldY -= speed;
                    }
                    if (keyH.downPressed) {
                        worldY += speed;
                    }
                    if (keyH.leftPressed) {
                        worldX -= speed;
                    }
                    if (keyH.rightPressed) {
                        worldX += speed;
                    }
                }
            }

            spriteCounter++;
            if(spriteCounter > spriteSpeedMultiplier) {
                switch (spriteNum){
                    case 1:
                        spriteNum = 2;
                        break;
                    case 2:
                        spriteNum = 3;
                        break;
                    case 3:
                        spriteNum = 4;
                        break;
                    case 4:
                        spriteNum = 1;
                        break;
                }
                spriteCounter = 0;
            }
        }
        if(keyH.mountPressed){
            speed = 15;
            spriteSpeedMultiplier = 4;
        }
        else{
            speed = 10;
            spriteSpeedMultiplier = 9;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Array Sprites
        BufferedImage[] upSprites = {up1, up2, up3, up4};
        BufferedImage[] downSprites = {down1, down2, down3, down4};
        BufferedImage[] leftSprites = {left1, left2, left3, left4};
        BufferedImage[] rightSprites = {right1, right2, right3, right4};

        switch (direction) {
            case "up":
                image = upSprites[spriteNum - 1]; // Cycle through the up sprites
                if (!keyH.upPressed) {
                    image = idleup;
                    spriteCounter = 8;
                }
                break;

            case "down":
                image = downSprites[spriteNum - 1]; // Cycle through the down sprites
                if (!keyH.downPressed) {
                    image = idledown;
                    spriteCounter = 8;
                }
                break;

            case "left":
                image = leftSprites[spriteNum - 1]; // Cycle through the left sprites
                if (!keyH.leftPressed) {
                    image = idleleft;
                    spriteCounter = 8;
                }
                break;

            case "right":
                image = rightSprites[spriteNum - 1]; // Cycle through the right sprites
                if (!keyH.rightPressed) {
                    image = idleright;
                    spriteCounter = 8;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
