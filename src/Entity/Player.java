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
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 3;
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
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/back1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/back2.png"));  // Fixed typo
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update() {
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                worldY -= speed;
                direction = "up";
            }
            if (keyH.downPressed) {
                worldY += speed;
                direction = "down";
            }
            if (keyH.leftPressed) {
                worldX -= speed;
                direction = "left";
            }
            if (keyH.rightPressed) {
                worldX += speed;
                direction = "right";
            }
            spriteCounter++;
            if(spriteCounter > 10) {
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                if(!keyH.upPressed){
                    image = idleup;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                if(!keyH.downPressed){
                    image = idledown;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                if(!keyH.leftPressed){
                    image = idleleft;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                if(!keyH.rightPressed){
                    image = idleright;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
