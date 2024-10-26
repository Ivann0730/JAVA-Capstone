package Entity;

import DannyGermanSimulator.GamePanel;
import DannyGermanSimulator.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLOutput;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int coins = 0;
    int mana = 0;
    public int hp = 0;
    int tempSpeed = 10;
    int tempSpriteSpeedMultiplier = 9;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp; // Initialize GamePanel
        this.keyH = keyH;
        this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);
        solidArea = new Rectangle();

        //TO FIX Optimize
        solidArea.x=32;
        solidArea.y=48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width=48;
        solidArea.height=48;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 10;
        direction = "down";
        coins = 0;
        mana = 0;
        hp = 3;
    }
    public void getPlayerImage() {
        try { //Diri ibutang nato atoang mga pictures for characters
//            idleleft = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Left1.png"));
//            idleright = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Right1.png"));
//            idledown = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Back1.png"));
//            idleup = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Front1.png"));
//            up1 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Front1.png"));
//            up2 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Front3.png"));
//            up3 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Front2.png"));
//            up4 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Front4.png"));
//            down1 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Back1.png"));
//            down2 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Back2.png"));  // Fixed typo
//            down3 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Back3.png"));
//            down4 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Back4.png"));
//            left1 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Left1.png"));
//            left2 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Left2.png"));
//            left3 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Left3.png"));
//            left4 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Left4.png"));
//            right1 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Right1.png"));
//            right2 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Right2.png"));
//            right3 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Right3.png"));
//            right4 = ImageIO.read(getClass().getResourceAsStream("/maxLevelArmor/Right4.png"));

            idleleft = ImageIO.read(getClass().getResourceAsStream("/player/Left1.png"));
            idleright = ImageIO.read(getClass().getResourceAsStream("/player/Right1.png"));
            idledown = ImageIO.read(getClass().getResourceAsStream("/player/Back1.png"));
            idleup = ImageIO.read(getClass().getResourceAsStream("/player/Front3.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Front3.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Front2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/Front3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/Front4.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Back1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Back2.png"));  // Fixed typo
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/Back3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/player/Back4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/Left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/Left4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/Right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/Right4.png"));

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

            //check object collision
            int objIndex = gp.colCheck.checkObject(this, true);
            pickUpObject(objIndex);


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
            speed = tempSpeed;
            spriteSpeedMultiplier = tempSpriteSpeedMultiplier;
        }
    }

    public void pickUpObject(int i){
        if (i != 999){
            String objectName = gp.obj[i].name;

            switch(objectName){
                case "Coin":
                    gp.playSE(0);
                    coins++;
                    gp.obj[i] = null;
                    gp.ui.showMessage("You got a Coin! x " + coins);
                    break;
                case "Mana":
                    mana+=10;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Mana has increased! Mana = " + mana);
                    break;
                case "Boots":
                    tempSpeed+=1;
                    tempSpriteSpeedMultiplier -= 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("Speed has Improved! Speed = " + tempSpeed);
                    break;
                case "Heart":
                    hp+=1;
                    gp.obj[i] = null;
                    gp.ui.showMessage("HP has increased! HP = " + hp);
                    break;
                //add case for game finished or player dies
                    //sound effect for dead player
                    //stop music
            }
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
        //to see collision box or hit box
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
    }

}