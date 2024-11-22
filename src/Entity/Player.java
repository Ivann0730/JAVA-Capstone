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

        //change value for longer attack range
        attackArea.width = 64;
        attackArea.height = 48;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
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
        idle = setUp("/player/heil",gp.tileSize,gp.tileSize);
        idleleft = setUp("/maxLevelArmor/Left1",gp.tileSize,gp.tileSize);
        idleright = setUp("/maxLevelArmor/Right1",gp.tileSize,gp.tileSize);
        idledown = setUp("/maxLevelArmor/Back1",gp.tileSize,gp.tileSize);
        idleup = setUp("/maxLevelArmor/Front1",gp.tileSize,gp.tileSize);
        up1 = setUp("/maxLevelArmor/Front2",gp.tileSize,gp.tileSize);
        up2 = setUp("/maxLevelArmor/Front3",gp.tileSize,gp.tileSize);
        up3 = setUp("/maxLevelArmor/Front2",gp.tileSize,gp.tileSize);
        up4 = setUp("/maxLevelArmor/Front4",gp.tileSize,gp.tileSize);
        down1 = setUp("/maxLevelArmor/Back1",gp.tileSize,gp.tileSize);
        down2 = setUp("/maxLevelArmor/Back2",gp.tileSize,gp.tileSize);
        down3 = setUp("/maxLevelArmor/Back3",gp.tileSize,gp.tileSize);
        down4 = setUp("/maxLevelArmor/Back4",gp.tileSize,gp.tileSize);
        left1 = setUp("/maxLevelArmor/Left1",gp.tileSize,gp.tileSize);
        left2 = setUp("/maxLevelArmor/Left2",gp.tileSize,gp.tileSize);
        left3 = setUp("/maxLevelArmor/Left3",gp.tileSize,gp.tileSize);
        left4 = setUp("/maxLevelArmor/Left4",gp.tileSize,gp.tileSize);
        right1 = setUp("/maxLevelArmor/Right1",gp.tileSize,gp.tileSize);
        right2 = setUp("/maxLevelArmor/Right2",gp.tileSize,gp.tileSize);
        right3 = setUp("/maxLevelArmor/Right3",gp.tileSize,gp.tileSize);
        right4 = setUp("/maxLevelArmor/Right4",gp.tileSize,gp.tileSize);
    }
    public void getPlayerAttackImage(){
        attackUp1 = setUp("/player/atkb1",gp.tileSize*2,gp.tileSize*2);
        attackUp2 = setUp("/player/atkb2",gp.tileSize*2,gp.tileSize*2);
        attackDown1 = setUp("/player/atk1",gp.tileSize*2,gp.tileSize*2);
        attackDown2 = setUp("/player/atk2",gp.tileSize*2,gp.tileSize*2);
        attackLeft1 = setUp("/player/atkl1",gp.tileSize*2,gp.tileSize*2);
        attackLeft2 = setUp("/player/atkl2",gp.tileSize*2,gp.tileSize*2);
        attackRight1 = setUp("/player/atkr1",gp.tileSize*2,gp.tileSize*2);
        attackRight2 = setUp("/player/atkr2",gp.tileSize*2,gp.tileSize*2);
    }
    public void update() {

        if(attacking){
            attacking();
        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
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

            //if collision is false, player can move
            if(!collisionOn && !keyH.enterPressed){
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
            gp.keyH.enterPressed = false;

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
    public void attacking(){

        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            //to save current solid areas
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //adjust player's worldX/Y fot attack area
            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }
            //attack area becomes solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            //check monster collision
            int monsterIndex = gp.colCheck.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);
            //restore position
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int index){
        if (index != 999){

        }
    }
    public void interactNPC(int index){

        if (gp.keyH.enterPressed) {
            if(index!=999){
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
            else {
                gp.playSE(7);
                attacking = true;
            }
        }
    }
    public void contactMonster(int index){
        if (index != 999){
            if(!invincible){
                gp.playSE(6);
                life-=1;
                invincible = true;
            }
        }
    }
    public void damageMonster(int index){
        if(index != 999) {
            if(!gp.monster[index].invincible){
                gp.playSE(5);
                gp.monster[index].life -= 1;
                gp.monster[index].invincible = true;
                gp.monster[index].damageReaction();
                if(gp.monster[index].life <= 0){
                    gp.monster[index].dying = true;

                }
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        // Array Sprites
        BufferedImage[] upSprites = {up1, up2, up3, up4};
        BufferedImage[] downSprites = {down1, down2, down3, down4};
        BufferedImage[] leftSprites = {left1, left2, left3, left4};
        BufferedImage[] rightSprites = {right1, right2, right3, right4};

        switch (direction) {
            case "up":
                if(!attacking){
                    image = upSprites[spriteNum - 1]; // Cycle through the up sprites
                    if (!keyH.upPressed) {
                        image = idleup;
                        spriteCounter = 8;
                    }
                }
                if(attacking){
                    tempScreenX = screenX - 64;
                    tempScreenY = screenY - 64;
                    if(spriteNum==1) image = attackUp1;
                    if(spriteNum==2) image = attackUp2;
                }
                break;

            case "down":
                if(!attacking){
                    image = downSprites[spriteNum - 1]; // Cycle through the down sprites
                    if (!keyH.downPressed) {
                        image = idledown;
                        spriteCounter = 8;
                    }
                }
                if(attacking){
                    tempScreenX = screenX - 64;
                    tempScreenY = screenY - 64;
                    if(spriteNum==1) image = attackDown1;
                    if(spriteNum==2) image = attackDown2;
                }
                break;

            case "left":
                if(!attacking){
                    image = leftSprites[spriteNum - 1]; // Cycle through the left sprites
                    if (!keyH.leftPressed) {
                        image = idleleft;
                        spriteCounter = 8;
                    }
                }
                if(attacking){
                    tempScreenX = screenX - 64;
                    tempScreenY = screenY - 64;
                    if(spriteNum==1) image = attackLeft1;
                    if(spriteNum==2) image = attackLeft2;
                }
                break;

            case "right":
                if(!attacking){
                    image = rightSprites[spriteNum - 1]; // Cycle through the right sprites
                    if (!keyH.rightPressed) {
                        image = idleright;
                        spriteCounter = 8;
                    }
                }
                if(attacking){
                    tempScreenX = screenX - 64;
                    tempScreenY = screenY - 64;
                    if(spriteNum==1) image = attackRight1;
                    if(spriteNum==2) image = attackRight2;
                }
                break;
        }

        //for immunity effect
        if(invincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);
        //reset
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));

        //to see collision box or hit box
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

        //debug
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:"+invincibleCounter,10, 400);

        // DEBUG FOR ATTACK
        // AttackArea
        tempScreenX = screenX + solidArea.x;
        tempScreenY = screenY + solidArea.y;
        switch(direction) {
            case "up": tempScreenY = screenY - attackArea.height; break;
            case "down": tempScreenY = screenY + gp.tileSize; break;
            case "left": tempScreenX = screenX - attackArea.width; break;
            case "right": tempScreenX = screenX + gp.tileSize; break;
        }
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(tempScreenX, tempScreenY, attackArea.width, attackArea.height);
    }
}