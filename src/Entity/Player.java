package Entity;

import DannyGermanSimulator.GamePanel;
import DannyGermanSimulator.KeyHandler;
import DannyGermanSimulator.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLOutput;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int tempSpeed = 10;
    int tempSpriteSpeedMultiplier = 9;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);
        solidArea = new Rectangle();

        //TO FIX Optimize
        solidArea.x=34;
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

        //PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }
    public void getPlayerImage() {
    //Diri ibutang nato atoang mga pictures for characters
//        idleleft = setUp("Left1");
//        idleright = setUp("Right1");
//        idledown = setUp("Back1");
//        idleup = setUp("Front1");
//        up1 = setUp("Front1");
//        up2 = setUp("Front2");
//        up3 = setUp("Front3");
//        up4 = setUp("Front4");
//        down1 = setUp("Back1");
//        down2 = setUp("Back2");
//        down3 = setUp("Back3");
//        down4 = setUp("Back4");
//        left1 = setUp("Left1");
//        left2 = setUp("Left2");
//        left3 = setUp("Left3");
//        left4 = setUp("Left4");
//        right1 = setUp("Right1");
//        right2 = setUp("Right2");
//        right3 = setUp("Right3");
//        right4 = setUp("Right4");
//
        idleleft = setUp("/maxLevelArmor/Left1");
        idleright = setUp("/maxLevelArmor/Right1");
        idledown = setUp("/maxLevelArmor/Back1");
        idleup = setUp("/maxLevelArmor/Front1");
        up1 = setUp("/maxLevelArmor/Front2");
        up2 = setUp("/maxLevelArmor/Front3");
        up3 = setUp("/maxLevelArmor/Front2");
        up4 = setUp("/maxLevelArmor/Front4");
        down1 = setUp("/maxLevelArmor/Back1");
        down2 = setUp("/maxLevelArmor/Back2");
        down3 = setUp("/maxLevelArmor/Back3");
        down4 = setUp("/maxLevelArmor/Back4");
        left1 = setUp("/maxLevelArmor/Left1");
        left2 = setUp("/maxLevelArmor/Left2");
        left3 = setUp("/maxLevelArmor/Left3");
        left4 = setUp("/maxLevelArmor/Left4");
        right1 = setUp("/maxLevelArmor/Right1");
        right2 = setUp("/maxLevelArmor/Right2");
        right3 = setUp("/maxLevelArmor/Right3");
        right4 = setUp("/maxLevelArmor/Right4");
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

            //Check NPC collision
            int npcIndex = gp.colCheck.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //Check Monster Collision
            int monsterIndex = gp.colCheck.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //Check Event
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;

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

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickUpObject(int index){
        if (index != 999){

        }
    }

    public void interactNPC(int index){
        if(index!=999){
            if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
        }
    }

    public void contactMonster(int index){
        if (index != 999){
            if(!invincible){
                life-=1;
                invincible = true;
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

        //for immunity effect
        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
        }

        g2.drawImage(image, screenX, screenY,null);

        //reset
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));

        //to see collision box or hit box
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

        //debug
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:"+invincibleCounter,10, 400);
    }

}